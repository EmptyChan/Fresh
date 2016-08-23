package com.fresh.company.fresh.Presenter;

/**
 * Created by CJH on 2016/7/31.
 */
public interface ILoginPresenter {
    boolean CheckUserInfo();
    void loginSuccess();
    void loginFailed();
    void TransportDataToView();
    void UpdateDataToView();
}
