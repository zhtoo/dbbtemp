package com.hs.doubaobao.model.AddLoanTable;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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

import java.util.LinkedHashMap;

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

    private AddLoanTableContract.Presenter presenter ;

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
        setRightStatus("保存");


        Intent intent = getIntent();
        borrowId = intent.getStringExtra("borrowId");
        map = new LinkedHashMap<>();
        new AddLoanTablePresener(this,this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!TextUtils.isEmpty(borrowId)){
            map.put("borrowId", borrowId);
            presenter.getData(map);
        }
    }





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
                if (TextUtils.isEmpty(customId)) {
                    //TODO:提示不能跳转界面
                    Toast.makeText(this, "请先保存贷款人信息", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO:跳转界面
                    Intent intent = new Intent(this, classes[i]);
                    startActivity(intent);
                }
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
                break;
        }
    }

    @Override
    public void setData(ApplyInfoBean bean) {

    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(AddLoanTableContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
