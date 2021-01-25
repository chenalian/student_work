package alian.mapper;

import alian.domain.Meaasge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeaasgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Meaasge record);

    int insertSelective(Meaasge record);

    Meaasge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meaasge record);

    int updateByPrimaryKeyWithBLOBs(Meaasge record);

    int updateByPrimaryKey(Meaasge record);


    List<Meaasge> findallStudentMessages(Meaasge meaasge);
}