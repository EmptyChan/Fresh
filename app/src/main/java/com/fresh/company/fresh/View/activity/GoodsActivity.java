package com.fresh.company.fresh.View.activity;

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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.CommonUtil.TypeToMipmap;
import com.fresh.company.fresh.Model.GoodsInfo;
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
//import com.rey.material.widget.Spinner;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GoodsActivity extends BaseActivity implements IGoodsView,ObservableScrollViewCallbacks
{

    private TextView mTest;
    private Spinner mSpinner;
    private CircularProgressView mProgressView;
    private ObservableScrollView mObservableScrollView;
    private IGoodsPresenter mIGoodsPresenter;
    private ArrayAdapter<String> adapter;
    private String str;
    private ImageButton mConfirmBtn,mCancelBtn;
    private ArrayList<String> mStrings;
    private EditText goodsName,goodsManufacturer,goodsPrice,goodsProductionDate,goodsDurabilityPeriod,goodsManualPeriod;
    private static final String INSERT_COMPLETE="添加成功";
    private static final String UPDATE_COMPLETE="更新成功";

    public GoodsActivity() {
        mIGoodsPresenter=new GoodsPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mObservableScrollView=(ObservableScrollView)findViewById(R.id.scrollView);
        mObservableScrollView.setScrollViewCallbacks(this);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        adapter=new ArrayAdapter<String>(this, R.layout.spinner_list_item,mStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bundle extras = getIntent().getExtras();
        if(extras.containsKey(CaptureActivity.RESULT)) {
            str = extras.getString(CaptureActivity.RESULT);
            mTest = (TextView) findViewById(R.id.barcode);
            mTest.setText(str);
        }else if(extras.containsKey(ListAllGoodsFragment.CURRENT_GOODS)){

        }
        initView();
        mSpinner=(Spinner)findViewById(R.id.goodsType);
        mSpinner.setAdapter(adapter);
        mProgressView = (CircularProgressView) findViewById(R.id.goods_progress_view);
        mProgressView.setVisibility(View.INVISIBLE);
        mConfirmBtn=(ImageButton)findViewById(R.id.btn_confirm);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsInfo t=new GoodsInfo();
                t.setmBarcode(str.trim());
                t.setmGoodsName(goodsName.getText().toString());
                t.setmGoodsType(GoodsType.values()[mSpinner.getSelectedItemPosition()]);
                t.setmManufacturer(goodsManufacturer.getText().toString());
                t.setmProductionDate(goodsProductionDate.getText().toString().trim());
                if (goodsPrice.getText().toString().contains("¥"))
                    t.setmPrice(Double.parseDouble(goodsPrice.getText().toString().split("¥")[1]));
                else
                    t.setmPrice(Double.parseDouble(goodsPrice.getText().toString()));
                t.setmPicturePath(String.valueOf(TypeToMipmap.Type2Mipmap(GoodsType.values()[mSpinner.getSelectedItemPosition()])));
                t.setmDurabilityPeriod(goodsDurabilityPeriod.getText().toString());
                t.setmManualPeriod(goodsManualPeriod.getText().toString());
                if (mDBManager.queryGoodsInfo(str)==null) {
                    mDBManager.addGoodsInfo(t);
                    ((ICallBackToMain)MainActivity.getCurrentFragment()).GoodsAdd(t);
                    Toast.makeText(view.getContext(),INSERT_COMPLETE, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(view.getContext(),UPDATE_COMPLETE, Toast.LENGTH_SHORT).show();
                    mDBManager.updateGoodsInfo(t);
                    ((ICallBackToMain)MainActivity.getCurrentFragment()).GoodsChange(t);
                }
                finish();
            }
        });
        mCancelBtn=(ImageButton)findViewById(R.id.btn_cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mSpinner.requestFocus();
        mIGoodsPresenter.WebRequest(str);
    }

    private void initView() {
        goodsName=(EditText)findViewById(R.id.goodsName);
        goodsManufacturer=(EditText)findViewById(R.id.goodsManufacturer);
        goodsPrice=(EditText)findViewById(R.id.goodsPrice);
        goodsProductionDate=(EditText)findViewById(R.id.goodsProductionDate);
        goodsDurabilityPeriod=(EditText)findViewById(R.id.goodsDurabilityPeriod);
        goodsManualPeriod=(EditText)findViewById(R.id.goodsManualPeriod);
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

    public interface ICallBackToMain{
        void GoodsAdd(GoodsInfo goodsInfo);
        void GoodsChange(GoodsInfo goodsInfo);
}
}
