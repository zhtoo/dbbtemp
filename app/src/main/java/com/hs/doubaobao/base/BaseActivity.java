package com.hs.doubaobao.base;

import android.os.Bundle;

import com.hs.doubaobao.R;

/**
 * 作者：zhanghaitao on 2018/1/8 13:31
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class BaseActivity  extends AppBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity切换中进入动画
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_in_to_left);
    }


    @Override
    public void finish() {
        super.finish();
        //Activity切换中退出动画
        overridePendingTransition(R.anim.slide_out_from_left, R.anim.slide_out_to_right);
    }



}
