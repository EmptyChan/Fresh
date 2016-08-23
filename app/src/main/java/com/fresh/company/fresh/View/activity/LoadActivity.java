package com.fresh.company.fresh.View.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.fresh.company.fresh.R;

public class LoadActivity extends AppCompatActivity {
    private static final int LOAD_DISPLAY_TIME = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_load);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  //设置全屏
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        //保存输入的信息
                        bundle.putString(LoginActivity.USR,"CJH");
                        Intent mainIntent = new Intent(LoadActivity.this, LoginActivity.class);
                        mainIntent.putExtras(bundle);
                        LoadActivity.this.startActivity(mainIntent);
                        LoadActivity.this.finish();
                    }
                }, LOAD_DISPLAY_TIME
        );
    }
}
