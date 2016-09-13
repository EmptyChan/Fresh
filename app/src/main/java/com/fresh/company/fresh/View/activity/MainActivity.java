package com.fresh.company.fresh.View.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.fragment.AboutMeFragment;
import com.fresh.company.fresh.View.fragment.DietPlanFragment;
import com.fresh.company.fresh.View.fragment.ListAllGoodsFragment;

import static com.fresh.company.fresh.R.layout.activity_main;

public class MainActivity extends BaseActivity {
    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private static Fragment mCurrentFragment,mFragmentContainer;
    private long exitTime = 0;
    //private Button mGoodsNavigate,mDietNavigate,mMeNavigate;
    private RadioButton mGoodsNavigate,mDietNavigate,mMeNavigate;
    private RadioGroup mRadioGroup;
    private Toolbar toolbar;

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static Fragment getCurrentFragment() {
        return mCurrentFragment;
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
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.index);

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
        super.onDestroy();
        mCurrentFragment.onDestroy();
        mCurrentFragment=null;
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
                        toolbar.setTitle(R.string.index);
                        break;
                    case R.id.dietNavigateBtn:
                        mFragmentTransaction.show(mFragments[1]).hide(mFragments[0]).hide(mFragments[2]).commit();
                        //SwitchContent(mCurrentFragment,mFragments[1]);
                        mCurrentFragment=mFragments[1];
                        toolbar.setTitle(R.string.plan);
                        break;
                    case R.id.meNavigateBtn:
                        mFragmentTransaction.show(mFragments[2]).hide(mFragments[1]).hide(mFragments[0]).commit();
                       // SwitchContent(mCurrentFragment,mFragments[2]);
                        mCurrentFragment=mFragments[2];
                        toolbar.setTitle(R.string.me);
                        break;
                }
            }
        });
    }
}
