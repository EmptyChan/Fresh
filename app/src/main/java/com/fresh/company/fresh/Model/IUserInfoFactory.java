package com.fresh.company.fresh.Model;

import java.util.UUID;

/**
 * Created by CJH on 2016/7/31.
 */
public interface IUserInfoFactory {
    UserInfo GetUserInfo(UUID mID);
    void AddUserInfo(UserInfo mUser);
    void RemoveUserInfo(UUID mID);
    void UpdateUserInfo(UserInfo mUser);
}
