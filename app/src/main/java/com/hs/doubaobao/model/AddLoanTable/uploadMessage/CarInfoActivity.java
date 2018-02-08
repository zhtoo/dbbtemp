package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.ApplyInfoBean;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.utils.ToastUtil;
import com.zht.bottomdialog.SelectBottomDialog;
import com.zht.datepicker.DateSelectUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hs.doubaobao.MyApplication.getContext;

/**
 * 作者：zhanghaitao on 2018/1/8 16:38
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class CarInfoActivity extends AppBarActivity {

    @BindView(R.id.car_owner)
    EditText carOwner;
    @BindView(R.id.car_brand)
    EditText carBrand;
    @BindView(R.id.car_color)
    EditText carColor;
    @BindView(R.id.car_cardid)
    EditText carCardid;
    @BindView(R.id.car_status)
    TextView carStatus;
    @BindView(R.id.car_status_item)
    LinearLayout carStatusItem;
    @BindView(R.id.car_monthlyMoney)
    EditText carMonthlyMoney;
    @BindView(R.id.car_monthlyMoney_item)
    LinearLayout carMonthlyMoneyItem;
    @BindView(R.id.car_price)
    EditText carPrice;
    @BindView(R.id.car_buyDate)
    TextView carBuyDate;
    @BindView(R.id.car_buyDate_item)
    LinearLayout carBuyDateItem;
    @BindView(R.id.car_otherInfo)
    EditText carOtherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_infor);
        ButterKnife.bind(this);
        setTitle("贷款人车辆信息");
        isShowRightView(false);
        initView();
    }

    private void initView() {
        ApplyLendUtil.setCarInfo(
                carOwner,
                carBrand,
                carColor,
                carCardid,
                carStatus,
                carMonthlyMoney,
                carPrice,
                carBuyDate,
                carOtherInfo
        );

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        ApplyInfoBean.ResDataBean.BorrowdataModelBean.CarInfoBean carInfo =
                bean.getResData().getBorrowdataModel().getCarInfo();
        int status = carInfo.getStatus();
        if (status == 2) {
            carMonthlyMoneyItem.setVisibility(View.VISIBLE);
        } else {
            carMonthlyMoneyItem.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.car_status_item, R.id.car_buyDate_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.car_status_item:
                SelectBottomDialog dialog = new SelectBottomDialog();
                //值：1：有车无贷款  ，2：有车有贷款
                dialog.setItemStrings(getContext(),
                        new String[]{"有车无贷款", "有车有贷款"});
                dialog.show(getSupportFragmentManager());
                dialog.setOnClickListener(new SelectBottomDialog.onItemClickListener() {
                    @Override
                    public void onClick(String text) {
                        carStatus.setText(text);
                        if (text.equals("有车有贷款")) {
                            carPrice.setText("");
                            carMonthlyMoneyItem.setVisibility(View.VISIBLE);
                        } else {
                            carPrice.setText("");
                            carMonthlyMoneyItem.setVisibility(View.GONE);
                        }
                    }
                });
                break;
            case R.id.car_buyDate_item:
                DateSelectUtil.showSelectDateDialog(this, carBuyDate);
                break;
        }
    }


    /**
     * 保存数据
     *
     * @return
     */
    @Override
    public boolean savaData() {
//        ApplyLendUtil.changeCarInfo(
//                carOwner,
//                carBrand,
//                carColor,
//                carCardid,
//                carStatus,
//                carMonthlyMoney,
//                carPrice,
//                carBuyDate,
//                carOtherInfo
//        );

        return super.savaData();
    }


    @OnClick(R.id.car_save)
    public void onViewClicked() {
        ApplyLendUtil.changeCarInfo(
                carOwner,
                carBrand,
                carColor,
                carCardid,
                carStatus,
                carMonthlyMoney,
                carPrice,
                carBuyDate,
                carOtherInfo
        );
        ToastUtil.showToast("保存成功");
    }
}
