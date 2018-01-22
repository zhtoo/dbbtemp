package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2018/1/19 13:40
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class DepartmentStatisticsBean {


    /**
     * resCode : 1
     * resData : {"accounts":[0,0,0,0,0,0,0],"applyCountDept":40,"applyMoneyDept":2395.86,"counts":[0,0,0,0,0,0,0],"dates":["01-16","01-17","01-18","01-19","01-20","01-21","01-22"],"deptName":"管理部门","failCountDept":12,"failMoneyDept":147.07,"loanCountDept":8,"loanMoneyDept":83.52,"todayCountDept":0,"todayMoneyDept":0}
     * resMsg : SUCCESS
     */

    private int resCode;
    private ResDataBean resData;
    private String resMsg;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public ResDataBean getResData() {
        return resData;
    }

    public void setResData(ResDataBean resData) {
        this.resData = resData;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public static class ResDataBean {
        /**
         * accounts : [0,0,0,0,0,0,0]
         * applyCountDept : 40
         * applyMoneyDept : 2395.86
         * counts : [0,0,0,0,0,0,0]
         * dates : ["01-16","01-17","01-18","01-19","01-20","01-21","01-22"]
         * deptName : 管理部门
         * failCountDept : 12
         * failMoneyDept : 147.07
         * loanCountDept : 8
         * loanMoneyDept : 83.52
         * todayCountDept : 0
         * todayMoneyDept : 0
         */

        private int applyCountDept;
        private double applyMoneyDept;
        private String deptName;
        private int failCountDept;
        private double failMoneyDept;
        private int loanCountDept;
        private double loanMoneyDept;
        private int todayCountDept;
        private int todayMoneyDept;
        private List<Integer> accounts;
        private List<Integer> counts;
        private List<String> dates;

        public int getApplyCountDept() {
            return applyCountDept;
        }

        public void setApplyCountDept(int applyCountDept) {
            this.applyCountDept = applyCountDept;
        }

        public double getApplyMoneyDept() {
            return applyMoneyDept;
        }

        public void setApplyMoneyDept(double applyMoneyDept) {
            this.applyMoneyDept = applyMoneyDept;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getFailCountDept() {
            return failCountDept;
        }

        public void setFailCountDept(int failCountDept) {
            this.failCountDept = failCountDept;
        }

        public double getFailMoneyDept() {
            return failMoneyDept;
        }

        public void setFailMoneyDept(double failMoneyDept) {
            this.failMoneyDept = failMoneyDept;
        }

        public int getLoanCountDept() {
            return loanCountDept;
        }

        public void setLoanCountDept(int loanCountDept) {
            this.loanCountDept = loanCountDept;
        }

        public double getLoanMoneyDept() {
            return loanMoneyDept;
        }

        public void setLoanMoneyDept(double loanMoneyDept) {
            this.loanMoneyDept = loanMoneyDept;
        }

        public int getTodayCountDept() {
            return todayCountDept;
        }

        public void setTodayCountDept(int todayCountDept) {
            this.todayCountDept = todayCountDept;
        }

        public int getTodayMoneyDept() {
            return todayMoneyDept;
        }

        public void setTodayMoneyDept(int todayMoneyDept) {
            this.todayMoneyDept = todayMoneyDept;
        }

        public List<Integer> getAccounts() {
            return accounts;
        }

        public void setAccounts(List<Integer> accounts) {
            this.accounts = accounts;
        }

        public List<Integer> getCounts() {
            return counts;
        }

        public void setCounts(List<Integer> counts) {
            this.counts = counts;
        }

        public List<String> getDates() {
            return dates;
        }

        public void setDates(List<String> dates) {
            this.dates = dates;
        }
    }
}
