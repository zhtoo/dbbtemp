package com.hs.doubaobao.model.AddLoanTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： Tom on 2018/1/20 20:33
 * @email： 820159571@qq.com
 *  创建单例模式，方便数据修改。
 */
public class ApplyInfoBean {

    private static ApplyInfoBean bean;


    private ApplyInfoBean() {
        resData = new ResDataBean();
        resData.setBorrowdataModel(new ResDataBean.BorrowdataModelBean());
        ResDataBean.BorrowdataModelBean borrowdataModel = resData.getBorrowdataModel();
        borrowdataModel.setBorrowModel(new ResDataBean.BorrowdataModelBean.BorrowModelBean());
        borrowdataModel.setCustomerInfo(new ResDataBean.BorrowdataModelBean.CustomerInfoBean());
        borrowdataModel.setCoborrow(new ResDataBean.BorrowdataModelBean.CoborrowBean());
        borrowdataModel.setCarInfo(new ResDataBean.BorrowdataModelBean.CarInfoBean());
        borrowdataModel.setUclList(new ArrayList<ResDataBean.BorrowdataModelBean.UclListBean>());
        borrowdataModel.setCertificates(new ResDataBean.BorrowdataModelBean.CertificatesBean());
        borrowdataModel.setCustomerRation(new ResDataBean.BorrowdataModelBean.CustomerRationBean());
        borrowdataModel.setPictureList(new ArrayList<ResDataBean.BorrowdataModelBean.PictureListBean>());
        borrowdataModel.setVideoList(new ArrayList<ResDataBean.BorrowdataModelBean.VideoListBean>());
    }

    public static ApplyInfoBean getInstance() {
        if (bean == null) {
            bean = new ApplyInfoBean();
        }
        return bean;
    }

    public static void setInstance(ApplyInfoBean newBean) {
        if (newBean == null) {
            bean = new ApplyInfoBean();
        }else {
            bean = null ;
            bean = newBean;
        }
    }

