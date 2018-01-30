package com.hs.doubaobao.model.AddLoanTable.uploadPicture;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.PictureUrlBean;
import com.hs.doubaobao.http.UploadFileHttp;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.threadpool.ThreadPoolProxyFactory;
import com.hs.doubaobao.utils.MyUtils;
import com.hs.doubaobao.utils.ToastUtil;
import com.hs.doubaobao.utils.log.Logger;
import com.werb.pickphotoview.util.PickConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hs.doubaobao.MyApplication.getContext;

/**
 * 作者：zhanghaitao on 2018/1/8 16:48
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class UploadPictureActivity extends AppBarActivity {


    @BindView(R.id.upload_picture_tablayout)
    TabLayout tablayout;
    @BindView(R.id.upload_picture_viewpager)
    ViewPager mViewpager;
    private ArrayList<UploadPictureFragment> fragments;
    private TabFragmentPagerAdapter adapter;
    private String[] mTabItemNameArray = {"四证", "征信报告", "银行流水", "车辆社保公积金", "家访", "经营场所", "其他", "签约"};
    private ProgressDialog dialog;
    private List<String> picturePaths;
    private int[] categoryArr = {1, 2, 3, 4, 5, 6, 7, 12};


    private String status = "编辑";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);
        ButterKnife.bind(this);
        setTitle("照片上传");
        //isShowRightView(false);
        setRightStatus(Color.parseColor("#E2570F"), status);

        addFragment();//初始化Fragmrnt，并添加到ViewPager中。
        initViewPager();
        tablayout.setupWithViewPager(mViewpager);
        mViewpager.setOffscreenPageLimit(fragments.size());
        checkPermission();
        //loading.setMessage("正在上传中，请耐心等待！");
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在上传中，请耐心等待！");
        initData();
    }

    @Override
    public void onRightForward(View forwardView) {
        super.onRightForward(forwardView);

        if (status.equals("编辑")) {
            status = "完成";
            editPiture(true);
        } else {
            status = "编辑";
            editPiture(false);
        }
        setRightStatus(Color.parseColor("#E2570F"), status);

    }


    private void editPiture(boolean showDelete) {
        for (int i = 0; i < categoryArr.length; i++) {
            fragments.get(i).setSelectPaths(categoryArr[i], showDelete);
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < categoryArr.length; i++) {
            fragments.get(i).setSelectPaths(categoryArr[i], status.equals("完成"));
        }
    }


    /**
     * 权限检测
     */
    private void checkPermission() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

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
            ActivityCompat.requestPermissions(this, permissions, 100);
        } else
            //申请到了权限
            if (checkSlfePermission == PackageManager.PERMISSION_GRANTED) {

            }
    }

    /**
     * 返回数据，上传图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (data == null) {
            return;
        }
        if (requestCode == PickConfig.PICK_PHOTO_DATA) {

            int currentItem = mViewpager.getCurrentItem();


            picturePaths = (List<String>) data.getSerializableExtra(PickConfig.INTENT_IMG_LIST_SELECT);
            if (picturePaths != null && picturePaths.size() > 0) {
                if (dialog != null) dialog.show();
                int size = picturePaths.size();
                for (int i = 0; i < size; i++) {
                    uploadFile(picturePaths.get(i),
                            i != (size - 1),
                            categoryArr[currentItem],
                            currentItem);
                }

            }
        }
    }

    /**
     * 上传文件
     */
    private void uploadFile(String filePath, final boolean showDialog, int category, final int currentItem) {

        File file = MyUtils.compressImage(filePath);

        final Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("category", category);
        paramsMap.put("uploads", file);

        //利用线程池管理类开启线程
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().submit(new Runnable() {
            @Override
            public void run() {
                String response =  UploadFileHttp
                        .getInstance()
                        .upLoadFile(BaseParams.UPLOAD_PICTURE, paramsMap);
                if (TextUtils.isEmpty(response)) {
                    if (!showDialog) {
                        sendMessage(1, "fail");
                    }
                } else {
                    try {
                        Gson gson = new Gson();
                        PictureUrlBean pictureUrlBean = gson.fromJson(response, PictureUrlBean.class);
                        if (pictureUrlBean != null) {
                            ApplyLendUtil.addPicture(pictureUrlBean);
                        }
                        if (!showDialog) {
                            sendMessage(2, "success");
                        }
                    } catch (Exception e) {
                        ToastUtil.showToast("哎呀！网络不给力o-o！");
                    }
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    //上传失败
                    ToastUtil.showToast("上传失败");
                    break;
                case 2:
                    //上传成功
                    int currentItem = mViewpager.getCurrentItem();
                    if (dialog != null) dialog.dismiss();
                    fragments.get(currentItem).setSelectPaths(categoryArr[currentItem], status.equals("完成"));
                    Logger.e("我去通知更新了", "去吧！");
                    ToastUtil.showToast("上传成功");
                    break;

            }
        }
    };

    public void sendMessage(int what, String msg) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = msg;
        handler.sendMessage(message);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // ToastUtil.showToast("我同意");
                } else {
                    ToastUtil.showToast("您还没有授权应用相应的权限，部分功能暂不可用。");
                }
                return;
            }
        }
    }

    /**
     * 在这里添加Fragment
     */
    private void addFragment() {
        if (tablayout == null || mViewpager == null) {
            return;
        }
        fragments = new ArrayList<>();

        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
        fragments.add(new UploadPictureFragment());
    }


    /**
     * 在这里初始化ViewPager
     */
    private void initViewPager() {
        if (fragments == null) {
            return;
        }
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragments, mTabItemNameArray);
        mViewpager.setAdapter(adapter);


    }


    /**
     * Tablayout的FragmentPager适配器
     * 用于将Fragment加载到ViewPager中
     * 作者：zhanghaitao on 2017/8/15 09:48
     * 邮箱：820159571@qq.com
     */

    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        /**
         * fragment集合
         */
        private List<UploadPictureFragment> mFragmentList = null;
        /**
         * 标题集合
         */
        private String[] titles;


        public TabFragmentPagerAdapter(FragmentManager mFragmentManager,
                                       ArrayList<UploadPictureFragment> fragmentList) {
            super(mFragmentManager);
            mFragmentList = fragmentList;
        }

        /**
         * titles是给TabLayout设置title用的
         *
         * @param mFragmentManager
         * @param fragmentList
         * @param titles
         */
        public TabFragmentPagerAdapter(FragmentManager mFragmentManager,
                                       List<UploadPictureFragment> fragmentList, String[] titles) {
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


}
