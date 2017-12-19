package xd.medicine.dao.autoMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xd.medicine.entity.bo.TrustAttr;
import xd.medicine.entity.bo.TrustAttrExample;

public interface TrustAttrMapper {
    int countByExample(TrustAttrExample example);

    int deleteByExample(TrustAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TrustAttr record);

    int insertSelective(TrustAttr record);

    List<TrustAttr> selectByExample(TrustAttrExample example);

    TrustAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TrustAttr record, @Param("example") TrustAttrExample example);

    int updateByExample(@Param("record") TrustAttr record, @Param("example") TrustAttrExample example);

    int updateByPrimaryKeySelective(TrustAttr record);

    int updateByPrimaryKey(TrustAttr record);
}