    /**
     * resCode : 1
     * resData : {"borrowdataModel":{"borrowModel":{"account":12,"applydate":"2017-10-24","approveContent":"的防守打法","id":166,"period":2,"purpose":"2","type":"01"},"carInfo":{"brand":"","buyDate":"","cardid":"","color":"","id":166,"monthlyMoney":0,"otherInfo":"","owner":"","price":0,"status":""},"certificates":{"abchelordom":"","accountBook":"","accountStatement":"","bankCard":"","bill":"","businessLicense":"","creditReport":"","divorceAgreement":"","divorceCertificate":"","driving":"","employmentCertify":"","hdw":"","houseCard":"","id":170,"identityCard":"","license":"","marriageLicense":"","mortgageContract":"","other":"","providentFund":"","purchaseContract":"","rfw":"","socialSecurity":""},"coborrow":{"birth":"","cardid":"","coname":"","crelationship":"","domicile":"","exitingBuildAddr":"","extPhone":"","id":189,"isBusinessOwner":"","mobilephone":"","monthlyIncome":0,"phone":"","providentFund":0,"sex":0,"socialSecurity":0,"workunitAge":"","workunitArea":"","workunitCity":"","workunitDepartment":"","workunitMark":"","workunitName":"","workunitNature":0,"workunitPlot":"","workunitProvince":"","workunitRoom":"","workunitStreet":"","workunitTower":""},"customerInfo":{"alipay":"","birth":"1993-06-19","buildPrice":"","buildStauts":"","carStauts":"","cardId":"411526199306190070","cname":"f","coborrowerStatus":0,"domicile":"f","exitingBuildAcreage":0,"exitingBuildAddr":"f","exitingBuildLivetime":"","id":172,"isBusinessOwner":0,"jobdepartment":"","jobdepartmentCount":"23","marriage":2,"mobilephone":"13521613953","monthlyWage":12312,"opinion":"","otherBuildAcreage":0,"otherBuildInfo":"","otherBuildProperty":"","ownBuildAcreage":12,"ownBuildAddr":"f","ownBuildProperty":1,"qq":"","reservedFunds":0,"sex":1,"socialSecurity":0,"workunitAge":"","workunitArea":"","workunitCity":"","workunitExtPhone":"","workunitMark":"","workunitName":"","workunitNature":"","workunitPhone":"","workunitPlot":"","workunitPostcode":"","workunitProvince":"","workunitRoom":"","workunitStreet":"","workunitTower":""},"customerRation":{"buildAddr":"2","buildBorrowTime":1,"buildType":2,"car":"","credit":2,"creditNumOne":"12","creditNumPer":"3","creditNumThree":"3","creditNumTotal":"2","family":"1","finalRation":"28","finalRationBa":"12000","finalRationBbt":"1","finalRationBt":"2","finalRationCar":"3","finalRationCredit":"7","finalRationFamily":"4","finalRationProprietor":"6","finalRationWorkunit":"5","id":93,"managerRation":0,"proprietor":"2","reserveNum":"azsdfg944","reservePwd":"zhaojie123456","riskControl":0,"socialNum":"azsdfg944","socialPwd":"zhaojie123456","wanglaNum":"azsdfg944","wanglaPwd":"228881441","wanglaVercode":"12345623","workunit":"2"},"pictureList":[{"category":12,"id":2381,"name":"yyzz - 副本.jpg","path":"http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html","pathTure":"http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html","size":0,"type":"1"}],"uclList":[{"id":764,"name":"小A","notice":1,"phone":"15655507878","relation":"配偶","type":0},{"id":765,"name":"吊死扶伤","notice":1,"phone":"13521613953","relation":"父母","type":0},{"id":766,"name":"士大夫","notice":1,"phone":"15878789877","relation":"子女","type":1},{"id":767,"name":"士大夫","notice":2,"phone":"13521613953","relation":"亲戚","type":1}],"videoList":[{"category":12,"id":2381,"name":"yyzz - 副本.jpg","path":"http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html","pathTure":"http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html","size":0,"type":"1"}]},"userId":1}
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
         * borrowdataModel : {"borrowModel":{"account":12,"applydate":"2017-10-24","approveContent":"的防守打法","id":166,"period":2,"purpose":"2","type":"01"},"carInfo":{"brand":"","buyDate":"","cardid":"","color":"","id":166,"monthlyMoney":0,"otherInfo":"","owner":"","price":0,"status":""},"certificates":{"abchelordom":"","accountBook":"","accountStatement":"","bankCard":"","bill":"","businessLicense":"","creditReport":"","divorceAgreement":"","divorceCertificate":"","driving":"","employmentCertify":"","hdw":"","houseCard":"","id":170,"identityCard":"","license":"","marriageLicense":"","mortgageContract":"","other":"","providentFund":"","purchaseContract":"","rfw":"","socialSecurity":""},"coborrow":{"birth":"","cardid":"","coname":"","crelationship":"","domicile":"","exitingBuildAddr":"","extPhone":"","id":189,"isBusinessOwner":"","mobilephone":"","monthlyIncome":0,"phone":"","providentFund":0,"sex":0,"socialSecurity":0,"workunitAge":"","workunitArea":"","workunitCity":"","workunitDepartment":"","workunitMark":"","workunitName":"","workunitNature":0,"workunitPlot":"","workunitProvince":"","workunitRoom":"","workunitStreet":"","workunitTower":""},"customerInfo":{"alipay":"","birth":"1993-06-19","buildPrice":"","buildStauts":"","carStauts":"","cardId":"411526199306190070","cname":"f","coborrowerStatus":0,"domicile":"f","exitingBuildAcreage":0,"exitingBuildAddr":"f","exitingBuildLivetime":"","id":172,"isBusinessOwner":0,"jobdepartment":"","jobdepartmentCount":"23","marriage":2,"mobilephone":"13521613953","monthlyWage":12312,"opinion":"","otherBuildAcreage":0,"otherBuildInfo":"","otherBuildProperty":"","ownBuildAcreage":12,"ownBuildAddr":"f","ownBuildProperty":1,"qq":"","reservedFunds":0,"sex":1,"socialSecurity":0,"workunitAge":"","workunitArea":"","workunitCity":"","workunitExtPhone":"","workunitMark":"","workunitName":"","workunitNature":"","workunitPhone":"","workunitPlot":"","workunitPostcode":"","workunitProvince":"","workunitRoom":"","workunitStreet":"","workunitTower":""},"customerRation":{"buildAddr":"2","buildBorrowTime":1,"buildType":2,"car":"","credit":2,"creditNumOne":"12","creditNumPer":"3","creditNumThree":"3","creditNumTotal":"2","family":"1","finalRation":"28","finalRationBa":"12000","finalRationBbt":"1","finalRationBt":"2","finalRationCar":"3","finalRationCredit":"7","finalRationFamily":"4","finalRationProprietor":"6","finalRationWorkunit":"5","id":93,"managerRation":0,"proprietor":"2","reserveNum":"azsdfg944","reservePwd":"zhaojie123456","riskControl":0,"socialNum":"azsdfg944","socialPwd":"zhaojie123456","wanglaNum":"azsdfg944","wanglaPwd":"228881441","wanglaVercode":"12345623","workunit":"2"},"pictureList":[{"category":12,"id":2381,"name":"yyzz - 副本.jpg","path":"http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html","pathTure":"http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html","size":0,"type":"1"}],"uclList":[{"id":764,"name":"小A","notice":1,"phone":"15655507878","relation":"配偶","type":0},{"id":765,"name":"吊死扶伤","notice":1,"phone":"13521613953","relation":"父母","type":0},{"id":766,"name":"士大夫","notice":1,"phone":"15878789877","relation":"子女","type":1},{"id":767,"name":"士大夫","notice":2,"phone":"13521613953","relation":"亲戚","type":1}],"videoList":[{"category":12,"id":2381,"name":"yyzz - 副本.jpg","path":"http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html","pathTure":"http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html","size":0,"type":"1"}]}
         * userId : 1
         */

        private BorrowdataModelBean borrowdataModel;
        private int userId;

        public BorrowdataModelBean getBorrowdataModel() {
            return borrowdataModel;
        }

