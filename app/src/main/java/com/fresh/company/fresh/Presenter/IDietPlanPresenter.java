package com.fresh.company.fresh.Presenter;

/**
 * Created by CJH on 2016/8/7.
 */
public interface IDietPlanPresenter extends IBasePresenter{
    void WriteToFile();
    String GetMorningContent(String key);
    String GetAfternoonContent(String key);
    String GetEveningContent(String key);
    void SetDietPlanInfoToFile();
    void DeleteDietPlanInfo();
}
