package com.hs.doubaobao.model.detail.pictrue;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.BaseFragment;
import com.hs.doubaobao.bean.PictrueBean;
import com.hs.doubaobao.model.detail.DetailActivity;
import com.hs.doubaobao.model.detail.pictrue.group.GroupAdapter;
import com.hs.doubaobao.model.detail.pictrue.group.GroupedGridLayoutManager;
import com.hs.doubaobao.model.detail.pictrue.group.PictureMessage;
import com.hs.doubaobao.utils.PullToRefresh;
import com.hs.doubaobao.utils.log.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * 作者：zhanghaitao on 2017/9/12 15:11
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictrueFragment extends BaseFragment implements PictrueContract.View, PictrueAdapter.onItemClickListener, PullToRefresh.PullToRefreshListener {

    private PictrueContract.Presenter presenter;

    private RecyclerView mRecycler;
    private List<PictureMessage> mList;
    private List<String> imgUrls =new ArrayList<>();
    private GroupAdapter adapter;
    private Button mReload;
    private PtrClassicFrameLayout ptrFrame;
    private Map<String, String> map;

    @Override
    protected int setLayout() {
        return R.layout.fragment_pictrue;
    }

    @Override
    protected void initView(View view) {

        //无数据刷新
        ptrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.pictrue_ptr);
        initPtrClassicFrameLayout();

        mRecycler = (RecyclerView) view.findViewById(R.id.pictrue_recycler);

        mList = new ArrayList<>();

        adapter = new GroupAdapter(getContext(), mList);
        //  GridLayoutManager lm = new GridLayoutManager(getContext(), 4);
        GroupedGridLayoutManager lm = new GroupedGridLayoutManager(getContext(), 4, adapter);
        lm.setOrientation(GridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(lm);


        mRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        DetailActivity activity = (DetailActivity) getActivity();
        String id = activity.id;

        //将Presenter和View进行绑定
        new PictruePresener(this, getContext());
        //获取数据
        map = new LinkedHashMap<>();
        map.put("id", id);
        presenter.getData(map);
    }


    /**
     * 初始化上拉加载下拉刷新的布局
     * 注意：adapter的初始化在 PullToRefresh 之前
     */
    private void initPtrClassicFrameLayout() {
        //注意：adapter的初始化在 PullToRefresh 之前
        //创建PtrClassicFrameLayout的包装类对象
        PullToRefresh refresh = new PullToRefresh();
        //初始化PtrClassicFrameLayout
        refresh.initPTR(getContext(), ptrFrame);
        //设置监听
        refresh.setPullToRefreshListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        //imagesize是作为loading时的图片size
        ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
         ImagePagerActivity.startImagePagerActivity(getContext(), imgUrls, position, imageSize);
    }

    @Override
    public void setData(PictrueBean bean) {
        if (bean == null || bean.getResData().getPicList() == null || bean.getResData().getPicList().size() == 0) {
            mRecycler.setVisibility(View.GONE);
        } else {
            List<PictrueBean.ResDataBean.PicListBean> picList = bean.getResData().getPicList();
            mList.clear();
            String type = "1-2-3-4-5-6-7";
            String type12 = "12";
            String[] typeArr = {"0", "四证", "征信报告", "银行流水", "车辆社保公积金", "家访",
                    "经营场所", "其他", "8", "9", "10", "11", "签约"};
            for (int i = 0; i < picList.size(); i++) {
                int category = picList.get(i).getCategory();
                if (type.contains(String.valueOf(category))) {
                    type = type.replace(String.valueOf(category), "");
                    PictureMessage pictureMessage = new PictureMessage();
                    pictureMessage.setCategory(category == 7 ? 13 : category);
                    pictureMessage.setTitleType(0);
                    pictureMessage.setName(typeArr[category]);
                    pictureMessage.setUrl("http://www.zht.com");//错误地址
                    mList.add(pictureMessage);
                } else if (category == 12&&!TextUtils.isEmpty(type12)) {
                    type12 = "";
                    PictureMessage pictureMessage = new PictureMessage();
                    pictureMessage.setCategory(category);
                    pictureMessage.setTitleType(0);
                    pictureMessage.setName(typeArr[category]);
                    pictureMessage.setUrl("http://www.zht.com");//错误地址
                    mList.add(pictureMessage);
                }
                PictureMessage pictureMessage = new PictureMessage();
                pictureMessage.setCategory(category == 7 ? 13 : category);
                pictureMessage.setTitleType(1);
                pictureMessage.setName(picList.get(i).getName());
                pictureMessage.setUrl(picList.get(i).getPath());
                pictureMessage.setListPosition(i);
                imgUrls.add(picList.get(i).getPath());
                mList.add(pictureMessage);
            }

            //检测视频的类别
            String[] positionArr1 = {"1", "2", "3", "4", "5", "6", "7"};
            for (int i = 0; i < positionArr1.length; i++) {
                if (type.contains(positionArr1[i]) ||
                        (!TextUtils.isEmpty(type12) && type12.contains(typeArr[i]))) {
                    int i1 = Integer.parseInt(positionArr1[i]);
                    PictureMessage pictureMessage = new PictureMessage();
                    pictureMessage.setCategory(i1 == 7 ? 13 : i1);
                    pictureMessage.setTitleType(0);
                    pictureMessage.setName(typeArr[i1]);
                    pictureMessage.setUrl("http://www.zht.com");
                    mList.add(pictureMessage);
                    PictureMessage pictureMessage1 = new PictureMessage();
                    pictureMessage1.setCategory(i1 == 7 ? 13 : i1);
                    pictureMessage1.setTitleType(1);
                    pictureMessage1.setName("空图片");
                    mList.add(pictureMessage1);
                }
            }

            //对集合数据按照拼音进行排序
            Collections.sort(mList, new Comparator<PictureMessage>() {

                public int compare(PictureMessage o1, PictureMessage o2) {
                    int i1 = o1.getCategory();
                    int i2 = o2.getCategory();
                    int i = i1 - i2;
                    return i;
                }
            });


            mRecycler.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String text) {
        mRecycler.setVisibility(View.GONE);
        Toast.makeText(getContext(), "访问数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PictrueContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pullToRefresh() {
        presenter.getData(map);
    }

    @Override
    public void pullToLoadMore() {
        presenter.getData(map);
    }
}
