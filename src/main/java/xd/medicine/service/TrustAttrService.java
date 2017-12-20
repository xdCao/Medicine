package xd.medicine.service;

import xd.medicine.entity.bo.TrustAttr;

/**
 * created by xdCao on 2017/12/20
 */

public interface TrustAttrService {

    Integer insertTrustAttr(TrustAttr trustAttr);

    Integer updateTrustAttr(TrustAttr trustAttr);

    TrustAttr getTrustAttrById(int id);

    void deleteById(Integer trustattrId);
}
