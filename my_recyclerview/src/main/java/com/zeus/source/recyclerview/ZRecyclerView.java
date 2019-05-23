package com.zeus.source.recyclerview;

import android.content.Context;
import android.database.Observable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.ScrollingView;


/**
 * =======================================
 * Created by tangchunlin on 2019-05-23.
 * =======================================
 */
public class ZRecyclerView extends ViewGroup
        implements ScrollingView, NestedScrollingChild2 {

    private static final String TAG = "ZRecyclerView";

    public ZRecyclerView(Context context) {
        super(context);
    }

    public ZRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ZRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //######################ScrollingView overwrite begin ################
    @Override
    public int computeHorizontalScrollRange() {
        return 0;
    }

    @Override
    public int computeHorizontalScrollOffset() {
        return 0;
    }

    @Override
    public int computeHorizontalScrollExtent() {
        return 0;
    }

    @Override
    public int computeVerticalScrollRange() {
        return 0;
    }

    @Override
    public int computeVerticalScrollOffset() {
        return 0;
    }

    @Override
    public int computeVerticalScrollExtent() {
        return 0;
    }

    //######################ScrollingView overwrite end ################


    //************************NestedScrollingChild2 overwrite begin ************************
    @Override
    public boolean startNestedScroll(int axes, int type) {
        return false;
    }

    @Override
    public void stopNestedScroll(int type) {

    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return false;
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }
    //************************NestedScrollingChild2 overwrite end ************************


    Adapter mAdapter;
    @VisibleForTesting
    LayoutManager mLayout;

    private final RecyclerViewDataObserver mObserver = new RecyclerViewDataObserver();

    final Recycler mRecycler = new Recycler();

    public void setLayoutManager(@Nullable LayoutManager layout) {
        if (layout == mLayout) {
            return;
        }
        mLayout = layout;
        requestLayout();
    }

    public void setAdapter(@Nullable Adapter adapter) {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setLayoutFrozen(boolean frozen) {

    }

    private void setAdapterInternal(@Nullable Adapter adapter,
                                    boolean compatibleWithPrevious,
                                    boolean removeAndRecycleViews) {


        final Adapter oldAdapter = mAdapter;
        mAdapter = adapter;

        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
            adapter.onAttachedToRecyclerView(this);
        }

        if (mLayout != null) {
            mLayout.onAdapterChanged(oldAdapter, mAdapter);
        }

    }

    void processDataSetCompletelyChanged(boolean dispatchItemsChanged) {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        dispatchLayout();
    }

    void dispatchLayout() {
        if (mAdapter == null) {
            Log.e(TAG, "No adapter attached; skipping layout");
            // leave the state in START
            return;
        }
        if (mLayout == null) {
            Log.e(TAG, "No layout manager attached; skipping layout");
            // leave the state in START
            return;
        }
    }

    public abstract static class LayoutManager {
        /**
         * Called if the RecyclerView this LayoutManager is bound to has a different adapter set via
         * {@link ZRecyclerView#setAdapter(Adapter)} or
         * {@link ZRecyclerView#swapAdapter(Adapter, boolean)}. The LayoutManager may use this
         * opportunity to clear caches and configure state such that it can relayout appropriately
         * with the new data and potentially new view types.
         *
         * <p>The default implementation removes all currently attached views.</p>
         *
         * @param oldAdapter The previous adapter instance. Will be null if there was previously no
         *                   adapter.
         * @param newAdapter The new adapter instance. Might be null if
         *                   {@link #setAdapter(ZRecyclerView.Adapter)} is called with {@code null}.
         */
        public void onAdapterChanged(@Nullable Adapter oldAdapter, @Nullable Adapter newAdapter) {
        }
    }

    public abstract static class Adapter<VH extends ViewHolder> {
        private final AdapterDataObservable mObservable = new AdapterDataObservable();

        public void registerAdapterDataObserver(@NonNull AdapterDataObserver observer) {
            mObservable.registerObserver(observer);
        }

        /**
         * Called by RecyclerView when it starts observing this Adapter.
         * <p>
         * Keep in mind that same adapter may be observed by multiple RecyclerViews.
         *
         * @param recyclerView The RecyclerView instance which started observing this adapter.
         * @see #onDetachedFromRecyclerView(ZRecyclerView)
         */
        public void onAttachedToRecyclerView(@NonNull ZRecyclerView recyclerView) {
        }
    }

    public abstract static class ViewHolder {

    }


    static class AdapterDataObservable extends Observable<AdapterDataObserver> {

    }

    private class RecyclerViewDataObserver extends AdapterDataObserver {

    }

    /**
     * Observer base class for watching changes to an {@link Adapter}.
     * See {@link Adapter#registerAdapterDataObserver(AdapterDataObserver)}.
     */
    public abstract static class AdapterDataObserver {
        public void onChanged() {
            // Do nothing
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            // do nothing
        }

        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            // fallback to onItemRangeChanged(positionStart, itemCount) if app
            // does not override this method.
            onItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            // do nothing
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            // do nothing
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            // do nothing
        }
    }

    public final class Recycler {

    }
}
