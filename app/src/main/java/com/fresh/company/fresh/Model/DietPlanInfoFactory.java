package com.fresh.company.fresh.Model;

import android.content.Context;

import com.fresh.company.fresh.CommonUtil.DBManager;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/9/4.
 */

public class DietPlanInfoFactory implements IDietPlanInfoFactory{
    private DBManager mDBManager;

    public DietPlanInfoFactory(Context c) {
        mDBManager=new DBManager(c);
    }

    @Override
    public void AddDietPlan(DietPlanInfo dietPlanInfo) {
        mDBManager.addDietPlan(dietPlanInfo);
    }

    @Override
    public void UpdateDietPlan(DietPlanInfo dietPlanInfo) {
        mDBManager.updateDietPlan(dietPlanInfo);
    }

    @Override
    public void UpdateMorningPlan(String key, String morning) {
        mDBManager.updateMorningPlan(key,morning);
    }

    @Override
    public void UpdateAfternoonPlan(String key, String afternoon) {
        mDBManager.updateMorningPlan(key,afternoon);
    }

    @Override
    public void UpdateEveningPlan(String key, String evening) {
        mDBManager.updateMorningPlan(key,evening);
    }

    @Override
    public void DeleteAllDietPlan() {
        mDBManager.deleteAllDietPlan();
    }

    @Override
    public void DeleteDietPlan(String key) {
        mDBManager.deleteDietPlan(key);
    }

    @Override
    public ArrayList<DietPlanInfo> GetAllDietPlanInfo() {
        return mDBManager.getAllDietPlanInfo();
    }

    @Override
    public DietPlanInfo QueryDietPlanDiet(String key) {
        return mDBManager.queryDietPlanDiet(key);
    }

    @Override
    public void CloseFactory() {
        mDBManager.CloseDB();
    }
}