        public void setBorrowdataModel(BorrowdataModelBean borrowdataModel) {
            this.borrowdataModel = borrowdataModel;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class BorrowdataModelBean {
            /**
             * borrowModel : {"account":12,"applydate":"2017-10-24","approveContent":"的防守打法","id":166,"period":2,"purpose":"2","type":"01"}
             * carInfo : {"brand":"","buyDate":"","cardid":"","color":"","id":166,"monthlyMoney":0,"otherInfo":"","owner":"","price":0,"status":""}
             * certificates : {"abchelordom":"","accountBook":"","accountStatement":"","bankCard":"","bill":"","businessLicense":"","creditReport":"","divorceAgreement":"","divorceCertificate":"","driving":"","employmentCertify":"","hdw":"","houseCard":"","id":170,"identityCard":"","license":"","marriageLicense":"","mortgageContract":"","other":"","providentFund":"","purchaseContract":"","rfw":"","socialSecurity":""}
             * coborrow : {"birth":"","cardid":"","coname":"","crelationship":"","domicile":"","exitingBuildAddr":"","extPhone":"","id":189,"isBusinessOwner":"","mobilephone":"","monthlyIncome":0,"phone":"","providentFund":0,"sex":0,"socialSecurity":0,"workunitAge":"","workunitArea":"","workunitCity":"","workunitDepartment":"","workunitMark":"","workunitName":"","workunitNature":0,"workunitPlot":"","workunitProvince":"","workunitRoom":"","workunitStreet":"","workunitTower":""}
             * customerInfo : {"alipay":"","birth":"1993-06-19","buildPrice":"","buildStauts":"","carStauts":"","cardId":"411526199306190070","cname":"f","coborrowerStatus":0,"domicile":"f","exitingBuildAcreage":0,"exitingBuildAddr":"f","exitingBuildLivetime":"","id":172,"isBusinessOwner":0,"jobdepartment":"","jobdepartmentCount":"23","marriage":2,"mobilephone":"13521613953","monthlyWage":12312,"opinion":"","otherBuildAcreage":0,"otherBuildInfo":"","otherBuildProperty":"","ownBuildAcreage":12,"ownBuildAddr":"f","ownBuildProperty":1,"qq":"","reservedFunds":0,"sex":1,"socialSecurity":0,"workunitAge":"","workunitArea":"","workunitCity":"","workunitExtPhone":"","workunitMark":"","workunitName":"","workunitNature":"","workunitPhone":"","workunitPlot":"","workunitPostcode":"","workunitProvince":"","workunitRoom":"","workunitStreet":"","workunitTower":""}
             * customerRation : {"buildAddr":"2","buildBorrowTime":1,"buildType":2,"car":"","credit":2,"creditNumOne":"12","creditNumPer":"3","creditNumThree":"3","creditNumTotal":"2","family":"1","finalRation":"28","finalRationBa":"12000","finalRationBbt":"1","finalRationBt":"2","finalRationCar":"3","finalRationCredit":"7","finalRationFamily":"4","finalRationProprietor":"6","finalRationWorkunit":"5","id":93,"managerRation":0,"proprietor":"2","reserveNum":"azsdfg944","reservePwd":"zhaojie123456","riskControl":0,"socialNum":"azsdfg944","socialPwd":"zhaojie123456","wanglaNum":"azsdfg944","wanglaPwd":"228881441","wanglaVercode":"12345623","workunit":"2"}
             * pictureList : [{"category":12,"id":2381,"name":"yyzz - 副本.jpg","path":"http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html","pathTure":"http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html","size":0,"type":"1"}]
             * uclList : [{"id":764,"name":"小A","notice":1,"phone":"15655507878","relation":"配偶","type":0},{"id":765,"name":"吊死扶伤","notice":1,"phone":"13521613953","relation":"父母","type":0},{"id":766,"name":"士大夫","notice":1,"phone":"15878789877","relation":"子女","type":1},{"id":767,"name":"士大夫","notice":2,"phone":"13521613953","relation":"亲戚","type":1}]
             * videoList : [{"category":12,"id":2381,"name":"yyzz - 副本.jpg","path":"http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html","pathTure":"http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html","size":0,"type":"1"}]
             */

            private BorrowModelBean borrowModel;
            private CarInfoBean carInfo;
            private CertificatesBean certificates;
            private CoborrowBean coborrow;
            private CustomerInfoBean customerInfo;
            private CustomerRationBean customerRation;
            private List<PictureListBean> pictureList;
            private List<UclListBean> uclList;
            private List<VideoListBean> videoList;

            public BorrowModelBean getBorrowModel() {
                return borrowModel;
            }

            public void setBorrowModel(BorrowModelBean borrowModel) {
                this.borrowModel = borrowModel;
            }

            public CarInfoBean getCarInfo() {
                return carInfo;
            }

            public void setCarInfo(CarInfoBean carInfo) {
                this.carInfo = carInfo;
            }

            public CertificatesBean getCertificates() {
                return certificates;
            }

            public void setCertificates(CertificatesBean certificates) {
                this.certificates = certificates;
            }

            public CoborrowBean getCoborrow() {
                return coborrow;
            }

            public void setCoborrow(CoborrowBean coborrow) {
                this.coborrow = coborrow;
            }

            public CustomerInfoBean getCustomerInfo() {
                return customerInfo;
            }

            public void setCustomerInfo(CustomerInfoBean customerInfo) {
                this.customerInfo = customerInfo;
            }

            public CustomerRationBean getCustomerRation() {
                return customerRation;
            }

            public void setCustomerRation(CustomerRationBean customerRation) {
                this.customerRation = customerRation;
            }

            public List<PictureListBean> getPictureList() {
                return pictureList;
            }

            public void setPictureList(List<PictureListBean> pictureList) {
                this.pictureList = pictureList;
            }

            public List<UclListBean> getUclList() {
                return uclList;
            }

            public void setUclList(List<UclListBean> uclList) {
                this.uclList = uclList;
            }

            public List<VideoListBean> getVideoList() {
                return videoList;
            }

            public void setVideoList(List<VideoListBean> videoList) {
                this.videoList = videoList;
            }

            public static class BorrowModelBean {
                /**
                 * account : 12
                 * applydate : 2017-10-24
                 * approveContent : 的防守打法
                 * id : 166
                 * period : 2
                 * purpose : 2
                 * type : 01
                 */

                private int account;
                private String applydate;
                private String approveContent;
                private int id;
                private int period;
                private String purpose;
                private String type;

                public int getAccount() {
                    return account;
                }

                public void setAccount(int account) {
                    this.account = account;
                }

                public String getApplydate() {
                    return applydate;
                }

                public void setApplydate(String applydate) {
                    this.applydate = applydate;
                }

                public String getApproveContent() {
                    return approveContent;
                }

                public void setApproveContent(String approveContent) {
                    this.approveContent = approveContent;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public static class CarInfoBean {
                /**
                 * brand :
                 * buyDate :
                 * cardid :
                 * color :
                 * id : 166
                 * monthlyMoney : 0
                 * otherInfo :
                 * owner :
                 * price : 0
                 * status :
                 */

                private String brand;
                private String buyDate;
                private String cardid;
                private String color;
                private int id;
                private int monthlyMoney;
                private String otherInfo;
                private String owner;
                private int price;
                private String status;

                public String getBrand() {
                    return brand;
                }

                public void setBrand(String brand) {
                    this.brand = brand;
                }

                public String getBuyDate() {
                    return buyDate;
                }

                public void setBuyDate(String buyDate) {
                    this.buyDate = buyDate;
                }

                public String getCardid() {
                    return cardid;
                }

                public void setCardid(String cardid) {
                    this.cardid = cardid;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getMonthlyMoney() {
                    return monthlyMoney;
                }

                public void setMonthlyMoney(int monthlyMoney) {
                    this.monthlyMoney = monthlyMoney;
                }

                public String getOtherInfo() {
                    return otherInfo;
                }

                public void setOtherInfo(String otherInfo) {
                    this.otherInfo = otherInfo;
                }

                public String getOwner() {
                    return owner;
                }

                public void setOwner(String owner) {
                    this.owner = owner;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }

            public static class CertificatesBean {
                /**
                 * abchelordom :
                 * accountBook :
                 * accountStatement :
                 * bankCard :
                 * bill :
                 * businessLicense :
                 * creditReport :
                 * divorceAgreement :
                 * divorceCertificate :
                 * driving :
                 * employmentCertify :
                 * hdw :
                 * houseCard :
                 * id : 170
                 * identityCard :
                 * license :
                 * marriageLicense :
                 * mortgageContract :
                 * other :
                 * providentFund :
                 * purchaseContract :
                 * rfw :
                 * socialSecurity :
                 */

                private String abchelordom;
                private String accountBook;
                private String accountStatement;
                private String bankCard;
                private String bill;
                private String businessLicense;
                private String creditReport;
                private String divorceAgreement;
                private String divorceCertificate;
                private String driving;
                private String employmentCertify;
                private String hdw;
                private String houseCard;
                private int id;
                private String identityCard;
                private String license;
                private String marriageLicense;
                private String mortgageContract;
                private String other;
                private String providentFund;
                private String purchaseContract;
                private String rfw;
                private String socialSecurity;

                public String getAbchelordom() {
                    return abchelordom;
                }

                public void setAbchelordom(String abchelordom) {
                    this.abchelordom = abchelordom;
                }

                public String getAccountBook() {
                    return accountBook;
                }

                public void setAccountBook(String accountBook) {
                    this.accountBook = accountBook;
                }

                public String getAccountStatement() {
                    return accountStatement;
                }

                public void setAccountStatement(String accountStatement) {
                    this.accountStatement = accountStatement;
                }

                public String getBankCard() {
                    return bankCard;
                }

                public void setBankCard(String bankCard) {
                    this.bankCard = bankCard;
                }

                public String getBill() {
                    return bill;
                }

                public void setBill(String bill) {
                    this.bill = bill;
                }

                public String getBusinessLicense() {
                    return businessLicense;
                }

                public void setBusinessLicense(String businessLicense) {
                    this.businessLicense = businessLicense;
                }

                public String getCreditReport() {
                    return creditReport;
                }

                public void setCreditReport(String creditReport) {
                    this.creditReport = creditReport;
                }

                public String getDivorceAgreement() {
                    return divorceAgreement;
                }

                public void setDivorceAgreement(String divorceAgreement) {
                    this.divorceAgreement = divorceAgreement;
                }

                public String getDivorceCertificate() {
                    return divorceCertificate;
                }

                public void setDivorceCertificate(String divorceCertificate) {
                    this.divorceCertificate = divorceCertificate;
                }

                public String getDriving() {
                    return driving;
                }

                public void setDriving(String driving) {
                    this.driving = driving;
                }

                public String getEmploymentCertify() {
                    return employmentCertify;
                }

                public void setEmploymentCertify(String employmentCertify) {
                    this.employmentCertify = employmentCertify;
                }

                public String getHdw() {
                    return hdw;
                }

                public void setHdw(String hdw) {
                    this.hdw = hdw;
                }

                public String getHouseCard() {
                    return houseCard;
                }

                public void setHouseCard(String houseCard) {
                    this.houseCard = houseCard;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIdentityCard() {
                    return identityCard;
                }

                public void setIdentityCard(String identityCard) {
                    this.identityCard = identityCard;
                }

                public String getLicense() {
                    return license;
                }

                public void setLicense(String license) {
                    this.license = license;
                }

                public String getMarriageLicense() {
                    return marriageLicense;
                }

                public void setMarriageLicense(String marriageLicense) {
                    this.marriageLicense = marriageLicense;
                }

                public String getMortgageContract() {
                    return mortgageContract;
                }

                public void setMortgageContract(String mortgageContract) {
                    this.mortgageContract = mortgageContract;
                }

                public String getOther() {
                    return other;
                }

                public void setOther(String other) {
                    this.other = other;
                }

                public String getProvidentFund() {
                    return providentFund;
                }

                public void setProvidentFund(String providentFund) {
                    this.providentFund = providentFund;
                }

                public String getPurchaseContract() {
                    return purchaseContract;
                }

                public void setPurchaseContract(String purchaseContract) {
                    this.purchaseContract = purchaseContract;
                }

                public String getRfw() {
                    return rfw;
                }

                public void setRfw(String rfw) {
                    this.rfw = rfw;
                }

                public String getSocialSecurity() {
                    return socialSecurity;
                }

                public void setSocialSecurity(String socialSecurity) {
                    this.socialSecurity = socialSecurity;
                }
            }

            public static class CoborrowBean {
                /**
                 * birth :
                 * cardid :
                 * coname :
                 * crelationship :
                 * domicile :
                 * exitingBuildAddr :
                 * extPhone :
                 * id : 189
                 * isBusinessOwner :
                 * mobilephone :
                 * monthlyIncome : 0
                 * phone :
                 * providentFund : 0
                 * sex : 0
                 * socialSecurity : 0
                 * workunitAge :
                 * workunitArea :
                 * workunitCity :
                 * workunitDepartment :
                 * workunitMark :
                 * workunitName :
                 * workunitNature : 0
                 * workunitPlot :
                 * workunitProvince :
                 * workunitRoom :
                 * workunitStreet :
                 * workunitTower :
                 */

                private String birth;
                private String cardid;
                private String coname;
                private String crelationship;
                private String domicile;
                private String exitingBuildAddr;
                private String extPhone;
                private int id;
                private String isBusinessOwner;
                private String mobilephone;
                private int monthlyIncome;
                private String phone;
                private int providentFund;
                private int sex;
                private int socialSecurity;
                private String workunitAge;
                private String workunitArea;
                private String workunitCity;
                private String workunitDepartment;
                private String workunitMark;
                private String workunitName;
                private int workunitNature;
                private String workunitPlot;
                private String workunitProvince;
                private String workunitRoom;
                private String workunitStreet;
                private String workunitTower;

                public String getBirth() {
                    return birth;
                }

                public void setBirth(String birth) {
                    this.birth = birth;
                }

                public String getCardid() {
                    return cardid;
                }

                public void setCardid(String cardid) {
                    this.cardid = cardid;
                }

                public String getConame() {
                    return coname;
                }

                public void setConame(String coname) {
                    this.coname = coname;
                }

                public String getCrelationship() {
                    return crelationship;
                }

                public void setCrelationship(String crelationship) {
                    this.crelationship = crelationship;
                }

                public String getDomicile() {
                    return domicile;
                }

                public void setDomicile(String domicile) {
                    this.domicile = domicile;
                }

                public String getExitingBuildAddr() {
                    return exitingBuildAddr;
                }

                public void setExitingBuildAddr(String exitingBuildAddr) {
                    this.exitingBuildAddr = exitingBuildAddr;
                }

                public String getExtPhone() {
                    return extPhone;
                }

                public void setExtPhone(String extPhone) {
                    this.extPhone = extPhone;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIsBusinessOwner() {
                    return isBusinessOwner;
                }

                public void setIsBusinessOwner(String isBusinessOwner) {
                    this.isBusinessOwner = isBusinessOwner;
                }

                public String getMobilephone() {
                    return mobilephone;
                }

                public void setMobilephone(String mobilephone) {
                    this.mobilephone = mobilephone;
                }

                public int getMonthlyIncome() {
                    return monthlyIncome;
                }

                public void setMonthlyIncome(int monthlyIncome) {
                    this.monthlyIncome = monthlyIncome;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public int getProvidentFund() {
                    return providentFund;
                }

                public void setProvidentFund(int providentFund) {
                    this.providentFund = providentFund;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public int getSocialSecurity() {
                    return socialSecurity;
                }

                public void setSocialSecurity(int socialSecurity) {
                    this.socialSecurity = socialSecurity;
                }

                public String getWorkunitAge() {
                    return workunitAge;
                }

                public void setWorkunitAge(String workunitAge) {
                    this.workunitAge = workunitAge;
                }

                public String getWorkunitArea() {
                    return workunitArea;
                }

                public void setWorkunitArea(String workunitArea) {
                    this.workunitArea = workunitArea;
                }

                public String getWorkunitCity() {
                    return workunitCity;
                }

                public void setWorkunitCity(String workunitCity) {
                    this.workunitCity = workunitCity;
                }

                public String getWorkunitDepartment() {
                    return workunitDepartment;
                }

                public void setWorkunitDepartment(String workunitDepartment) {
                    this.workunitDepartment = workunitDepartment;
                }

                public String getWorkunitMark() {
                    return workunitMark;
                }

                public void setWorkunitMark(String workunitMark) {
                    this.workunitMark = workunitMark;
                }

                public String getWorkunitName() {
                    return workunitName;
                }

                public void setWorkunitName(String workunitName) {
                    this.workunitName = workunitName;
                }

                public int getWorkunitNature() {
                    return workunitNature;
                }

                public void setWorkunitNature(int workunitNature) {
                    this.workunitNature = workunitNature;
                }

                public String getWorkunitPlot() {
                    return workunitPlot;
                }

                public void setWorkunitPlot(String workunitPlot) {
                    this.workunitPlot = workunitPlot;
                }

                public String getWorkunitProvince() {
                    return workunitProvince;
                }

                public void setWorkunitProvince(String workunitProvince) {
                    this.workunitProvince = workunitProvince;
                }

                public String getWorkunitRoom() {
                    return workunitRoom;
                }

                public void setWorkunitRoom(String workunitRoom) {
                    this.workunitRoom = workunitRoom;
                }

                public String getWorkunitStreet() {
                    return workunitStreet;
                }

                public void setWorkunitStreet(String workunitStreet) {
                    this.workunitStreet = workunitStreet;
                }

                public String getWorkunitTower() {
                    return workunitTower;
                }

                public void setWorkunitTower(String workunitTower) {
                    this.workunitTower = workunitTower;
                }
            }

            public static class CustomerInfoBean {
                /**
                 * alipay :
                 * birth : 1993-06-19
                 * buildPrice :
                 * buildStauts :
                 * carStauts :
                 * cardId : 411526199306190070
                 * cname : f
                 * coborrowerStatus : 0
                 * domicile : f
                 * exitingBuildAcreage : 0
                 * exitingBuildAddr : f
                 * exitingBuildLivetime :
                 * id : 172
                 * isBusinessOwner : 0
                 * jobdepartment :
                 * jobdepartmentCount : 23
                 * marriage : 2
                 * mobilephone : 13521613953
                 * monthlyWage : 12312
                 * opinion :
                 * otherBuildAcreage : 0
                 * otherBuildInfo :
                 * otherBuildProperty :
                 * ownBuildAcreage : 12
                 * ownBuildAddr : f
                 * ownBuildProperty : 1
                 * qq :
                 * reservedFunds : 0
                 * sex : 1
                 * socialSecurity : 0
                 * workunitAge :
                 * workunitArea :
                 * workunitCity :
                 * workunitExtPhone :
                 * workunitMark :
                 * workunitName :
                 * workunitNature :
                 * workunitPhone :
                 * workunitPlot :
                 * workunitPostcode :
                 * workunitProvince :
                 * workunitRoom :
                 * workunitStreet :
                 * workunitTower :
                 */

                private String alipay;
                private String birth;
                private String buildPrice;
                private String buildStauts;
                private String carStauts;
                private String cardId;
                private String cname;
                private int coborrowerStatus;
                private String domicile;
                private int exitingBuildAcreage;
                private String exitingBuildAddr;
                private String exitingBuildLivetime;
                private int id;
                private int isBusinessOwner;
                private String jobdepartment;
                private String jobdepartmentCount;
                private int marriage;
                private String mobilephone;
                private int monthlyWage;
                private String opinion;
                private int otherBuildAcreage;
                private String otherBuildInfo;
                private String otherBuildProperty;
                private int ownBuildAcreage;
                private String ownBuildAddr;
                private int ownBuildProperty;
                private String qq;
                private int reservedFunds;
                private int sex;
                private int socialSecurity;
                private String workunitAge;
                private String workunitArea;
                private String workunitCity;
                private String workunitExtPhone;
                private String workunitMark;
                private String workunitName;
                private String workunitNature;
                private String workunitPhone;
                private String workunitPlot;
                private String workunitPostcode;
                private String workunitProvince;
                private String workunitRoom;
                private String workunitStreet;
                private String workunitTower;

                public String getAlipay() {
                    return alipay;
                }

                public void setAlipay(String alipay) {
                    this.alipay = alipay;
                }

                public String getBirth() {
                    return birth;
                }

                public void setBirth(String birth) {
                    this.birth = birth;
                }

                public String getBuildPrice() {
                    return buildPrice;
                }

                public void setBuildPrice(String buildPrice) {
                    this.buildPrice = buildPrice;
                }

                public String getBuildStauts() {
                    return buildStauts;
                }

                public void setBuildStauts(String buildStauts) {
                    this.buildStauts = buildStauts;
                }

                public String getCarStauts() {
                    return carStauts;
                }

                public void setCarStauts(String carStauts) {
                    this.carStauts = carStauts;
                }

                public String getCardId() {
                    return cardId;
                }

                public void setCardId(String cardId) {
                    this.cardId = cardId;
                }

                public String getCname() {
                    return cname;
                }

                public void setCname(String cname) {
                    this.cname = cname;
                }

                public int getCoborrowerStatus() {
                    return coborrowerStatus;
                }

                public void setCoborrowerStatus(int coborrowerStatus) {
                    this.coborrowerStatus = coborrowerStatus;
                }

                public String getDomicile() {
                    return domicile;
                }

                public void setDomicile(String domicile) {
                    this.domicile = domicile;
                }

                public int getExitingBuildAcreage() {
                    return exitingBuildAcreage;
                }

                public void setExitingBuildAcreage(int exitingBuildAcreage) {
                    this.exitingBuildAcreage = exitingBuildAcreage;
                }

                public String getExitingBuildAddr() {
                    return exitingBuildAddr;
                }

                public void setExitingBuildAddr(String exitingBuildAddr) {
                    this.exitingBuildAddr = exitingBuildAddr;
                }

                public String getExitingBuildLivetime() {
                    return exitingBuildLivetime;
                }

                public void setExitingBuildLivetime(String exitingBuildLivetime) {
                    this.exitingBuildLivetime = exitingBuildLivetime;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIsBusinessOwner() {
                    return isBusinessOwner;
                }

                public void setIsBusinessOwner(int isBusinessOwner) {
                    this.isBusinessOwner = isBusinessOwner;
                }

                public String getJobdepartment() {
                    return jobdepartment;
                }

                public void setJobdepartment(String jobdepartment) {
                    this.jobdepartment = jobdepartment;
                }

                public String getJobdepartmentCount() {
                    return jobdepartmentCount;
                }

                public void setJobdepartmentCount(String jobdepartmentCount) {
                    this.jobdepartmentCount = jobdepartmentCount;
                }

                public int getMarriage() {
                    return marriage;
                }

                public void setMarriage(int marriage) {
                    this.marriage = marriage;
                }

                public String getMobilephone() {
                    return mobilephone;
                }

                public void setMobilephone(String mobilephone) {
                    this.mobilephone = mobilephone;
                }

                public int getMonthlyWage() {
                    return monthlyWage;
                }

                public void setMonthlyWage(int monthlyWage) {
                    this.monthlyWage = monthlyWage;
                }

                public String getOpinion() {
                    return opinion;
                }

                public void setOpinion(String opinion) {
                    this.opinion = opinion;
                }

                public int getOtherBuildAcreage() {
                    return otherBuildAcreage;
                }

                public void setOtherBuildAcreage(int otherBuildAcreage) {
                    this.otherBuildAcreage = otherBuildAcreage;
                }

                public String getOtherBuildInfo() {
                    return otherBuildInfo;
                }

                public void setOtherBuildInfo(String otherBuildInfo) {
                    this.otherBuildInfo = otherBuildInfo;
                }

                public String getOtherBuildProperty() {
                    return otherBuildProperty;
                }

                public void setOtherBuildProperty(String otherBuildProperty) {
                    this.otherBuildProperty = otherBuildProperty;
                }

                public int getOwnBuildAcreage() {
                    return ownBuildAcreage;
                }

                public void setOwnBuildAcreage(int ownBuildAcreage) {
                    this.ownBuildAcreage = ownBuildAcreage;
                }

                public String getOwnBuildAddr() {
                    return ownBuildAddr;
                }

                public void setOwnBuildAddr(String ownBuildAddr) {
                    this.ownBuildAddr = ownBuildAddr;
                }

                public int getOwnBuildProperty() {
                    return ownBuildProperty;
                }

                public void setOwnBuildProperty(int ownBuildProperty) {
                    this.ownBuildProperty = ownBuildProperty;
                }

                public String getQq() {
                    return qq;
                }

                public void setQq(String qq) {
                    this.qq = qq;
                }

                public int getReservedFunds() {
                    return reservedFunds;
                }

                public void setReservedFunds(int reservedFunds) {
                    this.reservedFunds = reservedFunds;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public int getSocialSecurity() {
                    return socialSecurity;
                }

                public void setSocialSecurity(int socialSecurity) {
                    this.socialSecurity = socialSecurity;
                }

                public String getWorkunitAge() {
                    return workunitAge;
                }

                public void setWorkunitAge(String workunitAge) {
                    this.workunitAge = workunitAge;
                }

                public String getWorkunitArea() {
                    return workunitArea;
                }

                public void setWorkunitArea(String workunitArea) {
                    this.workunitArea = workunitArea;
                }

                public String getWorkunitCity() {
                    return workunitCity;
                }

                public void setWorkunitCity(String workunitCity) {
                    this.workunitCity = workunitCity;
                }

                public String getWorkunitExtPhone() {
                    return workunitExtPhone;
                }

                public void setWorkunitExtPhone(String workunitExtPhone) {
                    this.workunitExtPhone = workunitExtPhone;
                }

                public String getWorkunitMark() {
                    return workunitMark;
                }

                public void setWorkunitMark(String workunitMark) {
                    this.workunitMark = workunitMark;
                }

                public String getWorkunitName() {
                    return workunitName;
                }

                public void setWorkunitName(String workunitName) {
                    this.workunitName = workunitName;
                }

                public String getWorkunitNature() {
                    return workunitNature;
                }

                public void setWorkunitNature(String workunitNature) {
                    this.workunitNature = workunitNature;
                }

                public String getWorkunitPhone() {
                    return workunitPhone;
                }

                public void setWorkunitPhone(String workunitPhone) {
                    this.workunitPhone = workunitPhone;
                }

                public String getWorkunitPlot() {
                    return workunitPlot;
                }

                public void setWorkunitPlot(String workunitPlot) {
                    this.workunitPlot = workunitPlot;
                }

                public String getWorkunitPostcode() {
                    return workunitPostcode;
                }

                public void setWorkunitPostcode(String workunitPostcode) {
                    this.workunitPostcode = workunitPostcode;
                }

                public String getWorkunitProvince() {
                    return workunitProvince;
                }

                public void setWorkunitProvince(String workunitProvince) {
                    this.workunitProvince = workunitProvince;
                }

                public String getWorkunitRoom() {
                    return workunitRoom;
                }

                public void setWorkunitRoom(String workunitRoom) {
                    this.workunitRoom = workunitRoom;
                }

                public String getWorkunitStreet() {
                    return workunitStreet;
                }

                public void setWorkunitStreet(String workunitStreet) {
                    this.workunitStreet = workunitStreet;
                }

                public String getWorkunitTower() {
                    return workunitTower;
                }

                public void setWorkunitTower(String workunitTower) {
                    this.workunitTower = workunitTower;
                }
            }

            public static class CustomerRationBean {
                /**
                 * buildAddr : 2
                 * buildBorrowTime : 1
                 * buildType : 2
                 * car :
                 * credit : 2
                 * creditNumOne : 12
                 * creditNumPer : 3
                 * creditNumThree : 3
                 * creditNumTotal : 2
                 * family : 1
                 * finalRation : 28
                 * finalRationBa : 12000
                 * finalRationBbt : 1
                 * finalRationBt : 2
                 * finalRationCar : 3
                 * finalRationCredit : 7
                 * finalRationFamily : 4
                 * finalRationProprietor : 6
                 * finalRationWorkunit : 5
                 * id : 93
                 * managerRation : 0
                 * proprietor : 2
                 * reserveNum : azsdfg944
                 * reservePwd : zhaojie123456
                 * riskControl : 0
                 * socialNum : azsdfg944
                 * socialPwd : zhaojie123456
                 * wanglaNum : azsdfg944
                 * wanglaPwd : 228881441
                 * wanglaVercode : 12345623
                 * workunit : 2
                 */

                private String buildAddr;
                private int buildBorrowTime;
                private int buildType;
                private String car;
                private int credit;
                private String creditNumOne;
                private String creditNumPer;
                private String creditNumThree;
                private String creditNumTotal;
                private String family;
                private String finalRation;
                private String finalRationBa;
                private String finalRationBbt;
                private String finalRationBt;
                private String finalRationCar;
                private String finalRationCredit;
                private String finalRationFamily;
                private String finalRationProprietor;
                private String finalRationWorkunit;
                private int id;
                private int managerRation;
                private String proprietor;
                private String reserveNum;
                private String reservePwd;
                private int riskControl;
                private String socialNum;
                private String socialPwd;
                private String wanglaNum;
                private String wanglaPwd;
                private String wanglaVercode;
                private String workunit;

                public String getBuildAddr() {
                    return buildAddr;
                }

                public void setBuildAddr(String buildAddr) {
                    this.buildAddr = buildAddr;
                }

                public int getBuildBorrowTime() {
                    return buildBorrowTime;
                }

                public void setBuildBorrowTime(int buildBorrowTime) {
                    this.buildBorrowTime = buildBorrowTime;
                }

                public int getBuildType() {
                    return buildType;
                }

                public void setBuildType(int buildType) {
                    this.buildType = buildType;
                }

                public String getCar() {
                    return car;
                }

                public void setCar(String car) {
                    this.car = car;
                }

                public int getCredit() {
                    return credit;
                }

                public void setCredit(int credit) {
                    this.credit = credit;
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

                public String getFamily() {
                    return family;
                }

                public void setFamily(String family) {
                    this.family = family;
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

                public String getProprietor() {
                    return proprietor;
                }

                public void setProprietor(String proprietor) {
                    this.proprietor = proprietor;
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

                public int getRiskControl() {
                    return riskControl;
                }

                public void setRiskControl(int riskControl) {
                    this.riskControl = riskControl;
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

                public String getWorkunit() {
                    return workunit;
                }

                public void setWorkunit(String workunit) {
                    this.workunit = workunit;
                }
            }

            public static class PictureListBean {
                /**
                 * category : 12
                 * id : 2381
                 * name : yyzz - 副本.jpg
                 * path : http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html
                 * pathTure : http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html
                 * size : 0
                 * type : 1
                 */

                private int category;
                private int id;
                private String name;
                private String path;
                private String pathTure;
                private int size;
                private String type;

                public int getCategory() {
                    return category;
                }

                public void setCategory(int category) {
                    this.category = category;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getPathTure() {
                    return pathTure;
                }

                public void setPathTure(String pathTure) {
                    this.pathTure = pathTure;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public static class UclListBean {
                /**
                 * id : 764
                 * name : 小A
                 * notice : 1
                 * phone : 15655507878
                 * relation : 配偶
                 * type : 0
                 */

                private int id;
                private String name;
                private int notice;
                private String phone;
                private String relation;
                private int type;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getNotice() {
                    return notice;
                }

                public void setNotice(int notice) {
                    this.notice = notice;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getRelation() {
                    return relation;
                }

                public void setRelation(String relation) {
                    this.relation = relation;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }

            public static class VideoListBean {
                /**
                 * category : 12
                 * id : 2381
                 * name : yyzz - 副本.jpg
                 * path : http://192.168.1.246:8085/borrow/1712180876615498-FFD8FF/view.html
                 * pathTure : http://192.168.1.246:8085/imgTemp/1712180876615498-FFD8FF/view.html
                 * size : 0
                 * type : 1
                 */

                private int category;
                private int id;
                private String name;
                private String path;
                private String pathTure;
                private int size;
                private String type;

                public int getCategory() {
                    return category;
                }

                public void setCategory(int category) {
                    this.category = category;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getPathTure() {
                    return pathTure;
                }

                public void setPathTure(String pathTure) {
                    this.pathTure = pathTure;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
