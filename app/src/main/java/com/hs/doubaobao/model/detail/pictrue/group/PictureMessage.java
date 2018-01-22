package com.hs.doubaobao.model.detail.pictrue.group;

import com.hs.doubaobao.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 作者：zhanghaitao on 2017/12/8 14:38
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictureMessage {

    private int category ;

    private int titleType;

    private String name;

    private String url;

    private int group ;

    private int emptyPicture= R.drawable.ic_video_null_bg;

    private int  listPosition;


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


    public int getTitleType() {
        return titleType;
    }

    public void setTitleType(int titleType) {
        this.titleType = titleType;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }

    /**
     * 排序
     */
    public ArrayList<PictureMessage> Collections(ArrayList<PictureMessage> pictureList) {
        Collections.sort(pictureList, new Comparator<PictureMessage>() {

            public int compare(PictureMessage o1, PictureMessage o2) {
                int i1 = o1.category;
                int i2 = o2.category;
                int i = i1 - i2;
                return i;
            }
        });

        return pictureList;
    }


}
