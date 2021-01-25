package alian.mapper;

import alian.domain.Homework;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeworkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKeyWithBLOBs(Homework record);

    int updateByPrimaryKey(Homework record);

    int insertHomework_returnKey(Homework homework);

    public int changePublishHomework(Homework homework);
}