package com.fresh.company.fresh.Component;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fresh.company.fresh.R;

import java.util.ArrayList;

/**
 * Created by CJH on 2016/9/7.
 */

public class ExpandableLayout extends RelativeLayout {
    private Boolean isAnimationRunning = false;
    private Boolean isOpened = false;
    private Integer duration;
    private FrameLayout morning_header,afternoon_header,evening_header;
    private FrameLayout morning_content,afternoon_content,evening_content;
    private RelativeLayout hintLayout;
    private Animation animation;
    private static int HEADER_HEIGHT;
    private static int CONTENT_HEIGHT;

    public ExpandableLayout(Context context)
    {
        super(context);
    }

    public ExpandableLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandableLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }
    public void setHeader(ArrayList<String> texts){
        TextView m=(TextView)morning_header.findViewById(R.id.header_text);
        m.setText(texts.get(0));
        TextView a=(TextView)afternoon_header.findViewById(R.id.header_text);
        a.setText(texts.get(1));
        TextView e=(TextView)evening_header.findViewById(R.id.header_text);
        e.setText(texts.get(2));
    }
    public void setHeaderHeight(int height){
        HEADER_HEIGHT=height;
        morning_header.getLayoutParams().height=HEADER_HEIGHT;
        morning_header.requestLayout();
        afternoon_header.getLayoutParams().height=HEADER_HEIGHT;
        afternoon_header.requestLayout();
        evening_header.getLayoutParams().height=HEADER_HEIGHT;
        evening_header.requestLayout();
//        TextView m=(TextView)morning_header.findViewById(R.id.header_text);
//        m.setMinHeight(HEADER_HEIGHT);
//        TextView a=(TextView)afternoon_header.findViewById(R.id.header_text);
//        a.setMinHeight(HEADER_HEIGHT);
//        TextView e=(TextView)evening_header.findViewById(R.id.header_text);
//        e.setMinHeight(HEADER_HEIGHT);
    }
    public void setContentHeight(int height){
        CONTENT_HEIGHT=height;
        morning_content.getLayoutParams().height=CONTENT_HEIGHT;
        morning_content.requestLayout();
        afternoon_content.getLayoutParams().height=CONTENT_HEIGHT;
        afternoon_content.requestLayout();
        evening_content.getLayoutParams().height=CONTENT_HEIGHT;
        evening_content.requestLayout();
//        EditText m=(EditText)morning_content.findViewById(R.id.content);
//        m.setMinHeight(CONTENT_HEIGHT);
//        EditText a=(EditText)morning_content.findViewById(R.id.content);
//        a.setMinHeight(CONTENT_HEIGHT);
//        EditText e=(EditText)morning_content.findViewById(R.id.content);
//        e.setMinHeight(CONTENT_HEIGHT);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    private void init(final Context context, AttributeSet attrs)
    {
        final View rootView = View.inflate(context, R.layout.view_expandable, this);
       //int height_three=LayoutParams.MATCH_PARENT/3;
        morning_header = (FrameLayout) rootView.findViewById(R.id.morning_header);
        afternoon_header = (FrameLayout) rootView.findViewById(R.id.afternoon_header);
        evening_header = (FrameLayout) rootView.findViewById(R.id.evening_header);
        hintLayout=(RelativeLayout)rootView.findViewById(R.id.diet_plan_hint);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLayout);
        final int  headerID = typedArray.getResourceId(R.styleable.ExpandableLayout_el_headerLayout, -1);
        final int contentID = typedArray.getResourceId(R.styleable.ExpandableLayout_el_contentLayout, -1);
        morning_content = (FrameLayout) rootView.findViewById(R.id.morning_content);
        afternoon_content = (FrameLayout) rootView.findViewById(R.id.afternoon_content);
        evening_content = (FrameLayout) rootView.findViewById(R.id.evening_content);

        if (headerID == -1 || contentID == -1)
            throw new IllegalArgumentException("HeaderLayout and ContentLayout cannot be null!");

        if (isInEditMode())
            return;

        duration = typedArray.getInt(R.styleable.ExpandableLayout_el_duration, getContext().getResources().getInteger(android.R.integer.config_shortAnimTime));
        final View headerView1 = View.inflate(context, headerID, null);
        final View headerView2 = View.inflate(context, headerID, null);
        final View headerView3 = View.inflate(context, headerID, null);
        headerView1.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        headerView2.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        headerView3.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        morning_header.addView(headerView1);
        afternoon_header.addView(headerView2);
        evening_header.addView(headerView3);

        final View contentView1 = View.inflate(context, contentID, null);
        final View contentView2 = View.inflate(context, contentID, null);
        final View contentView3 = View.inflate(context, contentID, null);
        contentView1.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        contentView2.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        contentView3.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        morning_content.addView(contentView1);
        afternoon_content.addView(contentView2);
        evening_content.addView(contentView3);
        morning_content.setVisibility(GONE);
        afternoon_content.setVisibility(GONE);
        evening_content.setVisibility(GONE);
        morning_header.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!isAnimationRunning) {
                    if (morning_content.getVisibility() == VISIBLE) {
                        collapse(morning_content);
                        expand(afternoon_header, HEADER_HEIGHT);
                        expand(evening_header, HEADER_HEIGHT);
                        hintLayout.setVisibility(VISIBLE);
                    } else {
                        collapse(evening_header);
                        collapse(afternoon_header);
                        expand(morning_content, CONTENT_HEIGHT);
                        hintLayout.setVisibility(GONE);
                    }
                    isAnimationRunning = true;
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            isAnimationRunning = false;
                        }
                    }, duration);
                }
            }
        });

        afternoon_header.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!isAnimationRunning) {
                    if (afternoon_content.getVisibility() == VISIBLE) {
                        collapse(afternoon_content);
                        expand(morning_header,HEADER_HEIGHT);
                        expand(evening_header,HEADER_HEIGHT);
                        hintLayout.setVisibility(VISIBLE);
                    }
                    else {
                        collapse(morning_header);
                        collapse(evening_header);
                        expand(afternoon_content, CONTENT_HEIGHT);
                        hintLayout.setVisibility(GONE);
                    }
                    isAnimationRunning = true;
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            isAnimationRunning = false;
                        }
                    }, 2*duration);
                }

            }
        });
        evening_header.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!isAnimationRunning) {
                    if (evening_content.getVisibility() == VISIBLE) {
                        collapse(evening_content);
                        expand(morning_header, HEADER_HEIGHT);
                        expand(afternoon_header, HEADER_HEIGHT);
                        hintLayout.setVisibility(VISIBLE);
                    } else {
                        collapse(afternoon_header);
                        collapse(morning_header);
                        expand(evening_content, CONTENT_HEIGHT);
                        hintLayout.setVisibility(GONE);
                    }
                    isAnimationRunning = true;
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            isAnimationRunning = false;
                        }
                    }, 3*duration);
                }
            }
        });
        typedArray.recycle();
    }

    private void expand(final View v,final int height)
    {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        final int targetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(VISIBLE);

        animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t)
            {
                if (interpolatedTime == 1)
                    isOpened = true;
                v.getLayoutParams().height = (interpolatedTime == 1) ? height : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }


            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    private void collapse(final View v)
    {
        final int initialHeight = v.getMeasuredHeight();
        animation = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1)
                {
                    v.setVisibility(View.GONE);
                    isOpened = false;
                }
                else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration(duration);
        v.startAnimation(animation);
    }

    public Boolean isOpened()
    {
        return isOpened;
    }

    public void show(final View v,final int height)
    {
        if (!isAnimationRunning)
        {
            expand(v,height);
            isAnimationRunning = true;
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    isAnimationRunning = false;
                }
            }, duration);
        }
    }

//    public FrameLayout getHeaderLayout()
//    {
//        return headerLayout;
//    }

//    public FrameLayout getContentLayout()
//    {
//        return contentLayout;
//    }

    public void hide(final View v)
    {
        if (!isAnimationRunning)
        {
            collapse(v);
            isAnimationRunning = true;
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    isAnimationRunning = false;
                }
            }, duration);
        }
    }

    @Override
    public void setLayoutAnimationListener(Animation.AnimationListener animationListener) {
        animation.setAnimationListener(animationListener);
    }
}
