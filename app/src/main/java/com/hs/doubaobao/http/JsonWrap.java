package com.hs.doubaobao.http;

import com.google.gson.Gson;
import com.hs.doubaobao.utils.log.Logger;

/**
 * 作者：zhanghaitao on 2017/9/19 14:29
 * 邮箱：820159571@qq.com
 *
 * @describe:GAON解析框架的封装
 */

public class JsonWrap {

    /**
     * 使用Gson进行解析对象
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            Logger.e("Json解析", e.toString());
        }
        return t;
    }

    /**
     * 使用Gson进行对象转Json
     * @param trim
     * @param <T>
     * @return
     */
    public static <T> String getString(T trim) {
        T t = (T) trim;
        String tojson = "";
        try {
            Gson gson = new Gson();
            tojson = gson.toJson(t);
            Logger.d("Json解析", tojson);
            return tojson;
        } catch (Exception e) {
            Logger.e("Json解析错误", e.toString());
            return tojson;
        }
    }

}
