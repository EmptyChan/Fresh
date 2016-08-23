package com.fresh.company.fresh.Model;

import java.util.Date;

/**
 * Created by CJH on 2016/7/31.
 */
public class GoodsInfo {
    private short mBarcode;
    private String mGoodsName;
    private GoodsType mGoodsType;
    private String mManufacturer;
    private Date mProductionDate;
    private double mPrice;
    private String mPicturePath;
    private String mDurabilityPeriod;
    private String mManualPeriod;

    public GoodsType getmGoodsType() {
        return mGoodsType;
    }

    public void setmGoodsType(GoodsType mGoodsType) {
        this.mGoodsType = mGoodsType;
    }

    public String getmGoodsName() {
        return mGoodsName;
    }

    public void setmGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }

    public short getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(short mBarcode) {
        this.mBarcode = mBarcode;
    }

    public String getmManufacturer() {
        return mManufacturer;
    }

    public void setmManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    public Date getmProductionDate() {
        //final SimpleDateFormat dateFormat = new SimpleDateFormat("EE-MM-dd-yyyy");
        return mProductionDate;
    }

    public void setmProductionDate(Date mProductionDate) {
        this.mProductionDate = mProductionDate;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getmPicturePath() {
        return mPicturePath;
    }

    public void setmPicturePath(String mPicturePath) {
        this.mPicturePath = mPicturePath;
    }

    public String getmDurabilityPeriod() {
        return mDurabilityPeriod;
    }

    public void setmDurabilityPeriod(String mDurabilityPeriod) {
        this.mDurabilityPeriod = mDurabilityPeriod;
    }

    public String getmManualPeriod() {
        return mManualPeriod;
    }

    public void setmManualPeriod(String mManualPeriod) {
        this.mManualPeriod = mManualPeriod;
    }
}
