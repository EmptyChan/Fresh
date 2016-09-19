package com.fresh.company.fresh.View.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.CommonUtil.TypeToMipmap;
import com.fresh.company.fresh.Component.DatePickerFragment;
import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Model.GoodsInfoFactory;
import com.fresh.company.fresh.Model.GoodsType;
import com.fresh.company.fresh.Presenter.GoodsPresenter;
import com.fresh.company.fresh.Presenter.IGoodsPresenter;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.IGoodsView;
import com.fresh.company.fresh.View.fragment.ListAllGoodsFragment;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.rey.material.app.DatePickerDialog;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.fresh.company.fresh.Component.DatePickerFragment.REQUEST_DATE;


public class GoodsActivity extends BaseActivity implements IGoodsView,ObservableScrollViewCallbacks,DatePickerFragment.GetDateFromFragment
{
    private TextView goodsBarcode;
    private Toolbar toolbar;
    private Spinner mSpinner;
    private CircularProgressView mProgressView;
    private ObservableScrollView mObservableScrollView;
    private IGoodsPresenter mIGoodsPresenter;
    private ArrayAdapter<String> adapter;
    private String str;
    private ImageButton mConfirmBtn,mCancelBtn;
    private ArrayList<String> mStrings;
    private TextView goodsProductionDate;
    private EditText goodsName,goodsManufacturer,goodsPrice,goodsDurabilityPeriod,goodsManualPeriod;
    private static final String INSERT_COMPLETE="添加成功";
    private static final String UPDATE_COMPLETE="更新成功";
    private ImageView goodsPcture;
    private GoodsInfo current=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mIGoodsPresenter=new GoodsPresenter(this,new GoodsInfoFactory(this));
        initView();
        Bundle extras = getIntent().getExtras();
        if(extras.containsKey(CaptureActivity.RESULT)) {
            str = extras.getString(CaptureActivity.RESULT);
            goodsBarcode.setText(str.trim());
        }else if(extras.containsKey(ListAllGoodsFragment.SEND_GOODS)){
            current=extras.getParcelable(ListAllGoodsFragment.SEND_GOODS);
            SetData(current);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.goods_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mObservableScrollView=(ObservableScrollView)findViewById(R.id.scrollView);
        mObservableScrollView.setScrollViewCallbacks(this);
        adapter=new ArrayAdapter<String>(this, R.layout.spinner_list_item,mStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        goodsBarcode = (TextView) findViewById(R.id.barcode);
        goodsName=(EditText)findViewById(R.id.goodsName);
        goodsManufacturer=(EditText)findViewById(R.id.goodsManufacturer);
        goodsPrice=(EditText)findViewById(R.id.goodsPrice);
        goodsPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if (!goodsPrice.getText().toString().contains("¥")){
                        String t="¥"+goodsPrice.getText();
                        goodsPrice.setText(t);
                    }
                }
            }
        });
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        goodsProductionDate=(TextView)findViewById(R.id.goodsProductionDate);
        if (goodsProductionDate.getText().toString().trim().equals("")){
            goodsProductionDate.setText(dateFormat.format(new Date()));
        }
        goodsProductionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date=dateFormat.parse(goodsProductionDate.getText().toString(),new ParsePosition(0));
                FragmentManager fm=GoodsActivity.this.getFragmentManager();
                DatePickerFragment df=DatePickerFragment.newInstance(date);
                df.show(fm,"");
            }
        });

        goodsDurabilityPeriod=(EditText)findViewById(R.id.goodsDurabilityPeriod);
        goodsManualPeriod=(EditText)findViewById(R.id.goodsManualPeriod);

        mSpinner=(Spinner)findViewById(R.id.goodsType);
        mSpinner.setAdapter(adapter);
        mProgressView = (CircularProgressView) findViewById(R.id.goods_progress_view);
        mProgressView.setVisibility(View.INVISIBLE);
        mConfirmBtn=(ImageButton)findViewById(R.id.btn_confirm);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoWork();
            }
        });
        mCancelBtn=(ImageButton)findViewById(R.id.btn_cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        goodsPcture=(ImageView)findViewById(R.id.goods_picture);
        goodsPcture.requestFocus();
        mIGoodsPresenter.WebRequest(str);
    }

    private void DoWork(){
        if (current==null) {
            if (mIGoodsPresenter.checkGoodsInfo(goodsBarcode.getText().toString())) {
                Intent i = mIGoodsPresenter.addGoodsInfo();
                sendBroadcast(i);
                Toast.makeText(this, INSERT_COMPLETE, Toast.LENGTH_SHORT).show();
            } else {
                Intent i = mIGoodsPresenter.updateGoodsInfo();
                sendBroadcast(i);
                Toast.makeText(this, UPDATE_COMPLETE, Toast.LENGTH_SHORT).show();
            }
        }else{
            if (!current.equals(mIGoodsPresenter.initGoodsInfo())){
                Intent i = mIGoodsPresenter.updateGoodsInfo();
                sendBroadcast(i);
                Toast.makeText(this, UPDATE_COMPLETE, Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void SetViewEnable(boolean b){

    }


    @Override
    public void ShowGoodsInfoStatus() {
        mProgressView.setVisibility(View.VISIBLE);
        mProgressView.startAnimation();
    }

    @Override
    public void HideGoodsInfoStatus() {
        mProgressView.stopAnimation();
        mProgressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void ShowGoodsInfoFailed() {

    }

    @Override
    public void ShowGoodsInfoSuccess() {

    }

    @Override
    public void ShowGoodInfo() {

    }

    @Override
    public void SetData(GoodsInfo goodsInfo) {
        goodsBarcode.setText(goodsInfo.getmBarcode());
        goodsName.setText(goodsInfo.getmGoodsName());
        mSpinner.setSelection(goodsInfo.getmGoodsType().ordinal());
        goodsManufacturer.setText(goodsInfo.getmManufacturer());
        goodsProductionDate.setText(goodsInfo.getmProductionDate());
        String tt="¥"+String.valueOf(goodsInfo.getmPrice());
        goodsPrice.setText(tt);
        goodsDurabilityPeriod.setText(goodsInfo.getmDurabilityPeriod());
        goodsManualPeriod.setText(goodsInfo.getmManualPeriod());
    }

    @Override
    public void SetDate(Date date) {

    }

    @Override
    public void SetData(int period) {

    }

    @Override
    public void Init(ArrayList<String> list) {
        mStrings=list;
    }

    @Override
    public void ClearAll() {

    }

    @Override
    public String getBarcode() {
        return goodsBarcode.getText().toString().trim();
    }

    @Override
    public String getGoodsName() {
        return goodsName.getText().toString().trim();
    }

    @Override
    public int getSelectedGoodsType() {
        return mSpinner.getSelectedItemPosition();
    }

    @Override
    public String getGoodsManufacturer() {
        return goodsManufacturer.getText().toString().trim();
    }

    @Override
    public String getGoodsProductionDate() {
        return goodsProductionDate.getText().toString().trim();
    }

    @Override
    public String getGoodsPrice() {
        return goodsPrice.getText().toString().trim();
    }

    @Override
    public String getGoodsDurabilityPeriod() {
        return goodsDurabilityPeriod.getText().toString().trim();
    }

    @Override
    public String getGoodsManualPeriod() {
        return goodsManualPeriod.getText().toString().trim();
    }


    /**
     * Called when the scroll change events occurred.
     * This won't be called just after the view is laid out, so if you'd like to
     * initialize the position of your views with this method, you should call this manually
     * or invoke scroll as appropriate.
     *
     * @param scrollY     scroll position in Y axis
     * @param firstScroll true when this is called for the first time in the consecutive motion events
     * @param dragging    true when the view is dragged and false when the view is scrolled in the inertia
     */
    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        //mObservableScrollView.setTranslationY(Math.max(0, scrollY));
    }

    /**
     * Called when the down motion event occurred.
     */
    @Override
    public void onDownMotionEvent() {

    }

    /**
     * Called when the dragging ended or canceled.
     *
     * @param scrollState state to indicate the scroll direction
     */
    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        mIGoodsPresenter.Dispose();
        super.onDestroy();
    }

    @Override
    public void GetDate(int requestCode, int resultCode,Intent date) {
        if( resultCode!= Activity.RESULT_OK) return;
        if(requestCode==REQUEST_DATE){
            Date da=(Date)date.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            goodsProductionDate.setText(dateFormat.format(da));
        }
    }
}
