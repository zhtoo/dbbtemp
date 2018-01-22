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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;


/**
 * 作者：zhanghaitao on 2017/8/17 09:11
 * 邮箱：820159571@qq.com
 */

public class LiveInformationFragment extends Fragment implements CityPickerListener {


    @BindView(R.id.lender_live_address)
    LinearLayout mLiveAddress;
    @BindView(R.id.lender_live_address_edit)
    TextView mLiveAddressEdit;
    @BindView(R.id.lender_live_street)
    TextView mLiveStreet;
    @BindView(R.id.lender_live_street_edit)
    EditText mLiveStreetEdit;
    @BindView(R.id.lender_support_number)
    TextView mSupportNumber;
    @BindView(R.id.lender_support_number_edit)
    EditText mSupportNumberEdit;
    Unbinder unbinder;
    private FilloutLenderInformationActivity activity;
    private CityPicker cityPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fillout_live_info, null, false);
        unbinder = ButterKnife.bind(this, view);

        activity = (FilloutLenderInformationActivity) getActivity();
        initListener();
        cityPicker = new CityPicker(activity, this);
        return view;


    }

//    @Override
//    public void onBackPressed() {
//        if (cityPicker.isShow()) {
//            cityPicker.close();
//            return;
//        }
//        super.onBackPressed();
//    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mLiveAddressEdit.addTextChangedListener(activity.editLiveListener);
        mLiveStreetEdit.addTextChangedListener(activity.editLiveListener);
        mSupportNumberEdit.addTextChangedListener(activity.editLiveListener);
        mLiveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void saveData() {
        ApplyLendUtil.changeLive(
                mLiveAddressEdit,
                mLiveStreetEdit,
                mSupportNumberEdit
        );
    }

    /**
     * 将省市的数据回显到界面
     *
     * @param name
     */
    @Override
    public void getCity(String name) {
        String trim = mLiveAddressEdit.getText().toString().trim();
        if(!TextUtils.isEmpty(trim)&&!TextUtils.isEmpty(name)){
            activity.changeProgress(activity.mLiveProgress, 1);
        }
        if (name.contains("undefined")) {
            name = name.substring(0, name.length() - "undefined".length());
        }
        mLiveAddressEdit.setText(name);
    }
}
