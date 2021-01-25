package alian.mapper;

import alian.domain.Semester;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SemesterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Semester record);

    int insertSelective(Semester record);

    Semester selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Semester record);

    int updateByPrimaryKey(Semester record);
}