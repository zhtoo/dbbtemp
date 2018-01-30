package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2018/1/8 17:00
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LoanEvaluationActivity extends AppBarActivity {


    @BindView(R.id.evaluation_standard)
    Button mStandard;
    @BindView(R.id.evaluate_name)
    TextView evaluateName;
    @BindView(R.id.evaluate_manager)
    TextView evaluateManager;
    @BindView(R.id.e_finalRationBbt)
    EditText eFinalRationBbt;
    @BindView(R.id.radio_mortgage_time01)
    RadioButton radioMortgageTime01;
    @BindView(R.id.radio_mortgage_time02)
    RadioButton radioMortgageTime02;
    @BindView(R.id.radio_mortgage_time03)
    RadioButton radioMortgageTime03;
    @BindView(R.id.radio_mortgage_time04)
    RadioButton radioMortgageTime04;
    @BindView(R.id.radio_housing_location01)
    RadioButton radioHousingLocation01;
    @BindView(R.id.radio_housing_location02)
    RadioButton radioHousingLocation02;
    @BindView(R.id.e_finalRationBa)
    EditText eFinalRationBa;
    @BindView(R.id.e_finalRationBt)
    EditText eFinalRationBt;
    @BindView(R.id.radio_house_properties01)
    RadioButton radioHouseProperties01;
    @BindView(R.id.radio_house_properties02)
    RadioButton radioHouseProperties02;
    @BindView(R.id.radio_house_properties03)
    RadioButton radioHouseProperties03;
    @BindView(R.id.radio_house_properties04)
    RadioButton radioHouseProperties04;
    @BindView(R.id.e_finalRationCar)
    EditText eFinalRationCar;
    @BindView(R.id.radio_car_value01)
    RadioButton radioCarValue01;
    @BindView(R.id.radio_car_value02)
    RadioButton radioCarValue02;
    @BindView(R.id.radio_car_value03)
    RadioButton radioCarValue03;
    @BindView(R.id.radio_car_value04)
    RadioButton radioCarValue04;
    @BindView(R.id.e_finalRationFamily)
    EditText eFinalRationFamily;
    @BindView(R.id.family_status01)
    RadioButton familyStatus01;
    @BindView(R.id.family_status02)
    RadioButton familyStatus02;
    @BindView(R.id.e_has_child)
    CheckBox eHasChild;
    @BindView(R.id.e_finalRationWorkunit)
    EditText eFinalRationWorkunit;
    @BindView(R.id.unit_nature01)
    RadioButton unitNature01;
    @BindView(R.id.unit_nature02)
    RadioButton unitNature02;
    @BindView(R.id.e_socialSecurity)
    CheckBox eSocialSecurity;
    @BindView(R.id.e_providentFund)
    CheckBox eProvidentFund;
    @BindView(R.id.e_finalRationProprietor)
    EditText eFinalRationProprietor;
    @BindView(R.id.e_proprietor01)
    CheckBox eProprietor01;
    @BindView(R.id.e_proprietor02)
    CheckBox eProprietor02;
    @BindView(R.id.e_finalRationCredit)
    EditText eFinalRationCredit;
    @BindView(R.id.radio_credit_liability01)
    RadioButton radioCreditLiability01;
    @BindView(R.id.radio_credit_liability02)
    RadioButton radioCreditLiability02;
    @BindView(R.id.radio_credit_liability03)
    RadioButton radioCreditLiability03;
    @BindView(R.id.radio_credit_liability04)
    RadioButton radioCreditLiability04;
    @BindView(R.id.radio_credit_liability05)
    RadioButton radioCreditLiability05;
    @BindView(R.id.e_creditNumOne)
    EditText eCreditNumOne;
    @BindView(R.id.e_creditNumThree)
    EditText eCreditNumThree;
    @BindView(R.id.e_creditNumPer)
    EditText eCreditNumPer;
    @BindView(R.id.e_creditNumTotal)
    EditText eCreditNumTotal;
    @BindView(R.id.e_finalRation)
    EditText eFinalRation;
    @BindView(R.id.e_wanglaNum)
    EditText eWanglaNum;
    @BindView(R.id.e_wanglaPwd)
    EditText eWanglaPwd;
    @BindView(R.id.e_wanglaVercode)
    EditText eWanglaVercode;
    @BindView(R.id.e_socialNum)
    EditText eSocialNum;
    @BindView(R.id.e_socialPwd)
    EditText eSocialPwd;
    @BindView(R.id.e_reserveNum)
    EditText eReserveNum;
    @BindView(R.id.e_reservePwd)
    EditText eReservePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_evaluation);
        ButterKnife.bind(this);
        setTitle("放款评估");
        isShowRightView(false);
        initView();
    }

    private void initView() {

        ApplyLendUtil.setLoanAssessment(
                eFinalRationBbt,
                radioMortgageTime01,
                radioMortgageTime02,
                radioMortgageTime03,
                radioMortgageTime04,
                radioHousingLocation01,
                radioHousingLocation02,
                eFinalRationBa,
                eFinalRationBt,
                radioHouseProperties01,
                radioHouseProperties02,
                radioHouseProperties03,
                radioHouseProperties04,
                eFinalRationCar,
                radioCarValue01,
                radioCarValue02,
                radioCarValue03,
                radioCarValue04,
                eFinalRationFamily,
                familyStatus01,
                familyStatus02,
                eHasChild,
                eFinalRationWorkunit,
                unitNature01,
                unitNature02,
                eSocialSecurity,
                eProvidentFund,
                eFinalRationProprietor,
                eProprietor01,
                eProprietor02,
                eFinalRationCredit,
                radioCreditLiability01,
                radioCreditLiability02,
                radioCreditLiability03,
                radioCreditLiability04,
                radioCreditLiability05,
                eCreditNumOne,
                eCreditNumThree,
                eCreditNumPer,
                eCreditNumTotal,
                eFinalRation,
                eWanglaNum,
                eWanglaPwd,
                eWanglaVercode,
                eSocialNum,
                eSocialPwd,
                eReserveNum,
                eReservePwd
        );
    }


    @OnClick(R.id.evaluation_standard)
    public void onViewClicked() {
        startActivity(new Intent(this, EvaluationStandardActivity.class));
    }


    /**
     * 保存数据
     *
     * @return
     */
    @Override
    public boolean savaData() {
//        ApplyLendUtil.changeLoanAssessment(
//                eFinalRationBbt,
//                radioMortgageTime01,
//                radioMortgageTime02,
//                radioMortgageTime03,
//                radioMortgageTime04,
//                radioHousingLocation01,
//                radioHousingLocation02,
//                eFinalRationBa,
//                eFinalRationBt,
//                radioHouseProperties01,
//                radioHouseProperties02,
//                radioHouseProperties03,
//                radioHouseProperties04,
//                eFinalRationCar,
//                radioCarValue01,
//                radioCarValue02,
//                radioCarValue03,
//                radioCarValue04,
//                eFinalRationFamily,
//                familyStatus01,
//                familyStatus02,
//                eHasChild,
//                eFinalRationWorkunit,
//                unitNature01,
//                unitNature02,
//                eSocialSecurity,
//                eProvidentFund,
//                eFinalRationProprietor,
//                eProprietor01,
//                eProprietor02,
//                eFinalRationCredit,
//                radioCreditLiability01,
//                radioCreditLiability02,
//                radioCreditLiability03,
//                radioCreditLiability04,
//                radioCreditLiability05,
//                eCreditNumOne,
//                eCreditNumThree,
//                eCreditNumPer,
//                eCreditNumTotal,
//                eFinalRation,
//                eWanglaNum,
//                eWanglaPwd,
//                eWanglaVercode,
//                eSocialNum,
//                eSocialPwd,
//                eReserveNum,
//                eReservePwd
//        );

        return super.savaData();
    }



    public void onSaveClicked(View view) {

        ApplyLendUtil.changeLoanAssessment(
                eFinalRationBbt,
                radioMortgageTime01,
                radioMortgageTime02,
                radioMortgageTime03,
                radioMortgageTime04,
                radioHousingLocation01,
                radioHousingLocation02,
                eFinalRationBa,
                eFinalRationBt,
                radioHouseProperties01,
                radioHouseProperties02,
                radioHouseProperties03,
                radioHouseProperties04,
                eFinalRationCar,
                radioCarValue01,
                radioCarValue02,
                radioCarValue03,
                radioCarValue04,
                eFinalRationFamily,
                familyStatus01,
                familyStatus02,
                eHasChild,
                eFinalRationWorkunit,
                unitNature01,
                unitNature02,
                eSocialSecurity,
                eProvidentFund,
                eFinalRationProprietor,
                eProprietor01,
                eProprietor02,
                eFinalRationCredit,
                radioCreditLiability01,
                radioCreditLiability02,
                radioCreditLiability03,
                radioCreditLiability04,
                radioCreditLiability05,
                eCreditNumOne,
                eCreditNumThree,
                eCreditNumPer,
                eCreditNumTotal,
                eFinalRation,
                eWanglaNum,
                eWanglaPwd,
                eWanglaVercode,
                eSocialNum,
                eSocialPwd,
                eReserveNum,
                eReservePwd
        );
        ToastUtil.showToast("保存成功");
    }
}
