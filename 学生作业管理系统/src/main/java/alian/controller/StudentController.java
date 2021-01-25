package alian.controller;

import alian.domain.*;
import alian.service.StudentService;
import alian.service.TeacherService;
import alian.utils.SaveFileUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping()
    public String Student()
    {
        return "index";
    }

    @RequestMapping("/studentReceiveMessages")
    public String studentReceiveMessages()
    {
        return "studentReceiveMessages";
    }

    @RequestMapping("/StudentReceiveHomeworks")
    public String StudentReceiveHomeworks()
    {
        return "StudentReceiveHomeworks";
    }

    @RequestMapping("/studentqueryScore")
    public String studentqueryScore()
    {
        return "studentqueryScore";
    }

    @RequestMapping("/slefinfo")
    public String slefinfo()
    {
        return "slefinfo";
    }

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    /*
    *
    * 查询用户收到的信息
    *
    * */
    @RequestMapping("/findallStudentMessages")
    public @ResponseBody Map findallStudentMessages(HttpServletRequest request)
    {
        Student user= (Student) request.getSession().getAttribute("user");
        Meaasge meaasge=new Meaasge();
        meaasge.setReceid(user.getId());
        meaasge.setRecestate(2);
        List<Meaasge> messages= studentService.findallStudentMessages(meaasge);
        Map map=new LinkedHashMap();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", 20);
        map.put("data", messages);
        return map;
    }

    /*
    *
    * 查询学生所在班级发布作业
    *
    * */
    @RequestMapping("/findallStudentHomeworks")
    public @ResponseBody Map findallStudentHomeworks(HttpServletRequest request)
    {
        Student user= (Student) request.getSession().getAttribute("user");
        List<Homework> messages= studentService.findallStudentHomeworks(user.getId());
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
        Student user= (Student) request.getSession().getAttribute("user");
        Map map=new HashMap();
        if(studentService.readMeeages(messageid))
        {
            studentService.readinfo(user.getId());
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
        Student user= (Student) request.getSession().getAttribute("user");
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
        if(studentService.receivemanyinfo(userInfo))
        {
            if(studentService.readmanyinfo(ids))
            {
                map.put("msg", "读取成功");
            }
        }
        else{
            map.put("msg", "读取成功");
        }
        return map;
    }
    /*
    *
    * 学生下载作业
    *
    * */
    @RequestMapping("/downHomework")
    public  void downHomework(String filename, HttpServletResponse response)
    {
        File a=new File("D:/upload/"+filename);
        FileInputStream read=null;
        OutputStream outputStream=null;
        try {
            read=new FileInputStream(a);
            outputStream=response.getOutputStream();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename="+filename);
            IOUtils.copy(read, outputStream);
            outputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    *
    * 提交作业
    *
    * */

    @RequestMapping("/uploadHomework")
    public @ResponseBody Map changePublishHomework(int id,int type,String reinfo,String text, MultipartFile file, HttpServletRequest request)
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
        Homeworkinfo homeworkinfo=new Homeworkinfo();
        homeworkinfo.setHomeworkId(id);
        homeworkinfo.setInfo(reinfo);
        homeworkinfo.setText(text);
        Student user= (Student) request.getSession().getAttribute("user");
        homeworkinfo.setStudentId(user.getId());
        homeworkinfo.setType(type);
        /*
        *
        * 查询老师id
        *
        * */
        int teacherid=studentService.queryTeacherByHomeworkid(id);

        String lastfilename=file.getOriginalFilename();
        lastfilename =lastfilename.substring(lastfilename.lastIndexOf('.'), lastfilename.length());
//        /*文件名*/
        String filename=id+new Date().getTime()+lastfilename ;
        filename=filename.replace(":", "");
        homeworkinfo.setFilename(filename);
//        /*消息类型
//         *
//         * 消息类型:几班:学生:提交:xx作业
//         * */
        String info=null;
//        /*解析text*/
        info=0+":"+user.getName()+"学生:"+"提交:"+id+"作业";
        map.put("msg","作业提交成功");
        String path="D:\\upload";
        if(SaveFileUtil.savefile(path,filename,file))
        {
            map.put("msg", "作业提交成功");

            /*
            *
            * 作业存入数据库
            *
            * */
            if(studentService.uploadHomework(homeworkinfo))
            {
                try {
                    //老师id先写死
                    sendMessagesToTeacher(teacherid,user.getId(),2,1,1,info);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else  map.put("msg", "作业提交失败，文件上传失败");
        return map;
    }
    /*
     *
     * 向学生列表发送信息
     * 同时跟新消息队列
     *
     * */
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    public void sendMessagesToTeacher(int teacherid,int student,int sendstate,int receivestate,int state,String info) throws Exception {

             simpMessagingTemplate.convertAndSend("/topic/teacherinfo/"+teacherid+"/",info);// 向老师发送消息
             updateMessages(teacherid,student,sendstate,receivestate,state,info);//跟新消息队列
             teacherService.teacherrecevieInfo(teacherid);// 老师更新未读消息
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
        message.setSendid(studentsid);
        message.setReceid(teacherid);
        message.setSendstate(sendstate);
        message.setRecestate(receivestate);
        message.setState(state);
        message.setInfo(info);
        studentService.insertMessage(message);
    }

    /*
    *
    *
    * 学生查看成绩
    *
    * */
    @RequestMapping("/studentqueryScores")
    public @ResponseBody Map checkhomeworkwork(HttpServletRequest request)
    {
        Student user= (Student) request.getSession().getAttribute("user");
        List<Homeworkinfo> messages= studentService.queryHomeworkInfoByStudentid(user.getId());
        Map map=new LinkedHashMap();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", messages.size());
        map.put("data", messages);
        return map;
    }
}
