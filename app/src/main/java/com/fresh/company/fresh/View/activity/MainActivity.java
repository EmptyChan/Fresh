package com.fresh.company.fresh.View.activity;


import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.fresh.company.fresh.CommonUtil.OnSwithPageListener;
import com.fresh.company.fresh.Component.AlarmReceiver;
import com.fresh.company.fresh.Component.GoodsReceiver;
import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.fragment.AboutMeFragment;
import com.fresh.company.fresh.View.fragment.DietPlanFragment;
import com.fresh.company.fresh.View.fragment.ListAllGoodsFragment;

import java.util.Calendar;

import static com.fresh.company.fresh.Component.MyAlarm.CURRENT_GOODS;
import static com.fresh.company.fresh.R.layout.activity_main;
import static com.fresh.company.fresh.View.fragment.ListAllGoodsFragment.SEND_NOTIFY;

public class MainActivity extends BaseActivity{
    public static final String GOODS_SEND="com.fresh.sendGoods";
    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mCurrentFragment,mFragmentContainer;
    private long exitTime = 0;
    //private Button mGoodsNavigate,mDietNavigate,mMeNavigate;
    private RadioButton mGoodsNavigate,mDietNavigate,mMeNavigate;
    private RadioGroup mRadioGroup;
    private Toolbar toolbar;
    private TextView title;
    private GoodsReceiver mReceiver;
    private static final String TAG="CJH";


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public void NotifyToFragement(GoodsInfo goodsInfo){
        if (mCurrentFragment instanceof ListAllGoodsFragment){
            ((ListAllGoodsFragment) mCurrentFragment).getIAllGoodsPresenter().DeleteGoodsInfo(goodsInfo.getmBarcode());
        }
    }
    public void NotifyToFragement(String goods_name){
        if (mCurrentFragment instanceof ListAllGoodsFragment){
            // get the AlarmManager instance
            AlarmManager am= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent i=new Intent(SEND_NOTIFY);
            i.putExtra(CURRENT_GOODS,goods_name);
            // create a PendingIntent that will perform a broadcast
            PendingIntent pi= PendingIntent.getBroadcast(this, 0,i , 0);
            am.cancel(pi);
//            c.set(Calendar.HOUR, 10);        //设置闹钟小时数
//            c.set(Calendar.MINUTE, 33);
//            int a=c.get(Calendar.YEAR);
//            int b=c.get(Calendar.MONTH);
//            int e=c.get(Calendar.DAY_OF_MONTH);
//            long x=System.currentTimeMillis();

            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        }
    }

