package xd.medicine.dao.autoMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.PostDutyExample;

public interface PostDutyMapper {
    int countByExample(PostDutyExample example);

    int deleteByExample(PostDutyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PostDuty record);

    int insertSelective(PostDuty record);

    List<PostDuty> selectByExample(PostDutyExample example);

    PostDuty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PostDuty record, @Param("example") PostDutyExample example);

    int updateByExample(@Param("record") PostDuty record, @Param("example") PostDutyExample example);

    int updateByPrimaryKeySelective(PostDuty record);

    int updateByPrimaryKey(PostDuty record);
}