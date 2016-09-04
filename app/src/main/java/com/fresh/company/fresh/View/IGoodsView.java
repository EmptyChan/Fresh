package com.fresh.company.fresh.View;

import com.fresh.company.fresh.Model.GoodsInfo;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by CJH on 2016/7/31.
 */
public interface IGoodsView {
    void ShowGoodsInfoStatus();
    void HideGoodsInfoStatus();
    void ShowGoodsInfoFailed();
    void ShowGoodsInfoSuccess();
    void ShowGoodInfo();
    void SetData(GoodsInfo goodsInfo);
    void SetDate(Date date);
    void SetData(int period);
    void Init(ArrayList<String> list);
}
