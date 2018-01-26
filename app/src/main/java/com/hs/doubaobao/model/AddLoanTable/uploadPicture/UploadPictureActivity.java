package com.hs.doubaobao.model.AddLoanTable.uploadPicture;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.http.UploadFileHttp;
import com.hs.doubaobao.http.requestCallBack;
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
import okhttp3.Call;

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

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:

                    break;
                case 2:
                    //上传成功
                    ToastUtil.showToast("上传成功");
                    break;
                case 3:
                    //上传进度
                    int current = msg.arg1;
                    int total = msg.arg2;
                    Logger.e("进度",current+"/"+total);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);
        ButterKnife.bind(this);
        setTitle("照片上传");
        isShowRightView(false);

        addFragment();//初始化Fragmrnt，并添加到ViewPager中。
        initViewPager();
        tablayout.setupWithViewPager(mViewpager);
        checkPermission();
        loading.setMessage("正在上传中，请耐心等待！");
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
            if (picturePaths != null&&picturePaths.size()>0) {
                if(loading !=null )loading.show();
                fragments.get(currentItem).setSelectPaths(picturePaths);
                fragments.get(currentItem).getmRecycler().getAdapter().notifyDataSetChanged();
                for (int i = 0; i < picturePaths.size(); i++) {
                    uploadFile(picturePaths.get(i),i != picturePaths.size() -1);
                }


            }
        }
    }

    /**
     * 上传文件
     */
    private void uploadFile(String filePath, final boolean showDialog) {

        File file = MyUtils.compressImage(filePath);

        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("category", "1");
        paramsMap.put("uploads", file);
        UploadFileHttp.getInstance(this)
                .upLoadFile(BaseParams.UPLOAD_PICTURE,
                        paramsMap, new requestCallBack() {

                            @Override
                            public void onError(Call call, Exception e) {

                                if(!showDialog){
                                    if(loading !=null )loading.dismiss();
                                }

                                ToastUtil.showToast("哎呀！网络不给力o-o！");
                            }
                            @Override
                            public void onResponse(String response) {

                                if(!showDialog){
                                    if(loading !=null )loading.dismiss();
                                }

                                Logger.e("123",response);

                            }
                        });

    }


    public void sendMessage(int what, long current, long total, String msg) {
        Message message = Message.obtain();
        message.what = what;
        message.arg1 = (int) current;
        message.arg2 = (int) total;
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
