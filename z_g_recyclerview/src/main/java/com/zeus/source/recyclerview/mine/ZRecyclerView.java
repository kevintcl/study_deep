package com.zeus.source.recyclerview.mine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;


/**
 * =======================================
 * Created by tangchunlin on 2019-05-29.
 * =======================================
 */
public class ZRecyclerView extends ViewGroup {

    private static final String TAG = "kevint";

    Adapter mAdapter;
    LayoutManager mLayout;

    final Recycler mRecycler = new Recycler();

    final State mState = new State();

    AdapterHelper mAdapterHelper;

    public ZRecyclerView(Context context) {
        this(context, null);
    }

    public ZRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAdapterManager();
    }

    void initAdapterManager() {
        mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {
        });
    }

    public void setLayoutManager(LayoutManager layout) {
        mLayout = layout;
        mLayout.setRecyclerView(this);
    }

    public void setAdapter(Adapter adapter) {
        setAdapterInternal(adapter, false, true);

        requestLayout();
    }

    private void setAdapterInternal(@Nullable Adapter adapter, boolean compatibleWithPrevious,
                                    boolean removeAndRecycleViews) {
        mAdapter = adapter;
    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {

        final int widthMode = MeasureSpec.getMode(widthSpec);
        final int heightMode = MeasureSpec.getMode(heightSpec);

        mLayout.onMeasure(mRecycler, mState, widthSpec, heightSpec);

        final boolean measureSpecModeIsExactly =
                widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY;
        RLog.i("onMeasure measureSpecModeIsExactly = " + measureSpecModeIsExactly);
        if (measureSpecModeIsExactly || mAdapter == null) {
            return;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        dispatchLayout();
    }

    void dispatchLayout() {
        if (mState.mLayoutStep == State.STEP_START) {
            // 1) 没有执行过布局流程的情况
            dispatchLayoutStep1();


            mLayout.setExactMeasureSpecsFrom(this);

            dispatchLayoutStep2();

        } else if (mAdapterHelper.hasUpdates() || mLayout.getWidth() != getWidth()
                || mLayout.getHeight() != getHeight()) {


            // 2) 执行过布局流程，但是之后size又有变化的情况
            // First 2 steps are done in onMeasure but looks like we have to run again due to
            // changed size.
            mLayout.setExactMeasureSpecsFrom(this);

            dispatchLayoutStep2();
        } else {
            // 3) 执行过布局流程，可以直接使用之前数据的情况
            // always make sure we sync them (to ensure mode is exact)
            mLayout.setExactMeasureSpecsFrom(this);
        }
        dispatchLayoutStep3();
    }


    private void dispatchLayoutStep1() {

    }

    private void dispatchLayoutStep2() {
        mLayout.onLayoutChildren(mRecycler, mState);
    }

    private void dispatchLayoutStep3() {

    }


    void defaultOnMeasure(int widthSpec, int heightSpec) {
        // calling LayoutManager here is not pretty but that API is already public and it is better
        // than creating another method since this is internal.
        final int width = LayoutManager.chooseSize(widthSpec,
                getPaddingLeft() + getPaddingRight(),
                ViewCompat.getMinimumWidth(this));

        final int height = LayoutManager.chooseSize(heightSpec,
                getPaddingTop() + getPaddingBottom(),
                ViewCompat.getMinimumHeight(this));

        setMeasuredDimension(width, height);
    }

    static class RLog {
        static final boolean DEBUG = true;

        static void i(String msg) {
            if (DEBUG) {
                Log.i(TAG, msg);
            }
        }

        static void e(String msg) {
            if (DEBUG) {
                Log.e(TAG, msg);
            }
        }
    }
}