    private void createFragment(){
        mFragments[0]= ListAllGoodsFragment.newInstance("","","CJH");
        mFragments[1]= DietPlanFragment.newInstance("","");
        mFragments[2]= AboutMeFragment.newInstance("","");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title=(TextView)findViewById(R.id.toolbar_tv);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        title.setText(R.string.index);

        mFragmentManager=getFragmentManager();
        mFragments=new Fragment[3];
        createFragment();
       // mFragmentContainer=mFragmentManager.findFragmentById(R.id.fragmentContainer);
//        mFragments[0]=mFragmentManager.findFragmentById(R.id.goodsFragmentNavigate);
//        mFragments[1]=mFragmentManager.findFragmentById(R.id.dietFragmentNavigate);
//        mFragments[2]=mFragmentManager.findFragmentById(R.id.meFragmentNavigate);
        mFragmentTransaction=mFragmentManager.beginTransaction();
        //mFragmentTransaction.show(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).commit();
        mFragmentTransaction.add(R.id.fragmentContainer,mFragments[2])
                .add(R.id.fragmentContainer,mFragments[1])
                .add(R.id.fragmentContainer,mFragments[0]).commit();
        mCurrentFragment=mFragments[0];
        setFragmentIndicator();
        mReceiver=new GoodsReceiver(this);
        mReceiver.registerAction(GOODS_SEND);
    }
//    private void SwitchContent(Fragment from,Fragment to) {
//        if (mCurrentFragment!=to) {
//            mCurrentFragment=to;
//            if (!to.isAdded()) {
//                mFragmentTransaction.hide(from).add(R.id.mainFrameLayout, to);
//                mFragmentTransaction.addToBackStack(null);
//            }else{
//                mFragmentTransaction.hide(from).show(to);
//            }
//            //记录commit一次操作，与mFragmentManager.popBackStack();匹配使用
//            //commitAllowingStateLoss 优于 commit 方法,当使用commit方法时，系统将进行状态判断，如果状态（mStateSaved）已经保存，将发生"Can not perform this action after onSaveInstanceState"错误。
//            //如果mNoTransactionsBecause已经存在，将发生"Can not perform this action inside of " + mNoTransactionsBecause错误。
//            mFragmentTransaction.commitAllowingStateLoss();
//        }
//    }


//    @Override
//    protected void onStart() {
//
//        Log.v(TAG,"onResume");
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//
//        Log.v(TAG,"onPause");
//        super.onStop();
//    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        if (mCurrentFragment.equals(mFragments[0])) {
            //super.onBackPressed();
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }else{
            //back to first fragment
            mGoodsNavigate.setChecked(true);
            mFragmentTransaction=mFragmentManager.beginTransaction();//.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            mFragmentTransaction.show(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).commit();
            mCurrentFragment=mFragments[0];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //shield Menu key
        if (keyCode==KeyEvent.KEYCODE_MENU)
        {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        mCurrentFragment.onDestroy();
        mCurrentFragment=null;
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setFragmentIndicator() {
        mRadioGroup=(RadioGroup)findViewById(R.id.navigateRg);
        mGoodsNavigate=(RadioButton)findViewById(R.id.goodsNavigateBtn);
        mDietNavigate=(RadioButton)findViewById(R.id.dietNavigateBtn);
        mMeNavigate=(RadioButton)findViewById(R.id.meNavigateBtn);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mFragmentTransaction=mFragmentManager.beginTransaction();//.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                switch (i){
                    case R.id.goodsNavigateBtn:
                        mFragmentTransaction.show(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).commit();
                       // SwitchContent(mCurrentFragment,mFragments[0]);
                        mCurrentFragment=mFragments[0];
                        //toolbar.setTitle(R.string.index);
                        title.setText(R.string.index);
                        break;
                    case R.id.dietNavigateBtn:
                        mFragmentTransaction.show(mFragments[1]).hide(mFragments[0]).hide(mFragments[2]).commit();
                        //SwitchContent(mCurrentFragment,mFragments[1]);
                        mCurrentFragment=mFragments[1];
                        ((OnSwithPageListener)mCurrentFragment).onSwithPage();
                        //toolbar.setTitle(R.string.plan);
                        title.setText(R.string.plan);
                        break;
                    case R.id.meNavigateBtn:
                        mFragmentTransaction.show(mFragments[2]).hide(mFragments[1]).hide(mFragments[0]).commit();
                       // SwitchContent(mCurrentFragment,mFragments[2]);
                        mCurrentFragment=mFragments[2];
                        //toolbar.setTitle(R.string.me);
                        title.setText(R.string.me);
                        break;
                }
            }
        });
    }


    public void GoodsAdd(GoodsInfo goodsInfo) {
        if (mCurrentFragment instanceof ListAllGoodsFragment){
            ((ListAllGoodsFragment) mCurrentFragment).getIAllGoodsPresenter().GoodsInfoAdd(goodsInfo);
            Log.v(TAG,"add in Main");
        }
    }


    public void GoodsChange(GoodsInfo goodsInfo) {
        if (mCurrentFragment instanceof ListAllGoodsFragment){
            ((ListAllGoodsFragment) mCurrentFragment).getIAllGoodsPresenter().GoodsInfoChange(goodsInfo);
            Log.v(TAG,"change in Main");
        }
    }
}
