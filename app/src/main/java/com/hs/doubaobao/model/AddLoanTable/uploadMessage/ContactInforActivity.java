package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.ApplyInfoBean;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.utils.ToastUtil;
import com.hs.doubaobao.view.ExpandableView;
import com.zht.bottomdialog.SelectBottomDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hs.doubaobao.MyApplication.getContext;

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

    private EditText contactInfoName01;
    private TextView contactInfoRelation01;
    private LinearLayout contactInfoRelationItem01;
    private EditText contactInfoPhone01;
    private RadioButton contactInfoYes01;
    private RadioButton contactInfoNo01;
    private RadioGroup contactInfoKonw01;
    private EditText contactInfoName02;
    private TextView contactInfoRelation02;
    private LinearLayout contactInfoRelationItem02;
    private EditText contactInfoPhone02;
    private RadioButton contactInfoYes02;
    private RadioButton contactInfoNo02;
    private RadioGroup contactInfoKonw02;
    private EditText contactInfoName03;
    private TextView contactInfoRelation03;
    private LinearLayout contactInfoRelationItem03;
    private EditText contactInfoPhone03;
    private RadioButton contactInfoYes03;
    private RadioButton contactInfoNo03;
    private RadioGroup contactInfoKonw03;
    private EditText contactInfoName04;
    private TextView contactInfoRelation04;
    private LinearLayout contactInfoRelationItem04;
    private EditText contactInfoPhone04;
    private RadioButton contactInfoYes04;
    private RadioButton contactInfoNo04;
    private RadioGroup contactInfoKonw04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_infor);
        ButterKnife.bind(this);
        setTitle("联系人信息");
        isShowRightView(false);
        init();
        initView();
    }

    /**
     * 根据数据渲染界面
     */
    private void initView() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean> uclList
                = bean.getResData().getBorrowdataModel().getUclList();
        if (uclList == null || uclList.size() == 0) return;
        int firstPosition = 1;
        int secondPosition = 1;
        for (int i = 0; i < uclList.size(); i++) {
            ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean
                    uclListBean = uclList.get(i);
            int type = uclListBean.getType();
            if (type == 0) {
                if (firstPosition == 1) {
                    ApplyLendUtil.setContacts(uclListBean, contactInfoName01, contactInfoRelation01, contactInfoPhone01, contactInfoYes01, contactInfoNo01
                    );
                    firstPosition++;
                } else {
                    ApplyLendUtil.setContacts(uclListBean, contactInfoName02, contactInfoRelation02, contactInfoPhone02, contactInfoYes02, contactInfoNo02
                    );
                }
            }
            if (type == 1) {
                if (secondPosition == 1) {
                    ApplyLendUtil.setContacts(uclListBean, contactInfoName03, contactInfoRelation03, contactInfoPhone03, contactInfoYes03, contactInfoNo03);
                    secondPosition++;
                } else {
                    ApplyLendUtil.setContacts(uclListBean, contactInfoName04, contactInfoRelation04, contactInfoPhone04, contactInfoYes04, contactInfoNo04);
                }
            }
        }
    }

    private void init() {
        createExpandableView(expandableView1, "两名直系亲属联系方式");
        createExpandableView1(expandableView2, "两名一般联系方人联系方式");
        intListener();
    }

    private void intListener() {
        contactInfoRelationItem01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRelation(contactInfoRelation01);
            }
        });

        contactInfoRelationItem02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRelation(contactInfoRelation02);
            }
        });
        contactInfoRelationItem03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRelation(contactInfoRelation03);
            }
        });
        contactInfoRelationItem04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRelation(contactInfoRelation04);
            }
        });
    }

    private void createExpandableView(ExpandableView mExpandableView, String title) {
        mExpandableView.fillData(0, title, true);
        View itemView = View.inflate(this, R.layout.item_contact_info01, null);
        View itemView1 = View.inflate(this, R.layout.item_contact_info02, null);
        initView01(itemView);
        initView02(itemView1);
        mExpandableView.addContentView(itemView);
        mExpandableView.addContentView(itemView1);
    }


    private void createExpandableView1(ExpandableView mExpandableView, String title) {
        mExpandableView.fillData(0, title, true);
        View itemView = View.inflate(this, R.layout.item_contact_info03, null);
        View itemView1 = View.inflate(this, R.layout.item_contact_info04, null);
        initView03(itemView);
        initView04(itemView1);

        mExpandableView.addContentView(itemView);
        mExpandableView.addContentView(itemView1);
    }


    private void chooseRelation(final TextView textView) {
        //  关系，值：配偶 ，父母，子女，兄弟姐妹，亲戚，朋友，其他
        SelectBottomDialog dialog1 = new SelectBottomDialog();
        dialog1.setItemStrings(getContext(),
                new String[]{"配偶", "父母", "子女", "兄弟姐妹", "亲戚", "朋友", "其他"});

        dialog1.show(getSupportFragmentManager());
        dialog1.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
            @Override
            public void onClick(String text) {
                textView.setText(text);
            }
        });
    }


    private void initView01(View itemView) {
        contactInfoName01 = (EditText) itemView.findViewById(R.id.contact_info_name01);
        contactInfoRelation01 = (TextView) itemView.findViewById(R.id.contact_info_relation01);
        contactInfoRelationItem01 = (LinearLayout) itemView.findViewById(R.id.contact_info_relation_item01);
        contactInfoPhone01 = (EditText) itemView.findViewById(R.id.contact_info_phone01);
        contactInfoYes01 = (RadioButton) itemView.findViewById(R.id.contact_info_yes01);
        contactInfoNo01 = (RadioButton) itemView.findViewById(R.id.contact_info_no01);
        contactInfoKonw01 = (RadioGroup) itemView.findViewById(R.id.contact_info_konw01);
    }

    private void initView02(View itemView) {
        contactInfoName02 = (EditText) itemView.findViewById(R.id.contact_info_name02);
        contactInfoRelation02 = (TextView) itemView.findViewById(R.id.contact_info_relation02);
        contactInfoRelationItem02 = (LinearLayout) itemView.findViewById(R.id.contact_info_relation_item02);
        contactInfoPhone02 = (EditText) itemView.findViewById(R.id.contact_info_phone02);
        contactInfoYes02 = (RadioButton) itemView.findViewById(R.id.contact_info_yes02);
        contactInfoNo02 = (RadioButton) itemView.findViewById(R.id.contact_info_no02);
        contactInfoKonw02 = (RadioGroup) itemView.findViewById(R.id.contact_info_konw02);
    }

    private void initView03(View itemView) {
        contactInfoName03 = (EditText) itemView.findViewById(R.id.contact_info_name03);
        contactInfoRelation03 = (TextView) itemView.findViewById(R.id.contact_info_relation03);
        contactInfoRelationItem03 = (LinearLayout) itemView.findViewById(R.id.contact_info_relation_item03);
        contactInfoPhone03 = (EditText) itemView.findViewById(R.id.contact_info_phone03);
        contactInfoYes03 = (RadioButton) itemView.findViewById(R.id.contact_info_yes03);
        contactInfoNo03 = (RadioButton) itemView.findViewById(R.id.contact_info_no03);
        contactInfoKonw03 = (RadioGroup) itemView.findViewById(R.id.contact_info_konw03);
    }

    private void initView04(View itemView) {
        contactInfoName04 = (EditText) itemView.findViewById(R.id.contact_info_name04);
        contactInfoRelation04 = (TextView) itemView.findViewById(R.id.contact_info_relation04);
        contactInfoRelationItem04 = (LinearLayout) itemView.findViewById(R.id.contact_info_relation_item04);
        contactInfoPhone04 = (EditText) itemView.findViewById(R.id.contact_info_phone04);
        contactInfoYes04 = (RadioButton) itemView.findViewById(R.id.contact_info_yes04);
        contactInfoNo04 = (RadioButton) itemView.findViewById(R.id.contact_info_no04);
        contactInfoKonw04 = (RadioGroup) itemView.findViewById(R.id.contact_info_konw04);
    }

    /**
     * 保存数据
     *
     * @return
     */
    @Override
    public boolean savaData() {
        return super.savaData();
    }

    @OnClick(R.id.contact_save)
    public void onViewClicked() {
        ApplyLendUtil.changeContacts(0, 0, contactInfoName01, contactInfoRelation01, contactInfoPhone01, contactInfoYes01, contactInfoNo01);
        ApplyLendUtil.changeContacts(1, 0, contactInfoName02, contactInfoRelation02, contactInfoPhone02, contactInfoYes02, contactInfoNo02);
        ApplyLendUtil.changeContacts(2, 1, contactInfoName03, contactInfoRelation03, contactInfoPhone03, contactInfoYes03, contactInfoNo03);
        ApplyLendUtil.changeContacts(3, 1, contactInfoName04, contactInfoRelation04, contactInfoPhone04, contactInfoYes04, contactInfoNo04);
        ToastUtil.showToast("保存成功");
    }
}
