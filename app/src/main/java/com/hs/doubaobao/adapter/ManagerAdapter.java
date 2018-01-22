package com.hs.doubaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.bean.ListBean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/20 09:15
 * 邮箱：820159571@qq.com
 *
 * @describe:总经理审批列表的适配器(写多个重复的adapter是为了避免点击冲突，这种情况待优化)
 */

public class ManagerAdapter extends RecyclerView.Adapter {

    private final Context context;
    private List<ListBean> mList;
    private int type = 0;

    public ManagerAdapter(Context context, List<ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    public ManagerAdapter(Context context, List<ListBean> mList, int type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
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
        //借款时间
        public TextView time;
        //借款用途
        public TextView purpose;
        //借款金额
        public TextView loanAmount;
        //客户电话
        public TextView customPhone;
        //借款期数
        public TextView loanPeriods;
        //客户经理
        public TextView officeName;
        //审批状态
        public TextView status;

        public TextView approval;
        public ImageView main;


        public MyListViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item_all);
            name = (TextView) itemView.findViewById(R.id.list_name);
            time = (TextView) itemView.findViewById(R.id.list_time);
            purpose = (TextView) itemView.findViewById(R.id.list_purpose);
            loanAmount = (TextView) itemView.findViewById(R.id.list_load_amount);
            customPhone = (TextView) itemView.findViewById(R.id.list_custom_phone);
            loanPeriods = (TextView) itemView.findViewById(R.id.list_loan_periods);
            officeName = (TextView) itemView.findViewById(R.id.list_office_name);
            status = (TextView) itemView.findViewById(R.id.list_status);
            approval = (TextView) itemView.findViewById(R.id.list_approval);
            main = (ImageView) itemView.findViewById(R.id.list_main);
        }

        public void setData(final int position) {
            ListBean listBean = mList.get(position);

            main.setVisibility(View.GONE);
            approval.setVisibility(View.VISIBLE);
            approval.setText("审批");

            time.setText(listBean.getTime());
            purpose.setText(listBean.getPurpose());
            status.setText(listBean.getStatus());
            name.setText(listBean.getName());
            loanAmount.setText((listBean.getLoanAmount()) + "万元");
            customPhone.setText(listBean.getCustomPhone());
            loanPeriods.setText(listBean.getLoanPeriods());
            officeName.setText(listBean.getOfficeName());

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
}