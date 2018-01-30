package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.utils.ToastUtil;
import com.zht.bottomdialog.SelectBottomDialog;
import com.zht.datepicker.DateSelectUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hs.doubaobao.MyApplication.getContext;

/**
 * 作者：zhanghaitao on 2018/1/8 13:31
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class TheLoansActivity extends AppBarActivity {

    @BindView(R.id.loan_type_text)
    TextView mTypeText;
    @BindView(R.id.loan_type)
    LinearLayout mType;
    @BindView(R.id.loan_account)
    EditText mAccount;
    @BindView(R.id.loan_period_text)
    TextView mPeriodText;
    @BindView(R.id.loan_period)
    LinearLayout mPeriod;
    @BindView(R.id.loan_applydate_text)
    TextView mApplydateText;
    @BindView(R.id.loan_applydate)
    LinearLayout mApplydate;
    @BindView(R.id.loan_purpose)
    EditText mPurpose;
    @BindView(R.id.the_loans_save)
    Button mLoansSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loans);
        ButterKnife.bind(this);
        setTitle("贷款事项");
        isShowRightView(false);
        initView();
    }

    private void initView() {
        ApplyLendUtil.setTheloans(
                mTypeText,
                mAccount,
                mPeriodText,
                mApplydateText,
                mPurpose
        );

    }


    @OnClick({R.id.loan_type, R.id.loan_period, R.id.loan_applydate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loan_type:
                //01 汇民贷 02 汇商贷 03 汇业贷 04 汇车贷 06 汇房贷 05 汇农贷
                SelectBottomDialog dialog = new SelectBottomDialog();
                dialog.setItemStrings(getContext(),
                        new String[]{"汇民贷", "汇商贷", "汇业贷", "汇车贷", "汇房贷", "汇农贷"});
                dialog.show(getSupportFragmentManager());
                dialog.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
                    @Override
                    public void onClick(String text) {
                        mTypeText.setText(text);
                    }
                });
                break;
            case R.id.loan_period:
                //申请期限，值：1:12期 2:18期 3:24期 4:36期 5:48期 6:60期
                SelectBottomDialog dialog1 = new SelectBottomDialog();
                dialog1.setItemStrings(getContext(),
                        new String[]{"12期", "18期", "24期", "36期", "48期", "60期"});

                dialog1.show(getSupportFragmentManager());
                dialog1.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
                    @Override
                    public void onClick(String text) {
                        mPeriodText.setText(text);
                    }
                });
                break;
            case R.id.loan_applydate:
                DateSelectUtil.showSelectDateDialog(this, mApplydateText);
                break;
        }
    }

    /**
     * 保存数据
     *
     * @return
     */
    @Override
    public boolean savaData() {
//        ApplyLendUtil.changeTheloans(
//                mTypeText,
//                mAccount,
//                mPeriodText,
//                mApplydateText,
//                mPurpose
//        );

        return super.savaData();
    }

    @OnClick(R.id.the_loans_save)
    public void onViewClicked() {
        ApplyLendUtil.changeTheloans(
                mTypeText,
                mAccount,
                mPeriodText,
                mApplydateText,
                mPurpose
        );
        ToastUtil.showToast("保存成功");
    }
}
