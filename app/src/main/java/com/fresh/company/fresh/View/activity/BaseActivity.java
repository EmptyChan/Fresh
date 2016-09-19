package com.fresh.company.fresh.View.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.fresh.company.fresh.CommonUtil.DBManager;

public abstract class BaseActivity extends AppCompatActivity {

    //protected DBManager mDBManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mDBManager=new DBManager(this);
    }
    //public DBManager getDBmanager(){
    //    return mDBManager;
   // }
}
