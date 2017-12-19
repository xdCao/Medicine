package xd.medicine.dao.autoMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xd.medicine.entity.bo.Others;
import xd.medicine.entity.bo.OthersExample;

public interface OthersMapper {
    int countByExample(OthersExample example);

    int deleteByExample(OthersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Others record);

    int insertSelective(Others record);

    List<Others> selectByExample(OthersExample example);

    Others selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Others record, @Param("example") OthersExample example);

    int updateByExample(@Param("record") Others record, @Param("example") OthersExample example);

    int updateByPrimaryKeySelective(Others record);

    int updateByPrimaryKey(Others record);
}