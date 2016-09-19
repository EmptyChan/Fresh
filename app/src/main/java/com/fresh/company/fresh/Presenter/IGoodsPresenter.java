package com.fresh.company.fresh.Presenter;


import android.content.Intent;

import com.fresh.company.fresh.Model.GoodsInfo;

import java.util.Date;

/**
 * Created by CJH on 2016/8/7.
 */
public interface IGoodsPresenter extends IBasePresenter{
    void loadSuccess();
    void loadFailed();
    void SetDataToView(GoodsInfo goodsInfo);
    void SetDate(String date);
    void SetPeriod(int period);
    void WebRequest(String goodsId);
    GoodsInfo initGoodsInfo();
    Intent addGoodsInfo();
    Intent updateGoodsInfo();
    boolean checkGoodsInfo(String barcode);
    boolean checkGoodsInfo(GoodsInfo oldGoodsInfo,GoodsInfo newGoodsInfo);
}
