package com.fresh.company.fresh.Model;

import java.util.UUID;

/**
 * Created by CJH on 2016/7/31.
 */
public interface IUserInfoFactory extends BaseFactory{
    UserInfo GetUserInfo(String mUserName);
    String QueryUsrInfo(String mUserName);
    void AddUserInfo(UserInfo mUser);
    void RemoveUserInfo(String mUserName);
    void UpdateUserInfo(UserInfo mUser);
    void UpdateUserInfoPassword(String mUserName,String mPsd);
}
