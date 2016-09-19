package com.fresh.company.fresh.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by CJH on 2016/7/31.
 */
public class GoodsInfo implements Parcelable {
    private String mBarcode;
    private String mGoodsName;
    private GoodsType mGoodsType;
    private String mManufacturer;
    private String mProductionDate;
    private double mPrice;
    private String mPicturePath;
    private String mDurabilityPeriod;
    private String mManualPeriod;

    public GoodsInfo(){

    }
    protected GoodsInfo(Parcel in) {
        mBarcode = in.readString();
        mGoodsName = in.readString();
        mGoodsType=(GoodsType) in.readValue(getClass().getClassLoader());
        mManufacturer = in.readString();
        mProductionDate = in.readString();
        mPrice = in.readDouble();
        mPicturePath = in.readString();
        mDurabilityPeriod = in.readString();
        mManualPeriod = in.readString();
    }

    public static final Creator<GoodsInfo> CREATOR = new Creator<GoodsInfo>() {
        @Override
        public GoodsInfo createFromParcel(Parcel in) {
            return new GoodsInfo(in);
        }

        @Override
        public GoodsInfo[] newArray(int size) {
            return new GoodsInfo[size];
        }
    };

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

    public String getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(String mBarcode) {
        this.mBarcode = mBarcode;
    }

    public String getmManufacturer() {
        return mManufacturer;
    }

    public void setmManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    public String getmProductionDate() {
        //final SimpleDateFormat dateFormat = new SimpleDateFormat("EE-MM-dd-yyyy");
        return mProductionDate;
    }

    public void setmProductionDate(String mProductionDate) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mBarcode);
        parcel.writeString(mGoodsName);
        parcel.writeValue(mGoodsType);
        parcel.writeString(mManufacturer);
        parcel.writeString(mProductionDate);
        parcel.writeDouble(mPrice);
        parcel.writeString(mPicturePath);
        parcel.writeString(mDurabilityPeriod);
        parcel.writeString(mManualPeriod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsInfo goodsInfo = (GoodsInfo) o;

        if (Double.compare(goodsInfo.mPrice, mPrice) != 0) return false;
        if (!mBarcode.equals(goodsInfo.mBarcode)) return false;
        if (!mGoodsName.equals(goodsInfo.mGoodsName)) return false;
        if (mGoodsType != goodsInfo.mGoodsType) return false;
        if (!mManufacturer.equals(goodsInfo.mManufacturer)) return false;
        if (!mProductionDate.equals(goodsInfo.mProductionDate)) return false;
        if (!mPicturePath.equals(goodsInfo.mPicturePath)) return false;
        if (!mDurabilityPeriod.equals(goodsInfo.mDurabilityPeriod)) return false;
        return mManualPeriod.equals(goodsInfo.mManualPeriod);

    }
}
