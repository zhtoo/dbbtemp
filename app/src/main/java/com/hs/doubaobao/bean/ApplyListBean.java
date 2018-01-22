package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2018/1/22 09:36
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApplyListBean {


    /**
     * resCode : 1
     * resData : {"pageDataList":{"list":[{"account":12,"actPeriod":2,"applydate":"2017-10-24","cusName":"f","id":166,"managerRation":0,"mobilephone":"13521613953","officeName":"安徽豆宝宝","operatorName":"豆宝宝超级管理员","period":2,"purpose":"2","status":"01"}],"page":{"currentPage":1,"currentPageSum":0,"end":1,"pages":1,"pernum":10,"start":0,"total":1},"type":0}}
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
         * pageDataList : {"list":[{"account":12,"actPeriod":2,"applydate":"2017-10-24","cusName":"f","id":166,"managerRation":0,"mobilephone":"13521613953","officeName":"安徽豆宝宝","operatorName":"豆宝宝超级管理员","period":2,"purpose":"2","status":"01"}],"page":{"currentPage":1,"currentPageSum":0,"end":1,"pages":1,"pernum":10,"start":0,"total":1},"type":0}
         */

        private PageDataListBean pageDataList;

        public PageDataListBean getPageDataList() {
            return pageDataList;
        }

        public void setPageDataList(PageDataListBean pageDataList) {
            this.pageDataList = pageDataList;
        }

        public static class PageDataListBean {
            /**
             * list : [{"account":12,"actPeriod":2,"applydate":"2017-10-24","cusName":"f","id":166,"managerRation":0,"mobilephone":"13521613953","officeName":"安徽豆宝宝","operatorName":"豆宝宝超级管理员","period":2,"purpose":"2","status":"01"}]
             * page : {"currentPage":1,"currentPageSum":0,"end":1,"pages":1,"pernum":10,"start":0,"total":1}
             * type : 0
             */

            private PageBean page;
            private int type;
            private List<ListBean> list;

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class PageBean {
                /**
                 * currentPage : 1
                 * currentPageSum : 0
                 * end : 1
                 * pages : 1
                 * pernum : 10
                 * start : 0
                 * total : 1
                 */

                private int currentPage;
                private int currentPageSum;
                private int end;
                private int pages;
                private int pernum;
                private int start;
                private int total;

                public int getCurrentPage() {
                    return currentPage;
                }

                public void setCurrentPage(int currentPage) {
                    this.currentPage = currentPage;
                }

                public int getCurrentPageSum() {
                    return currentPageSum;
                }

                public void setCurrentPageSum(int currentPageSum) {
                    this.currentPageSum = currentPageSum;
                }

                public int getEnd() {
                    return end;
                }

                public void setEnd(int end) {
                    this.end = end;
                }

                public int getPages() {
                    return pages;
                }

                public void setPages(int pages) {
                    this.pages = pages;
                }

                public int getPernum() {
                    return pernum;
                }

                public void setPernum(int pernum) {
                    this.pernum = pernum;
                }

                public int getStart() {
                    return start;
                }

                public void setStart(int start) {
                    this.start = start;
                }

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }
            }

            public static class ListBean {
                /**
                 * account : 12
                 * actPeriod : 2
                 * applydate : 2017-10-24
                 * cusName : f
                 * id : 166
                 * managerRation : 0
                 * mobilephone : 13521613953
                 * officeName : 安徽豆宝宝
                 * operatorName : 豆宝宝超级管理员
                 * period : 2
                 * purpose : 2
                 * status : 01
                 */

                private int account;
                private int actPeriod;
                private String applydate;
                private String cusName;
                private int id;
                private int managerRation;
                private String mobilephone;
                private String officeName;
                private String operatorName;
                private int period;
                private String purpose;
                private String status;

                public int getAccount() {
                    return account;
                }

                public void setAccount(int account) {
                    this.account = account;
                }

                public int getActPeriod() {
                    return actPeriod;
                }

                public void setActPeriod(int actPeriod) {
                    this.actPeriod = actPeriod;
                }

                public String getApplydate() {
                    return applydate;
                }

                public void setApplydate(String applydate) {
                    this.applydate = applydate;
                }

                public String getCusName() {
                    return cusName;
                }

                public void setCusName(String cusName) {
                    this.cusName = cusName;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getManagerRation() {
                    return managerRation;
                }

                public void setManagerRation(int managerRation) {
                    this.managerRation = managerRation;
                }

                public String getMobilephone() {
                    return mobilephone;
                }

                public void setMobilephone(String mobilephone) {
                    this.mobilephone = mobilephone;
                }

                public String getOfficeName() {
                    return officeName;
                }

                public void setOfficeName(String officeName) {
                    this.officeName = officeName;
                }

                public String getOperatorName() {
                    return operatorName;
                }

                public void setOperatorName(String operatorName) {
                    this.operatorName = operatorName;
                }

                public int getPeriod() {
                    return period;
                }

                public void setPeriod(int period) {
                    this.period = period;
                }

                public String getPurpose() {
                    return purpose;
                }

                public void setPurpose(String purpose) {
                    this.purpose = purpose;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }
        }
    }
}
