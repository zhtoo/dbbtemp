package com.hs.doubaobao.model.department;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.DepartmentStatisticsBean;
import com.hs.doubaobao.utils.PullToRefresh;
import com.hs.doubaobao.view.main.Indicator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2018/1/9 15:45
 * 邮箱：820159571@qq.com
 *
 * @describe:部门统计
 */

public class DepartmentStatisticsActivity extends AppBarActivity implements PullToRefresh.PullToRefreshListener, DSContract.View {


    @BindView(R.id.ds_stores_name)
    TextView mStoresName;
    @BindView(R.id.ds_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.indicator)
    Indicator mIndicator;
    @BindView(R.id.ds_ptr)
    PtrClassicFrameLayout mPtr;
    @BindView(R.id.department_today_apply)
    RadioButton mTodayApply;
    @BindView(R.id.department_total_lending)
    RadioButton mTotalLending;
    @BindView(R.id.department_loan_failure)
    RadioButton mLoanFailure;
    @BindView(R.id.department_total_loans)
    RadioButton mTotalLoans;
    @BindView(R.id.ds_show_text_left)
    TextView mShowTextLeft;
    @BindView(R.id.ds_show_number_left)
    TextView mShowNumberLeft;
    @BindView(R.id.ds_show_text_right)
    TextView mShowTextRight;
    @BindView(R.id.ds_show_number_right)
    TextView mShowNumberRight;
    @BindView(R.id.ds_radio_group)
    RadioGroup mRadioGroup;
    private LinkedHashMap<Object, Object> map;

    private DSContract.Presenter presenter;
    private List<DSViewPagerDataBean> list;
    private String storesName;
    private int todayCountDept;
    private double todayMoneyDept;
    private int loanCountDept;
    private double loanMoneyDept;
    private int failCountDept;
    private double failMoneyDept;
    private int applyCountDept;
    private double applyMoneyDept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_statistics);
        ButterKnife.bind(this);
        setTitle("部门统计");
        isShowRightView(false);
        initView();
        initPtrClassicFrameLayout();
        //将Presenter和View进行绑定
        new DSPresener(this, this);
        //获取数据
        map = new LinkedHashMap<>();
        // map.put("","");
        presenter.getData(map);
    }

    private void initView() {
        list = new ArrayList<>();
        //设置ViewPager适配器
        mViewpager.setAdapter(new DSPagerAdapter(this, list));
        //设置ViewPager的监听器
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.setoffset(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changShowText(checkedId);
            }
        });

    }

    private void changShowText(int checkedId) {
        switch (checkedId){
            case R.id.department_today_apply:
                mShowTextLeft.setText("今日申请个数");
                mShowNumberLeft.setText(todayCountDept + "");
                mShowTextRight.setText("今日贷款申请金额");
                mShowNumberRight.setText(todayMoneyDept + "");
               break;
            case R.id.department_total_lending:
                mShowTextLeft.setText("放款总数");
                mShowNumberLeft.setText(loanCountDept + "");
                mShowTextRight.setText("放款总额");
                mShowNumberRight.setText(loanMoneyDept + "");
                break;
            case R.id.department_loan_failure:
                mShowTextLeft.setText("贷款失败总数");
                mShowNumberLeft.setText(failCountDept + "");
                mShowTextRight.setText("贷款失败总额");
                mShowNumberRight.setText(failMoneyDept + "");
                break;
            case R.id.department_total_loans:
                mShowTextLeft.setText("申请贷款总数");
                mShowNumberLeft.setText(applyCountDept + "");
                mShowTextRight.setText("申请贷款总额");
                mShowNumberRight.setText(applyMoneyDept + "");
                break;
        }
    }


    /**
     * 初始化上拉加载下拉刷新的布局
     */
    private void initPtrClassicFrameLayout() {
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(this, mPtr, mViewpager);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }


    @Override
    public void pullToRefresh() {
        presenter.getData(map);
    }

    @Override
    public void pullToLoadMore() {
        presenter.getData(map);
    }

    @Override
    public void setData(DepartmentStatisticsBean bean) {
        list.clear();
        DepartmentStatisticsBean.ResDataBean resData = bean.getResData();
        List<String> dates = resData.getDates();
        List<Integer> counts = resData.getCounts();
        List<Integer> accounts = resData.getAccounts();
        DSViewPagerDataBean bean0 = new DSViewPagerDataBean();
        bean0.setTitle("贷款个数最近7天统计");
        bean0.setTitleY("个数(个)");
        bean0.setRefreshNumber("1301每个月");
        bean0.setRefreshDate("最近更新：今天");
        bean0.setDate(dates);
        bean0.setValues(counts);
        DSViewPagerDataBean bean1 = new DSViewPagerDataBean();
        bean1.setTitle("贷款金额最近7天统计");
        bean1.setTitleY("金额(万元)");
        bean1.setRefreshNumber("1031每个月");
        bean1.setRefreshDate("最近更新：昨天");
        bean1.setDate(dates);
        bean1.setValues(accounts);
        list.add(bean0);
        list.add(bean1);
        //将数据保存到本地
        storesName = resData.getDeptName();
        // 今日申请个数  今日贷款申请金额
        todayCountDept = resData.getTodayCountDept();
        todayMoneyDept = resData.getTodayMoneyDept();
        // 放款总数  放款总额
        loanCountDept = resData.getLoanCountDept();
        loanMoneyDept = resData.getLoanMoneyDept();
        // 贷款失败总数  贷款失败总额
        failCountDept = resData.getFailCountDept();
        failMoneyDept = resData.getFailMoneyDept();
        // 申请贷款总数  申请贷款总额
        applyCountDept = resData.getApplyCountDept();
        applyMoneyDept = resData.getApplyMoneyDept();
        mStoresName.setText(resData.getDeptName());

        changShowText( mRadioGroup.getCheckedRadioButtonId());

        mViewpager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {

    }

    @Override
    public void setPresenter(DSContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
