package com.fresh.company.fresh.View.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dtr.zxing.activity.CaptureActivity;
import com.fresh.company.fresh.R;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

public class GoodsActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private TextView mTest;
    private ObservableListView mObservableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mObservableListView = (ObservableListView) findViewById(R.id.list_view);
        mObservableListView.setScrollViewCallbacks(this);
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
