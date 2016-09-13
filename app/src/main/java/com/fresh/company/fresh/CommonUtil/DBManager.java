package com.fresh.company.fresh.CommonUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsType;
import com.fresh.company.fresh.Model.UserInfo;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//        db.execSQL("CREATE TABLE IF NOT EXISTS goodsInfo" +
//                "(barcode VARCHAR PRIMARY KEY, goods_name VARCHAR,type INTEGER ,manufacturer VARCHAR, production_date VARCHAR,price DOUBLE" +
//                ",picture_path VARCHAR, durability_period VARCHAR, manual_period VARCHAR)");
//       db.execSQL("DROP TABLE goodsInfo");
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        String sql=String.format("INSERT OR REPLACE INTO userInfo VALUES('%s','%s','%s')",userInfo.getmID().toString(), userInfo.getmUserName(), userInfo.getmPass());
        db.execSQL(sql);//执行SQL语句
    }

    @Override
    public void updateUserInfoPassword(String psd) {
        String sql=String.format("UPDATE userInfo SET psd='%s'",psd);
        db.execSQL(sql);//执行SQL语句
    }

    @Override
    public String queryUserInfo(String userName) {
        Cursor c = db.rawQuery("select * from userInfo where usr=?",new String[]{userName});
        String password="";
        if(c.moveToFirst()) {
            password = c.getString(c.getColumnIndex("psd"));
        }
        c.close();
        return password;
    }

    @Override
    public ArrayList<UserInfo> queryAllUusersInfo() {
        return null;
    }

    @Override
    public void addGoodsInfo(GoodsInfo goodsInfo) {
        int i=goodsInfo.getmGoodsType().ordinal();
        String sql=String.format("INSERT OR REPLACE INTO goodsInfo VALUES('%s','%s',%s,'%s','%s',%s,'%s','%s','%s')"
                ,goodsInfo.getmBarcode(), goodsInfo.getmGoodsName(), String.valueOf(goodsInfo.getmGoodsType().ordinal()),goodsInfo.getmManufacturer(),
                goodsInfo.getmProductionDate(),String.valueOf(goodsInfo.getmPrice()), goodsInfo.getmPicturePath(),
                goodsInfo.getmDurabilityPeriod(),goodsInfo.getmManualPeriod());
        db.execSQL(sql);//执行SQL语句
    }

    @Override
    public void updateGoodsInfo(GoodsInfo goodsInfo) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        int i=goodsInfo.getmGoodsType().ordinal();
        cv.put("goods_name",goodsInfo.getmGoodsName());//添加要更改的字段及内容
        cv.put("type",String.valueOf(i));
        cv.put("manufacturer",goodsInfo.getmManufacturer());
        cv.put("production_date",goodsInfo.getmProductionDate());
        cv.put("price",String.valueOf(goodsInfo.getmPrice()));
        cv.put("picture_path",goodsInfo.getmPicturePath());
        cv.put("durability_period",goodsInfo.getmDurabilityPeriod());
        cv.put("manual_period",goodsInfo.getmManualPeriod());

        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {goodsInfo.getmBarcode()};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void updateGoodsType(String barcode, GoodsType goodsType) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("type",String.valueOf(goodsType.ordinal()));
        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {barcode};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void updateProductionDate(String barcode,String productionDate) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("production_date",productionDate);
        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {barcode};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void updatePrice(String barcode,Double price) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("price",String.valueOf(price));
        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {barcode};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void updatePicPath(String barcode,String picPath) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("picture_path",picPath);
        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {barcode};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void updateDurabilityPeriod(String barcode,String durabilityPeriod) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("durability_period",durabilityPeriod);
        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {barcode};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void updateManualPeriod(String barcode,String manualPeriod) {
        ContentValues cv = new ContentValues();//实例化ContentValues
        cv.put("manual_period",manualPeriod);
        String whereClause = "barcode=?";//修改条件
        String[] whereArgs = {barcode};//修改条件的参数
        db.update("goodsInfo",cv,whereClause,whereArgs);//执行修改
    }

    @Override
    public void deleteAllGoodsInfo() {
        String sql = "delete from goodsInfo";//删除操作的SQL语句
        db.execSQL(sql);//执行删除操作
    }

    @Override
    public void deleteGoodsInfo(String barcode) {
        String whereClause = "barcode=?";//删除的条件
        String[] whereArgs = {barcode};//删除的条件参数
        db.delete("goodsInfo",whereClause,whereArgs);//执行删除
    }


    @Override
    public ArrayList<GoodsInfo> queryAllGoodsInfo() {
        ArrayList<GoodsInfo> list=new ArrayList<>();
        Cursor c = db.rawQuery("select * from goodsInfo",null);
        while(c.moveToNext()) {
            GoodsInfo t=new GoodsInfo();
            t.setmBarcode(c.getString(c.getColumnIndex("barcode")));
            t.setmGoodsName(c.getString(c.getColumnIndex("goods_name")));
            int type=c.getInt(c.getColumnIndex("type"));
            t.setmGoodsType(GoodsType.values()[type]);
            t.setmManufacturer(c.getString(c.getColumnIndex("manufacturer")));
            t.setmProductionDate(c.getString(c.getColumnIndex("production_date")));
            t.setmPrice(c.getDouble(c.getColumnIndex("price")));
            t.setmPicturePath(c.getString(c.getColumnIndex("picture_path")));
            t.setmDurabilityPeriod(c.getString(c.getColumnIndex("durability_period")));
            t.setmManualPeriod(c.getString(c.getColumnIndex("manual_period")));
            list.add(t);
        }
        c.close();
        return list;
    }

    @Override
    public GoodsInfo queryGoodsInfo(String barcode) {
        Cursor c = db.rawQuery("select * from goodsInfo where barcode=?",new String[]{barcode});
        GoodsInfo t=null;
        if(c.moveToFirst()) {
            t=new GoodsInfo();
            t.setmBarcode(c.getString(c.getColumnIndex("barcode")));
            t.setmGoodsName(c.getString(c.getColumnIndex("goods_name")));
            t.setmGoodsType(GoodsType.values()[c.getType(c.getColumnIndex("type"))]);
            t.setmManufacturer(c.getString(c.getColumnIndex("manufacturer")));
            t.setmProductionDate(c.getString(c.getColumnIndex("production_date")));
            t.setmPrice(c.getDouble(c.getColumnIndex("price")));
            t.setmPicturePath(c.getString(c.getColumnIndex("picture_path")));
            t.setmDurabilityPeriod(c.getString(c.getColumnIndex("durability_period")));
            t.setmManualPeriod(c.getString(c.getColumnIndex("manual_period")));
        }
        c.close();
        return t;
    }

    public void CloseDB(){
        db.close();
    }
}
