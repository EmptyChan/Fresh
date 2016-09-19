package com.fresh.company.fresh.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fresh.company.fresh.CommonUtil.DBManager;
import com.fresh.company.fresh.CommonUtil.TypeToMipmap;
import com.fresh.company.fresh.Component.GoodsReceiver;
import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsInfoFactory;
import com.fresh.company.fresh.Model.GoodsType;
import com.fresh.company.fresh.Model.IGoodsInfoFactory;
import com.fresh.company.fresh.View.IGoodsView;
import com.fresh.company.fresh.View.activity.GoodsActivity;
import com.fresh.company.fresh.View.activity.MainActivity;
import com.fresh.company.fresh.View.fragment.ListAllGoodsFragment;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CJH on 2016/8/7.
 */
public class GoodsPresenter implements IGoodsPresenter{
    private IGoodsView mIGoodsView;
    private GoodsInfo mGoodsInfo;
    private IGoodsInfoFactory mIGoodsInfoFactory;

    public GoodsPresenter(IGoodsView IGoodsView,IGoodsInfoFactory iGoodsInfoFactory) {
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
        mIGoodsInfoFactory=iGoodsInfoFactory;
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
    public void SetDataToView(GoodsInfo goodsInfo) {

    }

    @Override
    public void SetDate(String date) {

    }

    @Override
    public void SetPeriod(int period) {

    }

    @Override
    public void WebRequest(String goodsId) {

        //mIGoodsView.ShowGoodsInfoStatus();
    }
    @Override
    public GoodsInfo initGoodsInfo(){
        GoodsInfo t=new GoodsInfo();
        t.setmBarcode(mIGoodsView.getBarcode());
        t.setmGoodsName(mIGoodsView.getGoodsName());
        t.setmGoodsType(GoodsType.values()[mIGoodsView.getSelectedGoodsType()]);
        t.setmManufacturer(mIGoodsView.getGoodsManufacturer());
        t.setmProductionDate(mIGoodsView.getGoodsProductionDate());
        if (mIGoodsView.getGoodsPrice().contains("¥"))
            t.setmPrice(Double.parseDouble(mIGoodsView.getGoodsPrice().split("¥")[1]));
        else
            t.setmPrice(Double.parseDouble(mIGoodsView.getGoodsPrice()));
        t.setmPicturePath(String.valueOf(TypeToMipmap.Type2Mipmap(GoodsType.values()[mIGoodsView.getSelectedGoodsType()])));
        t.setmDurabilityPeriod(mIGoodsView.getGoodsDurabilityPeriod());
        t.setmManualPeriod(mIGoodsView.getGoodsManualPeriod());
        return t;
    }

    @Override
    public Intent addGoodsInfo() {
        GoodsInfo t=initGoodsInfo();
        mIGoodsInfoFactory.AddGoodsInfo(t);
        Intent i=new Intent(MainActivity.GOODS_SEND);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(ListAllGoodsFragment.ADD_GOODS,t);
        i.putExtras(mBundle);
        //((GoodsActivity.ICallBackToMain) MainActivity.getCurrentFragment()).GoodsAdd(t);
        return i;
    }

    @Override
    public Intent updateGoodsInfo() {
        GoodsInfo t=initGoodsInfo();
        mIGoodsInfoFactory.UpdateGoodsInfo(t);
        Intent i=new Intent(MainActivity.GOODS_SEND);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(ListAllGoodsFragment.UPDATE_GOODS,t);
        i.putExtras(mBundle);
        //((GoodsActivity.ICallBackToMain)MainActivity.getCurrentFragment()).GoodsChange(t);
        return i;
    }

    @Override
    public boolean checkGoodsInfo(String barcode) {
        if (mIGoodsInfoFactory.QueryGoodsInfo(barcode)==null)
            return true;
        return false;
    }

    @Override
    public boolean checkGoodsInfo(GoodsInfo oldGoodsInfo, GoodsInfo newGoodsInfo) {
        if (oldGoodsInfo.equals(newGoodsInfo)){
            return true;
        }
        return false;
    }

    @Override
    public void Dispose() {
        mIGoodsInfoFactory.CloseFactory();
    }
}
