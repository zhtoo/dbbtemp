package com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.FilloutLenderInformationActivity;
import com.zht.bottomdialog.SelectBottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;


/**
 * 作者：zhanghaitao on 2017/8/17 09:11
 * 邮箱：820159571@qq.com
 */

public class AssetInformationFragment extends Fragment implements CityPickerListener {


    Unbinder unbinder;
    @BindView(R.id.lender_own_address_edit)
    EditText mOwnAddressEdit;
    @BindView(R.id.lender_own_house_address)
    LinearLayout mOwnHouseAddress;
    @BindView(R.id.lender_own_street_edit)
    EditText mOwnStreetEdit;
    @BindView(R.id.lender_own_house_area_edit)
    EditText mOwnHouseAreaEdit;
    @BindView(R.id.lender_own_house_property_edit)
    TextView mOwnHousePropertyEdit;
    @BindView(R.id.lender_own_house_property)
    LinearLayout mOwnHouseProperty;
    @BindView(R.id.lender_monthly_income_edit)
    EditText mMonthlyIncomeEdit;
    @BindView(R.id.lender_buildPrice_edit)
    EditText mBuildPriceEdit;
    @BindView(R.id.lender_buildPrice)
    LinearLayout mBuildPrice;
    private FilloutLenderInformationActivity activity;
    private CityPicker cityPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fillout_asset_info, null, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (FilloutLenderInformationActivity) getActivity();
        mBuildPrice.setVisibility(View.GONE);
        initListener();
        cityPicker = new CityPicker(activity, this);

        ApplyLendUtil.setAsset(
                mOwnAddressEdit,
                mOwnStreetEdit,
                mOwnHouseAreaEdit,
                mOwnHousePropertyEdit,
                mMonthlyIncomeEdit,
                mBuildPriceEdit
        );

        String text = mOwnHousePropertyEdit.getText().toString().trim();
        if(!text.equals("请选择")){
            activity.changeProgress(activity.mAssetProgress, 1);
            if(text.equals("有房有贷款")){
                mBuildPrice.setVisibility(View.VISIBLE);
            }else {
                mBuildPrice.setVisibility(View.GONE);
            }
        }


        return view;
    }


    private void initListener() {
        mOwnAddressEdit.addTextChangedListener(activity.editAssetListener);
        mOwnStreetEdit.addTextChangedListener(activity.editAssetListener);
        mOwnHouseAreaEdit.addTextChangedListener(activity.editAssetListener);
        mMonthlyIncomeEdit.addTextChangedListener(activity.editAssetListener);
        mOwnHouseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  cityPicker.show();
            }
        });

        mOwnHouseProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectBottomDialog dialog = new SelectBottomDialog();
                //1：有房无贷款   2： 有房有贷款   3:回迁房  4：自建房
                dialog.setItemStrings(getContext(),
                        new String[]{"有房无贷款", "有房有贷款", "回迁房", "自建房"});
                dialog.show(activity.getSupportFragmentManager());
                dialog.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
                    @Override
                    public void onClick(String text) {
                        String oldText = mOwnHousePropertyEdit.getText().toString().trim();
                        if (oldText.equals("请选择") && !TextUtils.isEmpty(text)) {
                            activity.changeProgress(activity.mAssetProgress, 1);
                        }
                        mOwnHousePropertyEdit.setText(text);
                        if(text.equals("有房有贷款")){
                            mBuildPriceEdit.setText("");
                            mBuildPrice.setVisibility(View.VISIBLE);
                        }else {
                            mBuildPriceEdit.setText("");
                            mBuildPrice.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    public void saveData() {
        ApplyLendUtil.changeAsset(
                mOwnAddressEdit,
                mOwnStreetEdit,
                mOwnHouseAreaEdit,
                mOwnHousePropertyEdit,
                mMonthlyIncomeEdit,
                mBuildPriceEdit
        );
    }

    @Override
    public void getCity(String name) {
        String trim = mOwnAddressEdit.getText().toString().trim();
        if (!TextUtils.isEmpty(trim) && !TextUtils.isEmpty(name)) {
            activity.changeProgress(activity.mAssetProgress, 1);
        }
        if (name.contains("undefined")) {
            name = name.substring(0, name.length() - "undefined".length());
        }
        mOwnAddressEdit.setText(name);
    }
}
