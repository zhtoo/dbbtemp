package com.hs.doubaobao.bean;

/**
 * 作者：zhanghaitao on 2017/9/20 16:56
 * 邮箱：820159571@qq.com
 *
 * @describe:参考bean
 */

public class ReferenceBean {


    /**
     * resCode : 1
     * resData : {"customerRation":{"buildAddrString":"","buildBorrowTimeString":"12-24/月","buildTypeString":"","carString":"","cname":"蜀山","creditNumOne":"","creditNumPer":"","creditNumThree":"","creditNumTotal":"","creditString":"","familyString":"","finalRation":"6","finalRationBa":"","finalRationBbt":"","finalRationBt":"","finalRationCar":"","finalRationCredit":"","finalRationFamily":"","finalRationProprietor":"","finalRationWorkunit":"","operatorName":"豆宝宝超级管理员","proprietorString":"","reserveNum":"","reservePwd":"","socialNum":"","socialPwd":"","wanglaNum":"","wanglaPwd":"","wanglaVercode":"","workunitString":""}}
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
         * customerRation : {"buildAddrString":"","buildBorrowTimeString":"12-24/月","buildTypeString":"","carString":"","cname":"蜀山","creditNumOne":"","creditNumPer":"","creditNumThree":"","creditNumTotal":"","creditString":"","familyString":"","finalRation":"6","finalRationBa":"","finalRationBbt":"","finalRationBt":"","finalRationCar":"","finalRationCredit":"","finalRationFamily":"","finalRationProprietor":"","finalRationWorkunit":"","operatorName":"豆宝宝超级管理员","proprietorString":"","reserveNum":"","reservePwd":"","socialNum":"","socialPwd":"","wanglaNum":"","wanglaPwd":"","wanglaVercode":"","workunitString":""}
         */

        private CustomerRationBean customerRation;

        public CustomerRationBean getCustomerRation() {
            return customerRation;
        }

        public void setCustomerRation(CustomerRationBean customerRation) {
            this.customerRation = customerRation;
        }

        public static class CustomerRationBean {
            /**
             * buildAddrString :
             * buildBorrowTimeString : 12-24/月
             * buildTypeString :
             * carString :
             * cname : 蜀山
             * creditNumOne :
             * creditNumPer :
             * creditNumThree :
             * creditNumTotal :
             * creditString :
             * familyString :
             * finalRation : 6
             * finalRationBa :
             * finalRationBbt :
             * finalRationBt :
             * finalRationCar :
             * finalRationCredit :
             * finalRationFamily :
             * finalRationProprietor :
             * finalRationWorkunit :
             * operatorName : 豆宝宝超级管理员
             * proprietorString :
             * reserveNum :
             * reservePwd :
             * socialNum :
             * socialPwd :
             * wanglaNum :
             * wanglaPwd :
             * wanglaVercode :
             * workunitString :
             */

            private String buildAddrString;
            private String buildBorrowTimeString;
            private String buildTypeString;
            private String carString;
            private String cname;
            private String creditNumOne;
            private String creditNumPer;
            private String creditNumThree;
            private String creditNumTotal;
            private String creditString;
            private String familyString;
            private String finalRation;
            private String finalRationBa;
            private String finalRationBbt;
            private String finalRationBt;
            private String finalRationCar;
            private String finalRationCredit;
            private String finalRationFamily;
            private String finalRationProprietor;
            private String finalRationWorkunit;
            private String operatorName;
            private String proprietorString;
            private String reserveNum;
            private String reservePwd;
            private String socialNum;
            private String socialPwd;
            private String wanglaNum;
            private String wanglaPwd;
            private String wanglaVercode;
            private String workunitString;

            public String getBuildAddrString() {
                return buildAddrString;
            }

            public void setBuildAddrString(String buildAddrString) {
                this.buildAddrString = buildAddrString;
            }

            public String getBuildBorrowTimeString() {
                return buildBorrowTimeString;
            }

            public void setBuildBorrowTimeString(String buildBorrowTimeString) {
                this.buildBorrowTimeString = buildBorrowTimeString;
            }

            public String getBuildTypeString() {
                return buildTypeString;
            }

            public void setBuildTypeString(String buildTypeString) {
                this.buildTypeString = buildTypeString;
            }

            public String getCarString() {
                return carString;
            }

