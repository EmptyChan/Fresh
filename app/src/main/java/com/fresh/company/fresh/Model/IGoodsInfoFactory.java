package com.fresh.company.fresh.Model;

/**
 * Created by CJH on 2016/7/31.
 */
public interface IGoodsInfoFactory {
    void AddGoodsInfo(GoodsInfo mGoodsInfo);
    void RemoveGoodsInfo(short mBarcode);
    void UpdateGoodsInfo(GoodsInfo mGoodsInfo);
    GoodsInfo GetGoodsInfo(short mBarcode);
}
