package com.fresh.company.fresh.CommonUtil;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.UserInfo;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by CJH on 2016/8/30.
 */

public interface IDBManager{
    void addUserInfo(UserInfo userInfo);
    void updateUserInfoPassword(String psd);
    UserInfo queryUserInfo(String id);
    ArrayList<UserInfo> queryAllUusersInfo();
    void addGoodsInfo(GoodsInfo goodsInfo);
    void updateGoodsInfo(GoodsInfo goodsInfo);
    void updateProductionDate(Date productionDate);
    void updatePrice(Double price);
    void updatePicPath(String picPath);
    void updateDurabilityPeriod(String durabilityPeriod);
    void updateManualPeriod(String manualPeriod);
    void deleteGoodsInfo(GoodsInfo goodsInfo);
    ArrayList<GoodsInfo> queryAllGoodsInfo();
    GoodsInfo queryGoodsInfo(String barcode);
}
