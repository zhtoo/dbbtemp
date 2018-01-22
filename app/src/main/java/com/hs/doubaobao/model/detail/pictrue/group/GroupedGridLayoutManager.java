package com.hs.doubaobao.model.detail.pictrue.group;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * 为分组列表提供的GridLayoutManager。
 * 因为分组列表如果要使用GridLayoutManager实现网格布局。要保证组的头部和尾部是要单独占用一行的。
 * 否则组的头、尾可能会跟子项混着一起，造成布局混乱。
 */
public class GroupedGridLayoutManager extends GridLayoutManager {

    private GroupAdapter mAdapter;

    public GroupedGridLayoutManager(Context context, int spanCount,
                                    GroupAdapter adapter) {
        super(context, spanCount);
        mAdapter = adapter;
        setSpanSizeLookup();
    }

    public GroupedGridLayoutManager(Context context, int spanCount, int orientation,
                                    boolean reverseLayout, GroupAdapter adapter) {
        super(context, spanCount, orientation, reverseLayout);
        this.mAdapter = adapter;
        setSpanSizeLookup();
    }

    public GroupedGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                                    int defStyleRes, GroupAdapter adapter) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mAdapter = adapter;
        setSpanSizeLookup();
    }

    /**
     * 维护这个项目的大兄弟（大妹子），已经解决一个坑。
     * java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position 5(offset:5).state:10
     * at android.support.v7.widget.RecyclerView$Recycler.tryGetViewHolderForPositionByDeadline(RecyclerView.java:5504)
     * 曲线救国，这可能出现问题，但是为了保证不崩溃我catch了0.0！！！
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {//try catch一下
            super.onLayoutChildren( recycler, state );
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    private void setSpanSizeLookup() {
        super.setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int count = getSpanCount();
                if (mAdapter != null) {
                    int type = mAdapter.getItemViewType(position);
                    //只对子项做Grid效果
                    if (type == GroupAdapter.ITEM_TYPE_CONTENT) {
                        int groupPosition = mAdapter.getGroupPositionForPosition(position);
                        int childPosition =
                                mAdapter.getChildPositionForPosition(groupPosition, position);
                        return getChildSpanSize(groupPosition, childPosition);
                    }
                }

                return count;
            }
        });
    }

    /**
     * 提供这个方法可以使外部改变子项的SpanSize。
     * 这个方法的作用跟{@link SpanSizeLookup#getSpanSize(int)}一样。
     * @param groupPosition
     * @param childPosition
     * @return
     */
    public int getChildSpanSize(int groupPosition, int childPosition) {
        return 1;
    }

    @Override
    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {

    }
}