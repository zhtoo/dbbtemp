package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/18 15:38
 * 邮箱：820159571@qq.com
 *
 * @describe:主界面的bean
 */

public class HomeBean {


    /**
     * resCode : 1
     * resData : {"messageCount":{"messageRole509":0},"pageDataList":{"list":[{"account":2.52,"actPeriod":"","applydate":"2017-11-23","cusName":"蜀山","id":160,"managerRation":2.52,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":1,"purpose":"testing","status":"51"},{"account":11.11,"actPeriod":5,"applydate":"2017-11-22","cusName":"王二小","id":163,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":3,"applydate":"2017-11-22","cusName":"林漪","id":155,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":1,"applydate":"2017-11-22","cusName":"王二小","id":150,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":"","applydate":"2017-11-22","cusName":"王二小","id":162,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"50"},{"account":11.11,"actPeriod":"","applydate":"2017-11-22","cusName":"王二小王二小王二小王二小","id":161,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"50"},{"account":2.52,"actPeriod":"","applydate":"2017-11-21","cusName":"方兰东","id":149,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":1,"purpose":"yyyyyyyyy","status":"50"},{"account":32.12,"actPeriod":"","applydate":"2017-11-21","cusName":"penda11111","id":147,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":3,"purpose":"777777777777777777777777777777777777yy","status":"50"},{"account":100,"actPeriod":"","applydate":"2017-11-20","cusName":"BIGBANG","id":157,"managerRation":10,"mobilephone":"13301010101","officeName":"蜀山一号分店下的二级分店","period":6,"purpose":"满意鼎折覆餗","status":"51"},{"account":89.52,"actPeriod":"","applydate":"2017-11-20","cusName":"LEON YAN","id":146,"managerRation":20,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":6,"purpose":"999999999999999999999999999999testing","status":"51"}],"page":{"currentPage":1,"currentPageSum":0,"end":10,"pages":4,"pernum":10,"start":0,"total":31},"type":0},"roleIdList":[509]}
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
         * messageCount : {"messageRole509":0}
         * pageDataList : {"list":[{"account":2.52,"actPeriod":"","applydate":"2017-11-23","cusName":"蜀山","id":160,"managerRation":2.52,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":1,"purpose":"testing","status":"51"},{"account":11.11,"actPeriod":5,"applydate":"2017-11-22","cusName":"王二小","id":163,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":3,"applydate":"2017-11-22","cusName":"林漪","id":155,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":1,"applydate":"2017-11-22","cusName":"王二小","id":150,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":"","applydate":"2017-11-22","cusName":"王二小","id":162,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"50"},{"account":11.11,"actPeriod":"","applydate":"2017-11-22","cusName":"王二小王二小王二小王二小","id":161,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"50"},{"account":2.52,"actPeriod":"","applydate":"2017-11-21","cusName":"方兰东","id":149,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":1,"purpose":"yyyyyyyyy","status":"50"},{"account":32.12,"actPeriod":"","applydate":"2017-11-21","cusName":"penda11111","id":147,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":3,"purpose":"777777777777777777777777777777777777yy","status":"50"},{"account":100,"actPeriod":"","applydate":"2017-11-20","cusName":"BIGBANG","id":157,"managerRation":10,"mobilephone":"13301010101","officeName":"蜀山一号分店下的二级分店","period":6,"purpose":"满意鼎折覆餗","status":"51"},{"account":89.52,"actPeriod":"","applydate":"2017-11-20","cusName":"LEON YAN","id":146,"managerRation":20,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":6,"purpose":"999999999999999999999999999999testing","status":"51"}],"page":{"currentPage":1,"currentPageSum":0,"end":10,"pages":4,"pernum":10,"start":0,"total":31},"type":0}
         * roleIdList : [509]
         */

        private MessageCountBean messageCount;
        private PageDataListBean pageDataList;
        private String deptName;
        private String officeName;
        private List<Integer> roleIdList;


        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getOfficeName() {
            return officeName;
        }

        public void setOfficeName(String officeName) {
            this.officeName = officeName;
        }


        public MessageCountBean getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(MessageCountBean messageCount) {
            this.messageCount = messageCount;
        }

        public PageDataListBean getPageDataList() {
            return pageDataList;
        }

