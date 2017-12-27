package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;

import java.util.List;

/**
 * created by liubotao
 */
public interface PostDutyService {
    Integer insertPostDuty(PostDuty postDuty);

    int deletePostDuty(int id);

    Integer updatePostDuty(PostDuty postDuty);

    List<PostDuty> getAllPostDuties();

    PostDuty getPostDutyById(int id);

    PageInfo<PostDuty> getPostDutiesByPage(int page, int rows);

    List<PostDuty> getPostDutiesByChosen(boolean chosen);

    Integer countPostDutiesByChosen(boolean chosen);

    Integer count();
}
