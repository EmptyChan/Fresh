package com.fresh.company.fresh.CommonUtil;

import com.fresh.company.fresh.Model.GoodsType;
import com.fresh.company.fresh.R;

/**
 * Created by CJH on 2016/9/13.
 */

public class TypeToMipmap {
    public static int Type2Mipmap(GoodsType goodsType){
        int i=-1;
        switch (goodsType.ordinal()){
            case 0:
                i= R.mipmap.index_grain;
                break;
            case 1:i=R.mipmap.index_grain;
                break;
            case 2:i=R.mipmap.index_fruit;
                break;
            case 3:i=R.mipmap.index_meat;
                break;
            case 4:i=R.mipmap.index_bean;
                break;
            case 5:i=R.mipmap.index_meat;
                break;
            case 6:i=R.mipmap.index_bean;
                break;
            case 7:i=R.mipmap.index_drink;
                break;
        }
        return i;
    }
}
