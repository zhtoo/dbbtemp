package com.hs.doubaobao.model.ApplyLoad;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.ApplyListBean;
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

public class ApplyLoadPresener implements ApplyLoadContract.Presenter {

    private static final String TAG ="ApplyLoadPresener" ;
    ApplyLoadContract.View viewRoot;


    private Context context;

    public ApplyLoadPresener(ApplyLoadContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        this.context = context;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(Map mapParameter) {
        OKHttpWrap.getOKHttpWrap(context)
                .requestPost(BaseParams.LOAN_APPLICATION_URL, mapParameter, new requestCallBack() {

                    private ApplyListBean bean;

                    @Override
                    public void onError(Call call, Exception e) {
                        viewRoot.setError(e.getLocalizedMessage());
                    }
                    @Override
                    public void onResponse(String response) {
                        Logger.e(TAG,response);
                        bean = JsonWrap.getObject(response, ApplyListBean.class);
                        //回到不能在子线程中
                        if(bean !=null){
                            if(bean.getResCode() == 1){
                                viewRoot.setData(bean);
                            }else {
                                viewRoot.setError(bean.getResMsg());
                            }
                        }else {
                            viewRoot.setError("Json解析异常");
                        }
                    }
                });

    }
}
