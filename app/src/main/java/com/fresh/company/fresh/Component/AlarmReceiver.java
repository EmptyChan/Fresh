package com.fresh.company.fresh.Component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by CJH on 2016/9/18.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private Context ct=null;
    private AlarmReceiver mAlarmReceiver;
    private static final String TAG="CJH";

    public AlarmReceiver(Context ct) {
        this.ct = ct;
        mAlarmReceiver=this;
    }

    public AlarmReceiver() {
    }

    //注册
    public void registerAction(String action){
        IntentFilter filter=new IntentFilter();
        filter.addAction(action);
        ct.registerReceiver(mAlarmReceiver, filter);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        /* start another activity - MyAlarm to display the alarm */
//        Intent i=new Intent(context, MyAlarm.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.getStr
//        context.startActivity(i);
        Log.v(TAG,"in this!");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, MyAlarm.class);
        context.startActivity(intent);
    }
}
