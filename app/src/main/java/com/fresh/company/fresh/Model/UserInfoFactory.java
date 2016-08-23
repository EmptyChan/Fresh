package com.fresh.company.fresh.Model;

import java.util.UUID;

/**
 * Created by CJH on 2016/7/31.
 */
public class UserInfoFactory  implements IUserInfoFactory{


    /**
     * Get User Information from sqlite database.
     * @param mID :UUID for index
     * @return The UserInfo which want to get.
     */
    @Override
    public UserInfo GetUserInfo(UUID mID) {
        return null;
    }

    /**
     * Add User Information into sqlite database.
     * @param mUser :the UserInfo which you want to add.
     */
    @Override
    public void AddUserInfo(UserInfo mUser) {

    }

    /**
     * Remove User Information from sqlite database.
     * @param mID :the index
     */
    @Override
    public void RemoveUserInfo(UUID mID) {

    }

    /**
     * Update User Information into sqlite database.
     * @param mUser :the UserInfo which you want to update.
     */
    @Override
    public void UpdateUserInfo(UserInfo mUser) {

    }
}
