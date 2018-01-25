package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.zht.bottomdialog.SelectBottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

import static com.hs.doubaobao.MyApplication.getContext;

/**
 * 作者：zhanghaitao on 2018/1/8 16:24
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class MutualLenderInfoActivity extends AppBarActivity implements CityPickerListener {


    @BindView(R.id.coborrow_coname)
    EditText mConame;
    @BindView(R.id.coborrow_sex_male)
    RadioButton mSexMale;
    @BindView(R.id.coborrow_sex_female)
    RadioButton mSexFemale;
    @BindView(R.id.coborrow_crelationship)
    EditText mCrelationship;
    @BindView(R.id.coborrow_cardid)
    EditText mCardid;
    @BindView(R.id.coborrow_domicile)
    EditText mDomicile;
    @BindView(R.id.coborrow_exitingBuildAddr)
    TextView mExitingBuildAddr;
    @BindView(R.id.coborrow_exitingBuildAddr_item)
    LinearLayout mExitingBuildAddrItem;
    @BindView(R.id.coborrow_exitingBuildAddr01)
    EditText mExitingBuildAddr01;
    @BindView(R.id.coborrow_workunitName)
    EditText mWorkunitName;
    @BindView(R.id.coborrow_workunitDepartment)
    EditText mWorkunitDepartment;
    @BindView(R.id.coborrow_monthlyIncome)
    EditText mMonthlyIncome;
    @BindView(R.id.coborrow_isBusinessOwner)
    RadioButton mIsBusinessOwner;
    @BindView(R.id.coborrow_unBusinessOwner)
    RadioButton mUnBusinessOwner;
    @BindView(R.id.coborrow_workunitNature)
    TextView mWorkunitNature;
    @BindView(R.id.coborrow_workunitNature_item)
    LinearLayout mWorkunitNatureItem;
    @BindView(R.id.coborrow_phone)
    EditText mPhone;
    @BindView(R.id.coborrow_extPhone)
    EditText mExtPhone;
    @BindView(R.id.coborrow_workunitAge)
    EditText mWorkunitAge;
    @BindView(R.id.coborrow_socialSecurity)
    CheckBox mSocialSecurity;
    @BindView(R.id.coborrow_providentFund)
    CheckBox mProvidentFund;
    @BindView(R.id.coborrow_workunit_pca)
    TextView mWorkunitPca;
    @BindView(R.id.coborrow_workunit_pca_item)
    LinearLayout mWorkunitPcaItem;
    @BindView(R.id.coborrow_workunitStreet)
    EditText mWorkunitStreet;
    private CityPicker cityPicker;

    private boolean isExitingBuildAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_lender_info);
        ButterKnife.bind(this);
        setTitle("共同借款人信息");
        isShowRightView(false);
        cityPicker = new CityPicker(this, this);
        initView();
    }

    private void initView() {
        ApplyLendUtil.setCommonLenderInfo(
                mConame,
                mSexMale,
                mSexFemale,
                mCrelationship,
                mCardid,
                mDomicile,
                mExitingBuildAddr,
                mExitingBuildAddr01,
                mWorkunitName,
                mWorkunitDepartment,
                mMonthlyIncome,
                mIsBusinessOwner,
                mUnBusinessOwner,
                mWorkunitNature,
                mPhone,
                mExtPhone,
                mWorkunitAge,
                mSocialSecurity,
                mProvidentFund,
                mWorkunitPca,
                mWorkunitStreet
        );

    }


    @OnClick({R.id.coborrow_exitingBuildAddr_item, R.id.coborrow_workunitNature_item, R.id.coborrow_workunit_pca_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coborrow_exitingBuildAddr_item:
                isExitingBuildAddr = true;
                cityPicker.show();
                break;
            case R.id.coborrow_workunitNature_item:
                //1：事业单位/国家机关，2：国有企业/上市公司，3：外资/私营 ，4 个体 ，5：公检法/军队 ，6：自由/无业
                SelectBottomDialog dialog1 = new SelectBottomDialog();
                dialog1.setItemStrings(getContext(),
                        new String[]{"事业单位/国家机关", "国有企业/上市公司", "外资/私营", "个体", "公检法/军队", "自由/无业"});
                dialog1.show(getSupportFragmentManager());
                dialog1.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
                    @Override
                    public void onClick(String text) {
                        mWorkunitNature.setText(text);
                    }
                });
                break;
            case R.id.coborrow_workunit_pca_item:
                isExitingBuildAddr = false;
                cityPicker.show();
                break;
        }
    }

    @Override
    public void getCity(String name) {
        if (name.contains("undefined")) {
            name = name.substring(0, name.length() - "undefined".length());
        }
        if(isExitingBuildAddr){
            mExitingBuildAddr.setText(name);
        }else {
            mWorkunitPca.setText(name);
        }

    }

    @Override
    public void onBackPressed() {
        if (cityPicker.isShow()) {
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }


    /**
     * 保存数据
     * @return
     */
    @Override
    public boolean savaData() {
        ApplyLendUtil.changeCommonLenderInfo(
                 mConame,
                 mSexMale,
                 mSexFemale,
                 mCrelationship,
                 mCardid,
                 mDomicile,
                 mExitingBuildAddr,
                 mExitingBuildAddr01,
                 mWorkunitName,
                 mWorkunitDepartment,
                 mMonthlyIncome,
                 mIsBusinessOwner,
                 mUnBusinessOwner,
                 mWorkunitNature,
                 mPhone,
                 mExtPhone,
                 mWorkunitAge,
                 mSocialSecurity,
                 mProvidentFund,
                 mWorkunitPca,
                 mWorkunitStreet
        );

        return super.savaData();
    }


}
