package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2018/1/8 17:00
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LoanEvaluationActivity extends AppBarActivity {


    @BindView(R.id.evaluation_standard)
    Button mStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_evaluation);
        ButterKnife.bind(this);
        setTitle("放款评估");
        isShowRightView(false);
        initView();
    }

    private void initView() {
//        mRadioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(MyRadioGroup group, @IdRes int checkedId) {
//                RadioButton child = (RadioButton)   group.findViewById(checkedId);
//                String text = child.getText().toString().trim();
//                ToastUtil.showToast(text);
//            }
//        });
    }


    @OnClick(R.id.evaluation_standard)
    public void onViewClicked() {

startActivity(new Intent(this,EvaluationStandardActivity.class));
    }
}
