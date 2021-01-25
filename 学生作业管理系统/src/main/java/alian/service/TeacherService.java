package alian.service;
import alian.domain.*;

import java.util.List;

public interface TeacherService {
    public List<Integer> coursebycourse_id(int id);

    public int uploadHomework(Homework homework);

    public boolean receiveInfo(int id);

    public boolean insertMessage(Meaasge message);


    public List<Integer> findStudentsbyClassId(int id);

    public List<Integer> findStudentsbyHomeworkid(int id);

    public boolean readinfo(int id);

    public boolean readmanyinfo(String id);

    public boolean readMeeages(int id);

    public List<Homework> findallHomeworksbyteacherid(int id);

    List<Homeworkinfo> checkhomeworkwork(int teacherid);

    public boolean changePublishHomework(Homework homework);

    public int teacherrecevieInfo(int id);

    public boolean deleteHomeworkInfo(int id);

    public boolean uploadHomeworkScore(Homeworkinfo homeworkinfo);
//
//    public List<Homework> findhomeworks(int id);
//
    public List<Homeworkinfo> queryHomeworkInfoByTeacherId(int id);
//

    List<String> queryAllpingjia(int id);

    int queryallHomeworksMember(int id);

    int queryClassNms(int id);

    public boolean receivemanyinfo(UserInfo user);

    ScoreStatistic queryScoreLine(int id);
//    public String dowmloadHomework(int id);
//
//    public boolean uploadScore(Homeworkinfo Homeworkinfo);
//
//    public List<Student> findStudentsByClass(int id);
//
//    public Student findStudentbyid(int id);
}
