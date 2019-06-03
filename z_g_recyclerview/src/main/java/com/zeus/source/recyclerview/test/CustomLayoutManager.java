package com.zeus.source.recyclerview.test;

import android.view.View;
import android.view.ViewGroup;

import com.zeus.source.recyclerview.origin.RecyclerView;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-28.
 * =======================================
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {

        //返回ItemView的默认大小
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    /**
     * 处理Item布局的问题
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        // 第一步：移除当前界面中的item，并添加到回收站中
        detachAndScrapAttachedViews(recycler);

        int xOffset = 0;
        int yOffset = 0;

        // 第二步：把所有的Item放在他们应该放的位置
        for (int i = 0; i < getItemCount(); i++) {

            View view = recycler.getViewForPosition(i); // 从回收站中取出View

            addView(view);

            measureChildWithMargins(view, 0, 0); // 计算View的大小

            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            // 将view放置在正确的位置 （这个位置会收到ItemDecoration的影响）
            layoutDecorated(view, xOffset, yOffset, xOffset + width, yOffset + height);

            if (i % 6 == 5) {
                xOffset = 0;
                yOffset += height;
            } else {
                xOffset += width;
            }

        }

    }


    @Override
    public boolean canScrollVertically() {
        // 控制能否上下滚动
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 在滚动的时候移动view
        offsetChildrenVertical(-dy);
        return dy;
    }
}
