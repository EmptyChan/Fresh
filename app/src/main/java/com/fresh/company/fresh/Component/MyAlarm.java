package com.fresh.company.fresh.Component;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.activity.BaseActivity;

/**
 * Created by CJH on 2016/9/18.
 */

public class MyAlarm extends BaseActivity {
    /**
     * An identifier for this notification unique within your application
     */
    public static final String CURRENT_GOODS="current_goods";

    private TextView text;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        final Bundle extras = getIntent().getExtras();
        String goods_name=extras.getString(CURRENT_GOODS);
        text=(TextView)findViewById(R.id.tvNotification);
        text.setText("产品:"+goods_name+"即将过期");
        cancelBtn=(Button)findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //unregisterReceiver(getIntent().);
                finish();
            }
        });
    }
}
