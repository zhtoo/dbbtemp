package com.hs.doubaobao.model.AddLoanTable;


import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * @author： Tom on 2018/1/21 21:56
 * @email： 820159571@qq.com
 * <p>
 * set方法是将数据设置到对应的View上
 * change方法是将View上的数据设置到bean类中
 */
public class ApplyLendUtil {

    /**
     * 基本信息（必填1）
     * customerInfo:客户基本信息
     * cname                String     姓名
     * cardId               String     身份证号码
     * sex                  int        性别
     * marriage             int        婚姻状况，值：1 未婚  2：已婚  3：离异  4：丧偶
     * domicile             String     户籍所在地
     * mobilephone          String     移动电话
     */
    public static void setBasic(EditText mNameEdit,
                                EditText mIdCardEdit,
                                RadioButton mSexMale,
                                RadioButton mSexFemale,
                                TextView mMaritalStatusText,
                                EditText mDomicileEdit,
                                EditText mPhoneEdit) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();

        mNameEdit.setText(customerInfo.getCname());
        mIdCardEdit.setText(customerInfo.getCardId());
        int sex = customerInfo.getSex();
        //性别 1:男  0:女
        if (sex == 1) {
            mSexMale.setChecked(true);
        } else if (sex == 1) {
            mSexFemale.setChecked(true);
        } else {
            mSexMale.setChecked(false);
            mSexFemale.setChecked(false);
        }
        String[] marriageArr = {"","未婚","已婚","离异","丧偶"};

        String marriage = marriageArr[customerInfo.getMarriage()];
        mMaritalStatusText.setText(marriage);

