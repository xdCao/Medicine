package xd.medicine.dao.autoMapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xd.medicine.entity.bo.PostDutyLog;
import xd.medicine.entity.bo.PostDutyLogExample;

public interface PostDutyLogMapper {
    int countByExample(PostDutyLogExample example);

    int deleteByExample(PostDutyLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PostDutyLog record);

    int insertSelective(PostDutyLog record);

    List<PostDutyLog> selectByExample(PostDutyLogExample example);

    PostDutyLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PostDutyLog record, @Param("example") PostDutyLogExample example);

    int updateByExample(@Param("record") PostDutyLog record, @Param("example") PostDutyLogExample example);

    int updateByPrimaryKeySelective(PostDutyLog record);

    int updateByPrimaryKey(PostDutyLog record);
}