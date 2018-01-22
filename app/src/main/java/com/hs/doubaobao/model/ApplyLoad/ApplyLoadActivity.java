package com.hs.doubaobao.model.ApplyLoad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.bean.ApplyListBean;
import com.hs.doubaobao.model.AddLoanTable.AddLoanTableActivity;
import com.hs.doubaobao.model.ApplyLoad.RecyclerView.ApplyLoadAdapter;
import com.hs.doubaobao.model.ApplyLoad.RecyclerView.ChildItem;
import com.hs.doubaobao.model.ApplyLoad.RecyclerView.ParentItem;
import com.hs.doubaobao.utils.PullToRefresh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2017/11/20 15:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class ApplyLoadActivity extends AppBarActivity implements ApplyLoadContract.View, PullToRefresh.PullToRefreshListener {


    private ApplyLoadContract.Presenter presenter;
    private RecyclerView recyclerView;

    private List<ParentItem> parentItems = new ArrayList<>();;
    private ApplyLoadAdapter adapter;
    private LinkedHashMap<Object, Object> map;

    private int page = 1;
    private int pages = 1;
    private PtrClassicFrameLayout ptrFrame;
    private PtrClassicFrameLayout ptrFrame1;
    private ApplyListBean.ResDataBean.PageDataListBean.PageBean pageBean;
    private List<ApplyListBean.ResDataBean.PageDataListBean.ListBean> listBeen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_apply_load);
        setTitle("贷款申请");
        setRightStatus(R.drawable.ic_add_loan_table);
        initData();

        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.applyload_ptr);
        ptrFrame1 = (PtrClassicFrameLayout) findViewById(R.id.applyload_ptr1);
        initPtrClassicFrameLayout();
        //将Presenter和View进行绑定
        new ApplyLoadPresener(this, this);
        map = new LinkedHashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        parentItems.clear();
        loadData();
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


    private void loadData() {
        map.put("page", page+"");
        map.put("rows", "10");
        //flag-----值： 1:贷款申请列表  2:部门初审列表  3:门店一审列表
        map.put("flag", "3");
        presenter.getData(map);
    }



    @Override
    public void onRightForward(View forwardView) {
        Intent intent = new Intent(this,AddLoanTableActivity.class);
        startActivity(intent);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        recyclerView = (RecyclerView) findViewById(R.id.applyload_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ApplyLoadAdapter(this, parentItems);
        recyclerView.setAdapter(adapter);
        /**父类条目展开的监听*/
//        adapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
//            @Override
//            public void onParentExpanded(int parentPosition) {
//                Toast.makeText(ApplyLoadActivity.this, "parentPosition:" + parentPosition,
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onParentCollapsed(int parentPosition) {
//                Toast.makeText(ApplyLoadActivity.this, "parentPosition:" + parentPosition,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        /**详情界面按钮的监听*/
        adapter.setChildItemClickListener(new ApplyLoadAdapter.ChildItemClickListener() {
            @Override
            public void onChildItemClick(int parentPosition, int childPosition) {
//                Toast.makeText(ApplyLoadActivity.this, "clickPosition:" + parentPosition,
//                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ApplyLoadActivity.this,AddLoanTableActivity.class);
                intent.putExtra("borrowId",listBeen.get(parentPosition).getId()+"");
                startActivity(intent);
            }
        });
    }

    /////////////////////////////////////////////////////////////////////
    //////START 点击事件
    /////////////////////////////////////////////////////////////////////
    public void onBackClick(View view){
        finish();
    }


    /**
     * 跳转到创建新表界面
     * @param view
     */
    public void onAddNewTable(View view) {
        Intent intent = new Intent(this,AddLoanTableActivity.class);
        startActivity(intent);
    }

    @Override
    public void setData(ApplyListBean bean) {
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
                ChildItem child = new ChildItem();
                child.setLoansUse(listBeen.get(i).getPurpose());
                child.setLoanCategories("网汇贷" + i);
                child.setTelephone(listBeen.get(i).getMobilephone());
                child.setApplicationPeriod("期数" + listBeen.get(i).getPeriod());
                child.setStoreName(listBeen.get(i).getOfficeName());
                child.setLoanAmount(listBeen.get(i).getStatus());
                ParentItem parentItem = new ParentItem();
                parentItem.setCustomName(listBeen.get(i).getCusName());
                parentItem.setApplyDate(listBeen.get(i).getApplydate());
                parentItem.setmChildItems(Arrays.asList(child));
                parentItems.add(parentItem);
            }
            adapter.setFlatItemList(parentItems);
            ptrFrame.setVisibility(View.VISIBLE);
            ptrFrame1.setVisibility(View.GONE);
        } else {
            ptrFrame.setVisibility(View.GONE);
            ptrFrame1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setError(String text) {
        ptrFrame.setVisibility(View.GONE);
        ptrFrame1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ApplyLoadContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pullToRefresh() {
        parentItems.clear();
        loadData();
    }

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
