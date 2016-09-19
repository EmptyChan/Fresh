package com.fresh.company.fresh.Model;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/7/31.
 */
public interface IGoodsInfoFactory extends BaseFactory{
    void AddGoodsInfo(GoodsInfo goodsInfo);
    void UpdateGoodsInfo(GoodsInfo goodsInfo);
    void UpdateGoodsType(String barcode,GoodsType goodsType);
    void UpdateProductionDate(String barcode,String productionDate);
    void UpdatePrice(String barcode,Double price);
    void UpdatePicPath(String barcode,String picPath);
    void UpdateDurabilityPeriod(String barcode,String durabilityPeriod);
    void UpdateManualPeriod(String barcode,String manualPeriod);
    void DeleteAllGoodsInfo();
    void DeleteGoodsInfo(String barcode);
    ArrayList<GoodsInfo> QueryAllGoodsInfo();
    GoodsInfo QueryGoodsInfo(String barcode);
}
