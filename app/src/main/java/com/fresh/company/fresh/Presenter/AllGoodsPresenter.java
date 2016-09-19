package com.fresh.company.fresh.Presenter;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.IGoodsInfoFactory;
import com.fresh.company.fresh.View.IAllGoodsView;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/9/16.
 */

public class AllGoodsPresenter implements IAllGoodsPresenter {
    private IAllGoodsView mIAllGoodsView;
    private IGoodsInfoFactory mIGoodsInfoFactory;
    private ArrayList<GoodsInfo> mGoodsInfos=null;

    public AllGoodsPresenter(IAllGoodsView IAllGoodsView, IGoodsInfoFactory IGoodsInfoFactory) {
        mIAllGoodsView = IAllGoodsView;
        mIGoodsInfoFactory = IGoodsInfoFactory;
    }

    @Override
    public void GoodsInfoAdd(GoodsInfo goodsInfo) {
        ArrayList<GoodsInfo> temp=mIAllGoodsView.GetAllGoodsInfo();
        if (temp!=null) {
            if (isHaveGoodsInfo(temp,goodsInfo.getmBarcode())){
                return;
            }else {
                mIAllGoodsView.GoodsAdd(goodsInfo);
            }
        }else
            mIAllGoodsView.GoodsAdd(goodsInfo);

    }

    private boolean isHaveGoodsInfo(ArrayList<GoodsInfo> list,String barcode){
        int length=list.size();
        for (int i=0;i<length;i++){
            if(list.get(i).getmBarcode().trim().equals(barcode.trim())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void GoodsInfoChange(GoodsInfo goodsInfo) {
        int c=0;
        for (int i=0;i<mGoodsInfos.size();i++){
            String barcode=mGoodsInfos.get(i).getmBarcode();
            String bar=goodsInfo.getmBarcode();
            if (barcode.equals(goodsInfo.getmBarcode())){
                mGoodsInfos.get(i).setmGoodsName(goodsInfo.getmGoodsName());
                mGoodsInfos.get(i).setmGoodsType(goodsInfo.getmGoodsType());
                mGoodsInfos.get(i).setmManufacturer(goodsInfo.getmManufacturer());
                mGoodsInfos.get(i).setmProductionDate(goodsInfo.getmProductionDate());
                mGoodsInfos.get(i).setmPrice(goodsInfo.getmPrice());
                mGoodsInfos.get(i).setmPicturePath(goodsInfo.getmPicturePath());
                mGoodsInfos.get(i).setmDurabilityPeriod(goodsInfo.getmDurabilityPeriod());
                mGoodsInfos.get(i).setmManualPeriod(goodsInfo.getmManualPeriod());
                c=i;
            }
        }
        mIAllGoodsView.GoodsChange(c);
    }

    @Override
    public ArrayList<GoodsInfo> GetAllGoodsInfo() {
        if (mGoodsInfos==null) {
            mGoodsInfos = mIGoodsInfoFactory.QueryAllGoodsInfo();
        }
        return mGoodsInfos;
    }

    @Override
    public void DeleteGoodsInfo(String barcode) {
        mIGoodsInfoFactory.DeleteGoodsInfo(barcode);
        mIAllGoodsView.NotifyToast("Delete Complete");
    }

    @Override
    public void Dispose() {
        mIGoodsInfoFactory.CloseFactory();
    }
}
