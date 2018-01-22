package com.hs.doubaobao.model.detail.video;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 作者：zhanghaitao on 2017/12/8 11:28
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class VideoMessage {

    private int category ;

    private String name;

    private String url;

    /**
     * 排序
     */
    public ArrayList<VideoMessage> Collections(ArrayList<VideoMessage> videoList) {
        Collections.sort(videoList, new Comparator<VideoMessage>() {

                    public int compare(VideoMessage o1, VideoMessage o2) {
                        int i1 = o1.category;
                        int i2 = o2.category;
                        int i = i1 - i2;
                        return i;
                    }
                });
        return videoList;
    }


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
