package alian.mapper;

import alian.domain.Homework;
import alian.domain.Homeworkinfo;
import alian.domain.Student;
import alian.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    String findpasswordbyid(String id);

    boolean receiveinfo(int id);

    int receivemanyinfo(UserInfo user);

    Student findinfoByname(String name);

    List<Homework> findallStudentHomeworks(int studentid);

    public boolean readinfo(int id);

    public int readmanyinfo(String ids);

    public int queryTeacherByHomeworkid(int id);

    List<Homeworkinfo> queryHomeworkInfoByStudentid(int id);
}