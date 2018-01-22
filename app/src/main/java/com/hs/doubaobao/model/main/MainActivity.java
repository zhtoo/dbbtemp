package com.hs.doubaobao.model.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.HomeAdapter;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.HomeBean;
import com.hs.doubaobao.bean.ListBean;
import com.hs.doubaobao.model.ApplyLoad.ApplyLoadActivity;
import com.hs.doubaobao.model.GeneralManager.GeneralManagerApprovalActivity;
import com.hs.doubaobao.model.Login.LoginActivity;
import com.hs.doubaobao.model.StoreInstance.StoreInstanceActivity;
import com.hs.doubaobao.model.department.DepartmentStatisticsActivity;
import com.hs.doubaobao.model.department.DepartmentTrialActivity;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.model.invalid.InvalidListActivity;
import com.hs.doubaobao.model.riskControl.RiskControlApprovalActivity;
import com.hs.doubaobao.utils.PullToRefresh;
import com.hs.doubaobao.utils.SPHelp;
import com.hs.doubaobao.utils.log.Logger;
import com.hs.doubaobao.view.DotView;
import com.hs.doubaobao.view.MyRelativeLayout;
import com.hs.doubaobao.view.SlidingMenu;
import com.zht.datepicker.DateSelectUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 主界面
 * rectification by zht on 2017/9/11  16:51
 */
public class MainActivity extends AppCompatActivity implements MainContract.View, HomeAdapter.onItemClickListener, View.OnClickListener, PullToRefresh.PullToRefreshListener, SlidingMenu.onMenuShowListener {

    private static final String TAG = "MainActivity";
    //控件
    private SlidingMenu sliding_menu;
    private LinearLayout ll_menu;
    private LinearLayout mSearch;
    private RecyclerView mRecyclerView;
    private MyRelativeLayout mSearchContainer;
    private LinearLayout mStatusBar;
    private TextView mName;
    private TextView mMenuName;
    private LinearLayout mMenuRisk;
    private LinearLayout mMenuManager;
    private LinearLayout mMenuInvalid;
    private EditText mSearchName;

    private PtrClassicFrameLayout ptrFrame1;
    private DotView mMainDot;
    private DotView mMenuRiskDot;
    private DotView mMenuManagerDot;
    private LinearLayout mGray;
    private LinearLayout mMenu;
    private RelativeLayout mMenuLogo;
    private RelativeLayout mTitleBar;

    private MainContract.Presenter presenter;
    private HomeBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private HomeBean.ResDataBean.MessageCountBean messageCount;
    private List<HomeBean.ResDataBean.PageDataListBean.ListBean> listBeen;

    private List<Integer> roleIdList;
    private List<ListBean> mList = new ArrayList<>();

    private HomeAdapter adapter;
    public static boolean isShowing = false;
    private PtrClassicFrameLayout ptrFrame;
    //存放请求参数
    private Map<String, Object> map;
    private List<String> searchList = new ArrayList<>();
    //分页
    private int page = 1;
    private int pages = 1;
    private Spinner mSearchType;
    private Spinner mSearchPeriods;
    private Spinner mSearchStatus;
    private EditText mSearchAccountStart;
    private EditText mSearchAccountEnd;
    private TextView mSearchTimeStart;
    private TextView mSearchTimeEnd;
    private Button mSearchReset;
    private Button mSearchStart;
    private List<String> params = new ArrayList<>();

