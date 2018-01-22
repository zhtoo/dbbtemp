package com.hs.doubaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.bean.ListBean;
import com.hs.doubaobao.view.MarqueeTextView;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/20 09:15
 * 邮箱：820159571@qq.com
 *
 * @describe:首页的适配器(写多个重复的adapter是为了避免点击冲突，这种情况待优化)
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private final Context context;
    private List<ListBean> mList;
    private int type = 0;

    public HomeAdapter(Context context, List<ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public HomeAdapter(Context context, List<ListBean> mList, int type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mian, parent, false);
        return new MyListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyListViewHolder viewHolder = (MyListViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout item;
        //客户姓名
        public TextView name;
        //贷款时间
        public TextView time;
        //贷款用途
        public TextView purpose;
        //贷款金额
        public TextView loadAmount;
        //放款金额
        public TextView loanAmount;
        //客户电话
        public TextView customPhone;
        //贷款期数
        public TextView loanPeriods;
        //实批期数
        public MarqueeTextView mRealNumberPeriods;
        //门店名称
        public TextView mStoreName;
        //审批状态
        public TextView status;
        //预览箭头
        public ImageView mainArrow;


        public MyListViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.main_item_all);

            name = (TextView) itemView.findViewById(R.id.main_list_name);
            time = (TextView) itemView.findViewById(R.id.main_list_time);
            purpose = (TextView) itemView.findViewById(R.id.main_list_purpose);
            loadAmount = (TextView) itemView.findViewById(R.id.main_list_load_amount);
            loanAmount = (TextView) itemView.findViewById(R.id.main_list_loan_amount);
            customPhone = (TextView) itemView.findViewById(R.id.main_list_custom_phone);
            loanPeriods = (TextView) itemView.findViewById(R.id.main_list_loan_periods);
            mRealNumberPeriods = (MarqueeTextView) itemView.findViewById(R.id.main_list_real_number_periods);
            mStoreName = (TextView) itemView.findViewById(R.id.main_list_store_name);
            status = (TextView) itemView.findViewById(R.id.main_list_status);
            mainArrow = (ImageView) itemView.findViewById(R.id.main_list_arrow);

        }

        public void setData(final int position) {
            ListBean listBean = mList.get(position);

            name.setText(checkEmpty(listBean.getName()));
            time.setText(checkEmpty(listBean.getTime()));
            purpose.setText(checkEmpty(listBean.getPurpose()));
            loadAmount.setText(checkNumberEmpty(listBean.getLoanAmount(), "万元"));
            loanAmount.setText(checkNumberEmpty(listBean.getLoanRation(), "万元"));
            customPhone.setText(checkEmpty(listBean.getCustomPhone()));
            loanPeriods.setText(checkEmpty(listBean.getLoanPeriods()));
            mRealNumberPeriods.setText(checkEmpty(listBean.getActPeriod()));
            mStoreName.setText(checkEmpty(listBean.getOfficeName()));
            status.setText(checkEmpty(listBean.getStatus()));


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(/**/View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public static onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(int postion);
    }


    private String checkEmpty(String check) {
        return TextUtils.isEmpty(check) ? "未填写":check;
    }

    private String checkNumberEmpty(double check, String unit) {
        return check != 0 ? (check + unit) : "未填写";
    }

}