        public void setPageDataList(PageDataListBean pageDataList) {
            this.pageDataList = pageDataList;
        }

        public List<Integer> getRoleIdList() {
            return roleIdList;
        }

        public void setRoleIdList(List<Integer> roleIdList) {
            this.roleIdList = roleIdList;
        }

        public static class MessageCountBean {
            /**
             * messageRole338： 1：代表风控审批有消息		0：代表无
             * messageRole509： 1：代表总经理审批有消息		0：代表无
             * messageRole335： 1：代表门店一审有消息		0：代表无
             * messageRole523： 1：代表部门审批有消息		0：代表无
             */

            private int messageRole338;
            private int messageRole509;
            private int messageRole335;
            private int messageRole523;

            public int getMessageRole338() {
                return messageRole338;
            }

            public void setMessageRole338(int messageRole338) {
                this.messageRole338 = messageRole338;
            }

            public int getMessageRole509() {
                return messageRole509;
            }

            public void setMessageRole509(int messageRole509) {
                this.messageRole509 = messageRole509;
            }


            public int getMessageRole335() {
                return messageRole335;
            }

            public void setMessageRole335(int messageRole335) {
                this.messageRole335 = messageRole335;
            }

            public int getMessageRole523() {
                return messageRole523;
            }

            public void setMessageRole523(int messageRole523) {
                this.messageRole523 = messageRole523;
            }


        }

        public static class PageDataListBean {
            /**
             * list : [{"account":2.52,"actPeriod":"","applydate":"2017-11-23","cusName":"蜀山","id":160,"managerRation":2.52,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":1,"purpose":"testing","status":"51"},{"account":11.11,"actPeriod":5,"applydate":"2017-11-22","cusName":"王二小","id":163,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":3,"applydate":"2017-11-22","cusName":"林漪","id":155,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":1,"applydate":"2017-11-22","cusName":"王二小","id":150,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"51"},{"account":11.11,"actPeriod":"","applydate":"2017-11-22","cusName":"王二小","id":162,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"50"},{"account":11.11,"actPeriod":"","applydate":"2017-11-22","cusName":"王二小王二小王二小王二小","id":161,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":5,"purpose":"11111111111111111111111111111","status":"50"},{"account":2.52,"actPeriod":"","applydate":"2017-11-21","cusName":"方兰东","id":149,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":1,"purpose":"yyyyyyyyy","status":"50"},{"account":32.12,"actPeriod":"","applydate":"2017-11-21","cusName":"penda11111","id":147,"managerRation":12,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":3,"purpose":"777777777777777777777777777777777777yy","status":"50"},{"account":100,"actPeriod":"","applydate":"2017-11-20","cusName":"BIGBANG","id":157,"managerRation":10,"mobilephone":"13301010101","officeName":"蜀山一号分店下的二级分店","period":6,"purpose":"满意鼎折覆餗","status":"51"},{"account":89.52,"actPeriod":"","applydate":"2017-11-20","cusName":"LEON YAN","id":146,"managerRation":20,"mobilephone":"13301010101","officeName":"安徽豆宝宝","period":6,"purpose":"999999999999999999999999999999testing","status":"51"}]
             * page : {"currentPage":1,"currentPageSum":0,"end":10,"pages":4,"pernum":10,"start":0,"total":31}
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
                 * end : 10
                 * pages : 4
                 * pernum : 10
                 * start : 0
                 * total : 31
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
                 * account : 2.52
                 * actPeriod :
                 * applydate : 2017-11-23
                 * cusName : 蜀山
                 * id : 160
                 * managerRation : 2.52
                 * mobilephone : 13301010101
                 * officeName : 安徽豆宝宝
                 * period : 1
                 * purpose : testing
                 * status : 51
                 */

                private double account;
                private String actPeriod;
                private String applydate;
                private String cusName;
                private int id;
                private double managerRation;
                private String mobilephone;
                private String officeName;
                private int period;
                private String purpose;
                private String status;

                public double getAccount() {
                    return account;
                }

                public void setAccount(double account) {
                    this.account = account;
                }

                public String getActPeriod() {
                    return actPeriod;
                }

                public void setActPeriod(String actPeriod) {
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

                public double getManagerRation() {
                    return managerRation;
                }

                public void setManagerRation(double managerRation) {
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
