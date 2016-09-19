package com.fresh.company.fresh.Model;

import android.content.Context;

import com.fresh.company.fresh.CommonUtil.DBManager;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/7/31.
 */
public class GoodsInfoFactory implements IGoodsInfoFactory{

    private DBManager mDBManager;

    public GoodsInfoFactory(Context c) {
        mDBManager=new DBManager(c);
    }

    /**
     * Add Goods Information into sqlite database.
     * @param goodsInfo :the GoodsInfo which you want to add.
     */
    @Override
    public void AddGoodsInfo(GoodsInfo goodsInfo) {
        mDBManager.addGoodsInfo(goodsInfo);
    }

    /**
     * Remove Goods Information from sqlite database.
     * @param barcode :the index
     */
    @Override
    public void DeleteGoodsInfo(String barcode) {
        mDBManager.deleteGoodsInfo(barcode);
    }

    @Override
    public ArrayList<GoodsInfo> QueryAllGoodsInfo() {
        return mDBManager.queryAllGoodsInfo();
    }


    /**
     * Update Goods Information into sqlite database.
     * @param goodsInfo :the GoodsInfo which you want to update.
     */
    @Override
    public void UpdateGoodsInfo(GoodsInfo goodsInfo) {
        mDBManager.updateGoodsInfo(goodsInfo);
    }

    @Override
    public void UpdateGoodsType(String barcode, GoodsType goodsType) {
        mDBManager.updateGoodsType(barcode,goodsType);
    }

    @Override
    public void UpdateProductionDate(String barcode, String productionDate) {
        mDBManager.updateProductionDate(barcode,productionDate);
    }

    @Override
    public void UpdatePrice(String barcode, Double price) {
        mDBManager.updatePrice(barcode,price);
    }

    @Override
    public void UpdatePicPath(String barcode, String picPath) {
        mDBManager.updatePicPath(barcode,picPath);
    }

    @Override
    public void UpdateDurabilityPeriod(String barcode, String durabilityPeriod) {
        mDBManager.updateDurabilityPeriod(barcode,durabilityPeriod);
    }

    @Override
    public void UpdateManualPeriod(String barcode, String manualPeriod) {
        mDBManager.updateManualPeriod(barcode,manualPeriod);
    }

    @Override
    public void DeleteAllGoodsInfo() {
        mDBManager.deleteAllGoodsInfo();
    }

    /**
     * Get User Information from network.
     * @param barcode :index
     * @return The Info which want to get.Goods
     */
    @Override
    public GoodsInfo  QueryGoodsInfo(String barcode) {
        return mDBManager.queryGoodsInfo(barcode);
    }

    @Override
    public void CloseFactory() {
        mDBManager.CloseDB();
    }
}
