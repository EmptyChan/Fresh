package com.fresh.company.fresh.Model;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/9/4.
 */

public interface IDietPlanInfoFactory extends BaseFactory{
    void AddDietPlan(DietPlanInfo dietPlanInfo);
    void UpdateDietPlan(DietPlanInfo dietPlanInfo);
    void UpdateMorningPlan(String key,String morning);
    void UpdateAfternoonPlan(String key,String afternoon);
    void UpdateEveningPlan(String key,String evening);
    void DeleteAllDietPlan();
    void DeleteDietPlan(String key);
    ArrayList<DietPlanInfo> GetAllDietPlanInfo();
    DietPlanInfo QueryDietPlanDiet(String key);
}
