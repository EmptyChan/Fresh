package com.fresh.company.fresh.View;

/**
 * Created by CJH on 2016/7/31.
 */
public interface IDietPlanView {
    void ShowDietPlanInfoStatus();
    void HideDietPlanInfoStatus();
    void ShowDietPlanInfo(String content);
    String GetMorningContent();
    String GetAfternoonContent();
    String GetEveningContent();
}
