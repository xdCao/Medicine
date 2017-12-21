package xd.medicine.service;

import com.github.pagehelper.PageInfo;
import xd.medicine.entity.bo.Others;

import java.util.List;

/**
 * created by xdCao on 2017/12/21
 */

public interface OthersService {
    Integer insert(Others others);

    Integer update(Others others);

    Integer delete(int id);

    Others getOthersById(int id);

    List<Others> getAllOthers();

    PageInfo<Others> getOthersByPage(int page, int rows);

    List<Others> getOthersByAccount(String account);


}
