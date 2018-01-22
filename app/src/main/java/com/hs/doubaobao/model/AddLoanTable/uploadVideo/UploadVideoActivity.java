package com.hs.doubaobao.model.AddLoanTable.uploadVideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick.VideoConfig;
import com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick.VideoListActivity;
import com.hs.doubaobao.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hs.doubaobao.MyApplication.getContext;
import static com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick.VideoConfig.VIDEO_LIST;

/**
 * 作者：zhanghaitao on 2018/1/8 16:48
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class UploadVideoActivity extends AppBarActivity {


    @BindView(R.id.upload_video_tablayout)
    TabLayout tablayout;
    @BindView(R.id.upload_video_viewpager)
    ViewPager mViewpager;
    private String[] mTabItemNameArray = {"面审视频", "家访视频", "其他视频"};
    private List<UploadVideoFragment> fragments;
    private UploadVideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);
        ButterKnife.bind(this);
        setTitle("视频上传");
        isShowRightView(false);

        addFragment();//初始化Fragmrnt，并添加到ViewPager中。
        initViewPager();
        tablayout.setupWithViewPager(mViewpager);
        checkPermission();
    }


    /**
     * 权限检测
     */
    private void checkPermission() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};

        //定义一个变量 记录当前权限的状态
        int checkSlfePermission = PackageManager.PERMISSION_GRANTED;

        for (int i = 0; i < permissions.length; i++) {
            int denide = ContextCompat.checkSelfPermission(getContext(), permissions[i]);
            if (denide == PackageManager.PERMISSION_DENIED) {
                checkSlfePermission = PackageManager.PERMISSION_DENIED;
            }
        }

        //当前么有相应的权限
        if (checkSlfePermission == PackageManager.PERMISSION_DENIED) {
            //申请权限 （弹出一个申请权限的对话框）
            ActivityCompat.requestPermissions(this, permissions, 120);
        } else
            //申请到了权限
            if (checkSlfePermission == PackageManager.PERMISSION_GRANTED) {

            }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (data == null) {
            return;
        }
        if (requestCode == VideoConfig.PICK_VIDEO_REQUEST) {
            ArrayList<CharSequence> list = data.getCharSequenceArrayListExtra(VIDEO_LIST);
            int currentItem = mViewpager.getCurrentItem();
            if(list!=null){
                fragments.get(currentItem).setVideoPaths(list);
                fragments.get(currentItem).getRecycler().getAdapter().notifyDataSetChanged();
            }
        }
    }

    private void addFragment() {
        if (tablayout == null || mViewpager == null) {
            return;
        }
        fragments = new ArrayList<>();

        fragments.add(new UploadVideoFragment());
        fragments.add(new UploadVideoFragment());
        fragments.add(new UploadVideoFragment());

    }

    private void initViewPager() {
        if (fragments == null) {
            return;
        }
        adapter = new UploadVideoAdapter(getSupportFragmentManager(), fragments, mTabItemNameArray);
        mViewpager.setAdapter(adapter);
    }


    public class UploadVideoAdapter extends FragmentPagerAdapter {

        /**
         * fragment集合
         */
        private List<UploadVideoFragment> mFragmentList = null;
        /**
         * 标题集合
         */
        private String[] titles;

        /**
         * titles是给TabLayout设置title用的
         *
         * @param mFragmentManager
         * @param fragmentList
         * @param titles
         */
        public UploadVideoAdapter(FragmentManager mFragmentManager,
                                  List<UploadVideoFragment> fragmentList, String[] titles) {
            super(mFragmentManager);
            mFragmentList = fragmentList;
            this.titles = titles;
        }

        /**
         * 获取数量
         *
         * @return the count
         * @see android.support.v4.view.PagerAdapter#getCount()
         */
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        /**
         * 获取索引位置的Fragment.
         *
         * @param position the position
         * @return the item
         * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
         */
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            if (position < mFragmentList.size()) {
                fragment = mFragmentList.get(position);
            } else {
                fragment = mFragmentList.get(0);
            }
            return fragment;
        }

        /**
         * 将标题设置到相对应的位置
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            if (titles != null && titles.length > 0)
                return titles[position];
            return null;
        }
    }


    public void onVideoClick(View view) {
        String mVendor = Build.MANUFACTURER;
        Logger.e("手机制造商", mVendor);
        startActivityForResult(new Intent(this, VideoListActivity.class),VideoConfig.PICK_VIDEO_REQUEST);
    }

}
