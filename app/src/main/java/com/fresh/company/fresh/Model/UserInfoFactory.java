package com.fresh.company.fresh.Model;

import android.content.Context;

import com.fresh.company.fresh.CommonUtil.DBManager;

import java.util.UUID;

/**
 * Created by CJH on 2016/7/31.
 */
public class UserInfoFactory  implements IUserInfoFactory{

    private DBManager mDBManager;

    public UserInfoFactory(Context c) {
        mDBManager=new DBManager(c);
    }

    /**
     * Get User Information from sqlite database.
     * @param mUserName :name
     * @return The UserInfo which want to get.
     */
    @Override
    public UserInfo GetUserInfo(String mUserName) {
        return null;
    }

    @Override
    public String QueryUsrInfo(String mUserName) {
        return mDBManager.queryUserInfo(mUserName);
    }

    /**
     * Add User Information into sqlite database.
     * @param mUser :the UserInfo which you want to add.
     */
    @Override
    public void AddUserInfo(UserInfo mUser) {
        mDBManager.addUserInfo(mUser);
    }

    /**
     * Remove User Information from sqlite database.
     * @param mUserName :the name
     */
    @Override
    public void RemoveUserInfo(String mUserName) {
        mDBManager.deleteUserInfo(mUserName);
    }

    /**
     * Update User Information into sqlite database.
     * @param mUser :the UserInfo which you want to update.
     */
    @Override
    public void UpdateUserInfo(UserInfo mUser) {

    }

    @Override
    public void UpdateUserInfoPassword(String mUserName,String mPsd) {
        mDBManager.updateUserInfoPassword(mUserName,mPsd);
    }

    @Override
    public void CloseFactory() {
        mDBManager.CloseDB();
    }
}
