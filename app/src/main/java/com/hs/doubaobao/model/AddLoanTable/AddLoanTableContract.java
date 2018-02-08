package com.hs.doubaobao.model.AddLoanTable;

import com.hs.doubaobao.base.BasePresenter;
import com.hs.doubaobao.base.BaseView;
import com.hs.doubaobao.bean.ApplyInfoBean;

import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/12 10:59
 * 邮箱：820159571@qq.com
 *
 * @describe:复制粘贴类
 */

public interface AddLoanTableContract {

    interface Presenter extends BasePresenter {
        //ODO:需要哪些获取数据的方法，就在此处定义
        void uploadData(Map<String,Object> bean);
    }

    interface View extends BaseView<Presenter> {
        //TODO:在此处定义需要用来更新视图的方法
        void setData(ApplyInfoBean bean);
        void uploadDataBack(String data);
        void setError(String text);
    }

}
