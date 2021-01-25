package alian.service;

import alian.domain.*;

import java.util.List;

public interface StudentService {
    public boolean receiveInfo(int id);


    public boolean receivemanyinfo(UserInfo user);

    public List<Meaasge> findallStudentMessages(Meaasge meaasge);


    public List<Homework> findallStudentHomeworks(int studentid);

    public boolean readinfo(int id);

    public boolean readmanyinfo(String id);

    public boolean readMeeages(int id);

    public int queryTeacherByHomeworkid(int id);

    public boolean insertMessage(Meaasge message);

//    public String dowmloadHomework(int id);
//
    public boolean uploadHomework(Homeworkinfo homeworkinfo);

    public List<Homeworkinfo> queryHomeworkInfoByStudentid(int id);
//
//    public List<Homeworkinfo> findallHomeworkbyStudent(int id);
//
//    public List<Teacher> findTeachersByHomeworksid(int id);
}
