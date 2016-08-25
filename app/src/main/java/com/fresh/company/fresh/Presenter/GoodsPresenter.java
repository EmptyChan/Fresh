package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.View.IGoodsView;

import java.util.Date;

/**
 * Created by CJH on 2016/8/7.
 */
public class GoodsPresenter implements IGoodsPresenter{
    private IGoodsView mIGoodsView;
    private GoodsInfo mGoodsInfo;

    public GoodsPresenter(IGoodsView IGoodsView) {
        mIGoodsView = IGoodsView;
    }

    @Override
    public void loadSuccess() {

    }

    @Override
    public void loadFailed() {

    }

    @Override
    public void SetDataToView(String goodsId) {

    }

    @Override
    public void SetDate(Date date) {

    }

    @Override
    public void SetPeriod(int period) {

    }
}
