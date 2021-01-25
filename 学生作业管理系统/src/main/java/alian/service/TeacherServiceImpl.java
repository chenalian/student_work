package alian.service;
import alian.domain.*;
import alian.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Autowired
    private TeacherMapper teacherMapper;


    @Autowired
    private MeaasgeMapper meaasgeMapper;

    @Autowired
    private HomeworkinfoMapper homeworkinfoMapper;

    @Override
    public List<Integer> coursebycourse_id(int id) {
        return  classMapper.coursebycourse_teacherid(id);
    }

    @Override
    public int uploadHomework(Homework homework) {
        int result=homeworkMapper.insertHomework_returnKey(homework);
        return homework.getId();
    }
    @Override
    public boolean receiveInfo(int id) {
        return teacherMapper.receiveinfo(id);
    }

    @Override
    public boolean insertMessage(Meaasge message) {
        return meaasgeMapper.insert(message)==1?true:false;
    }

    @Override
    public List<Integer> findStudentsbyClassId(int id) {
        return classMapper.findStudentsbyClassId(id);
    }

    @Override
    public List<Integer> findStudentsbyHomeworkid(int id) {
        return classMapper.findStudentsbyHomeworkid(id);
    }

    @Override
    public boolean readinfo(int id) {
        return teacherMapper.readinfo(id);
    }

    @Override
    public boolean readmanyinfo(String id) {
        return teacherMapper.readmanyinfo(id)>0?true:false;
    }

    @Override
    public boolean readMeeages(int id) {
        return meaasgeMapper.deleteByPrimaryKey(id)==1?true:false;
    }

    @Override
    public List<Homework> findallHomeworksbyteacherid(int id) {
        return teacherMapper.findallHomeworksbyteacherid(id);
    }

    @Override
    public List<Homeworkinfo> checkhomeworkwork(int teacherid) {
        return teacherMapper.checkhomeworkwork(teacherid);
    }

    @Override
    public boolean changePublishHomework(Homework homework) {
        return homeworkMapper.changePublishHomework(homework)==1?true:false;
    }

    @Override
    public int teacherrecevieInfo(int id) {
        return teacherMapper.teacherrecevieInfo(id);
    }

    @Override
    public boolean deleteHomeworkInfo(int id) {
        return homeworkinfoMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public boolean uploadHomeworkScore(Homeworkinfo homeworkinfo) {
        return homeworkinfoMapper.uploadHomeworkScore(homeworkinfo)>0?true:false;
    }

    @Override
    public List<Homeworkinfo> queryHomeworkInfoByTeacherId(int id) {
        return teacherMapper.queryHomeworkInfoByTeacherId(id);
    }

    @Override
    public List<String> queryAllpingjia(int id) {
        return teacherMapper.queryAllpingjia(id);
    }

    @Override
    public int queryallHomeworksMember(int id) {
        return teacherMapper.queryallHomeworksMember(id);
    }

    @Override
    public int queryClassNms(int id) {
        return teacherMapper.queryClassNms(id);
    }

    @Override
    public boolean receivemanyinfo(UserInfo user) {
        return teacherMapper.receivemanyinfo(user)>0?true:false;
    }

    @Override
    public ScoreStatistic queryScoreLine(int id) {
        return teacherMapper.queryScoreLine(id);
    }

}
