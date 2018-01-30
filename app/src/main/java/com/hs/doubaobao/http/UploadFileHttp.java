package com.hs.doubaobao.http;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.utils.MD5Util;
import com.hs.doubaobao.utils.log.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：zhanghaitao on 2018/1/26 11:25
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class UploadFileHttp {

    private static final String TAG = "UploadFileHttp";

    private static UploadFileHttp uploadFileHttp;

    //创建okHttpClient对象
    private static OkHttpClient okHttpClient;

    private final Handler handler;

    //编码格式
    private static final String CHARSET_NAME = "UTF-8";
    //传输的数据类型
    private static final MediaType MEDIA_OBJECT_STREAM = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致


    public static UploadFileHttp getInstance() {
        if (uploadFileHttp == null) {

            synchronized (UploadFileHttp.class) {

                if (uploadFileHttp == null) {
                    uploadFileHttp = new UploadFileHttp();
                }
            }
        }
        return uploadFileHttp;
    }


    /**
     * 私有构造函数
     */
    private UploadFileHttp() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置连接超时时间
        builder.connectTimeout(3000, TimeUnit.SECONDS);
        builder.readTimeout(3000, TimeUnit.SECONDS);
        builder.writeTimeout(3000, TimeUnit.SECONDS);
        // 打印参数
        builder.addInterceptor(new HttpLogging().setLevel(HttpLogging.Level.BODY));
        // 创建一个OkHttpClient
        okHttpClient = builder.build();

        //获取主线程的handler
        handler = MyApplication.getMainThreadHandler();

    }

    /**
     * 添加公共参数
     *
     * @param paramsMap
     * @param <T>
     * @return
     */
    private static <T> Map<String, T> getRequestMap(Map<String, T> paramsMap) {
        // 时间戳
        String ts = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String, T> mParamsMap = new HashMap<>();
        //AppKey
        mParamsMap.put("appkey", (T) BaseParams.APP_KEY);
        //签名
        mParamsMap.put("signa", (T) getSigna(ts));
        //时间戳
        mParamsMap.put("ts", (T) ts);
        //手机型号
        mParamsMap.put("mobileType", (T) BaseParams.MOBILE_TYPE);
        //APP版本号
        mParamsMap.put("versionNumber", (T) getVersion());
        //上传非空的userId
        if (!TextUtils.isEmpty(BaseParams.USER_ID)) {
            mParamsMap.put("userId", (T) BaseParams.USER_ID);
        }
        mParamsMap.putAll(paramsMap);
        return mParamsMap;
    }

    /**
     * 上传文件
     *
     * @param url       接口地址
     * @param paramsMap 参数
     */
    public String upLoadFile(String url, Map<String, Object> paramsMap) {

        synchronized (MyApplication.getContext()) {

            Map<String, Object> mParamsMap = getRequestMap(paramsMap);
            Logger.e("请求参数", mParamsMap.toString().replaceAll(",", "\n"));
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : mParamsMap.keySet()) {
                Object object = mParamsMap.get(key);
                if (!(object instanceof File)) {
                    //添加请求参数
                    builder.addFormDataPart(key, object.toString());
                } else {
                    //添加文件
                    File file = (File) object;
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_OBJECT_STREAM, file));
                }
            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            final Request request = new Request.Builder().url(url).post(body).build();
            //单独设置参数 比如读取超时时间
          return   requestClient(request);
        }
    }

    /**
     * 发起网络请求(同步请求，不然图片会换乱)
     * @param request
     */
    private String requestClient( Request request) {
        try {
            Response response =okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
               String result = response.body().string();
                return result;
            } else {
                Log.e(TAG, "Unexpected code " + response);
                return "Unexpected code " + response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送成功的结果
     *
     * @param object
     * @param callback
     */
    private void sendSuccessfulCallBack(final Object object, final CallBack callback) {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    /**
     * 发送失败结果
     *
     * @param call
     * @param e
     * @param callback
     */
    private void sendFailResultCallBack(final Call call, final IOException e, final CallBack callback) {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onAfter();
                callback.onError(call, e);
            }
        });
    }


    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param ts 时间戳
     */
    private static String getSigna(String ts) {
        // appsecrt拼接ts的字符串后进行MD5（32）
        String signa = MD5Util.encode(BaseParams.APP_SECRET + ts, CHARSET_NAME);
        // 得到的MD5串拼接appkey再次MD5，所得结果转大写
        signa = MD5Util.encode(signa + BaseParams.APP_KEY, CHARSET_NAME).toUpperCase();
        return signa;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = MyApplication.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "can not find version name";
        }
    }

}
