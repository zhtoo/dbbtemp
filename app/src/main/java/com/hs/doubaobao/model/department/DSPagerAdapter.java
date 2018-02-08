package com.hs.doubaobao.model.department;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.view.main.HstogramView;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/11/9 13:52
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class DSPagerAdapter extends PagerAdapter {


    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<DSViewPagerDataBean> list;


    public DSPagerAdapter(Context context, List<DSViewPagerDataBean> list) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.item_ds_viewpager, container, false);
        TextView title = (TextView) view.findViewById(R.id.pager_title);
        TextView titley = (TextView) view.findViewById(R.id.pager_title_y);
        TextView refreshDate = (TextView) view.findViewById(R.id.pager_refreah_date);
        TextView refreshNuber = (TextView) view.findViewById(R.id.pager_refreah_number);
        HstogramView hstogramView = (HstogramView) view.findViewById(R.id.main_hstogram);

        title.setText(list.get(position).getTitle());
        titley.setText(list.get(position).getTitleY());
        refreshDate.setText(list.get(position).getRefreshDate());
        refreshNuber.setText(list.get(position).getRefreshNumber());
        hstogramView.setDate(list.get(position).getDate());
        hstogramView.setValues(list.get(position).getValues());

        if(position == 0){
            title.setBackgroundColor(Color.argb(255, 23, 140, 219));
            hstogramView.setColors(new int[]{Color.argb(255, 23, 163, 219), Color.argb(255, 23, 140, 219)});
        }

        if(position == 1){
            title.setBackgroundColor(Color.argb(255, 239, 128, 0));
            hstogramView.setColors(new int[]{Color.argb(255, 242, 169, 100), Color.argb(255, 239, 128, 0)});
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
