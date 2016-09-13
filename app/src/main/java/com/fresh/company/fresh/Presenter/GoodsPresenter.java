package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsType;
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
        list.add(GoodsType.谷物类食品.toString());
        list.add(GoodsType.杂粮类食品.toString());
        list.add(GoodsType.果蔬类食品.toString());
        list.add(GoodsType.水产类食品.toString());
        list.add(GoodsType.奶系列食品.toString());
        list.add(GoodsType.肉类食品.toString());
        list.add(GoodsType.蛋类食品.toString());
        list.add(GoodsType.饮料食品.toString());
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

        //mIGoodsView.ShowGoodsInfoStatus();
    }
}
