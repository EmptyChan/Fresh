package com.fresh.company.fresh.View.activity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Presenter.GoodsPresenter;
import com.fresh.company.fresh.Presenter.IGoodsPresenter;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.IGoodsView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
//import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;


public class GoodsActivity extends AppCompatActivity implements IGoodsView,ObservableScrollViewCallbacks
{

    private TextView mTest;
    private Spinner mSpinner;
    private CircularProgressView mProgressView;
    private ObservableScrollView mObservableScrollView;
    private IGoodsPresenter mIGoodsPresenter;
    private ArrayAdapter<String> adapter;
    private String str;
    private ImageButton mConfirmBtn,mCancelBtn;

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
        mTest=(TextView)findViewById(R.id.test);
        mTest.setText(str);
        mSpinner=(Spinner)findViewById(R.id.goodsType);
        mSpinner.setAdapter(adapter);
        mProgressView = (CircularProgressView) findViewById(R.id.progress_view);

        mConfirmBtn=(ImageButton)findViewById(R.id.btn_confirm);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mCancelBtn=(ImageButton)findViewById(R.id.btn_cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
        adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bundle extras = getIntent().getExtras();
        str=extras.getString(CaptureActivity.RESULT);
        mIGoodsPresenter.WebRequest(str);
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
}
