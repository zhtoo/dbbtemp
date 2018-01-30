package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2018/1/8 15:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class InvestigaOpinionActivity extends AppBarActivity {

    @BindView(R.id.opinion_build_stauts)
    EditText mBuildStauts;
    @BindView(R.id.opinion_car_stauts)
    EditText mCarStauts;
    @BindView(R.id.opinion_face_trial)
    EditText mFaceTrial;
    @BindView(R.id.opinion_approve_content)
    EditText mApproveContent;
    @BindView(R.id.opinion_house_card)
    CheckBox mHouseCard;
    @BindView(R.id.opinion_license)
    CheckBox mLicense;
    @BindView(R.id.opinion_driving)
    CheckBox mDriving;
    @BindView(R.id.opinion_identity_card)
    CheckBox mIdentityCard;
    @BindView(R.id.opinion_account_book)
    CheckBox mAccountBook;
    @BindView(R.id.opinion_marriage_license)
    CheckBox mMarriageLicense;
    @BindView(R.id.opinion_divorce_certificate)
    CheckBox mDivorceCertificate;
    @BindView(R.id.opinion_divorce_agreement)
    CheckBox mDivorceAgreement;
    @BindView(R.id.opinion_abchelordom)
    CheckBox mAbchelordom;
    @BindView(R.id.opinion_purchase_contract)
    CheckBox mPurchaseContract;
    @BindView(R.id.opinion_mortgage_contract)
    CheckBox mMortgageContract;
    @BindView(R.id.opinion_bank_card)
    CheckBox mBankCard;
    @BindView(R.id.opinion_credit_report)
    CheckBox mCreditReport;
    @BindView(R.id.opinion_account_statement)
    CheckBox mAccountStatement;
    @BindView(R.id.opinion_employment_certify)
    CheckBox mEmploymentCertify;
    @BindView(R.id.opinion_social_security)
    CheckBox mSocialSecurity;
    @BindView(R.id.opinion_provident_fund)
    CheckBox mProvidentFund;
    @BindView(R.id.opinion_bill)
    CheckBox mBill;
    @BindView(R.id.opinion_business_license)
    CheckBox mBusinessLicense;
    @BindView(R.id.opinion_rfw)
    CheckBox mRfw;
    @BindView(R.id.opinion_hdw)
    CheckBox mHdw;
    @BindView(R.id.opinion_other)
    CheckBox mOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investiga_opinion);
        ButterKnife.bind(this);
        setTitle("客户情况调查及意见");
        isShowRightView(false);
        intiView();
    }

    private void intiView() {
        ApplyLendUtil.setCustomerSurveyOpinion(
                mBuildStauts,
                mCarStauts,
                mFaceTrial,
                mApproveContent,
                mHouseCard,
                mLicense,
                mDriving,
                mIdentityCard,
                mAccountBook,
                mMarriageLicense,
                mDivorceCertificate,
                mDivorceAgreement,
                mAbchelordom,
                mPurchaseContract,
                mMortgageContract,
                mBankCard,
                mCreditReport,
                mAccountStatement,
                mEmploymentCertify,
                mSocialSecurity,
                mProvidentFund,
                mBill,
                mBusinessLicense,
                mRfw,
                mHdw,
                mOther);
    }


    @Override
    public boolean savaData() {

//        ApplyLendUtil.changeCustomerSurveyOpinion(
//                mBuildStauts,
//                mCarStauts,
//                mFaceTrial,
//                mApproveContent,
//                mHouseCard,
//                mLicense,
//                mDriving,
//                mIdentityCard,
//                mAccountBook,
//                mMarriageLicense,
//                mDivorceCertificate,
//                mDivorceAgreement,
//                mAbchelordom,
//                mPurchaseContract,
//                mMortgageContract,
//                mBankCard,
//                mCreditReport,
//                mAccountStatement,
//                mEmploymentCertify,
//                mSocialSecurity,
//                mProvidentFund,
//                mBill,
//                mBusinessLicense,
//                mRfw,
//                mHdw,
//                mOther);
        return super.savaData();
    }


    @OnClick(R.id.opinion_save)
    public void onViewClicked() {

        ApplyLendUtil.changeCustomerSurveyOpinion(
                mBuildStauts,
                mCarStauts,
                mFaceTrial,
                mApproveContent,
                mHouseCard,
                mLicense,
                mDriving,
                mIdentityCard,
                mAccountBook,
                mMarriageLicense,
                mDivorceCertificate,
                mDivorceAgreement,
                mAbchelordom,
                mPurchaseContract,
                mMortgageContract,
                mBankCard,
                mCreditReport,
                mAccountStatement,
                mEmploymentCertify,
                mSocialSecurity,
                mProvidentFund,
                mBill,
                mBusinessLicense,
                mRfw,
                mHdw,
                mOther);
        ToastUtil.showToast("保存成功");
    }
}
