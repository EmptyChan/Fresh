package com.fresh.company.fresh.Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by CJH on 2016/7/31.
 */
public class UserInfo {
    public UserInfo() {
        this.mID = UUID.randomUUID();
    }
    public UserInfo(String mUserName,String mPass) {
        this.mID = UUID.randomUUID();
        this.mUserName=mUserName;
        this.mPass=mPass;
    }
    private UUID mID;
    private String mUserName;
    private String mPass;

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public UUID getmID() {
        return mID;
    }

    @Override
    public String toString() {
        return mUserName;
    }

    @Override
    public boolean equals(Object obj) {
       UserInfo s=(UserInfo) obj;
        if (this.mID.equals(s.getmID()) && this.mUserName.equals(s.getmUserName()) &&
                this.mPass.equals(s.getmPass())){
            return true;
        }
        return false;
    }
}
