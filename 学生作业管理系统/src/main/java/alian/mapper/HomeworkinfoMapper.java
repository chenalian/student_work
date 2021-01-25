package alian.mapper;

import alian.domain.Homeworkinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeworkinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homeworkinfo record);

    int insertSelective(Homeworkinfo record);

    Homeworkinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homeworkinfo record);

    int updateByPrimaryKey(Homeworkinfo record);

    int uploadHomeworkScore(Homeworkinfo homeworkinfo);
}