        mDomicileEdit.setText(customerInfo.getDomicile());
        mPhoneEdit.setText(customerInfo.getMobilephone());
    }

    public static void changeBasic(EditText mNameEdit,
                                   EditText mIdCardEdit,
                                   RadioButton mSexMale, RadioButton mSexFemale,
                                   TextView mMaritalStatusText,
                                   EditText mDomicileEdit,
                                   EditText mPhoneEdit) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();

        customerInfo.setCname(mNameEdit.getText().toString().trim());
        customerInfo.setCardId(mIdCardEdit.getText().toString().trim());
        boolean maleChecked = mSexMale.isChecked();
        boolean femaleChecked = mSexFemale.isChecked();
        if(maleChecked){
            customerInfo.setSex(1);
        }else
        if(femaleChecked){
            customerInfo.setSex(0);
        }else {
            customerInfo.setSex(-1);
        }
        String[] marriageArr = {"","未婚","已婚","离异","丧偶"};
        String marriage = mMaritalStatusText.getText().toString().trim();
        for (int i = 0; i < marriageArr.length; i++) {
            if(marriage.equals(marriageArr[i])){
                customerInfo.setMarriage(i);
            }
        }
        customerInfo.setDomicile(mDomicileEdit.getText().toString().trim());
        customerInfo.setMobilephone(mPhoneEdit.getText().toString().trim());
    }


    /**
     * 基本信息（必填2）
     * customerInfo:客户基本信息
     * exitingBuildAddr     String     现居住地址
     * jobdepartmentCount   String     供养人数
     */

    public static void setLive() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();



    }

    public static void changeLive(
            TextView mLiveAddressEdit,
            EditText mLiveStreetEdit,
            EditText mSupportNumberEdit) {

        String liveAddress = mLiveAddressEdit.getText().toString().trim();
        String liveStreet = mLiveStreetEdit.getText().toString().trim();
        String liveSupportNumber = mSupportNumberEdit.getText().toString().trim();
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();

        customerInfo.setExitingBuildAddr(liveAddress+"-"+liveStreet);
        customerInfo.setJobdepartmentCount(liveSupportNumber);

    }

    /**
     * 基本信息（必填3）
     * customerInfo:客户基本信息
     * ownBuildAddr         String     自有房产地址
     * ownBuildAcreage      double     自有房产建筑面积
     * ownBuildProperty     int        自有房产性质，值：1：有房无贷款   2： 有房有贷款   3:回迁房  4：自建房
     * monthlyWage          double     工资月均收入
     *  buildPrice           double     每月房贷
     */
    public static void setAsset() {

    }

    public static void changeAsset(
            TextView mOwnAddressEdit,
            EditText mOwnStreetEdit,
            EditText mOwnHouseAreaEdit,
            TextView mOwnHousePropertyEdit,
            EditText mMonthlyIncomeEdit,
            EditText mBuildPriceEdit) {

        String ownBuildAddr = mOwnAddressEdit.getText().toString().trim();
        String ownBuildAddr2 = mOwnStreetEdit.getText().toString().trim();
        String ownBuildAcreage = mOwnHouseAreaEdit.getText().toString().trim();
        String ownBuildProperty = mOwnHousePropertyEdit.getText().toString().trim();
        String monthlyWage = mMonthlyIncomeEdit.getText().toString().trim();
        String buildPrice = mBuildPriceEdit.getText().toString().trim();
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();

        customerInfo.setOwnBuildAddr(ownBuildAddr+ownBuildAddr2);
        customerInfo.setOwnBuildAcreage(Integer.parseInt(ownBuildAcreage));
        String[] ownBuildPropertyArr = {"","有房无贷款","有房有贷款","回迁房","自建房"};

        for (int i = 0; i < ownBuildPropertyArr.length; i++) {
            if(ownBuildProperty.equals(ownBuildPropertyArr[i])){
                customerInfo.setOwnBuildProperty(i);
            }
        }
        customerInfo.setMonthlyWage(Integer.parseInt(monthlyWage));
        if(!TextUtils.isEmpty(buildPrice)){
            customerInfo.setBuildPrice(buildPrice);
        }

    }

    /**
     * 贷款事项（必填）Theloans
     * borrowModel：借款相关信息
     * type                 String    贷款类别，值： 01 汇民贷 02 汇商贷 03 汇业贷 04 汇车贷 06 汇房贷 05 汇农贷
     * account              double    贷款金额
     * period               int       申请期限，值：1:12期 2:18期 3:24期 4:36期 5:48期 6:60期
     * applydate            String    申请日期
     * purpose              String    借款用途
     */
    public static void setTheloans() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.BorrowModelBean borrowModel
                = bean.getResData().getBorrowdataModel().getBorrowModel();
        borrowModel.getType();
        borrowModel.getAccount();
        borrowModel.getPeriod();
        borrowModel.getApplydate();
        borrowModel.getPurpose();

    }

    public static void changeTheloans() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.BorrowModelBean borrowModel
                = bean.getResData().getBorrowdataModel().getBorrowModel();
        borrowModel.setType("");
        borrowModel.setAccount(0);
        borrowModel.setPeriod(0);
        borrowModel.setApplydate("");
        borrowModel.setPurpose("");
    }

    /**
     * 联系人信息
     * uclList:联系人信息 类型list<BorrowContant>
     * id                   int         联系人id
     * name                 String      姓名
     * relation             String      关系，值：配偶 ，父母，子女，兄弟姐妹，亲戚，朋友，其他
     * phone                String      手机号码
     * notice               int         是否知晓贷款，值：1是  2否
     * type                 int         联系人类型，值：1：一般联系人 ，2：直系亲属联系人
     */
    public static void setContacts(int position) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean> uclList
                = bean.getResData().getBorrowdataModel().getUclList();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean
                uclListBean = uclList.get(position);

        uclListBean.getName();
        uclListBean.getRelation();
        uclListBean.getPhone();
        uclListBean.getNotice();
        uclListBean.getType();

    }

    public static void changeContacts(int position) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean> uclList
                = bean.getResData().getBorrowdataModel().getUclList();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean
                uclListBean = uclList.get(position);

        uclListBean.setName("");
        uclListBean.setRelation("");
        uclListBean.setPhone("");
        uclListBean.setNotice(0);
        uclListBean.setType(0);
    }

    /**
     * 客户情况调查及意见CustomerSurveyOpinion
     * customerInfo---buildStauts          String     房产情况
     * customerInfo---carStauts            String     车辆情况
     * customerInfo---opinion              String     面审情况及意见
     * borrowModel---approveContent       String     家访客户情况汇总，flag值为2时必填；
     * <p>
     * certificates 资料信息（以下各值，有传字符串"有",无传字符串"")
     * houseCard            String      房产证
     * license              String      驾驶证
     * driving              String      行驶证
     * identityCard         String      身份证
     * accountBook          String      户口本
     * marriageLicense      String      结婚证
     * divorceCertificate   String      离婚证
     * divorceAgreement     String      离婚协议
     * abchelordom          String      单身证明
     * purchaseContract     String      购房合同
     * mortgageContract     String      银行按揭合同
     * bankCard             String      银行卡
     * creditReport         String      个人征信报告
     * accountStatement     String      银行流水
     * employmentCertify    String      工作证明
     * socialSecurity       String      社保
     * providentFund        String      公积金
     * bill                 String      水电费单
     * businessLicense      String      营业执照
     * rfw                  String      人法网查询
     * hdw                  String      红盾网查询
     * other                String      其他
     */
    public static void setCustomerSurveyOpinion() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.BorrowModelBean borrowModel
                = bean.getResData().getBorrowdataModel().getBorrowModel();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CertificatesBean certificates
                = bean.getResData().getBorrowdataModel().getCertificates();
        customerInfo.getBuildStauts();
        customerInfo.getCarStauts();
        customerInfo.getOpinion();
        borrowModel.getApproveContent();

        certificates.getHouseCard();
        certificates.getLicense();
        certificates.getDriving();
        certificates.getIdentityCard();
        certificates.getAccountBook();
        certificates.getMarriageLicense();
        certificates.getDivorceCertificate();
        certificates.getDivorceAgreement();
        certificates.getAbchelordom();
        certificates.getPurchaseContract();
        certificates.getMortgageContract();
        certificates.getBankCard();
        certificates.getCreditReport();
        certificates.getAccountStatement();
        certificates.getEmploymentCertify();
        certificates.getSocialSecurity();
        certificates.getProvidentFund();
        certificates.getBill();
        certificates.getBusinessLicense();
        certificates.getRfw();
        certificates.getHdw();
        certificates.getOther();

    }

    public static void changeCustomerSurveyOpinion() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.BorrowModelBean borrowModel
                = bean.getResData().getBorrowdataModel().getBorrowModel();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CertificatesBean certificates
                = bean.getResData().getBorrowdataModel().getCertificates();
        customerInfo.setBuildStauts("");
        customerInfo.setCarStauts("");
        customerInfo.setOpinion("");
        borrowModel.setApproveContent("");

        certificates.setHouseCard("");
        certificates.setLicense("");
        certificates.setDriving("");
        certificates.setIdentityCard("");
        certificates.setAccountBook("");
        certificates.setMarriageLicense("");
        certificates.setDivorceCertificate("");
        certificates.setDivorceAgreement("");
        certificates.setAbchelordom("");
        certificates.setPurchaseContract("");
        certificates.setMortgageContract("");
        certificates.setBankCard("");
        certificates.setCreditReport("");
        certificates.setAccountStatement("");
        certificates.setEmploymentCertify("");
        certificates.setSocialSecurity("");
        certificates.setProvidentFund("");
        certificates.setBill("");
        certificates.setBusinessLicense("");
        certificates.setRfw("");
        certificates.setHdw("");
        certificates.setOther("");
    }

    /**
     * 贷款人信息（选填）LenderInfo
     * * customerInfo:客户基本信息
     * exitingBuildAcreage  double     现居住建筑面积
     * exitingBuildLivetime String     现住房居住时间
     * otherBuildInfo       String     其他房产信息
     * otherBuildAcreage    double     其他房产建筑面积
     * otherBuildProperty   String     其他房产性质
     * <p>
     * workunitName         String     单位名称
     * jobdepartment        String     部门及职务
     * isBusinessOwner      int        是否是企业主/个体户 ，值：2 ：是   ，1：否
     * <p>
     * workunitNature       int        单位性质 ，值：1：事业单位/国家机关，2：国有企业/上市公司，3：外资/私营 ，4 个体 ，5：公检法/军队 ，6：自由/无业
     * workunitPhone        String     单位电话
     * workunitExtPhone     String     分机
     * workunitAge          String     累计工作年限
     * socialSecurity       int        是否有社保，值：1 代表有 ，-1：代表无
     * reservedFunds        int        是否有公积金，值：2：代表有，-1代表无
     * workunitProvince     String     单位地址省
     * <p>
     * workunitCity         String     单位地址市
     * workunitArea         String     单位地址区
     * workunitStreet       String     单位地址街道
     * workunitPlot         String     单位地址弄
     * workunitMark         String     单位地址号
     * workunitTower        String     单位地址号
     * workunitRoom         String     单位地址室
     * <p>
     * birth                String     出生日期
     * buildPrice           double     每月房贷
     * opinion              String     面审情况及意见
     * qq                   String     qq
     * alipay               String     支付宝
     * buildStauts          String     房产情况
     * carStauts            String     车辆情况
     */

    public static void setLenderInfo() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();
        customerInfo.getExitingBuildAcreage();
        customerInfo.getExitingBuildLivetime();
        customerInfo.getOtherBuildInfo();
        customerInfo.getOtherBuildAcreage();
        customerInfo.getOtherBuildProperty();
        customerInfo.getWorkunitName();
        customerInfo.getJobdepartment();
        customerInfo.getIsBusinessOwner();
        customerInfo.getWorkunitNature();
        customerInfo.getWorkunitPhone();
        customerInfo.getWorkunitExtPhone();
        customerInfo.getWorkunitAge();
        customerInfo.getSocialSecurity();
        customerInfo.getReservedFunds();
        customerInfo.getWorkunitProvince();
        customerInfo.getWorkunitCity();
        customerInfo.getWorkunitArea();
        customerInfo.getWorkunitStreet();
        customerInfo.getWorkunitPlot();
        customerInfo.getWorkunitMark();
        customerInfo.getWorkunitTower();
        customerInfo.getWorkunitRoom();
    }

    public static void changeLenderInfo() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();
        customerInfo.setExitingBuildAcreage(0);
        customerInfo.setExitingBuildLivetime("");
        customerInfo.setOtherBuildInfo("");
        customerInfo.setOtherBuildAcreage(0);
        customerInfo.setOtherBuildProperty("");
        customerInfo.setWorkunitName("");
        customerInfo.setJobdepartment("");
        customerInfo.setIsBusinessOwner(0);
        customerInfo.setWorkunitNature("");
        customerInfo.setWorkunitPhone("");
        customerInfo.setWorkunitExtPhone("");
        customerInfo.setWorkunitAge("");
        customerInfo.setSocialSecurity(0);
        customerInfo.setReservedFunds(0);
        customerInfo.setWorkunitProvince("");
        customerInfo.setWorkunitCity("");
        customerInfo.setWorkunitArea("");
        customerInfo.setWorkunitStreet("");
        customerInfo.setWorkunitPlot("");
        customerInfo.setWorkunitMark("");
        customerInfo.setWorkunitTower("");
        customerInfo.setWorkunitRoom("");

    }

    /**
     * 共同贷款人信息CommonLenderInfo
     * coborrow:共同借款人信息
     * coname               String      姓名
     * sex                  int         性别，值：1：男，2：女
     * crelationship        String      与借款人关系
     * cardid               String      身份证号码
     * birth                String      出生日期
     * domicile             String      户籍所在地
     * exitingBuildAddr     String      居住地址
     * <p>
     * workunitName         String      单位名称
     * workunitDepartment   String      部门及职位
     * monthlyIncome        double      工资月均收入
     * isBusinessOwner      int         是否是企业主/个体户，值 2：是，1：否
     * workunitNature       int         单位性质  值：1：事业单位/国家机关，2：国有企业/上市公司，3：外资/私营 ，4 个体 ，5：公检法/军队 ，6：自由/无业
     * phone                String      单位电话
     * extPhone             String      分机
     * workunitAge          String      累计工作年限
     * socialSecurity       int         是否有社保，值：1 代表有 ，-1：代表无
     * providentFund        int         是否有公积金，值：2：代表有，-1代表无
     * <p>
     * ?????? mobilephone          String      移动电话
     * workunitProvince     String      单位地址省
     * workunitCity         String      单位地址市
     * workunitArea         String      单位地址区
     * workunitStreet       String      单位地址街道
     * workunitPlot         String      单位地址弄
     * workunitMark         String      单位地址号
     * workunitTower        String      单位地址号
     * workunitRoom         String      单位地址室
     */

    public static void setCommonLenderInfo() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CoborrowBean coborrow
                = bean.getResData().getBorrowdataModel().getCoborrow();
        coborrow.getConame();
        coborrow.getSex();
        coborrow.getCrelationship();
        coborrow.getCardid();
        coborrow.getBirth();
        coborrow.getDomicile();
        coborrow.getExitingBuildAddr();
        coborrow.getWorkunitName();
        coborrow.getWorkunitDepartment();
        coborrow.getMonthlyIncome();
        coborrow.getIsBusinessOwner();
        coborrow.getWorkunitNature();
        coborrow.getPhone();
        coborrow.getExtPhone();
        coborrow.getWorkunitAge();
        coborrow.getSocialSecurity();
        coborrow.getProvidentFund();
        coborrow.getWorkunitProvince();
        coborrow.getWorkunitCity();
        coborrow.getWorkunitArea();
        coborrow.getWorkunitStreet();
        coborrow.getWorkunitPlot();
        coborrow.getWorkunitMark();
        coborrow.getWorkunitTower();
        coborrow.getWorkunitRoom();
    }

    public static void changeCommonLenderInfo() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CoborrowBean coborrow
                = bean.getResData().getBorrowdataModel().getCoborrow();
        coborrow.setConame("");
        coborrow.setSex(0);
        coborrow.setCrelationship("");
        coborrow.setCardid("");
        coborrow.setBirth("");
        coborrow.setDomicile("");
        coborrow.setExitingBuildAddr("");
        coborrow.setWorkunitName("");
        coborrow.setWorkunitDepartment("");
        coborrow.setMonthlyIncome(0);
        coborrow.setIsBusinessOwner("");
        coborrow.setWorkunitNature(0);
        coborrow.setPhone("");
        coborrow.setExtPhone("");
        coborrow.setWorkunitAge("");
        coborrow.setSocialSecurity(0);
        coborrow.setProvidentFund(0);
        coborrow.setWorkunitProvince("");
        coborrow.setWorkunitCity("");
        coborrow.setWorkunitArea("");
        coborrow.setWorkunitStreet("");
        coborrow.setWorkunitPlot("");
        coborrow.setWorkunitMark("");
        coborrow.setWorkunitTower("");
        coborrow.setWorkunitRoom("");
    }

    /**
     * 车辆信息 CarInfo
     * carInfo ：车辆信息
     * id                   int         车辆id
     * owner                String      车辆所有人
     * brand                String      品牌
     * color                String      颜色
     * cardid               String      车牌号码
     * status               int         车辆状况，值：1：有车无贷款  ，2：有车有贷款
     * monthlyMoney         double      月还供
     * price                double      裸车价
     * buyDate              String      车辆购买日期
     * otherInfo            String      其他车辆信息
     */

    public static void setCarInfo() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CarInfoBean carInfo =
                bean.getResData().getBorrowdataModel().getCarInfo();
        carInfo.getOwner();
        carInfo.getBrand();
        carInfo.getColor();
        carInfo.getCardid();
        carInfo.getStatus();
        carInfo.getMonthlyMoney();
        carInfo.getPrice();
        carInfo.getBuyDate();
        carInfo.getOtherInfo();
    }

    public static void changeCarInfo() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CarInfoBean carInfo =
                bean.getResData().getBorrowdataModel().getCarInfo();
        carInfo.setOwner("");
        carInfo.setBrand("");
        carInfo.setColor("");
        carInfo.setCardid("");
        carInfo.setStatus("");
        carInfo.setMonthlyMoney(1000);
        carInfo.setPrice(1000);
        carInfo.setBuyDate("");
        carInfo.setOtherInfo("");
    }

    /**
     * 放款评估 LoanAssessment
     * customerRation放款评估信息
     * id                      int         放款评估id
     * buildBorrowTime         int         放贷时间，值为：1 6-12月 2 12-24月   3 24月 以上  4 全款
     * finalRationBbt          String      放贷时间额度
     * buildAddr               String      房产位置，值：1合肥市，2三县
     * finalRationBa           String      市场均价
     * buildType               Int         房产性质，值：1：回迁房，2：公寓，3：住宅，4：单位集资房
     * finalRationBt           String      房产性质额度
     * car                     Integer     车辆，值：1：5万以下，2：5-10万，3:10-20万  ，4:20万以上
     * finalRationCar          String      车辆额度
     * family                  String      家庭情况，值1：已婚，2：未婚，3：孩子；为多选，两个值之间以，连接
     * finalRationFamily       String      家庭情况额度
     * workunit                String      单位性质，值：1：国企/公务员，2：民营，3：社保，4：公积金    为多选，两个值之间以，连接
     * finalRationWorkunit     String      单位性质额度
     * proprietor              String      私人业主，值：1：营业执照，2：实体经营    为多选，两个值之间以，连接
     * finalRationProprietor   String      私人业主额度
     * credit                  int         信用卡负债，值：1:50%，2:60%，3:70%，4:80%，5:90%
     * finalRationCredit       String      征信报告额度
     * finalRation             String      汇总额度
     * creditNumOne            String      近一个月查询次数
     * creditNumThree          String      近三个月查询次数
     * creditNumPer            String      个人查询近三个月查询次数
     * creditNumTotal          String      个人查询总共次数
     * wanglaNum               String      网拉账号
     * wanglaPwd               String      网拉密码
     * wanglaVercode           String      网拉验证码
     * socialNum               String      社保账号
     * socialPwd               String      社保密码
     * reserveNum              String      公积金账号
     * reservePwd              String      公积金密码
     */

    public static void setLoanAssessment() {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
    }

    public static void changeLoanAssessment() {

    }

}
