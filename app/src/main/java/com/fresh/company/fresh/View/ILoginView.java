package com.fresh.company.fresh.View;

import com.fresh.company.fresh.Model.UserInfo;

/**
 * Created by CJH on 2016/7/31.
 */
public interface ILoginView {
    void ShowLoginStatuss();
    void HideLoginStatuss();
    String GetUsr();
    String GetPsd();
    void clearUsr();
    void clearPsd();
    void ShowLoginFailed();
    void ShowLoginSuccess();
    void toNextActivity(UserInfo m);
}
