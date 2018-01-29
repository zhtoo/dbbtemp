package com.hs.doubaobao.model.ApplyLoad.RecyclerView;

import android.text.TextUtils;

/**
 * 作者：zhanghaitao on 2017/11/20 17:21
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ChildItem {

    private String loansUse;
    private String loanCategories;
    private String telephone;
    private String applicationPeriod;
    private String storeName;
    private String loanAmount;

    private String[] aar = new String[]{"", "12期", "18期", "24期", "36期", "48期", "60期"};

    public ChildItem() {}

    public ChildItem(String loansUse, String loanCategories, String telephone, String applicationPeriod, String storeName, String loanAmount) {
        this.loansUse = loansUse;
        this.loanCategories = loanCategories;
        this.telephone = telephone;
        this.applicationPeriod = applicationPeriod;
        this.storeName = storeName;
        this.loanAmount = loanAmount;
    }

    public String getLoansUse() {
        return loansUse;
    }

    public void setLoansUse(String loansUse) {
        this.loansUse = loansUse;
    }

    public String getLoanCategories() {
        return loanCategories;
    }

    public void setLoanCategories(String loanCategories) {
        //贷款类别，值： 01 汇民贷 02 汇商贷 03 汇业贷 04 汇车贷 06 汇房贷 05 汇农贷
        String[] typeArr = {"","01汇民贷","02汇商贷","03汇业贷","04汇车贷","05汇农贷","06汇房贷"};
        for (int i = 1; i < typeArr.length; i++) {
            if(typeArr[i].contains(loanCategories)){
                this.loanCategories = typeArr[i].substring(2,typeArr[i].length());
            }
        }

        if(TextUtils.isEmpty(loanCategories)){

            this.loanCategories = "未填写";
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getApplicationPeriod() {
        return applicationPeriod;
    }

    public void setApplicationPeriod(int applicationPeriod) {
        if (applicationPeriod > 0 && applicationPeriod < 7) {
            this.applicationPeriod = aar[applicationPeriod];
        } else {
            this.applicationPeriod = "";
        }
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount + "万元";
    }
}
