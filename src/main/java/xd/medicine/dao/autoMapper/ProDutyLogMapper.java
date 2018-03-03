package xd.medicine.dao.autoMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xd.medicine.entity.bo.ProDutyLog;
import xd.medicine.entity.bo.ProDutyLogExample;

public interface ProDutyLogMapper {
    int countByExample(ProDutyLogExample example);

    int deleteByExample(ProDutyLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProDutyLog record);

    int insertSelective(ProDutyLog record);

    List<ProDutyLog> selectByExample(ProDutyLogExample example);

    ProDutyLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProDutyLog record, @Param("example") ProDutyLogExample example);

    int updateByExample(@Param("record") ProDutyLog record, @Param("example") ProDutyLogExample example);

    int updateByPrimaryKeySelective(ProDutyLog record);

    int updateByPrimaryKey(ProDutyLog record);
}