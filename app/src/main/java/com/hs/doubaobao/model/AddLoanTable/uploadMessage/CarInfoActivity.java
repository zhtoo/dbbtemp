package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

import butterknife.ButterKnife;

/**
 * 作者：zhanghaitao on 2018/1/8 16:38
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class CarInfoActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_infor);
        ButterKnife.bind(this);
        setTitle("贷款人车辆信息");
        isShowRightView(false);
    }

}
