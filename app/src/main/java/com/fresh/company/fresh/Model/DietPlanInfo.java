package com.fresh.company.fresh.Model;

/**
 * Created by CJH on 2016/9/4.
 */

public class DietPlanInfo {
    private String date;
    private String morningPlan;
    private String afternoonPlan;
    private String eveningPlan;

    public DietPlanInfo() {
    }

    public DietPlanInfo(String date, String morningPlan, String afternoonPlan, String eveningPlan) {
        this.date = date;
        this.morningPlan = morningPlan;
        this.afternoonPlan = afternoonPlan;
        this.eveningPlan = eveningPlan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMorningPlan() {
        return morningPlan;
    }

    public void setMorningPlan(String morningPlan) {
        this.morningPlan = morningPlan;
    }

    public String getAfternoonPlan() {
        return afternoonPlan;
    }

    public void setAfternoonPlan(String afternoonPlan) {
        this.afternoonPlan = afternoonPlan;
    }

    public String getEveningPlan() {
        return eveningPlan;
    }

    public void setEveningPlan(String eveningPlan) {
        this.eveningPlan = eveningPlan;
    }
}
