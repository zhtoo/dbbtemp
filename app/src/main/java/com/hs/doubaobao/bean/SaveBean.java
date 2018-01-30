package com.hs.doubaobao.bean;

/**
 * 作者：zhanghaitao on 2018/1/30 13:34
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class SaveBean {


    /**
     * resCode : 1
     * resData : {"borrowId":382}
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
         * borrowId : 382
         */

        private int borrowId;

        public int getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(int borrowId) {
            this.borrowId = borrowId;
        }
    }
}
