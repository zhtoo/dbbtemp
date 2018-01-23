package com.hs.doubaobao.model.AddLoanTable;


import android.text.TextUtils;
import android.widget.CheckBox;
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
    public static void setBasic(
            EditText mNameEdit,
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
        String[] marriageArr = {"", "未婚", "已婚", "离异", "丧偶"};

        String marriage = marriageArr[customerInfo.getMarriage()];
        mMaritalStatusText.setText(marriage);

        mDomicileEdit.setText(customerInfo.getDomicile());
        mPhoneEdit.setText(customerInfo.getMobilephone());
    }

    public static void changeBasic(
            EditText mNameEdit,
            EditText mIdCardEdit,
            RadioButton mSexMale,
            RadioButton mSexFemale,
            TextView mMaritalStatusText,
            EditText mDomicileEdit,
            EditText mPhoneEdit
    ) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();

        customerInfo.setCname(mNameEdit.getText().toString().trim());
        customerInfo.setCardId(mIdCardEdit.getText().toString().trim());
        boolean maleChecked = mSexMale.isChecked();
        boolean femaleChecked = mSexFemale.isChecked();
        if (maleChecked) {
            customerInfo.setSex(1);
        } else if (femaleChecked) {
            customerInfo.setSex(0);
        } else {
            customerInfo.setSex(-1);
        }
        String[] marriageArr = {"", "未婚", "已婚", "离异", "丧偶"};
        String marriage = mMaritalStatusText.getText().toString().trim();
        for (int i = 0; i < marriageArr.length; i++) {
            if (marriage.equals(marriageArr[i])) {
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

        customerInfo.setExitingBuildAddr(liveAddress + "-" + liveStreet);
        customerInfo.setJobdepartmentCount(liveSupportNumber);

    }

    /**
     * 基本信息（必填3）
     * customerInfo:客户基本信息
     * ownBuildAddr         String     自有房产地址
     * ownBuildAcreage      double     自有房产建筑面积
     * ownBuildProperty     int        自有房产性质，值：1：有房无贷款   2： 有房有贷款   3:回迁房  4：自建房
     * monthlyWage          double     工资月均收入
     * buildPrice           double     每月房贷
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

        customerInfo.setOwnBuildAddr(ownBuildAddr + ownBuildAddr2);

        if(!TextUtils.isEmpty(ownBuildAcreage)){
            customerInfo.setOwnBuildAcreage(Integer.parseInt(ownBuildAcreage));
        }

        String[] ownBuildPropertyArr = {"", "有房无贷款", "有房有贷款", "回迁房", "自建房"};

        for (int i = 0; i < ownBuildPropertyArr.length; i++) {
            if (ownBuildProperty.equals(ownBuildPropertyArr[i])) {
                customerInfo.setOwnBuildProperty(i);
            }
        }

        if(!TextUtils.isEmpty(monthlyWage)){
            customerInfo.setMonthlyWage(Integer.parseInt(monthlyWage));
        }

        if (!TextUtils.isEmpty(buildPrice)) {
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

    public static void changeTheloans(
            TextView mTypeText,
            EditText mAccount,
            TextView mPeriodText,
            TextView mApplydateText,
            EditText mPurpose) {

        String type = mTypeText.getText().toString().trim();
        String account = mAccount.getText().toString().trim();
        String period = mPeriodText.getText().toString().trim();
        String applydate = mApplydateText.getText().toString().trim();
        String purpose = mPurpose.getText().toString().trim();

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.BorrowModelBean borrowModel
                = bean.getResData().getBorrowdataModel().getBorrowModel();

        //01 汇民贷 02 汇商贷 03 汇业贷 04 汇车贷 06 汇房贷 05 汇农贷
        String[] marriageArr = {"", "汇民贷", "汇商贷", "汇业贷", "汇车贷", "汇农贷", "汇房贷"};
        for (int i = 0; i < marriageArr.length; i++) {
            if (type.equals(marriageArr[i])) {
                borrowModel.setType("0" + i);
            }
        }
        //申请期限，值：1:12期 2:18期 3:24期 4:36期 5:48期 6:60期
        String[] periodArr = {"", "12期", "18期", "24期", "36期", "48期", "60期"};
        for (int i = 0; i < marriageArr.length; i++) {
            if (period.equals(marriageArr[i])) {
                borrowModel.setPeriod(i);
            }
        }

        if(!TextUtils.isEmpty(account)){
            borrowModel.setAccount(Integer.parseInt(account));
        }

        borrowModel.setApplydate(applydate);
        borrowModel.setPurpose(purpose);
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
    public static void setContacts(int position, int type) {
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

    public static void changeContacts(int position, int type,
                                      EditText contactInfoName,
                                      TextView contactInfoRelation,
                                      EditText contactInfoPhone,
                                      RadioButton contactInfoYes,
                                      RadioButton contactInfoNo) {

        String name = contactInfoName.getText().toString().trim();
        String relation = contactInfoRelation.getText().toString().trim();
        String phone = contactInfoPhone.getText().toString().trim();
        boolean checkedYes = contactInfoYes.isChecked();
        boolean checkedNo = contactInfoNo.isChecked();


        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean> uclList
                = bean.getResData().getBorrowdataModel().getUclList();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean
                uclListBean;
        if (uclList != null && uclList.size() == 4) {
            uclListBean = uclList.get(position);
        } else {
            uclListBean  = new ApplyInfoBean.ResDataBean.BorrowdataModelBean.UclListBean();
        }

        uclListBean.setName(name);
        uclListBean.setRelation(relation);
        uclListBean.setPhone(phone);
        if (checkedYes) {
            uclListBean.setNotice(1);
        } else if (checkedNo) {
            uclListBean.setNotice(2);
        }
        uclListBean.setType(type);

        if (uclList != null && uclList.size() == 4) {
        } else {
            uclList.add(uclListBean);
        }
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

    public static void changeCustomerSurveyOpinion(
            EditText mBuildStauts,
            EditText mCarStauts,
            EditText mFaceTrial,
            EditText mApproveContent,
            CheckBox mHouseCard,
            CheckBox mLicense,
            CheckBox mDriving,
            CheckBox mIdentityCard,
            CheckBox mAccountBook,
            CheckBox mMarriageLicense,
            CheckBox mDivorceCertificate,
            CheckBox mDivorceAgreement,
            CheckBox mAbchelordom,
            CheckBox mPurchaseContract,
            CheckBox mMortgageContract,
            CheckBox mBankCard,
            CheckBox mCreditReport,
            CheckBox mAccountStatement,
            CheckBox mEmploymentCertify,
            CheckBox mSocialSecurity,
            CheckBox mProvidentFund,
            CheckBox mBill,
            CheckBox mBusinessLicense,
            CheckBox mRfw,
            CheckBox mHdw,
            CheckBox mOther
    ) {

        String buildStauts = mBuildStauts.getText().toString().trim();
        String carStauts = mCarStauts.getText().toString().trim();
        String opinion = mFaceTrial.getText().toString().trim();
        String approveContent = mApproveContent.getText().toString().trim();

        boolean houseCard           =             mHouseCard .isChecked();
        boolean license             =               mLicense .isChecked();
        boolean driving             =               mDriving .isChecked();
        boolean identityCard        =          mIdentityCard .isChecked();
        boolean accountBook         =           mAccountBook .isChecked();
        boolean marriageLicense     =       mMarriageLicense .isChecked();
        boolean divorceCertificat   =     mDivorceCertificate .isChecked();
        boolean divorceAgreemen     =       mDivorceAgreement.isChecked();
        boolean abchelordom         =           mAbchelordom .isChecked();
        boolean purchaseContract    =      mPurchaseContract .isChecked();
        boolean mortgageContract    =      mMortgageContract .isChecked();
        boolean bankCard            =              mBankCard .isChecked();
        boolean creditReport        =          mCreditReport .isChecked();
        boolean accountStatement    =      mAccountStatement .isChecked();
        boolean employmentCertify   =     mEmploymentCertify .isChecked();
        boolean socialSecurity      =        mSocialSecurity .isChecked();
        boolean providentFund       =         mProvidentFund .isChecked();
        boolean bill                =                  mBill .isChecked();
        boolean businessLicense     =       mBusinessLicense .isChecked();
        boolean rfw                 =                   mRfw .isChecked();
        boolean hdw                 =                   mHdw .isChecked();
        boolean other               =                   mOther.isChecked();


        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.BorrowModelBean borrowModel
                = bean.getResData().getBorrowdataModel().getBorrowModel();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CertificatesBean certificates
                = bean.getResData().getBorrowdataModel().getCertificates();
        customerInfo.setBuildStauts(buildStauts);
        customerInfo.setCarStauts(carStauts);
        customerInfo.setOpinion(opinion);
        borrowModel.setApproveContent(approveContent);

        certificates.setHouseCard           (checkCustomerSurveyOpinion(houseCard         ));
        certificates.setLicense             (checkCustomerSurveyOpinion(license           ));
        certificates.setDriving             (checkCustomerSurveyOpinion(driving           ));
        certificates.setIdentityCard        (checkCustomerSurveyOpinion(identityCard      ));
        certificates.setAccountBook         (checkCustomerSurveyOpinion(accountBook       ));
        certificates.setMarriageLicense     (checkCustomerSurveyOpinion(marriageLicense   ));
        certificates.setDivorceCertificate  (checkCustomerSurveyOpinion(divorceCertificat ));
        certificates.setDivorceAgreement    (checkCustomerSurveyOpinion(divorceAgreemen   ));
        certificates.setAbchelordom         (checkCustomerSurveyOpinion(abchelordom       ));
        certificates.setPurchaseContract    (checkCustomerSurveyOpinion(purchaseContract  ));
        certificates.setMortgageContract    (checkCustomerSurveyOpinion(mortgageContract  ));
        certificates.setBankCard            (checkCustomerSurveyOpinion(bankCard          ));
        certificates.setCreditReport        (checkCustomerSurveyOpinion(creditReport      ));
        certificates.setAccountStatement    (checkCustomerSurveyOpinion(accountStatement  ));
        certificates.setEmploymentCertify   (checkCustomerSurveyOpinion(employmentCertify ));
        certificates.setSocialSecurity      (checkCustomerSurveyOpinion(socialSecurity    ));
        certificates.setProvidentFund       (checkCustomerSurveyOpinion(providentFund     ));
        certificates.setBill                (checkCustomerSurveyOpinion(bill              ));
        certificates.setBusinessLicense     (checkCustomerSurveyOpinion(businessLicense   ));
        certificates.setRfw                 (checkCustomerSurveyOpinion(rfw               ));
        certificates.setHdw                 (checkCustomerSurveyOpinion(hdw               ));
        certificates.setOther               (checkCustomerSurveyOpinion(other             ));
    }

    private static String checkCustomerSurveyOpinion(Boolean isCheck){
        return  isCheck?"有":"";
    }
    /**
     * 贷款人信息（选填）LenderInfo
     * customerInfo:客户基本信息
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

    public static void changeLenderInfo(EditText mExitingBuildAcreage, EditText mExitingBuildLiveTime, EditText mOtherBuildInfo, EditText mOtherBuildAcreage, TextView mOtherBuildProperty, EditText mWorkunitName, EditText mJobDepartment, RadioButton mIsBusinessOwner, RadioButton mUnBusinessOwner, TextView mWorkunitNature, EditText mWorkunitPhone, EditText mWorkunitExtPhone, EditText mWorkunitAge, CheckBox mSocialSecurity, CheckBox mReservedFunds, TextView mWorkunitPca, EditText mWorkunitStreet, EditText mQQ, EditText mAlipay) {

        String exitingBuildAcreage  = mExitingBuildAcreage.getText().toString().trim();
        String exitingBuildLivetime = mExitingBuildLiveTime.getText().toString().trim();
        String otherBuildInfo       = mOtherBuildInfo.getText().toString().trim();
        String otherBuildAcreage    = mOtherBuildAcreage.getText().toString().trim();
        String otherBuildProperty   = mOtherBuildProperty.getText().toString().trim();
        String workunitName         = mWorkunitName.getText().toString().trim();
        String jobdepartment        = mJobDepartment.getText().toString().trim();
        boolean isBusinessOwner     = mIsBusinessOwner.isChecked();
        boolean unBusinessOwner     = mUnBusinessOwner.isChecked();
        String workunitNature       = mWorkunitNature.getText().toString().trim();
        String workunitPhone        = mWorkunitPhone.getText().toString().trim();
        String workunitExtPhone     = mWorkunitExtPhone.getText().toString().trim();
        String workunitAge          = mWorkunitAge.getText().toString().trim();
        boolean socialSecurity      = mSocialSecurity.isChecked();
        boolean reservedFunds       = mReservedFunds.isChecked();
        String workunitProvince     = mWorkunitPca.getText().toString().trim();
        String workunitStreet       = mWorkunitStreet.getText().toString().trim();
        String qq                   = mQQ.getText().toString().trim();
        String alipay               = mAlipay.getText().toString().trim();

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerInfoBean customerInfo
                = bean.getResData().getBorrowdataModel().getCustomerInfo();

        if(!TextUtils.isEmpty(exitingBuildAcreage)){
            customerInfo.setExitingBuildAcreage(Double.parseDouble(exitingBuildAcreage));
        }
        customerInfo.setExitingBuildLivetime(exitingBuildLivetime);
        customerInfo.setOtherBuildInfo(otherBuildInfo);

        if(!TextUtils.isEmpty(otherBuildAcreage)){
            customerInfo.setOtherBuildAcreage(Double.parseDouble(otherBuildAcreage));
        }

        customerInfo.setOtherBuildProperty(otherBuildProperty);
        customerInfo.setWorkunitName(workunitName);
        customerInfo.setJobdepartment(jobdepartment);

        // 是否是企业主/个体户 ，值：2 ：是   ，1：否
        if(isBusinessOwner){
            customerInfo.setIsBusinessOwner(2);
        }else
        if(unBusinessOwner){
            customerInfo.setIsBusinessOwner(1);
        }
        //1：事业单位/国家机关，2：国有企业/上市公司，3：外资/私营 ，4 个体 ，5：公检法/军队 ，6：自由/无业
        String[] workunitNatureArr =
                {"", "事业单位/国家机关", "国有企业/上市公司", "外资/私营", "个体", "公检法/军队", "自由/无业"};
        for (int i = 0; i < workunitNatureArr.length; i++) {
            if (workunitNature.equals(workunitNatureArr[i])) {
                customerInfo.setWorkunitNature(i);
            }
        }

        customerInfo.setWorkunitPhone(workunitPhone);
        customerInfo.setWorkunitExtPhone(workunitExtPhone);
        customerInfo.setWorkunitAge(workunitAge);
        //是否有社保，值：1 代表有 ，-1：代表无
        //是否有公积金，值：2：代表有，-1代表无
        customerInfo.setSocialSecurity(socialSecurity?1:-1);
        customerInfo.setReservedFunds(reservedFunds?1:-1);

        customerInfo.setWorkunitProvince(workunitProvince);
        customerInfo.setWorkunitCity("");
        customerInfo.setWorkunitArea("");
        customerInfo.setWorkunitStreet(workunitStreet);
        customerInfo.setWorkunitPlot("");
        customerInfo.setWorkunitMark("");
        customerInfo.setWorkunitTower("");
        customerInfo.setWorkunitRoom("");

        customerInfo.setQq(qq);
        customerInfo.setAlipay(alipay);
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

    public static void changeCommonLenderInfo(EditText mConame, RadioButton mSexMale, RadioButton mSexFemale, EditText mCrelationship, EditText mCardid, EditText mDomicile, TextView mExitingBuildAddr, EditText mExitingBuildAddr01, EditText mWorkunitName, EditText mWorkunitDepartment, EditText mMonthlyIncome, RadioButton mIsBusinessOwner, RadioButton mUnBusinessOwner, TextView mWorkunitNature, EditText mPhone, EditText mExtPhone, EditText mWorkunitAge, CheckBox mSocialSecurity, CheckBox mProvidentFund, TextView mWorkunitPca, EditText mWorkunitStreet) {
        String  coname              =  mConame.getText().toString().trim();
        boolean male                =  mSexMale.isChecked();
        boolean female              =  mSexFemale.isChecked();
        String  crelationship       =  mCrelationship.getText().toString().trim();
        String  cardid              =  mCardid.getText().toString().trim();
        String  domicile            =  mDomicile.getText().toString().trim();
        String  exitingBuildAddr    =  mExitingBuildAddr.getText().toString().trim();
        String  exitingBuildAddr01  =  mExitingBuildAddr01.getText().toString().trim();
        String  workunitName        =  mWorkunitName.getText().toString().trim();
        String  workunitDepartment  =  mWorkunitDepartment.getText().toString().trim();
        String  monthlyIncome       =  mMonthlyIncome.getText().toString().trim();
        boolean isBusinessOwner     =  mIsBusinessOwner.isChecked();
        boolean unBusinessOwner     =  mUnBusinessOwner.isChecked();
        String  workunitNature      =  mWorkunitNature.getText().toString().trim();
        String  phone               =  mPhone.getText().toString().trim();
        String  extPhone            =  mExtPhone.getText().toString().trim();
        String  workunitAge         =  mWorkunitAge.getText().toString().trim();
        boolean socialSecurity      =  mSocialSecurity.isChecked();
        boolean providentFund       =  mProvidentFund.isChecked();
        String  workunitProvince    =  mWorkunitPca.getText().toString().trim();
        String  workunitStreet      =  mWorkunitStreet.getText().toString().trim();

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CoborrowBean coborrow
                = bean.getResData().getBorrowdataModel().getCoborrow();

        coborrow.setConame(coname);

        if(male){
            coborrow.setSex(1);
        }else
        if(female){
            coborrow.setSex(2);
        }

        coborrow.setCrelationship(crelationship);
        coborrow.setCardid(cardid);
       // coborrow.setBirth(domicile);
        coborrow.setDomicile(domicile);
        coborrow.setExitingBuildAddr(exitingBuildAddr +exitingBuildAddr01);
        coborrow.setWorkunitName(workunitName);
        coborrow.setWorkunitDepartment(workunitDepartment);

        if(!TextUtils.isEmpty(monthlyIncome)){
            coborrow.setMonthlyIncome(Double.parseDouble(monthlyIncome));
        }

        // 是否是企业主/个体户 ，值：2 ：是   ，1：否
        if(isBusinessOwner){
            coborrow.setIsBusinessOwner(2);
        }else
        if(unBusinessOwner){
            coborrow.setIsBusinessOwner(1);
        }

        //1：事业单位/国家机关，2：国有企业/上市公司，3：外资/私营 ，4 个体 ，5：公检法/军队 ，6：自由/无业
        String[] workunitNatureArr =
                {"", "事业单位/国家机关", "国有企业/上市公司", "外资/私营", "个体", "公检法/军队", "自由/无业"};
        for (int i = 0; i < workunitNatureArr.length; i++) {
            if (workunitNature.equals(workunitNatureArr[i])) {
                coborrow.setWorkunitNature(i);
            }
        }

        coborrow.setPhone(phone);
        coborrow.setExtPhone(extPhone);
        coborrow.setWorkunitAge(workunitAge);

        coborrow.setSocialSecurity(socialSecurity?1:-1);
        coborrow.setProvidentFund(providentFund?1:-1);

        coborrow.setWorkunitProvince(workunitProvince);
        coborrow.setWorkunitCity("");
        coborrow.setWorkunitArea("");
        coborrow.setWorkunitStreet(workunitStreet);
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

    public static void changeCarInfo(
            EditText carOwner,
            EditText carBrand,
            EditText carColor,
            EditText carCardid,
            TextView carStatus,
            EditText carMonthlyMoney,
            EditText carPrice,
            TextView carBuyDate,
            EditText carOtherInfo
    ) {
        String owner            = carOwner.getText().toString().trim();
        String brand            = carBrand.getText().toString().trim();
        String color            = carColor.getText().toString().trim();
        String cardid           = carCardid.getText().toString().trim();
        String status           = carStatus.getText().toString().trim();
        String monthlyMoney     = carMonthlyMoney.getText().toString().trim();
        String price            = carPrice.getText().toString().trim();
        String buyDate          = carBuyDate.getText().toString().trim();
        String otherInfo        = carOtherInfo.getText().toString().trim();

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CarInfoBean carInfo =
                bean.getResData().getBorrowdataModel().getCarInfo();

        carInfo.setOwner(owner);
        carInfo.setBrand(brand);
        carInfo.setColor(color);
        carInfo.setCardid(cardid);

        //车辆状况，值：1：有车无贷款  ，2：有车有贷款
        String[] statusArr =
                {"", "有车无贷款", "有车有贷款"};
        for (int i = 0; i < statusArr.length; i++) {
            if (status.equals(statusArr[i])) {
                carInfo.setStatus(i);
            }
        }

        if(!TextUtils.isEmpty(monthlyMoney)){
            carInfo.setMonthlyMoney(Double.parseDouble(monthlyMoney));
        }

        if(!TextUtils.isEmpty(price)){
            carInfo.setPrice(Double.parseDouble(price));
        }
        carInfo.setBuyDate(buyDate);
        carInfo.setOtherInfo(otherInfo);
    }

    /**
     * customerRation放款评估信息
     * buildBorrowTime         int         放贷时间，值为：1 6-12月 2 12-24月   3 24月 以上  4 全款
     * finalRationBbt          String      放贷时间额度
     *
     * buildAddr               String      房产位置，值：1合肥市，2三县
     * finalRationBa           String      市场均价
     *
     * buildType               int         房产性质，值：1：回迁房，2：公寓，3：住宅，4：单位集资房
     * finalRationBt           String      房产性质额度
     *
     * car                     Integer     车辆，值：1：5万以下，2：5-10万，3:10-20万  ，4:20万以上
     * finalRationCar          String      车辆额度
     *
     * family                  String      家庭情况，值1：已婚，2：未婚，3：孩子；为多选，两个值之间以，连接
     * finalRationFamily       String      家庭情况额度
     *
     * workunit                String      单位性质，值：1：国企/公务员，2：民营，3：社保，4：公积金    为多选，两个值之间以，连接
     * finalRationWorkunit     String      单位性质额度
     *
     * proprietor              String      私人业主，值：1：营业执照，2：实体经营    为多选，两个值之间以，连接
     * finalRationProprietor   String      私人业主额度
     *
     * credit                  int         信用卡负债，值：1:50%，2:60%，3:70%，4:80%，5:90%
     * finalRationCredit       String      征信报告额度
     *
     * finalRation             String      汇总额度
     *
     * creditNumOne            String      近一个月查询次数
     * creditNumThree          String      近三个月查询次数
     * creditNumPer            String      个人查询近三个月查询次数
     * creditNumTotal          String      个人查询总共次数
     *
     *
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

    public static void changeLoanAssessment( EditText eFinalRationBbt, RadioButton radioMortgageTime01, RadioButton radioMortgageTime02, RadioButton radioMortgageTime03, RadioButton radioMortgageTime04, RadioButton radioHousingLocation01, RadioButton radioHousingLocation02, EditText eFinalRationBa, EditText eFinalRationBt, RadioButton radioHouseProperties01, RadioButton radioHouseProperties02, RadioButton radioHouseProperties03, RadioButton radioHouseProperties04, EditText eFinalRationCar, RadioButton radioCarValue01, RadioButton radioCarValue02, RadioButton radioCarValue03, RadioButton radioCarValue04, EditText eFinalRationFamily, RadioButton familyStatus01, RadioButton familyStatus02, CheckBox eHasChild, EditText eFinalRationWorkunit, RadioButton unitNature01, RadioButton unitNature02, CheckBox eSocialSecurity, CheckBox eProvidentFund, EditText eFinalRationProprietor, CheckBox eProprietor01, CheckBox eProprietor02, EditText eFinalRationCredit, RadioButton radioCreditLiability01, RadioButton radioCreditLiability02, RadioButton radioCreditLiability03, RadioButton radioCreditLiability04, RadioButton radioCreditLiability05, EditText eCreditNumOne, EditText eCreditNumThree, EditText eCreditNumPer, EditText eCreditNumTotal, EditText eFinalRation, EditText eWanglaNum, EditText eWanglaPwd, EditText eWanglaVercode, EditText eSocialNum, EditText eSocialPwd, EditText eReserveNum, EditText eReservePwd) {
        String  finalRationBbt        = eFinalRationBbt.getText().toString().trim();
        boolean buildBorrowTime01 =  radioMortgageTime01.isChecked();
        boolean buildBorrowTime02 =  radioMortgageTime02.isChecked();
        boolean buildBorrowTime03 =  radioMortgageTime03.isChecked();
        boolean buildBorrowTime04 =  radioMortgageTime04.isChecked();

        String  finalRationBa         = eFinalRationBa.getText().toString().trim();
        boolean  buildAddr01           = radioHousingLocation01.isChecked();
        boolean  buildAddr02           = radioHousingLocation02.isChecked();

        String  finalRationBt         = eFinalRationBt.getText().toString().trim();
        boolean  buildType01          = radioHouseProperties01.isChecked();
        boolean  buildType02          = radioHouseProperties02.isChecked();
        boolean  buildType03          = radioHouseProperties03.isChecked();
        boolean  buildType04          = radioHouseProperties04.isChecked();

        String  finalRationCar        = eFinalRationCar.getText().toString().trim();
        boolean  car01                 = radioCarValue01.isChecked();
        boolean  car02                 = radioCarValue02.isChecked();
        boolean  car03                 = radioCarValue03.isChecked();
        boolean  car04                 = radioCarValue04.isChecked();

        String  finalRationFamily     = eFinalRationFamily.getText().toString().trim();
        boolean  family01              = familyStatus01.isChecked();
        boolean  family02              = familyStatus02.isChecked();
        boolean  hasChild              = eHasChild.isChecked();

        String  finalRationWorkunit   = eFinalRationWorkunit.getText().toString().trim();
        boolean  workunit01            = unitNature01.isChecked();
        boolean  workunit02            = unitNature02.isChecked();
        boolean socialSecurity        = eSocialSecurity.isChecked();
        boolean providentFund         = eProvidentFund.isChecked();

        String  finalRationProprietor = eFinalRationProprietor.getText().toString().trim();
        boolean  proprietor01          = eProprietor01.isChecked();
        boolean  proprietor02          = eProprietor02.isChecked();

        String  finalRationCredit     = eFinalRationCredit.getText().toString().trim();
        boolean  credit01              = radioCreditLiability01.isChecked();
        boolean  credit02              = radioCreditLiability02.isChecked();
        boolean  credit03              = radioCreditLiability03.isChecked();
        boolean  credit04              = radioCreditLiability04.isChecked();
        boolean  credit05              = radioCreditLiability05.isChecked();

        String  creditNumOne          = eCreditNumOne.getText().toString().trim();
        String  creditNumThree        = eCreditNumThree.getText().toString().trim();
        String  creditNumPer          = eCreditNumPer.getText().toString().trim();
        String  creditNumTotal        = eCreditNumTotal.getText().toString().trim();

        String finalRation            = eFinalRation.getText().toString().trim();

        String  wanglaNum             = eWanglaNum.getText().toString().trim();
        String  wanglaPwd             = eWanglaPwd.getText().toString().trim();
        String  wanglaVercode         = eWanglaVercode.getText().toString().trim();

        String  socialNum             = eSocialNum.getText().toString().trim();
        String  socialPwd             = eSocialPwd.getText().toString().trim();

        String  reserveNum            = eReserveNum.getText().toString().trim();
        String  reservePwd            = eReservePwd.getText().toString().trim();

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CustomerRationBean customerRation =
                bean.getResData().getBorrowdataModel().getCustomerRation();
        /**放贷时间：1 6-12月 2 12-24月   3 24月 以上  4 全款*/
        if(buildBorrowTime01){
            customerRation.setBuildBorrowTime(1);
        }else
        if(buildBorrowTime02){
            customerRation.setBuildBorrowTime(2);
        }else
        if(buildBorrowTime03){
            customerRation.setBuildBorrowTime(3);
        }else
        if(buildBorrowTime04){
            customerRation.setBuildBorrowTime(4);
        }
        customerRation.setFinalRationBbt(finalRationBbt);
        /**房产位置，值：1合肥市，2三县*/
        if(buildAddr01){
            customerRation.setBuildAddr("1");
        }else
        if(buildAddr02){
            customerRation.setBuildAddr("2");
        }
        customerRation.setFinalRationBa(finalRationBa);
        /**房产性质，值：1：回迁房，2：公寓，3：住宅，4：单位集资房*/
        if(buildType01){
            customerRation.setBuildType(1);
        }else
        if(buildType02){
            customerRation.setBuildType(2);
        }else
        if(buildType03){
            customerRation.setBuildType(3);
        }else
        if(buildType04){
            customerRation.setBuildType(4);
        }
        customerRation.setFinalRationBt(finalRationBt);
        /**车辆，值：1：5万以下，2：5-10万，3:10-20万  ，4:20万以上*/
        if(car01){
            customerRation.setCar(1);
        }else
        if(car02){
            customerRation.setCar(2);
        }else
        if(car03){
            customerRation.setCar(3);
        }else
        if(car04){
            customerRation.setCar(4);
        }
        customerRation.setFinalRationCar(finalRationCar);
        /**家庭情况，值1：已婚，2：未婚，3：孩子；为多选，两个值之间以，连接*/
        String family = "";
        if(family01){
            family = "1";
        }else
        if(family02){
            family = "2";
        }
        if(hasChild){
           if(!TextUtils.isEmpty(family)){
               family +=",3";
           }else {
               family ="3";
           }
        }
        if(!TextUtils.isEmpty(family)){
            customerRation.setFamily(family);
        }
        customerRation.setFinalRationFamily(finalRationFamily);
        /**单位性质，值：1：国企/公务员，2：民营，3：社保，4：公积金*/
        String workunit = "";
        if(workunit01){
            workunit = "1";
        }else
        if(workunit02){
            workunit = "2";
        }
        if(socialSecurity){
            if(!TextUtils.isEmpty(workunit)){
                workunit +=",3";
            }else {
                workunit ="3";
            }
        }
        if(providentFund){
            if(!TextUtils.isEmpty(workunit)){
                workunit +=",4";
            }else {
                workunit ="4";
            }
        }
        if(!TextUtils.isEmpty(workunit)){
            customerRation.setWorkunit(workunit);
        }
        customerRation.setFinalRationWorkunit(finalRationWorkunit);
        /**私人业主，值：1：营业执照，2：实体经营   为多选，两个值之间以，连接*/
        String proprietor = "";
        if(proprietor01){
            if(!TextUtils.isEmpty(proprietor)){
                proprietor +=",1";
            }else {
                proprietor ="1";
            }
        }
        if(proprietor02){
            if(!TextUtils.isEmpty(proprietor)){
                proprietor +=",2";
            }else {
                proprietor ="2";
            }
        }
        if(!TextUtils.isEmpty(proprietor)){
            customerRation.setProprietor(proprietor);
        }
        customerRation.setFinalRationProprietor(finalRationProprietor);
        /**信用卡负债，值：1:50%，2:60%，3:70%，4:80%，5:90%*/
        if(credit01){
            customerRation.setCredit(1);
        }else
        if(credit02){
            customerRation.setCredit(2);
        }else
        if(credit03){
            customerRation.setCredit(3);
        }else
        if(credit04){
            customerRation.setCredit(4);
        } else
        if(credit05){
            customerRation.setCredit(5);
        }
        customerRation.setFinalRationCredit(finalRationCredit);

        customerRation.setFinalRation           (finalRation    );
        customerRation.setCreditNumOne          (creditNumOne   );
        customerRation.setCreditNumThree        (creditNumThree );
        customerRation.setCreditNumPer          (creditNumPer   );
        customerRation.setCreditNumTotal        (creditNumTotal );
        customerRation.setWanglaNum             (wanglaNum      );
        customerRation.setWanglaPwd             (wanglaPwd      );
        customerRation.setWanglaVercode         (wanglaVercode  );
        customerRation.setSocialNum             (socialNum      );
        customerRation.setSocialPwd             (socialPwd      );
        customerRation.setReserveNum            (reserveNum     );
        customerRation.setReservePwd            (reservePwd     );
    }

}
