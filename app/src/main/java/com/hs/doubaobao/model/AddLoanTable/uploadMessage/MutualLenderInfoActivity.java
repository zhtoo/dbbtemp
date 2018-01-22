package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

import butterknife.ButterKnife;

/**
 * 作者：zhanghaitao on 2018/1/8 16:24
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class MutualLenderInfoActivity extends AppBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_lender_info);
        ButterKnife.bind(this);
        setTitle("共同借款人信息");
        isShowRightView(false);

    }



}
