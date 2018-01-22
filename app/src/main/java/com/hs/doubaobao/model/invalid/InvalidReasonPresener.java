package com.hs.doubaobao.model.invalid;

import android.content.Context;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.InvalidReasonBean;
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
 * @describe:
 */

public class InvalidReasonPresener implements InvalidReasonContract.Presenter {

    private static final String TAG ="InvalidReasonPresener" ;
    InvalidReasonContract.View viewRoot;

    private Context context;

    public InvalidReasonPresener(InvalidReasonContract.View viewRoot, Context context) {
        this.viewRoot = viewRoot;
        this.context = context;
        viewRoot.setPresenter(this);
    }

    @Override
    public void getData(Map mapParameter) {

        OKHttpWrap.getOKHttpWrap(context)
                .requestPost(BaseParams.INVALID_REASON_URL, mapParameter, new requestCallBack() {

                    private InvalidReasonBean bean;

                    @Override
                    public void onError(Call call, Exception e) {
                        viewRoot.setError(e.getLocalizedMessage());
                    }
                    @Override
                    public void onResponse(String response) {
                        Logger.e(TAG,response);
                        bean = JsonWrap.getObject(response, InvalidReasonBean.class);
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
