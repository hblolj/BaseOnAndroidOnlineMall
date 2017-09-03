package com.app.baseonandroidonlinemall.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by txj on 15/4/14.
 */
public class MyScrollView extends ScrollView {

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    private Context mContext;
    private View mSplitViewStart, mSplitViewEnd;
    private GestureDetector mGestureDetector;
    private boolean mShowingGraphicInfo;
    private boolean mIsFling;

    public MyScrollView(Context context) {
        super(context);
        initMyScrollView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyScrollView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initMyScrollView(context);
    }

    private void initMyScrollView(Context context) {
        mContext = context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mShowingGraphicInfo) {
            return false;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
        mIsFling = true;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (!mShowingGraphicInfo && mIsFling) {
            if (Math.abs(t - oldt) < 2 || t >= getMeasuredHeight() || t == 0) {
                mIsFling = false;
                int scrollSplitViewStartY = Math.max(0, mSplitViewStart.getTop() - getHeight());
                if (t > scrollSplitViewStartY) {
                    smoothScrollTo(0, scrollSplitViewStartY + mSplitViewStart.getHeight());
                }
            }
        }
    }

    public void setSplitView(View start, View end) {
        mSplitViewStart = start;
        mSplitViewEnd = end;
        mShowingGraphicInfo = false;
        mGestureDetector = new GestureDetector(mContext, new MyGestureDetector());

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mGestureDetector.onTouchEvent(event)) {
                    return true;
                } else if (mShowingGraphicInfo) {
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    int scrollY = getScrollY();
                    int scrollSplitViewStartY = Math.max(0, mSplitViewStart.getTop() - getHeight());
                    int scrollSplitViewEndY = Math.max(0, mSplitViewEnd.getTop() - getHeight());
                    if (scrollY > scrollSplitViewStartY && scrollY <= scrollSplitViewEndY) {
                        smoothScrollTo(0, scrollSplitViewStartY + mSplitViewStart.getHeight());
                    } else if (scrollY > scrollSplitViewEndY) {
                        smoothScrollTo(0, scrollSplitViewEndY + getHeight());
                        mShowingGraphicInfo = true;
                    } else {
                        return false;
                    }

                    return true;
                }

                return false;
            }
        });
    }

    public void scrollToSplitStart() {
        int scrollSplitViewStartY = Math.max(0, mSplitViewStart.getTop() - getHeight());
        smoothScrollTo(0, scrollSplitViewStartY + mSplitViewStart.getHeight());
        mShowingGraphicInfo = false;
    }

    public void scrollToSplitEnd() {
        int scrollSplitViewEndY = Math.max(0, mSplitViewEnd.getTop() - getHeight());
        if (getY() < scrollSplitViewEndY) {
            smoothScrollTo(0, scrollSplitViewEndY + getHeight());
        }
    }

    public void scrollToTop() {
        smoothScrollTo(0, 0);
        mShowingGraphicInfo = false;
    }
}
