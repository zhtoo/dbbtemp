package com.hs.doubaobao.model.AddLoanTable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.CarInfoActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.ContactInforActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.FilloutLenderInformationActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.InvestigaOpinionActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.LenderInfoOptionalActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.LoanEvaluationActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.MutualLenderInfoActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.TheLoansActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadPicture.UploadPictureActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadVideo.UploadVideoActivity;
import com.hs.doubaobao.utils.log.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2017/11/23 15:38
 * 邮箱：820159571@qq.com
 *
 * @describe:资料填写的入口界面+状态提交的界面
 */

public class AddLoanTableActivity extends AppBarActivity implements AddLoanTableContract.View {


    @BindView(R.id.add_lender_information)
    LinearLayout addLenderInformation;
    @BindView(R.id.add_the_loans)
    LinearLayout addTheLoans;
    @BindView(R.id.add_contact_information)
    LinearLayout addContactInformation;
    @BindView(R.id.add_investigation_and_opinion)
    LinearLayout addInvestigationAndOpinion;
    @BindView(R.id.add_lender_information_optional)
    LinearLayout addLenderInformationOptional;
    @BindView(R.id.add_mutual_lender_information)
    LinearLayout addMutualLenderInformation;
    @BindView(R.id.add_car_information)
    LinearLayout addCarInformation;
    @BindView(R.id.add_loan_evaluation)
    LinearLayout addLoanEvaluation;
    @BindView(R.id.add_upload_picture)
    LinearLayout addUploadPicture;
    @BindView(R.id.add_upload_vidoe)
    LinearLayout addUploadVidoe;
    @BindView(R.id.add_submit)
    Button mSubmit;

    private AddLoanTableContract.Presenter presenter;

    private int[] ids = {
            R.id.add_the_loans,
            R.id.add_contact_information,
            R.id.add_investigation_and_opinion,
            R.id.add_lender_information_optional,
            R.id.add_mutual_lender_information,
            R.id.add_car_information,
            R.id.add_loan_evaluation,
            R.id.add_upload_picture,
            R.id.add_upload_vidoe};

    private String customId = "1";
    private String borrowId;

    private LinkedHashMap<Object, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan_table);
        ButterKnife.bind(this);
        setTitle("填写资料");
        setRightStatus(Color.parseColor("#E2570F"), "保存");

        Intent intent = getIntent();
        borrowId = intent.getStringExtra("borrowId");
        map = new LinkedHashMap<>();
        new AddLoanTablePresener(this, this);
        if (!TextUtils.isEmpty(borrowId)) {
            map.put("borrowId", borrowId);
            presenter.getData(map);
        }
    }

    /**
     * 提交数据
     */
    private void submitData() {
        Map<String, Object> uploadDataMap = new LinkedHashMap<>();
        uploadDataMap.put("flag", "2");// flag-----1:代表保存，2：代表提交
        Gson gson = new Gson();
        String s = gson.toJson(ApplyInfoBean.getInstance().getResData().getBorrowdataModel());
        Logger.e("Tag",s);
        uploadDataMap.put("borrowdataModel",
                gson.toJson(ApplyInfoBean.getInstance().getResData().getBorrowdataModel()));
        presenter.uploadData(uploadDataMap);
    }


    /**
     * 保存数据
     */
    private void saveNewData() {
        Map<String, Object> uploadDataMap = new LinkedHashMap<>();
        uploadDataMap.put("flag", "1");// flag-----1:代表保存，2：代表提交
        Gson gson = new Gson();
        String json = gson.toJson(ApplyInfoBean.getInstance().getResData().getBorrowdataModel());
        Logger.e("经过修改保存的数据",json);
        uploadDataMap.put("borrowdataModel", json);
        presenter.uploadData(uploadDataMap);
    }


    @Override
    public void onRightForward(View forwardView) {
        saveNewData();
    }

    /**
     * 处理网络数据
     *
     * @param bean
     */
    @Override
    public void setData(ApplyInfoBean bean) {
        if (bean != null) {
            //将网络数据存放到单例中
            ApplyInfoBean.setInstance(bean);//数据可以正常设置
            Gson gson = new Gson();
            String s = gson.toJson(ApplyInfoBean.getInstance().getResData().getBorrowdataModel());
            Logger.e("请求回来的数据",s);
        }
    }

    /**
     * 处理上传返回的数据
     *
     * @param data
     */
    @Override
    public void uploadDataBack(String data) {

    }

    @Override
    public void setError(String text) {

    }


    /////////////////////////////////////////////////////////////////////
    //////点击事件START
    /////////////////////////////////////////////////////////////////////
    @OnClick({R.id.add_lender_information, R.id.add_the_loans,
            R.id.add_contact_information, R.id.add_investigation_and_opinion,
            R.id.add_lender_information_optional, R.id.add_mutual_lender_information,
            R.id.add_car_information, R.id.add_loan_evaluation,
            R.id.add_upload_picture, R.id.add_upload_vidoe, R.id.add_submit})
    public void onViewClicked(View view) {
        int viewId = view.getId();

        Class[] classes = {
                TheLoansActivity.class,
                ContactInforActivity.class,
                InvestigaOpinionActivity.class,
                LenderInfoOptionalActivity.class,
                MutualLenderInfoActivity.class,
                CarInfoActivity.class,
                LoanEvaluationActivity.class,//待定
                UploadPictureActivity.class,
                UploadVideoActivity.class
        };

        for (int i = 0; i < ids.length; i++) {
            if (viewId == ids[i]) {
//                if (TextUtils.isEmpty(customId)) {
//                    //TODO:提示不能跳转界面（取消）
//                    Toast.makeText(this, "请先保存贷款人信息", Toast.LENGTH_SHORT).show();
//                } else {
                //TODO:跳转界面
                Intent intent = new Intent(this, classes[i]);
                startActivity(intent);
//                }
            }
        }
        switch (viewId) {
            case R.id.add_lender_information:
                //TODO：直接跳转贷款人信息填写界面
                Intent intent = new Intent(this, FilloutLenderInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.add_submit:
                //TODO：联网申请改变客户的申请状态
                submitData();
                break;
        }
    }


    @Override
    public void setPresenter(AddLoanTableContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public boolean savaData() {
        ApplyInfoBean.setInstance(null);
        return super.savaData();
    }
}
