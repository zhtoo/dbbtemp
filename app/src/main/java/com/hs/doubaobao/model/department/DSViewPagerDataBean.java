package com.hs.doubaobao.model.department;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhanghaitao on 2017/11/9 14:05
 * 邮箱：820159571@qq.com
 *
 * @describe:viewpager的条目数据
 */

public class DSViewPagerDataBean {

    private String title;

    private String refreshDate;

    private String refreshNumber;

    private String titleY;

    private List<String> date;

    private List<Double> values;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRefreshDate() {
        return refreshDate;
    }

    public void setRefreshDate(String refreshDate) {
        this.refreshDate = refreshDate;
    }

    public String getRefreshNumber() {
        return refreshNumber;
    }

    public void setRefreshNumber(String refreshNumber) {
        this.refreshNumber = refreshNumber;
    }

    public String getTitleY() {
        return titleY;
    }

    public void setTitleY(String titleY) {
        this.titleY = titleY;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List values) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }

        for (int i = 0; i < values.size(); i++) {
            this.values.add(Double.parseDouble(values.get(i).toString()));
        }

    }
}
