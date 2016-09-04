package com.fresh.company.fresh.Presenter;


import java.util.Date;

/**
 * Created by CJH on 2016/8/7.
 */
public interface IGoodsPresenter {
    void loadSuccess();
    void loadFailed();
    void SetDataToView(String goodsId);
    void SetDate(Date date);
    void SetPeriod(int period);
    void WebRequest(String goodsId);
}