    /**
     * 贷款类别
     * //01:汇民贷，02：汇商贷，03：汇业贷，04：汇车贷，05：汇农贷，06：汇房贷
     * <p>
     * 贷款期限/实批期数
     * 1：12期，2：18期，3：24期，4：36期，5：48期，6：60期
     * <p>
     * 贷款状态
     * 00：待提交，01：待初审，03：初审不通过，10：待家访， 11：待放款评估，20：待风控审批，21：风控审批不通过，30：待总经理审批，31：总经理审批不通过，40：待签约，41:签约失败，50:待放款，52：放款失败，51：放款成功
     */
    private String[] loadTypeArr = {"", "汇民贷", "汇商贷", "汇业贷", "汇车贷", "汇农贷", "汇房贷"};
    private String[] loadPeriodsArr = {"", "12期", "18期", "24期", "36期", "48期", "60期"};
    private Map<String, String> loadStatusMap = new LinkedHashMap<>();
    private LinearLayout mMenuLoanApply;
    private LinearLayout mMenuDepartmentTrial;
    private LinearLayout mMenuDepartmentStatistics;
    private LinearLayout mMenuStoreInstance;
    private DotView mMenuDepartmentTrialDot;
    private DotView mMenuStoreInstanceDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.e(TAG, "当前线程的进程的ID：" + Process.myPid());
        initView();
        initState();
        initLisener();
        initRecyclerView();
        initPtrClassicFrameLayout();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mName.setText(name);
        mMenuName.setText(name);
        new MainPresenter(this, this);
        loadStatusMap.put("待提交", "00");
        loadStatusMap.put("待初审", "01");
        loadStatusMap.put("初审不通过", "03");
        loadStatusMap.put("待家访", "10");
        loadStatusMap.put("待放款评估", "11");
        loadStatusMap.put("待风控审批", "20");
        loadStatusMap.put("风控审批不通过", "21");
        loadStatusMap.put("待总经理审批", "30");
        loadStatusMap.put("总经理审批不通过", "31");
        loadStatusMap.put("待签约", "40");
        loadStatusMap.put("签约失败", "41");
        loadStatusMap.put("待放款", "50");
        loadStatusMap.put("放款失败", "52");
        loadStatusMap.put("放款成功", "51");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mList.clear();
        searchList.clear();
        loadData(null);
    }

    @Override
    protected void onStop() {
        super.onPause();
        sliding_menu.startScroll(0);
    }

    /**
     * 联网访问数据
     */
    private void loadData(List<String> paramValues) {
        map = new LinkedHashMap<>();
        map.put("userId", BaseParams.USER_ID);
        map.put("page", page + "");
        map.put("rows", "10");
        if (paramValues != null) {
            checkParams("searchName", paramValues.get(0));
            checkParams("searchBorrowType", paramValues.get(1));
            checkParams("searchPeriod", paramValues.get(2));
            checkParams("searchStatus", paramValues.get(3));
            checkParams("searchSartMoney", paramValues.get(4));
            checkParams("searchEndMoney", paramValues.get(5));
            checkParams("searchSartTime", paramValues.get(6));
            checkParams("searchEndTime", paramValues.get(7));
        }
        presenter.getData(map);
    }

    private void checkParams(String params, String valus) {
        if (!TextUtils.isEmpty(valus)) {
            map.put(params, valus);
        } else {
            map.put(params, "");
        }
    }

