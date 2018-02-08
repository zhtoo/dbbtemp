package com.hs.doubaobao.utils;

import android.text.TextUtils;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 作者：zhanghaitao on 2018/2/6 17:58
 * 邮箱：820159571@qq.com
 *
 * @describe:移除json数据中的空对象。
 * 该版本未使用，已经测试通过。
 * 解决的问题：后台数值类型的数据有可能给你传 "" ,这样你的Gson就会报错。
 * 其实还有一些坑，看到的我都解决了，抱歉坑不是我挖的，所以自己刨坑自己填。
 * This company is very stingy, there is no year-end bonus.
 * Sometimes your performance will be withheld.
 */

public class RemoveJsonNullObjectUtil {

    /**
     * json
     * "" : ""
     * 特殊字符： " : ,{} []  0-9
     * 1、 "" : 2123 ,
     * 2、 "" : ""   ,
     * 3、 "" : {},
     * 4、 "" : [],
     * {}
     * 对象之间的分割以,隔开
     * 过滤其中的空值对象
     * @param json
     * @return
     */
    public static String removeNullObject(String json) {
        if(TextUtils.isEmpty(json))return "";
        StringBuffer SB = new StringBuffer(json);
        //Map必须是可排序的，而且是降序排列。
        TreeMap<Integer, Integer> postionMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        boolean couldDelete = false;
        int startPosition = 1;
        int quotationMark = 0;

        for (int i = 0; i < json.length(); i++) {
            char compareChar = json.charAt(i);
            //若果json的value是对象或者数组或者是结束标志，重置判断条件。
            if (compareChar == '{' || compareChar == '[') {
                quotationMark = 0;
                couldDelete = false;
            } else if (compareChar == '\"') {
                //对象的开头是否记录
                if (quotationMark > 0) {
                    quotationMark++;
                    if (quotationMark == 4 && couldDelete && i > 0) {
                        char preChar = json.charAt(i - 1);
                        char nextChar = json.charAt(i + 1);
                        if (preChar == '\"') {
                            if (nextChar == ',') {
                                postionMap.put(startPosition, i + 2);
                            } else {
                                postionMap.put(startPosition, i + 1);
                                couldDelete = false;
                            }
                        }
                    }
                } else {
                    startPosition = i;
                    quotationMark = 1;
                }
            } else if (compareChar == ':') {
                if (quotationMark == 2) {
                    couldDelete = true;
                } else {
                    quotationMark = 0;
                    couldDelete = false;
                }
            } else if (compareChar >= '0' && compareChar <= '9') {
                if (quotationMark == 2 && couldDelete) {
                    quotationMark = 0;
                    couldDelete = false;
                }
            } else if (compareChar == ',') {
                quotationMark = 0;
            }
        }
        //循环删除SB中的空格对象
        for (Integer start : postionMap.keySet()) {
            Integer end = postionMap.get(start);
            SB.delete(start, end);
        }
        return SB.toString();
    }

}