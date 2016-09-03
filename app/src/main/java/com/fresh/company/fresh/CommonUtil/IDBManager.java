package com.fresh.company.fresh.CommonUtil;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.UserInfo;

/**
 * Created by CJH on 2016/8/30.
 */

public interface IDBManager {
    void addUerInfo(UserInfo userInfo);
    void updateUserInfoPassword();
    void addGoodsInfo(GoodsInfo goodsInfo);
    void updateGoodsInfo(GoodsInfo goodsInfo);

}
