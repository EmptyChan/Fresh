package com.fresh.company.fresh.CommonUtil;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsType;
import com.fresh.company.fresh.Model.UserInfo;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by CJH on 2016/8/30.
 */

public interface IDBManager{
    void addUserInfo(UserInfo userInfo);
    void updateUserInfoPassword(String psd);
    String queryUserInfo(String userName);
    ArrayList<UserInfo> queryAllUusersInfo();
    void addGoodsInfo(GoodsInfo goodsInfo);
    void updateGoodsInfo(GoodsInfo goodsInfo);
    void updateGoodsType(String barcode,GoodsType goodsType);
    void updateProductionDate(String barcode,String productionDate);
    void updatePrice(String barcode,Double price);
    void updatePicPath(String barcode,String picPath);
    void updateDurabilityPeriod(String barcode,String durabilityPeriod);
    void updateManualPeriod(String barcode,String manualPeriod);
    void deleteAllGoodsInfo();
    void deleteGoodsInfo(String barcode);
    ArrayList<GoodsInfo> queryAllGoodsInfo();
    GoodsInfo queryGoodsInfo(String barcode);
}
