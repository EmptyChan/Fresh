package com.fresh.company.fresh.Component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.View.activity.MainActivity;
import com.fresh.company.fresh.View.fragment.ListAllGoodsFragment;

public class GoodsReceiver extends BroadcastReceiver {
    private Context ct=null;
    private static final String TAG="CJH";
    private GoodsReceiver mReceiver;
    public GoodsReceiver(Context ct) {
        this.ct=ct;
        mReceiver=this;
    }
    public GoodsReceiver(){

    }
    //注册
    public void registerAction(String action){
        IntentFilter filter=new IntentFilter();
        filter.addAction(action);
        ct.registerReceiver(mReceiver, filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Log.v(TAG,"In this!");
        GoodsInfo goodsInfo=null;
        if (context instanceof MainActivity){
            if (intent.hasExtra(ListAllGoodsFragment.ADD_GOODS)) {
                goodsInfo=intent.getParcelableExtra(ListAllGoodsFragment.ADD_GOODS);
                ((MainActivity) context).GoodsAdd(goodsInfo);
                Log.v(TAG,"add success!");
            }else if (intent.hasExtra(ListAllGoodsFragment.UPDATE_GOODS)){
                goodsInfo=intent.getParcelableExtra(ListAllGoodsFragment.UPDATE_GOODS);
                ((MainActivity) context).GoodsChange(goodsInfo);
                Log.v(TAG,"update success!");
            }
        }
    }
}
