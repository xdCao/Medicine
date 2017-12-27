package xd.medicine.dao.autoMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.entity.bo.ProDutyExample;

public interface ProDutyMapper {
    int countByExample(ProDutyExample example);

    int deleteByExample(ProDutyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProDuty record);

    int insertSelective(ProDuty record);

    List<ProDuty> selectByExample(ProDutyExample example);

    ProDuty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProDuty record, @Param("example") ProDutyExample example);

    int updateByExample(@Param("record") ProDuty record, @Param("example") ProDutyExample example);

    int updateByPrimaryKeySelective(ProDuty record);

    int updateByPrimaryKey(ProDuty record);
}