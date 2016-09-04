package com.fresh.company.fresh.CommonUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.UserInfo;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by CJH on 2016/8/30.
 */

public class DBManager implements IDBManager{
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        String sql=String.format("INSERT INTO userInfo VALUES(%s,%s,%s)",userInfo.getmID().toString(), userInfo.getmUserName(), userInfo.getmPass());
    }

    @Override
    public void updateUserInfoPassword(String psd) {

    }

    @Override
    public UserInfo queryUserInfo(String id) {
        return null;
    }

    @Override
    public ArrayList<UserInfo> queryAllUusersInfo() {
        return null;
    }

    @Override
    public void addGoodsInfo(GoodsInfo goodsInfo) {

    }

    @Override
    public void updateGoodsInfo(GoodsInfo goodsInfo) {

    }

    @Override
    public void updateProductionDate(Date productionDate) {

    }

    @Override
    public void updatePrice(Double price) {

    }

    @Override
    public void updatePicPath(String picPath) {

    }

    @Override
    public void updateDurabilityPeriod(String durabilityPeriod) {

    }

    @Override
    public void updateManualPeriod(String manualPeriod) {

    }

    @Override
    public void deleteGoodsInfo(GoodsInfo goodsInfo) {

    }

    @Override
    public ArrayList<GoodsInfo> queryAllGoodsInfo() {
        return null;
    }

    @Override
    public GoodsInfo queryGoodsInfo(String barcode) {
        return null;
    }
}
