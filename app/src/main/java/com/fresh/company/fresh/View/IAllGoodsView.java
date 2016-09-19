package com.fresh.company.fresh.View;


import com.fresh.company.fresh.CommonUtil.RecyclerViewAdapter;
import com.fresh.company.fresh.Model.GoodsInfo;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/9/16.
 */

public interface IAllGoodsView {
    void GoodsAdd(GoodsInfo goodsInfo);
    void GoodsChange(int i);
    void NotifyToast(String text);
    ArrayList<GoodsInfo> GetAllGoodsInfo();
}
