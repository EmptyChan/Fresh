package com.fresh.company.fresh.CommonUtil;

import com.fresh.company.fresh.Model.DietPlanInfo;
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
    void deleteUserInfo(String userName);
    void updateUserInfoPassword(String userName,String psd);
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
    void addDietPlan(DietPlanInfo dietPlanInfo);
    void updateDietPlan(DietPlanInfo dietPlanInfo);
    void updateMorningPlan(String key,String morning);
    void updateAfternoonPlan(String key,String afternoon);
    void updateEveningPlan(String key,String evening);
    void deleteAllDietPlan();
    void deleteDietPlan(String key);
    ArrayList<DietPlanInfo> getAllDietPlanInfo();
    DietPlanInfo queryDietPlanDiet(String key);
}
