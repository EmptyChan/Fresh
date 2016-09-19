package com.fresh.company.fresh.Presenter;

/**
 * Created by CJH on 2016/7/31.
 */
public interface ILoginPresenter extends IBasePresenter{
    boolean CheckUserInfoThenLogin();
    void loginSuccess();
    void loginFailed();
    void TransportDataToView();
    void UpdateDataToView();
    boolean ForgetUserInfo();
}
