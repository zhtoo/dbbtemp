package com.hs.doubaobao.model.department;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.DepartmentStatisticsBean;
import com.hs.doubaobao.http.JsonWrap;
import com.hs.doubaobao.http.OKHttpWrap;
import com.hs.doubaobao.http.requestCallBack;
import com.hs.doubaobao.utils.log.Logger;

import java.util.Map;

import okhttp3.Call;

/**
 * 作者：zhanghaitao on 2017/9/12 11:03
 * 邮箱：820159571@qq.com
 *
 * @describe:复制粘贴类
 */

public class DSPresener implements DSContract.Presenter {

    private static final String TAG = "CopyPresener";
    DSContract.View viewRoot;


    private Context context;

    public DSPresener(DSContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        this.context = context;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(Map mapParameter) {
        OKHttpWrap.getOKHttpWrap(context)
                .requestPost(BaseParams.DEPARTMENT_STATISTICS_URL, mapParameter, new requestCallBack() {

                    private DepartmentStatisticsBean bean;

                    @Override
                    public void onError(Call call, Exception e) {
                        viewRoot.setError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Logger.e(TAG, response);
                        bean = JsonWrap.getObject(response, DepartmentStatisticsBean.class);
                        //回到不能在子线程中
                        if (bean != null) {
                            viewRoot.setData(bean);
                        } else {
                            viewRoot.setError("Json解析异常");
                        }
                    }
                });

    }
}
