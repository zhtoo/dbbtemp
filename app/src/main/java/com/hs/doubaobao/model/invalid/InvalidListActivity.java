package com.hs.doubaobao.model.invalid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.adapter.InvalidAdapter;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.CommonBean;
import com.hs.doubaobao.bean.ListBean;
import com.hs.doubaobao.utils.PullToRefresh;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/11 17:44
 * 邮箱：820159571@qq.com
 *
 * @describe:无效列表
 */

public class InvalidListActivity extends AppBarActivity implements InvalidListContract.View, InvalidAdapter.onItemClickListener, PullToRefresh.PullToRefreshListener {

    private InvalidListContract.Presenter presenter;
    private RecyclerView mRecyclerView;

    private InvalidAdapter adapter;
    private List<ListBean> mList = new ArrayList<>();
    private CommonBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private List<CommonBean.ResDataBean.PageDataListBean.ListBean> listBeen;

    private PtrClassicFrameLayout ptrFrame;
    private Map<String, Object> map;
    private int page = 1;
    private int pages = 1;
    private EditText mSearchName;
    private PtrClassicFrameLayout ptrFrame1;
    private ImageView nullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_list);

        setTitle(getString(R.string.invalid_list));
        setTitleTextColor(R.color.textAggravating);

//        float width = getResources().getDimension(R.dimen.x36);
//        float height = getResources().getDimension(R.dimen.x8);
      //  setRightStatus(R.drawable.ic_invalid_search_selector,width,height);
        isShowRightView(false);
        setStatusBarBackground(R.drawable.ic_battery_bg);

        initView();

        new InvalidListPresenter(this,this);
        map = new LinkedHashMap<>();
        loadData();
    }

    private void loadData() {
        map.put("userId", BaseParams.USER_ID);
        map.put("page", page+"");
        map.put("rows", "10");
        presenter.getData(map);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.invalid_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        adapter = new InvalidAdapter(this, mList, 4);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);


        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.invalid_ptr);
        ptrFrame1 = (PtrClassicFrameLayout) findViewById(R.id.invalid_ptr1);

        nullImageView = (ImageView) findViewById(R.id.null_imageView);
        nullImageView.setImageResource(R.drawable.ic_null1);


        initPtrClassicFrameLayout();

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

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    public void setData(CommonBean bean) {
        //分页
        pageBean = bean.getResData().getPageDataList().getPage();
        //list内容
        listBeen = bean.getResData().getPageDataList().getList();
        pages = pageBean.getPages();

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
            ptrFrame1.setVisibility(View.GONE);
            ptrFrame.setVisibility(View.VISIBLE);
        } else {
            ptrFrame.setVisibility(View.GONE);
            ptrFrame1.setVisibility(View.VISIBLE);

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {
        ptrFrame.setVisibility(View.GONE);
        ptrFrame1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(InvalidListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    private void hideInput(View view) {
        //获取输入模式的管理对象
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            //关闭软键盘
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onItemClick(int postion) {
        Intent intent = new Intent(this, InvalidReasonActivity.class);
        intent.putExtra("invalidId", "" + mList.get(postion).getId());
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
        loadData();
    }

    /**
     * 上拉刷新
     */
    @Override
    public void pullToLoadMore() {
        if (page >= pages) {
            Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
        } else {
            page += 1;
            loadData();
        }
    }
}