            public void setCarString(String carString) {
                this.carString = carString;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public String getCreditNumOne() {
                return creditNumOne;
            }

            public void setCreditNumOne(String creditNumOne) {
                this.creditNumOne = creditNumOne;
            }

            public String getCreditNumPer() {
                return creditNumPer;
            }

            public void setCreditNumPer(String creditNumPer) {
                this.creditNumPer = creditNumPer;
            }

            public String getCreditNumThree() {
                return creditNumThree;
            }

            public void setCreditNumThree(String creditNumThree) {
                this.creditNumThree = creditNumThree;
            }

            public String getCreditNumTotal() {
                return creditNumTotal;
            }

            public void setCreditNumTotal(String creditNumTotal) {
                this.creditNumTotal = creditNumTotal;
            }

            public String getCreditString() {
                return creditString;
            }

            public void setCreditString(String creditString) {
                this.creditString = creditString;
            }

            public String getFamilyString() {
                return familyString;
            }

            public void setFamilyString(String familyString) {
                this.familyString = familyString;
            }

            public String getFinalRation() {
                return finalRation;
            }

            public void setFinalRation(String finalRation) {
                this.finalRation = finalRation;
            }

            public String getFinalRationBa() {
                return finalRationBa;
            }

            public void setFinalRationBa(String finalRationBa) {
                this.finalRationBa = finalRationBa;
            }

            public String getFinalRationBbt() {
                return finalRationBbt;
            }

            public void setFinalRationBbt(String finalRationBbt) {
                this.finalRationBbt = finalRationBbt;
            }

            public String getFinalRationBt() {
                return finalRationBt;
            }

            public void setFinalRationBt(String finalRationBt) {
                this.finalRationBt = finalRationBt;
            }

            public String getFinalRationCar() {
                return finalRationCar;
            }

            public void setFinalRationCar(String finalRationCar) {
                this.finalRationCar = finalRationCar;
            }

            public String getFinalRationCredit() {
                return finalRationCredit;
            }

            public void setFinalRationCredit(String finalRationCredit) {
                this.finalRationCredit = finalRationCredit;
            }

            public String getFinalRationFamily() {
                return finalRationFamily;
            }

            public void setFinalRationFamily(String finalRationFamily) {
                this.finalRationFamily = finalRationFamily;
            }

            public String getFinalRationProprietor() {
                return finalRationProprietor;
            }

            public void setFinalRationProprietor(String finalRationProprietor) {
                this.finalRationProprietor = finalRationProprietor;
            }

            public String getFinalRationWorkunit() {
                return finalRationWorkunit;
            }

            public void setFinalRationWorkunit(String finalRationWorkunit) {
                this.finalRationWorkunit = finalRationWorkunit;
            }

            public String getOperatorName() {
                return operatorName;
            }

            public void setOperatorName(String operatorName) {
                this.operatorName = operatorName;
            }

            public String getProprietorString() {
                return proprietorString;
            }

            public void setProprietorString(String proprietorString) {
                this.proprietorString = proprietorString;
            }

            public String getReserveNum() {
                return reserveNum;
            }

            public void setReserveNum(String reserveNum) {
                this.reserveNum = reserveNum;
            }

            public String getReservePwd() {
                return reservePwd;
            }

            public void setReservePwd(String reservePwd) {
                this.reservePwd = reservePwd;
            }

            public String getSocialNum() {
                return socialNum;
            }

            public void setSocialNum(String socialNum) {
                this.socialNum = socialNum;
            }

            public String getSocialPwd() {
                return socialPwd;
            }

            public void setSocialPwd(String socialPwd) {
                this.socialPwd = socialPwd;
            }

            public String getWanglaNum() {
                return wanglaNum;
            }

            public void setWanglaNum(String wanglaNum) {
                this.wanglaNum = wanglaNum;
            }

            public String getWanglaPwd() {
                return wanglaPwd;
            }

            public void setWanglaPwd(String wanglaPwd) {
                this.wanglaPwd = wanglaPwd;
            }

            public String getWanglaVercode() {
                return wanglaVercode;
            }

            public void setWanglaVercode(String wanglaVercode) {
                this.wanglaVercode = wanglaVercode;
            }

            public String getWorkunitString() {
                return workunitString;
            }

            public void setWorkunitString(String workunitString) {
                this.workunitString = workunitString;
            }
        }
    }
}
