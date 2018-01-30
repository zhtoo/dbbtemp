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
import com.hs.doubaobao.utils.ToastUtil;
import com.zht.bottomdialog.SelectBottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

import static com.hs.doubaobao.MyApplication.getContext;

/**
 * 作者：zhanghaitao on 2018/1/8 15:57
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class LenderInfoOptionalActivity extends AppBarActivity implements CityPickerListener {


    @BindView(R.id.lender_exiting_build_acreage)
    EditText mExitingBuildAcreage;
    @BindView(R.id.lender_exiting_build_live_time)
    EditText mExitingBuildLiveTime;
    @BindView(R.id.lender_other_build_info)
    EditText mOtherBuildInfo;
    @BindView(R.id.lender_other_build_acreage)
    EditText mOtherBuildAcreage;
    @BindView(R.id.lender_other_build_property)
    TextView mOtherBuildProperty;
    @BindView(R.id.lender_other_build_property_item)
    LinearLayout mOtherBuildPropertyItem;
    @BindView(R.id.lender_workunit_name)
    EditText mWorkunitName;
    @BindView(R.id.lender_job_department)
    EditText mJobDepartment;
    @BindView(R.id.lender_is_business_owner)
    RadioButton mIsBusinessOwner;
    @BindView(R.id.lender_un_business_owner)
    RadioButton mUnBusinessOwner;
    @BindView(R.id.lender_workunit_nature)
    TextView mWorkunitNature;
    @BindView(R.id.lender_workunit_nature_item)
    LinearLayout mWorkunitNatureItem;
    @BindView(R.id.lender_workunit_phone)
    EditText mWorkunitPhone;
    @BindView(R.id.lender_workunit_ext_phone)
    EditText mWorkunitExtPhone;
    @BindView(R.id.lender_workunit_age)
    EditText mWorkunitAge;
    @BindView(R.id.lender_social_security)
    CheckBox mSocialSecurity;
    @BindView(R.id.lender_reserved_funds)
    CheckBox mReservedFunds;
    @BindView(R.id.lender_workunit_pca)
    TextView mWorkunitPca;
    @BindView(R.id.lender_workunit_pca_item)
    LinearLayout mWorkunitPcaItem;
    @BindView(R.id.lender_workunit_street)
    EditText mWorkunitStreet;
    @BindView(R.id.lender_qq)
    EditText mQQ;
    @BindView(R.id.lender_alipay)
    EditText mAlipay;
    private CityPicker cityPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender_info_optional);
        ButterKnife.bind(this);
        setTitle("贷款人信息-选填");
        isShowRightView(false);
        cityPicker = new CityPicker(this, this);
        initView();
    }

    private void initView() {
        ApplyLendUtil.setLenderInfo(
                mExitingBuildAcreage,
                mExitingBuildLiveTime,
                mOtherBuildInfo,
                mOtherBuildAcreage,
                mOtherBuildProperty,
                mWorkunitName,
                mJobDepartment,
                mIsBusinessOwner,
                mUnBusinessOwner,
                mWorkunitNature,
                mWorkunitPhone,
                mWorkunitExtPhone,
                mWorkunitAge,
                mSocialSecurity,
                mReservedFunds,
                mWorkunitPca,
                mWorkunitStreet,
                mQQ,
                mAlipay
        );

    }


    @OnClick({R.id.lender_other_build_property_item, R.id.lender_workunit_nature_item, R.id.lender_workunit_pca_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lender_other_build_property_item:
//1：有房无贷款   2： 有房有贷款   3:回迁房  4：自建房
                SelectBottomDialog dialog = new SelectBottomDialog();
                dialog.setItemStrings(getContext(),
                        new String[]{"有房无贷款", "有房有贷款", "回迁房", "自建房"});
                dialog.show(getSupportFragmentManager());
                dialog.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
                    @Override
                    public void onClick(String text) {
                        mOtherBuildProperty.setText(text);
                    }
                });
                break;
            case R.id.lender_workunit_nature_item:
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
            case R.id.lender_workunit_pca_item:
                cityPicker.show();
                break;
        }
    }

    @Override
    public void getCity(String name) {
        if (name.contains("undefined")) {
            name = name.substring(0, name.length() - "undefined".length());
        }
        mWorkunitPca.setText(name);
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
     *
     * @return
     */
    @Override
    public boolean savaData() {
//        ApplyLendUtil.changeLenderInfo(
//                mExitingBuildAcreage,
//                mExitingBuildLiveTime,
//                mOtherBuildInfo,
//                mOtherBuildAcreage,
//                mOtherBuildProperty,
//                mWorkunitName,
//                mJobDepartment,
//                mIsBusinessOwner,
//                mUnBusinessOwner,
//                mWorkunitNature,
//                mWorkunitPhone,
//                mWorkunitExtPhone,
//                mWorkunitAge,
//                mSocialSecurity,
//                mReservedFunds,
//                mWorkunitPca,
//                mWorkunitStreet,
//                mQQ,
//                mAlipay
//        );

        return super.savaData();
    }


    @OnClick(R.id.lender_save)
    public void onViewClicked() {
        ApplyLendUtil.changeLenderInfo(
                mExitingBuildAcreage,
                mExitingBuildLiveTime,
                mOtherBuildInfo,
                mOtherBuildAcreage,
                mOtherBuildProperty,
                mWorkunitName,
                mJobDepartment,
                mIsBusinessOwner,
                mUnBusinessOwner,
                mWorkunitNature,
                mWorkunitPhone,
                mWorkunitExtPhone,
                mWorkunitAge,
                mSocialSecurity,
                mReservedFunds,
                mWorkunitPca,
                mWorkunitStreet,
                mQQ,
                mAlipay
        );
        ToastUtil.showToast("保存成功");
    }
}