/////////////////////////////////////////////////////////////////////
//////视图初始化START
/////////////////////////////////////////////////////////////////////

    /**
     * 初始化视图
     */
    private void initView() {
        sliding_menu = (SlidingMenu) findViewById(R.id.sliding_menu);
        initMainView();
        initMenuView();
        initSearchView();
        //设置状态栏的背景
        mStatusBar.setBackgroundResource(R.drawable.ic_battery_bg);
    }

    private void initMainView() {
        //全局的灰色背景
        mGray = (LinearLayout) findViewById(R.id.main_gray_bg);
        //状态栏
        mStatusBar = (LinearLayout) findViewById(R.id.main_status_bar);
        //搜索栏
        mSearch = (LinearLayout) findViewById(R.id.main_search);
        //标题栏
        mTitleBar = (RelativeLayout) findViewById(R.id.main_title_bar);
        //搜索栏的容器
        mSearchContainer = (MyRelativeLayout) findViewById(R.id.main_search_container);
        //首页的RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        //用户名称
        mName = (TextView) findViewById(R.id.home_person_name);
        //消息小红点
        mMainDot = (DotView) findViewById(R.id.main_dot);
        //下拉刷新，上拉加载
        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.main_ptr);
        ptrFrame1 = (PtrClassicFrameLayout) findViewById(R.id.main_ptr1);
    }

    private void initMenuView() {
        //菜单的rootView
        mMenu = (LinearLayout) findViewById(R.id.menu);
        //ScrollView里面的子孩子
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        //菜单的背景图标
        mMenuLogo = (RelativeLayout) findViewById(R.id.menu_logo_bg);
        //菜单的用户名称
        mMenuName = (TextView) findViewById(R.id.menu_person_name);
        //菜单的七个条目(根据权限控制显示和隐藏)

        //部门申请
        mMenuLoanApply = (LinearLayout) findViewById(R.id.menu_loan_apply);
        //部门初审
        mMenuDepartmentTrial = (LinearLayout) findViewById(R.id.menu_department_trial);
        //部门统计
        mMenuDepartmentStatistics = (LinearLayout) findViewById(R.id.menu_department_statistics);
        //门店一审
        mMenuStoreInstance = (LinearLayout) findViewById(R.id.menu_store_instance);
        mMenuRisk = (LinearLayout) findViewById(R.id.menu_risk);
        mMenuManager = (LinearLayout) findViewById(R.id.menu_manager);
        mMenuInvalid = (LinearLayout) findViewById(R.id.menu_invalid);
        //消息小红点
        mMenuStoreInstanceDot = (DotView) findViewById(R.id.menu_store_instance_dot);
        mMenuDepartmentTrialDot = (DotView) findViewById(R.id.menu_department_trial_dot);
        mMenuRiskDot = (DotView) findViewById(R.id.menu_risk_dot);
        mMenuManagerDot = (DotView) findViewById(R.id.menu_manager_dot);

        //保正菜单栏的比例合适
        ViewGroup.LayoutParams layoutParams = mMenuLogo.getLayoutParams();
        layoutParams.width = sliding_menu.getWidthPixels() * 59 / 72;
        layoutParams.height = sliding_menu.getWidthPixels() * 42 / 72;
        mMenuLogo.setLayoutParams(layoutParams);
    }

    private void initSearchView() {
        //刷选栏的条件:---输入客户名称/门店名称/电话号进行搜索
        mSearchName = (EditText) findViewById(R.id.search_name);
        //贷款类别
        mSearchType = (Spinner) findViewById(R.id.search_load_type);
        //贷款期限
        mSearchPeriods = (Spinner) findViewById(R.id.search_load_periods);
        //状态
        mSearchStatus = (Spinner) findViewById(R.id.search_load_status);
        //金额范围
        mSearchAccountStart = (EditText) findViewById(R.id.search_account_start);
        mSearchAccountEnd = (EditText) findViewById(R.id.search_account_end);
        //申请时间
        mSearchTimeStart = (TextView) findViewById(R.id.search_apply_time_start);
        mSearchTimeEnd = (TextView) findViewById(R.id.search_apply_time_end);
        //重置
        mSearchReset = (Button) findViewById(R.id.search_reset);
        //开始搜索
        mSearchStart = (Button) findViewById(R.id.search_start);

        setSpinnnerStyle(mSearchType, R.array.search_loan_categories);
        setSpinnnerStyle(mSearchPeriods, R.array.search_loan_periods_arr);
        setSpinnnerStyle(mSearchStatus, R.array.search_load_status_arr);
    }

    /**
     * 给Spinnner设置自己的样式
     *
     * @param spinner
     * @param resId
     */
    private void setSpinnnerStyle(Spinner spinner, int resId) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner_simple);
        String level[] = getResources().getStringArray(resId);//资源文件R.array.search_load_status_arr
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinner.setAdapter(adapter);
    }

    /**
     * 初始化监听事件
     */
    private void initLisener() {
        mSearch.setOnClickListener(this);
        sliding_menu.setMenuShowListener(this);
        mName.setOnClickListener(this);

        //时间选择
        mSearchTimeStart.setOnClickListener(this);
        mSearchTimeEnd.setOnClickListener(this);
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    private void initState() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mStatusBar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight;
            //通过反射的方式获取状态栏高度
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
                statusHeight = 0;
            }
            //动态的设置隐藏布局的高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mStatusBar.getLayoutParams();
            params.height = statusHeight;
            mStatusBar.setLayoutParams(params);
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        adapter = new HomeAdapter(this, mList, 0);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 初始化上拉加载下拉刷新的布局
     * 注意：adapter的初始化在 PullToRefresh 之前
     */
    private void initPtrClassicFrameLayout() {
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(this, ptrFrame);
        refresh.initPTR(this, ptrFrame1);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    /////////////////////////////////////////////////////////////////////
    //////点击事件处理START
    /////////////////////////////////////////////////////////////////////

    /**
     * 主界面上的菜单按钮被单击了
     */
    public void onMenuToggleClick(View v) {
        //if (v.getId() == R.id.home_person_name) {
//        hideInput(v);
//        if (isShowing) {
//            isShowing = false;
//            mSearch.setVisibility(View.GONE);
//            mSearchContainer.setVisibility(View.GONE);
//        }
//        sliding_menu.toggle();
        //}
    }

    /**
     * 主界面的筛选按钮被点击
     */
    public void onMenuSearchClick(final View view) {
        hideInput(view);
        if (isShowing) {
            isShowing = false;
            ShowSearchAnimator(1f, 0f);
        } else {
            mSearchName.setText("");
            isShowing = true;
            mSearchContainer.setVisibility(View.VISIBLE);
            mSearch.setVisibility(View.VISIBLE);
            ShowSearchAnimator(0f, 1f);
        }
    }

    /**
     * 主界面的灰色部分被点击
     */
    public void onMenuSearchContainerClick(final View view) {
        hideInput(view);
        if (isShowing) {
            isShowing = false;
            ShowSearchAnimator(1f, 0f);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    private void hideInput(View view) {
        //获取输入模式的管理对象
        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 菜单列表中的某个菜单项被单击了
     */
    public void onMenuItemClick(int type) {
        Class[] classes = {RiskControlApprovalActivity.class,
                GeneralManagerApprovalActivity.class,
                InvalidListActivity.class,
                ApplyLoadActivity.class,
                DepartmentStatisticsActivity.class,
                DepartmentTrialActivity.class,
                StoreInstanceActivity.class
        };
        if (type != -1) {
            Intent intent = new Intent(this, classes[type]);
            startActivity(intent);
        }
    }

    /*贷款申请*/
    public void onLoanApplyClick(View v) {
        onMenuItemClick(3);
   }

    /*部门初审*/
    public void onDepartmentTrialClick(View v) {
        onMenuItemClick(5);
    }

    /*部门统计*/
    public void onDepartmentStatisticsClick(View v) {
        onMenuItemClick(4);
    }

    /*门店一审*/
    public void onStoreInstanceClick(View v) {
        onMenuItemClick(6);
    }

    /*风控*/
    public void onRiskClick(View v) {
        onMenuItemClick(0);
    }

    /*总经理*/
    public void onManagerClick(View v) {
        onMenuItemClick(1);
    }

    /*无效列表*/
    public void onInvalidClick(View v) {
        onMenuItemClick(2);
    }

    /**
     * 开始搜索
     */
    public void startSearch(View v) {
        hideInput(v);
        if (isShowing) {
            isShowing = false;
            ShowSearchAnimator(1f, 0f);
        }
        mList.clear();
        page = 1;
        //搜索名字
        String name = mSearchName.getText().toString().trim();
        //贷款类别
        String type = mSearchType.getSelectedItem().toString().trim();
        //贷款期限
        String periods = mSearchPeriods.getSelectedItem().toString().trim();
        //状态
        String status = mSearchStatus.getSelectedItem().toString().trim();
        //金额范围
        String accountStart = mSearchAccountStart.getText().toString().trim();
        String accountEnd = mSearchAccountEnd.getText().toString().trim();
        //申请时间
        String timeStart = mSearchTimeStart.getText().toString().trim();
        String timeEnd = mSearchTimeEnd.getText().toString().trim();

        params.add(0, name);
        String typeCode = getStatusCode(type, loadTypeArr);
        params.add(1, TextUtils.isEmpty(typeCode) ? "" : ("0" + typeCode));
        params.add(2, getStatusCode(periods, loadPeriodsArr));

        params.add(3, loadStatusMap.get(status));
        params.add(4, accountStart);
        params.add(5, accountEnd);

        if (timeStart.equals("开始日期")) {
            timeStart = "";
        }
        if (timeEnd.equals("结束日期")) {
            timeEnd = "";
        }
        params.add(6, timeStart);
        params.add(7, timeEnd);

        searchList = params;

        loadData(params);
        // resetSearchDate();
    }

    private String getStatusCode(String key, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (key.equals(list[i])) {
                return String.valueOf(i);
            }
        }
        return "";
    }

    /**
     * 重置搜索
     */
    public void resetSearch(View v) {
        hideInput(v);
        resetSearchDate();
    }

    private void resetSearchDate() {
        mSearchName.setText("");
        mSearchType.setSelection(0);
        mSearchPeriods.setSelection(0);
        mSearchStatus.setSelection(0);
        mSearchAccountStart.setText("");
        mSearchAccountEnd.setText("");
        mSearchTimeStart.setText("开始日期");
        mSearchTimeEnd.setText("结束日期");
    }

    /**
     * 退出当前账号
     */
    public void onExit(View v) {
        Dialog alertDialog = new AlertDialog.Builder(this).
                setMessage("您确定退出当前账号吗？").
                setIcon(R.mipmap.ic_launcher).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPHelp.setData("name", "");
                        SPHelp.setData("password", "");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).
                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                }).create();
        alertDialog.show();
    }

    public void onGrayClick(View v) {
        sliding_menu.toggle();
    }

    @Override
    public void onClick(View v) {
        hideInput(v);
        switch (v.getId()) {
            case R.id.home_person_name:
                hideInput(v);
                if (isShowing) {
                    isShowing = false;
                    mSearch.setVisibility(View.GONE);
                    mSearchContainer.setVisibility(View.GONE);
                }
                sliding_menu.toggle();
                break;
            case R.id.search_apply_time_start:
                DateSelectUtil.showSelectDateDialog(this, mSearchTimeStart);
                break;
            case R.id.search_apply_time_end:
                DateSelectUtil.showSelectDateDialog(this, mSearchTimeEnd);
                break;
        }
    }

    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("ID", mList.get(postion).getId() + "");
        startActivity(intent);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void pullToRefresh() {
        //清空数据
        mList.clear();
        page = 1;
        if (searchList != null && searchList.size() > 0) {
            loadData(searchList);
        } else {
            loadData(null);
        }
    }

    /**
     * 上拉加载
     */
    @Override
    public void pullToLoadMore() {
        if (page >= pages) {
            Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
        } else {
            page += 1;
            if (searchList != null && searchList.size() > 0) {
                loadData(searchList);
            } else {
                loadData(null);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    //////数据处理START
    /////////////////////////////////////////////////////////////////////

    /**
     * 请求回来的数据处理
     *
     * @param bean
     */
    @Override
    public void setData(HomeBean bean) {
        //角色权限
        roleIdList = bean.getResData().getRoleIdList();
        //角色的消息
        messageCount = bean.getResData().getMessageCount();
        changeMenuStatus();
        //分页
        pageBean = bean.getResData().getPageDataList().getPage();
        //获取总页数
        pages = pageBean.getPages();
        //每页总数
        pageBean.getPernum();
        //list内容
        listBeen = bean.getResData().getPageDataList().getList();
        if (listBeen != null && listBeen.size() > 0) {
            for (int i = 0; i < listBeen.size(); i++) {
                ListBean mBean = new ListBean();
                mBean.setName(listBeen.get(i).getCusName());
                mBean.setTime(listBeen.get(i).getApplydate());
                mBean.setPurpose(listBeen.get(i).getPurpose());
                mBean.setLoanAmount(listBeen.get(i).getAccount());
                mBean.setCustomPhone(listBeen.get(i).getMobilephone());
                mBean.setOfficeName(listBeen.get(i).getOfficeName());
                mBean.setLoanPeriods(listBeen.get(i).getPeriod());
                mBean.setStatus(listBeen.get(i).getStatus());
                mBean.setId(listBeen.get(i).getId());
                mBean.setActPeriod(listBeen.get(i).getActPeriod());
                mBean.setLoanRation(listBeen.get(i).getManagerRation());
                mList.add(mBean);
            }
            ptrFrame.setVisibility(View.VISIBLE);
            ptrFrame1.setVisibility(View.GONE);
        } else {
            ptrFrame.setVisibility(View.GONE);
            ptrFrame1.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 根据数据改变菜单的状态
     */
    private void changeMenuStatus() {
        int size = roleIdList.size();
        if (roleIdList == null || size == 0) {
            mMenuDepartmentTrial.setVisibility(View.GONE);
            mMenuDepartmentStatistics.setVisibility(View.GONE);
            mMenuStoreInstance.setVisibility(View.GONE);
            mMenuRisk.setVisibility(View.GONE);
            mMenuManager.setVisibility(View.GONE);
        } else {
            String vlues = "";
            //将所有的权限拼接成字符串
            for (int key : roleIdList) {
                vlues += key + "-";
            }
            mMenuDepartmentTrial.setVisibility(vlues.contains("523") ? View.VISIBLE : View.GONE);
            mMenuDepartmentStatistics.setVisibility(vlues.contains("523") ? View.VISIBLE : View.GONE);
            mMenuStoreInstance.setVisibility(vlues.contains("335") ? View.VISIBLE : View.GONE);
            mMenuRisk.setVisibility(vlues.contains("338") ? View.VISIBLE : View.GONE);
            mMenuManager.setVisibility(vlues.contains("509") ? View.VISIBLE : View.GONE);
        }

        //部门审批消息
        int messageRole523 = messageCount.getMessageRole523();
        //一审审批消息
        int messageRole335 = messageCount.getMessageRole335();
        //风控审批消息
        int messageRole338 = messageCount.getMessageRole338();
        //总经理审批消息
        int messageRole509 = messageCount.getMessageRole509();

        mMainDot.setVisibility((
                messageRole523 == 1 ||
                        messageRole335 == 1||
                        messageRole338 == 1 ||
                        messageRole509 == 1
        ) ? View.VISIBLE : View.GONE);
        mMenuRiskDot.setVisibility(messageRole338 == 1 ? View.VISIBLE : View.GONE);
        mMenuManagerDot.setVisibility(messageRole509 == 1 ? View.VISIBLE : View.GONE);
        mMenuStoreInstanceDot.setVisibility(messageRole335 == 1 ? View.VISIBLE : View.GONE);
        mMenuDepartmentTrialDot.setVisibility(messageRole523 == 1 ? View.VISIBLE : View.GONE);
    }

    /**
     * 数据错误
     *
     * @param text
     */
    @Override
    public void setError(String text) {
        Toast.makeText(this, "网络不给力", Toast.LENGTH_SHORT).show();
        ptrFrame.setVisibility(View.GONE);
        ptrFrame1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 当菜单显示的时候  onGrayClick
     *
     * @param show
     */
    @Override
    public void onMenuShow(boolean show) {
        mGray.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * 搜索菜单显示和隐藏的动画
     *
     * @param start
     * @param end
     */
    private void ShowSearchAnimator(float start, float end) {
        //设置缩放的相对位置
        mSearch.setPivotX(getResources().getDimension(R.dimen.x625));//相对于控件的位子
        mSearch.setPivotY(0);
        //设置动画
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(mSearch, "main_search", start, end)//设置变化目标值
                .setDuration(500);//设置动画时间
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                mSearch.setAlpha(cVal);
                mSearch.setScaleX(cVal);
                mSearch.setScaleY(cVal);
            }
        });

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShowing) {
                    mSearch.setVisibility(View.GONE);
                    mSearchContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    //--------------使用onKeyDown()完成双击退出程序--------------

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
