package com.fresh.company.fresh.View.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.R;

public class GoodsActivity extends FragmentActivity{

    private TextView mTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        Bundle extras = getIntent().getExtras();
        String str=extras.getString(CaptureActivity.RESULT);
        mTest=(TextView)findViewById(R.id.test);
        mTest.setText(str);
    }
}
