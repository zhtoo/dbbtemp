package com.hs.doubaobao.bean;

import android.text.TextUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：zhanghaitao on 2017/9/19 10:25
 * 邮箱：820159571@qq.com
 *
 * @describe:用于相似的list条目的bean
 */

public class ListBean {

    //借款时间
    private String time;
    //借款用途
    private String purpose;
    //审批状态
    private String status;
    //客户姓名
    private String name;

    //借款金额
    private double loanAmount;
    //放款金额
    private double loanRation;
    //客户电话
    private String customPhone;

    //贷款期数
    private String loanPeriods;
    //实批期数
    private String actPeriod;
    //门店名称
    private String officeName;

    //客户经理
    private String customManager;
    //列表显示类型
    private int showType = 0;//0: 首页  ； 1 风控  2 总经理

    //客户id
    private int id;

    private Map<String, String> map;
    private String[] aar = new String[]{"", "12期", "18期", "24期", "36期", "48期", "60期"};


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    public String getLoanPeriods() {
        return loanPeriods;
    }

    public void setLoanPeriods(int loanPeriods) {
        //1：12期，2：18期，3：24期，4：36期，5：48期，6：60期aar = new String[]{"", "12期", "18期", "24期", "36期", "48期", "60期"};
        if (loanPeriods > 0 && loanPeriods < 7) {
            this.loanPeriods = aar[loanPeriods];
        } else {
            this.loanPeriods = "";
        }
    }

    public String getCustomManager() {
        return customManager;
    }

    public void setCustomManager(String customManager) {
        this.customManager = customManager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        map = new LinkedHashMap<>();
        map.put("00", "待提交");
        map.put("01", "待门店一审");
        map.put("03", "初审不通过");
        map.put("10", "待家访");
        map.put("11", "待放款评估");
        map.put("12", "待部门初审");
        map.put("13", "部门经理不通过");
        map.put("20", "待风控审批");
        map.put("21", "风控审批不通过");
        map.put("30", "待总经理审批");
        map.put("31", "总经理审批不通过");
        map.put("40", "待签约");
        map.put("41", "签约失败");
        map.put("50", "待放款");
        map.put("52", "放款失败");
        map.put("51", "放款成功");

        for (String key : map.keySet()) {
            if (status.equals(key)) {
                this.status = map.get(key);
            }
        }
        if (TextUtils.isEmpty(this.status)) {
            this.status = "未知状态";
        }
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLoanPeriods(String loanPeriods) {
        this.loanPeriods = loanPeriods;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public double getLoanRation() {
        return loanRation;
    }

    public void setLoanRation(double loanRation) {
        this.loanRation = loanRation;
    }

    public String getActPeriod() {
        return actPeriod;
    }

    public void setActPeriod(String actPeriod) {
        int position ;
        try {
            position = Integer.parseInt(actPeriod);
        } catch (Exception e) {
            position = 0;
        }
        if (position > 0 && position < 7) {
            this.actPeriod = aar[position];
        } else {
            this.actPeriod = "未填写";
        }

    }
}
