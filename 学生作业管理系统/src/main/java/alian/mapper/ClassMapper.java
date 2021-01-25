package alian.mapper;

import alian.domain.Class;
import alian.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);


    List<Integer> coursebycourse_teacherid(int id);


    List<Integer> findStudentsbyClassId(int id);

    List<Integer> findStudentsbyHomeworkid(int id);
}