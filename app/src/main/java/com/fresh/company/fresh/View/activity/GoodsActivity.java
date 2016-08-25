package com.fresh.company.fresh.View.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.Model.GoodsInfo;
import com.fresh.company.fresh.Presenter.GoodsPresenter;
import com.fresh.company.fresh.R;
import com.fresh.company.fresh.View.IGoodsView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.Date;


public class GoodsActivity extends AppCompatActivity implements IGoodsView,ObservableScrollViewCallbacks
{ //implements ObservableScrollViewCallbacks,IGoodsView{

    private TextView mTest;
    private ObservableScrollView mObservableScrollView;
    private GoodsPresenter mGoodsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mObservableScrollView=(ObservableScrollView)findViewById(R.id.scrollView);
        mObservableScrollView.setScrollViewCallbacks(this);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        Bundle extras = getIntent().getExtras();
        String str=extras.getString(CaptureActivity.RESULT);
        mTest=(TextView)findViewById(R.id.test);
        mTest.setText(str);
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
//    @Override
//    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
//
//    }
//
//    /**
//     * Called when the down motion event occurred.
//     */
//    @Override
//    public void onDownMotionEvent() {
//
//    }
//
//    /**
//     * Called when the dragging ended or canceled.
//     *
//     * @param scrollState state to indicate the scroll direction
//     */
//    @Override
//    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
//        ActionBar ab = getSupportActionBar();
//        if (scrollState == ScrollState.UP) {
//            if (ab.isShowing()) {
//                ab.hide();
//            }
//        } else if (scrollState == ScrollState.DOWN) {
//            if (!ab.isShowing()) {
//                ab.show();
//            }
//        }
//    }


    @Override
    public void ShowGoodInfoStatus() {

    }

    @Override
    public void HideGoodInfoStatus() {

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
