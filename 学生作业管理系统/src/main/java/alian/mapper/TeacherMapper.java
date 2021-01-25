package alian.mapper;

import alian.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKeyWithBLOBs(Teacher record);

    int updateByPrimaryKey(Teacher record);

    String findpasswordbyid(String id);

    boolean receiveinfo(int id);

    Teacher findinfoByname(String id);

    List<Homework> findallHomeworksbyteacherid(int teacherid);

    List<Homeworkinfo> checkhomeworkwork(int teacherid);

    public int teacherrecevieInfo(int id);

    List<Homeworkinfo> queryHomeworkInfoByTeacherId(int id);

    List<String> queryAllpingjia(int id);

    int queryallHomeworksMember(int id);

    int queryClassNms(int id);

    public ScoreStatistic  queryScoreLine(int id);

    public boolean readinfo(int id);

    public int readmanyinfo(String ids);

    int receivemanyinfo(UserInfo user);
}