package com.hs.doubaobao.model.AddLoanTable.uploadMessage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.listener.EditListener;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.AssetInformationFragment;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.BasicInformationFragment;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.FilloutLenderInfoAdapter;
import com.hs.doubaobao.model.AddLoanTable.uploadMessage.fragment.LiveInformationFragment;
import com.hs.doubaobao.view.ArcProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：zhanghaitao on 2017/11/23 17:30
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class FilloutLenderInformationActivity extends AppBarActivity {

    @BindView(R.id.fillout_basic_info_progress)
    public ArcProgressView mBasicProgress;
    @BindView(R.id.fillout_live_info_progress)
    public ArcProgressView mLiveProgress;
    @BindView(R.id.fillout_asset_info_progress)
    public ArcProgressView mAssetProgress;
    @BindView(R.id.fillout_lender_info_save)
    Button filloutSave;
    @BindView(R.id.fillout_basic_info_tab)
    FrameLayout mBasicInfoTab;
    @BindView(R.id.fillout_live_info_tab)
    FrameLayout mtLiveInfoTab;
    @BindView(R.id.fillout_asset_info_tab)
    FrameLayout mAssetInfoTab;
    @BindView(R.id.lender_info_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.lender_info_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.fillout_basic_info_icon)
    ImageView mBasicInfoIcon;
    @BindView(R.id.fillout_live_info_icon)
    ImageView mLiveInfoIcon;
    @BindView(R.id.fillout_asset_info_icon)
    ImageView mAssetInfoIcon;

    private String[] mTabItemNameArray;//标题集合
    private List<Fragment> fragments;
    //viewpager的监听器
    private FilloutLenderInfoAdapter adapter;
    //监听器
    public EditListener editBasicListener;
    public EditListener editLiveListener;
    public EditListener editAssetListener;

    //最大值
    private int maxBasicProgress = 6;
    private int maxLiveProgress = 2;
    private int maxAssetProgress = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("贷款人信息");
        isShowRightView(false);
        setContentView(R.layout.activity_fillout_lender_information);
        ButterKnife.bind(this);
        initListener();
        intData();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        /**输入监听*/
        editBasicListener = new EditListener() {
            @Override
            public void toChangedProgress(int isAdd) {
                changeProgress(mBasicProgress, isAdd);
            }
        };
        editLiveListener = new EditListener() {
            @Override
            public void toChangedProgress(int isAdd) {
                changeProgress(mLiveProgress, isAdd);
            }
        };
        editAssetListener = new EditListener() {
            @Override
            public void toChangedProgress(int isAdd) {
                changeProgress(mAssetProgress, isAdd);
            }
        };

        /**
         * 滑动监听
         */
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 2) {
                    filloutSave.setText("保存");
                } else {
                    filloutSave.setText("保存并下一步");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 改变Icon的状态
     *
     * @param image
     * @param progressView
     * @param normal
     * @param finish
     */
    private void changeIcon(ImageView image,
                            ArcProgressView progressView,
                            int normal, int finish) {
        float progress = progressView.getmProgress();
        float max = progressView.getmProgressMax();
        if(progress == max){
            image.setImageResource(finish);
        }else {
            image.setImageResource(normal);
        }
    }

    /**
     * 根据传入的数值将与之对应的的ArcProgressView的状态进行改变
     *
     * @param progressView
     * @param isAdd
     */
    public void changeProgress(ArcProgressView progressView, int isAdd) {
        if (isAdd != 0) {
            float progress = progressView.getmProgress();
            progressView.setmProgress(progress + isAdd);
            changeIcon(progressView);
        }

    }

    private void changeIcon(ArcProgressView progressView){
        if(progressView == mBasicProgress){
            changeIcon(mBasicInfoIcon,
                    mBasicProgress,
                    R.drawable.ic_basic_info,
                    R.drawable.ic_basic_info_finish);
        }
        if(progressView == mLiveProgress){
            changeIcon(mLiveInfoIcon,
                    mLiveProgress,
                    R.drawable.ic_live_info,
                    R.drawable.ic_live_info_finish);
        }
        if(progressView == mAssetProgress){
            changeIcon(mAssetInfoIcon,
                    mAssetProgress,
                    R.drawable.ic_asset_info,
                    R.drawable.ic_asset_info_finish);
        }
    }

    /**
     * 初始化数据（不需要联网）
     */
    private void intData() {
        mTabItemNameArray = getResources().getStringArray(R.array.fillout_tab_item);

        fragments = new ArrayList<>();
        fragments.add(new BasicInformationFragment());
        fragments.add(new LiveInformationFragment());
        fragments.add(new AssetInformationFragment());

        adapter = new FilloutLenderInfoAdapter(getSupportFragmentManager(), fragments, mTabItemNameArray);
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(fragments.size());
        mTabLayout.setupWithViewPager(mViewpager);

        mBasicProgress.setmProgressMax(maxBasicProgress);
        mLiveProgress.setmProgressMax(maxLiveProgress);
        mAssetProgress.setmProgressMax(maxAssetProgress);
    }

    /**
     * 保存按钮
     */
    @OnClick(R.id.fillout_lender_info_save)
    public void onViewClicked() {
        int currentItem = mViewpager.getCurrentItem();
        switch (currentItem) {
            case 0:
                BasicInformationFragment fragment = (BasicInformationFragment) fragments.get(0);
                fragment.saveData();
                mViewpager.setCurrentItem(1);
                break;
            case 1:
                LiveInformationFragment fragment1 = (LiveInformationFragment) fragments.get(1);
                fragment1.saveData();
                mViewpager.setCurrentItem(2);
                break;
            case 2:
                AssetInformationFragment fragment2 = (AssetInformationFragment) fragments.get(2);
                fragment2.saveData();
                break;
        }

    }

    /**
     * 顶部图标和ViewPager绑定
     *
     * @param view
     */
    @OnClick({R.id.fillout_basic_info_tab, R.id.fillout_live_info_tab, R.id.fillout_asset_info_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fillout_basic_info_tab:
                mViewpager.setCurrentItem(0);
                break;
            case R.id.fillout_live_info_tab:
                mViewpager.setCurrentItem(1);
                break;
            case R.id.fillout_asset_info_tab:
                filloutSave.setText("保存");
                mViewpager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public boolean savaData() {

        return super.savaData();
    }
}
