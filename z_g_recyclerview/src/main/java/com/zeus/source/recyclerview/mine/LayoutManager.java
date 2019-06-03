package com.zeus.source.recyclerview.mine;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

public abstract class LayoutManager {

    private ZRecyclerView mRecyclerView;

    private int mWidthMode, mHeightMode;
    private int mWidth, mHeight;

    /**
     * On M+, an unspecified measure spec may include a hint which we can use. On older platforms,
     * this value might be garbage. To save LayoutManagers from it, RecyclerView sets the size to
     * 0 when mode is unspecified.
     */
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = Build.VERSION.SDK_INT >= 23;


    public abstract void onLayoutChildren(Recycler recycler, State state);

    public void onMeasure(@NonNull Recycler recycler, @NonNull State state, int widthSpec,
                          int heightSpec) {
        mRecyclerView.defaultOnMeasure(widthSpec, heightSpec);
    }

    public void setRecyclerView(ZRecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public static int chooseSize(int spec, int desired, int min) {
        final int mode = View.MeasureSpec.getMode(spec);
        final int size = View.MeasureSpec.getSize(spec);
        switch (mode) {
            case View.MeasureSpec.EXACTLY:
                return size;
            case View.MeasureSpec.AT_MOST:
                return Math.min(size, Math.max(desired, min));
            case View.MeasureSpec.UNSPECIFIED:
            default:
                return Math.max(desired, min);
        }
    }

    public void setExactMeasureSpecsFrom(ZRecyclerView recyclerView) {
        setMeasureSpecs(
                View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), View.MeasureSpec.EXACTLY)
        );
    }

    void setMeasureSpecs(int wSpec, int hSpec) {
        mWidth = View.MeasureSpec.getSize(wSpec);
        mWidthMode = View.MeasureSpec.getMode(wSpec);
        if (mWidthMode == View.MeasureSpec.UNSPECIFIED && !ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            mWidth = 0;
        }

        mHeight = View.MeasureSpec.getSize(hSpec);
        mHeightMode = View.MeasureSpec.getMode(hSpec);
        if (mHeightMode == View.MeasureSpec.UNSPECIFIED && !ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            mHeight = 0;
        }
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }
}