package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.Model.GoodsInfo;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/9/16.
 */

public interface IAllGoodsPresenter extends IBasePresenter {
    void GoodsInfoAdd(GoodsInfo goodsInfo);
    void GoodsInfoChange(GoodsInfo goodsInfo);
    ArrayList<GoodsInfo> GetAllGoodsInfo();
    void DeleteGoodsInfo(String barcode);
}
