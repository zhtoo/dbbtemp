package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;

import butterknife.ButterKnife;

/**
 * 作者：zhanghaitao on 2018/1/8 15:57
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LenderInfoOptionalActivity extends AppBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender_info_optional);
        ButterKnife.bind(this);
        setTitle("贷款人信息-选填");
        isShowRightView(false);

//        View view = LayoutInflater.from(this).inflate(R.layout.item_spinner_simple,null,false);
//        view.measure(
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//                );
    }




}
