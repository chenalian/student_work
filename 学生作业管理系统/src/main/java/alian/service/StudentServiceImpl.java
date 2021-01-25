package alian.service;
import alian.domain.*;
import alian.mapper.HomeworkinfoMapper;
import alian.mapper.MeaasgeMapper;
import alian.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

//    @Autowired
//    private HomeworkMapper homeworkMapper;
//
//    @Autowired
//    private HomeworkinfoMapper homeworkinfoMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MeaasgeMapper meaasgeMapper;

    @Autowired
    private HomeworkinfoMapper homeworkinfoMapper;

    @Override
    public boolean receiveInfo(int id) {
        return studentMapper.receiveinfo(id);
    }

    @Override
    public boolean receivemanyinfo(UserInfo user) {
        return studentMapper.receivemanyinfo(user)>0?true:false;
    }
    @Override
    public List<Meaasge> findallStudentMessages(Meaasge meaasge) {
        return meaasgeMapper.findallStudentMessages(meaasge);
    }

    @Override
    public List<Homework> findallStudentHomeworks(int studentid) {
        return studentMapper.findallStudentHomeworks(studentid);
    }

    @Override
    public boolean readinfo(int id) {
        return studentMapper.readinfo(id);
    }

    @Override
    public boolean readmanyinfo(String id) {
        return studentMapper.readmanyinfo(id)>0?true:false;
    }

    @Override
    public boolean readMeeages(int id) {
        return meaasgeMapper.deleteByPrimaryKey(id)==1?true:false;
    }

    @Override
    public int queryTeacherByHomeworkid(int id) {
        return studentMapper.queryTeacherByHomeworkid(id);
    }

    @Override
    public boolean insertMessage(Meaasge message) {
        return meaasgeMapper.insert(message)==1?true:false;
    }

    @Override
    public boolean uploadHomework(Homeworkinfo homeworkinfo) {
        return homeworkinfoMapper.insert(homeworkinfo)>0?true:false;
    }

    @Override
    public List<Homeworkinfo> queryHomeworkInfoByStudentid(int id) {
        return studentMapper.queryHomeworkInfoByStudentid(id);
    }


//    @Autowired
//    private StudentMapper studentMapper;
//    @Override
//    public String dowmloadHomework(int id) {
//        Homework homework=homeworkMapper.selectByPrimaryKey(id);
//        return homework.getInfo();
//    }
//    @Override
//    public boolean uploadHomework(Homeworkinfo homeworkinfo) {
//         int result=homeworkinfoMapper.insert(homeworkinfo);
//        return result==1?true:false;
//    }
//
//    @Override
//    public List<Homeworkinfo> findallHomeworkbyStudent(int id) {
//        return homeworkinfoMapper.findallHomeworkbyStudent(id);
//    }
//
//    @Override
//    public List<Teacher> findTeachersByHomeworksid(int id) {
//        return studentMapper.findTeachersByHomeworksid(id);
//    }
}
