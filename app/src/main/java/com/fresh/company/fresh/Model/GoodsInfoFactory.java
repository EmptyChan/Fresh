package com.fresh.company.fresh.Model;

/**
 * Created by CJH on 2016/7/31.
 */
public class GoodsInfoFactory implements IGoodsInfoFactory{

    /**
     * Add Goods Information into sqlite database.
     * @param mGoodsInfo :the GoodsInfo which you want to add.
     */
    @Override
    public void AddGoodsInfo(GoodsInfo mGoodsInfo) {

    }

    /**
     * Remove Goods Information from sqlite database.
     * @param mBarcode :the index
     */
    @Override
    public void RemoveGoodsInfo(short mBarcode) {

    }


    /**
     * Update Goods Information into sqlite database.
     * @param mGoodsInfo :the GoodsInfo which you want to update.
     */
    @Override
    public void UpdateGoodsInfo(GoodsInfo mGoodsInfo) {

    }

    /**
     * Get User Information from network.
     * @param mBarcode :index
     * @return The Info which want to get.Goods
     */
    @Override
    public GoodsInfo GetGoodsInfo(short mBarcode) {
        return null;
    }
}
