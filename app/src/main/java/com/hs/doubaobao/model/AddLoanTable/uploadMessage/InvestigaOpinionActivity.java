package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

import butterknife.ButterKnife;

/**
 * 作者：zhanghaitao on 2018/1/8 15:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class InvestigaOpinionActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investiga_opinion);
        ButterKnife.bind(this);
        setTitle("客户情况调查及意见");
        isShowRightView(false);

    }



}
