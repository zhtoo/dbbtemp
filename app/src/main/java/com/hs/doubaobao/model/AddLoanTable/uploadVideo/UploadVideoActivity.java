package com.hs.doubaobao.model.AddLoanTable.uploadVideo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
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
import com.hs.doubaobao.MyApplication;
import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.bean.VideoUrlBean;
import com.hs.doubaobao.http.HttpSocket;
import com.hs.doubaobao.model.AddLoanTable.ApplyLendUtil;
import com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick.VideoConfig;
import com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick.VideoListActivity;
import com.hs.doubaobao.utils.ToastUtil;
import com.hs.doubaobao.utils.log.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    // {"面审视频", "家访视频", "其他视频"};
    //8:面审视频9：家访视频10：其他
    private String[] mTabItemNameArray = {"面审视频", "家访视频", "其他视频"};
    private int[] categoryArr = {8, 9, 10};
    private List<UploadVideoFragment> fragments;
    private UploadVideoAdapter adapter;
    private ProgressDialog dialog;

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
        mViewpager.setOffscreenPageLimit(fragments.size());
        checkPermission();
        // loading.setMessage("正在上传中，请耐心等待！");

        dialog = new ProgressDialog(this);
        dialog.setMessage("正在上传中，请耐心等待！");
            initData();
    }

    private void initData() {
        for (int i = 0; i < categoryArr.length; i++) {
            fragments.get(i).setVideoPaths(categoryArr[i]);
        }

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
            if (list != null && list.size() > 0) {
                if (dialog != null) dialog.show();
                for (int i = 0; i < list.size(); i++) {
                    uploadFile(list.get(i).toString(), i != list.size() - 1, categoryArr[currentItem]);
                }
//                fragments.get(currentItem).setVideoPaths(list);
//                fragments.get(currentItem).getRecycler().getAdapter().notifyDataSetChanged();
            }
        }
    }


    /**
     * 上传文件
     */
    private void uploadFile(final String filePath, final boolean showDialog, final int category) {

        final long startTime = System.currentTimeMillis();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File videoFile = new File(filePath);
                    File videoPictures = getVideoThumbnail(filePath,
                            120, 120,
                            MediaStore.Video.Thumbnails.MINI_KIND);

                    Map<String, Object> paramsMap = new LinkedHashMap<>();
                    paramsMap.put("category", category);
                    //category

                    HttpSocket socket = new HttpSocket(startTime);

                    String response = socket.uploadFile(paramsMap,
                            "videoUploads", videoFile, null,
                            "videoPictures", videoPictures, null,
                            BaseParams.UPLOAD_VIDEO);

                    if (!TextUtils.isEmpty(response)) {
                        sendMessage(SUCCESS_CODE, response, showDialog ? 0 : 1);
                    } else {
                        sendMessage(FAIL_CODE, response, showDialog ? 0 : 1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        File videoFile = new File(filePath);
//        File videoPictures = getVideoThumbnail(filePath,
//                120, 120,
//                MediaStore.Video.Thumbnails.MINI_KIND);
//
//        Map<String, Object> paramsMap = new LinkedHashMap<>();
//        paramsMap.put("category", "8");
//        paramsMap.put("videoUploads", videoFile);
//        paramsMap.put("videoPictures", videoPictures);
//        UploadFileHttp.getInstance(this)
//                .upLoadFile(BaseParams.UPLOAD_VIDEO,
//                        paramsMap, new requestCallBack() {
//
//                            @Override
//                            public void onError(Call call, Exception e) {
//                                long endTime = System.currentTimeMillis();
//                                ToastUtil.showToast("哎呀！网络不给力o-o！");
//                                Logger.e("用时", (endTime - startTime) + "ms");
//                                Logger.e("用时", e.toString());
//
//                                if(!showDialog){
//                                    if(loading !=null )loading.dismiss();
//                                }
//                            }
//
//                            @Override
//                            public void onResponse(String response) {
//                                Logger.e("123", response);
//                                long endTime = System.currentTimeMillis();
//                                Logger.e("用时", (endTime - startTime) + "ms");
//
//                                if(!showDialog){
//                                    if(loading !=null )loading.dismiss();
//                                }
//                            }
//                        });

    }

    private void sendMessage(int statusCode, String response, int stopDialog) {
        Message message = new Message();
        message.what = statusCode;
        message.arg1 = stopDialog;
        message.obj = response;
        handler.sendMessage(message);

    }

    public static final int FAIL_CODE = 10;
    public static final int SUCCESS_CODE = 11;


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SUCCESS_CODE:
                    if (msg.arg1 == 1) {
                        dialog.dismiss();
                    }
                    String response = (String) msg.obj;
                    try {
                        Gson gson = new Gson();
                        VideoUrlBean videoUrlBean = gson.fromJson(response, VideoUrlBean.class);
                        if (videoUrlBean != null) {
                            ApplyLendUtil.addVideo(videoUrlBean);
                        }
                        if (msg.arg1 == 1) {
                            ToastUtil.showToast("上传成功");
                        }
                    } catch (Exception e) {
                        if (msg.arg1 == 1) {
                            ToastUtil.showToast("上传失败");
                        }
                    }

                    if (msg.arg1 == 1) {
                        int currentItem = mViewpager.getCurrentItem();
                        fragments.get(currentItem).setVideoPaths(categoryArr[currentItem]);
                        fragments.get(currentItem).getRecycler().getAdapter().notifyDataSetChanged();

                    }

                    break;
                case FAIL_CODE:
                    if (msg.arg1 == 1) {
                        dialog.dismiss();
                        ToastUtil.showToast("上传失败");
                    }
                    break;
            }
        }
    };


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
        startActivityForResult(new Intent(this, VideoListActivity.class), VideoConfig.PICK_VIDEO_REQUEST);
    }


    /**
     * 获取视频缩略图
     */
    private File getVideoThumbnail(String videoPath, int width, int height, int kind) {
        long mStartTime = System.currentTimeMillis();
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        long mEndTime = System.currentTimeMillis();
        Logger.e("获取视频耗时", "" + (mEndTime - mStartTime));

        String[] split = videoPath.split("/");

        String videoPicturesName = split[split.length - 1];

        boolean contains = videoPicturesName.contains(".");

        if (contains) {
            int i = videoPicturesName.indexOf(".");
            videoPicturesName = videoPicturesName.substring(0, i) + ".jpg";
        }

        File file = new File(MyApplication.getContext().getCacheDir(), videoPicturesName);
        //创建输出流
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}
