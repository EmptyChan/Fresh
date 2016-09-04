package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.View.IGoodsView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CJH on 2016/8/7.
 */
public class GoodsPresenter implements IGoodsPresenter{
    private IGoodsView mIGoodsView;
    private GoodsInfo mGoodsInfo;

    public GoodsPresenter(IGoodsView IGoodsView) {
        ArrayList<String> list=new ArrayList<String>();
        list.add( "Commodity");//日用品
        list.add( "Foodstuff");//食品
        list.add( "Book");//图书图像类);
        mIGoodsView = IGoodsView;
        mIGoodsView.Init(list);
    }

    @Override
    public void loadSuccess() {
        mIGoodsView.ShowGoodsInfoSuccess();
        mIGoodsView.HideGoodsInfoStatus();
    }

    @Override
    public void loadFailed() {
        mIGoodsView.ShowGoodsInfoFailed();
        mIGoodsView.HideGoodsInfoStatus();
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

    @Override
    public void WebRequest(String goodsId) {
        mIGoodsView.ShowGoodsInfoStatus();
    }
}
