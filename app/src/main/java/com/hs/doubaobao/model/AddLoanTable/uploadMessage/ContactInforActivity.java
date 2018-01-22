package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.view.View;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.view.ExpandableView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：zhanghaitao on 2018/1/8 13:59
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ContactInforActivity extends AppBarActivity {


    @BindView(R.id.expandable_view1)
    ExpandableView expandableView1;
    @BindView(R.id.expandable_view2)
    ExpandableView expandableView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_infor);
        ButterKnife.bind(this);
        setTitle("联系人信息");
        isShowRightView(false);
        init();
    }


    private void init() {
        createExpandableView(expandableView1, "两名直系亲属联系方式");
        createExpandableView1(expandableView2, "两名一般联系方人联系方式");
    }

    private void createExpandableView(ExpandableView mExpandableView, String title) {
        mExpandableView.fillData(0, title, true);
        View itemView = View.inflate(this, R.layout.item_contact_info01, null);
        View itemView1 = View.inflate(this, R.layout.item_contact_info02, null);
        mExpandableView.addContentView(itemView);
        mExpandableView.addContentView(itemView1);
    }

    private void createExpandableView1(ExpandableView mExpandableView, String title) {
        mExpandableView.fillData(0, title, true);
        View itemView = View.inflate(this, R.layout.item_contact_info03, null);
        View itemView1 = View.inflate(this, R.layout.item_contact_info04, null);
        mExpandableView.addContentView(itemView);
        mExpandableView.addContentView(itemView1);
    }


}
