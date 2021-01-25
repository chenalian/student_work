package alian.mapper;

import alian.domain.Classstudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassstudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classstudent record);

    int insertSelective(Classstudent record);

    Classstudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classstudent record);

    int updateByPrimaryKey(Classstudent record);
}