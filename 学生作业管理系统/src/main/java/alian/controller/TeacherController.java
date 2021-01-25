package alian.controller;

import alian.config.WebSocketServer;
import alian.domain.*;
import alian.service.StudentService;
import alian.service.TeacherService;
import alian.utils.SaveFileUtil;
import com.hankcs.hanlp.HanLP;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @GetMapping()
    public  String teacher()
    {
        return "index";
    }


    @RequestMapping("/teacherpublishomework")
    public String teacherpublishomework()
    {
        return "teacherpublishomework";
    }

    @RequestMapping("/teacheredithomeworkwork")
    public String teacheredithomeworkwork()
    {
        return "teacheredithomeworkwork";
    }


    @RequestMapping("/homeworkInfoStatistics")
    public String homeworkInfoStatistics()
    {
        return "homeworkInfoStatistics";
    }

    @RequestMapping("/teacherReceiveMessages")
    public String teacherReceiveMessages()
    {
        return "teacherReceiveMessages";
    }


    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/TeacherEditHomeworkTable")
    public String TeacherEditHomeworkTable(String id, Model model) {
        model.addAttribute("id", id);
        return "TeacherEditHomeworkTable";
    }

    @RequestMapping("/teachercheckhomeworkwork")
    public String teachercheckhomeworkwork() {
        return "teachercheckhomeworkwork";
    }

    @Autowired
    private StudentService studentService;
    /*
     *
     * 查询教师所带的班级接口
     *
     * */
    @RequestMapping(value ="class")
    public @ResponseBody
    Map classbyteacher_id(HttpServletRequest request)
    {
        Teacher user= (Teacher) request.getSession().getAttribute("user");

        List<Integer> lists = teacherService.coursebycourse_id(user.getId());
        Map map=new LinkedHashMap();
        map.put("lists", lists);
        return map;
    }
    /**
     *
     * 作业的发布
     *
     */
    @RequestMapping(value = "publishHomework")
    public @ResponseBody Map publishHomework(String classid, int type ,String time, String text,MultipartFile file, HttpServletRequest request)
    {
        Map map=new LinkedHashMap();
        map.put("code", 0);
        /*
         *
         * 将文件按照班级号+发布时间名命名
         * 存入upload文件夹
         *
         * 班级号 时间戳 文件名
         *
         * */
        String stime=time.substring(0,19);
        String etime=time.substring(22, 41);
        Homework homework=new Homework();
        homework.setClassId(Integer.parseInt(classid));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Date date1=null,date2=null;
        Teacher user= (Teacher) request.getSession().getAttribute("user");

        try {
            date1 = format.parse(stime);
            date2=format.parse(etime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Integer> students=teacherService.findStudentsbyClassId(Integer.parseInt( classid));
        homework.setStime(date1);
        homework.setEtime(date2);
        homework.setState(1);
        homework.setType(type);
        homework.setText(text);
        /*消息类型
        *
        * 消息类型:几班:xxlao老师:发布:xx作业
        * */
        String info=null;
        if(type==0)
        {
            /*解析text*/
            homework.setText(text);
            int homeworkid=teacherService.uploadHomework(homework);
            info=1+":"+classid+"班:"+user.getName()+"老师:"+"发布:"+homeworkid+"作业";
                map.put("msg","作业发布成功");
                /*
                *
                * 作业成功发布的同时向学生推送
                *
                * */
                /*
                *
                *
                * 发送者 接受者 发送者类型 接受者类型 消息类型
                * （state角色0表示系统，1表示教师，2表示学生）（普通消息（0），作业消息（1））
                *
                *
                * */
                try {
                    sendMessagesToStudents(user.getId(),students,1,2,1,info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        else
        {
            if(type==1) text=null;
            homework.setText(text);
            // 文件后缀名
            String lastfilename=file.getOriginalFilename();
            lastfilename =lastfilename.substring(lastfilename.lastIndexOf('.'), lastfilename.length());
            /*文件名*/
            String filename=classid+new Date().getTime()+lastfilename ;
            filename=filename.replace(":", "");
            homework.setFilename(filename);

            int homeworkid=teacherService.uploadHomework(homework);
            info=classid+"班:"+user.getName()+"老师:"+"发布:"+homeworkid+"作业";
                String path="D:\\upload";
                if(SaveFileUtil.savefile(path,filename,file))
                {
                    map.put("msg", "作业发布成功");
                    try {
                        //老师id先写死
                        sendMessagesToStudents(user.getId(),students,1,2,1,info);

                        } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else  map.put("msg", "作业发布失败，文件上传失败");

            }
        return map;
    }
    /*跟新消息队列*/
    /*
     *
     *
     * 发送者 接受者 发送者类型 接受者类型 消息类型
     * （state角色0表示系统，1表示教师，2表示学生）（普通消息（0），作业消息（1））
     *
     *
     * */
    public void  updateMessages(int teacherid,Integer studentsid,int sendstate,int receivestate,int state,String info)
    {
    Meaasge message=new Meaasge();
    message.setSendid(teacherid);
    message.setReceid(studentsid);
    message.setSendstate(sendstate);
    message.setRecestate(receivestate);
    message.setState(state);
    message.setInfo(info);
    teacherService.insertMessage(message);
    }
    /*
    *
    * 向学生列表发送信息
    * 同时跟新消息队列
    *
    * */
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    public void sendMessagesToStudents(int teacherid,List<Integer> students,int sendstate,int receivestate,int state,String info) throws Exception {
        for(Integer student: students)
        {
            simpMessagingTemplate.convertAndSend("/topic/studentinfo/"+student+"/",info);// 向学生发送消息
            updateMessages(teacherid,student,sendstate,receivestate,state,info);//跟新消息队列
            studentService.receiveInfo(student);// 学生更新未读消息
        }
    }

    /*教师查询发布的作业*/

    @RequestMapping("/findallHomeworksbyteacherid")
    public @ResponseBody Map findallHomeworksbyteacherid(HttpServletRequest request)
    {
        Teacher user= (Teacher) request.getSession().getAttribute("user");
        List<Homework> messages= teacherService.findallHomeworksbyteacherid(user.getId());
        Map map=new LinkedHashMap();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", messages.size());
        map.put("data", messages);
        return map;
    }
    /*
    *
    * 教师批改作业
    *
    * */

    @RequestMapping("/checkhomeworkwork")
    public @ResponseBody Map checkhomeworkwork(HttpServletRequest request)
    {
        Teacher user= (Teacher) request.getSession().getAttribute("user");
        List<Homeworkinfo> messages= teacherService.checkhomeworkwork(user.getId());
        Map map=new LinkedHashMap();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", messages.size());
        map.put("data", messages);
        return map;
    }

    /*
    *
    * 修改作业
    *
    *
    * */
    @RequestMapping(value = "changePublishHomework")
    public @ResponseBody Map changePublishHomework(String id ,String time, String text,MultipartFile file, HttpServletRequest request)
    {
        Map map=new LinkedHashMap();
        map.put("code", 0);
        /*
         *
         * 将文件按照班级号+发布时间名命名
         * 存入upload文件夹
         *
         * 班级号 时间戳 文件名
         *
         * */
        Homework homework=new Homework();
        homework.setId(Integer.parseInt(id));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        Date date1=null;
        Teacher user= (Teacher) request.getSession().getAttribute("user");
        try {
            date1 = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        homework.setEtime(date1);
        List<Integer> students=teacherService.findStudentsbyHomeworkid(Integer.parseInt(id));
        String lastfilename=file.getOriginalFilename();
        lastfilename =lastfilename.substring(lastfilename.lastIndexOf('.'), lastfilename.length());
        /*文件名*/
        String filename=id+new Date().getTime()+lastfilename ;
        filename=filename.replace(":", "");
        homework.setFilename(filename);
        /*消息类型
         *
         * 消息类型:几班:xxlao老师:发布:xx作业
         * */
        String info=null;
        /*解析text*/
        homework.setText(text);
        boolean homeworkid=teacherService.changePublishHomework(homework);
        info=0+":"+id+"班:"+user.getName()+"老师:"+"修改:"+id+"作业";
        map.put("msg","作业发布成功");
        String path="D:\\upload";
        if(SaveFileUtil.savefile(path,filename,file))
        {
            map.put("msg", "作业修改成功");
            try {
                //老师id先写死
                sendMessagesToStudents(user.getId(),students,1,2,1,info);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else  map.put("msg", "作业修改失败，文件上传失败");
        return map;
    }

    /*
    *
    * 老师打回作业
    *
    * 提交作业减一
    * 消息减一
    * 老师未读消息减一
    * 学生添加消息
    *
    *
    * */
    @RequestMapping("/trunbackHomework")
    public @ResponseBody Map trunbackHomework(int id,int studentid,HttpServletRequest request)
    {
        if(teacherService.deleteHomeworkInfo(id))
        {
            Teacher user= (Teacher) request.getSession().getAttribute("user");
            List<Integer> students=new ArrayList<>();
            students.add(studentid);
            String info="";
            info=1+":"+user.getName()+"老师:"+"打回:"+id+"作业";
            try {
                sendMessagesToStudents(user.getId(), students, 1, 2, 1,info );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map map=new LinkedHashMap();
        map.put("msg", "打回成功");
        return map;
    }
    /*
    *
    * 老师为学生打分
    *
    *
    * */
    @RequestMapping("/uploadHomeworkScore")
    public @ResponseBody Map trunbackHomework(int id,String piyu,int filescore,int textscore,HttpServletRequest request)
    {
       Homeworkinfo homeworkinfo=new Homeworkinfo();
       homeworkinfo.setId(id);
       homeworkinfo.setPiyu(piyu);
       homeworkinfo.setFilescore(filescore);
       homeworkinfo.setTextscore(textscore);
       int score=filescore+textscore;
       homeworkinfo.setScore(score+"");
        Map map=new LinkedHashMap();
       if(teacherService.uploadHomeworkScore(homeworkinfo))
       {
           map.put("msg", "打分成功");
       }
       else{
           map.put("msg", "打分失败");
       }
        return map;
    }
    /*
    *
    *
    * 下载作业
    *
    * */

    @RequestMapping("/downloadHomeworkInfo")
    public void downloadHomeworkInfo(String filename, HttpServletResponse response) {
        File a = new File("D:/upload/" + filename);
        FileInputStream read = null;
        OutputStream outputStream = null;
        try {
            read = new FileInputStream(a);
            outputStream = response.getOutputStream();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename="+filename);
            IOUtils.copy(read, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    *
    *
    * 提交作业关键字提取
    *
    * */
    @RequestMapping("/keywords")
    public String keywords(int id,Model model)
    {
        /*
        * 从提交的作业评价情况
        * 提炼出5个关键字
        *
        * */

        List<String> infos=teacherService.queryAllpingjia(id);
        String result="";
        for(String info:infos)
        {
            result+=","+info;
        }
        List<String> results=null;
//        results=HanLP.extractKeyword("程序员(英文Programmer)是从事程序开发、" +
//                "维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，" +
//                        "特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类", 5);
        results=HanLP.extractKeyword(result, 5);
        model.addAttribute("results", results);
        return "keywords";
    }
    /*
    *
    * 分数单成绩统计
    *
    *
    * */
    @RequestMapping("/scorestatictis")
    public String scorestatictis(int id,Model model,HttpServletRequest request)
    {
        /*
        *
        * 根据作业id
        * 分段查出分数段的情况
        *
        * */
//        Teacher user= (Teacher) request.getSession().getAttribute("user");
        // 1.提交作业的分数
        int nms=teacherService.queryallHomeworksMember(id);
        //2.班级人数
        int classnms=teacherService.queryClassNms(id);
        // 查出各分数段的人数
        ScoreStatistic scoreStatistic=teacherService.queryScoreLine(id);
        scoreStatistic.setWeijiao(classnms-nms);
        model.addAttribute("result", scoreStatistic);
        return "scorestatictis";
    }
    /*
     *
     * 查询用户收到的信息
     *
     * */
    @RequestMapping("/findallteacherMessages")
    public @ResponseBody Map findallStudentMessages(HttpServletRequest request)
    {
        Teacher user= (Teacher) request.getSession().getAttribute("user");
        Meaasge meaasge=new Meaasge();
        meaasge.setReceid(user.getId());
        meaasge.setRecestate(1);
        List<Meaasge> messages= studentService.findallStudentMessages(meaasge);
        Map map=new LinkedHashMap();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", 20);
        map.put("data", messages);
        return map;
    }
    @RequestMapping("/readinfo")
    public @ResponseBody Map readinfo(int messageid,HttpServletRequest request)
    {
        Teacher user= (Teacher) request.getSession().getAttribute("user");
        Map map=new HashMap();
        if(teacherService.readMeeages(messageid))
        {
            teacherService.readinfo(user.getId());
            map.put("msg", "读取成功");
        }
        else {
            map.put("msg", "请不要重复点击读取");
        }
        return map;
    }

    @RequestMapping("/readmanyinfo")
    public @ResponseBody Map readmanyinfo(@RequestParam(value = "messageid[]",required = true) int messageid[], HttpServletRequest request)
    {
        Teacher user= (Teacher) request.getSession().getAttribute("user");
        Map map=new HashMap();
        String ids="";
        for(int i=0;i<messageid.length;i++)
        {
            if(i==0){
                ids=""+messageid[i];
            }
            else
                ids=ids+","+messageid[i];
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setNums(messageid.length);
        if(teacherService.receivemanyinfo(userInfo))
        {
            if(teacherService.readmanyinfo(ids))
            {
                map.put("msg", "读取成功");
            }
        }
        else{
            map.put("msg", "读取成功");
        }
        return map;
    }

}
