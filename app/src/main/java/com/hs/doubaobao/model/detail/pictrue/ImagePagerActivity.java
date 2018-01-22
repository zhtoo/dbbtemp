package com.hs.doubaobao.model.detail.pictrue;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.hs.doubaobao.R;
import com.hs.doubaobao.base.AppBarActivity;
import com.hs.doubaobao.utils.log.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zht on 2017/9/13.
 */
public class ImagePagerActivity extends AppBarActivity {

    private final String TAG = getClass().getSimpleName();

    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    public static final String INTENT_IMAGESIZE = "imagesize";

    public ImageSize imageSize;
    private int startPos;
    private ArrayList<String> imgUrls;
    private TextView guidePercent;
    private ImageButton guideRotate;
    private ViewPager viewPager;
    private ImageAdapter mAdapter;
    private float rotation = 0;


    public static void startImagePagerActivity(Context context, List<String> imgUrls, int position, ImageSize imageSize) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        intent.putExtra(INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepager);

        setTitle("图片预览");
        isShowRightView(false);
        hideTitleBar();

        viewPager = (ViewPager) findViewById(R.id.pager);

        guidePercent = (TextView) findViewById(R.id.guide_percent);

        guideRotate = (ImageButton) findViewById(R.id.guide_rotate);
        //获取Intent中的数据
        getIntentData();
        //初始化Adapter
        mAdapter = new ImageAdapter(this);
        //设置图片路径
        mAdapter.setDatas(imgUrls);
        //设置图片的的大小
        mAdapter.setImageSize(imageSize);
        //设置adapter
        viewPager.setAdapter(mAdapter);
        //给viewPager设置监听
        viewPager.addOnPageChangeListener(pageChangeListener);
        //添加下标指示器
        addGuideView(guidePercent, startPos, imgUrls);
        //设置开始界面
        viewPager.setCurrentItem(startPos);

        guideRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotatePhotoView();
            }
        });


    }

    /**
     * 获取Intent传递过来的数据
     */
    private void getIntentData() {
        startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);
        imageSize = (ImageSize) getIntent().getSerializableExtra(INTENT_IMAGESIZE);
    }


    /**
     * 旋转PhotoView
     */
    private void rotatePhotoView() {
        int currentPosition = viewPager.getCurrentItem();
        ViewGroup currentView = null;
        PhotoView imageView = null;
        Log.e(TAG, "currentPosition:" + currentPosition);
        try {
            currentView = (ViewGroup) mAdapter.getPrimaryItem();
            imageView = (PhotoView) currentView.findViewById(R.id.image);
            rotation = (rotation + 90) % 360;
            Logger.e(TAG, "rotation:" + rotation);
            imageView.setRotationTo(rotation);
        } catch (Exception e) {
            if (currentView == null) {
                Logger.e(TAG, "currentView is null object!!!---" + currentPosition);
            }
            if (imageView == null) {
                Logger.e(TAG, "imageView is null object!!!---" + currentPosition);
            }
        }
    }


    /**
     * 添加数字
     *
     * @param guidePercent
     * @param startPos
     * @param imgUrls
     */
    private void addGuideView(TextView guidePercent, int startPos, ArrayList<String> imgUrls) {
        if (imgUrls != null && imgUrls.size() > 0) {
            guidePercent.setText((startPos + 1) + "/" + imgUrls.size());
        }
    }
    /**ViewPager的页面变换的监听器*/
    private  ViewPager.OnPageChangeListener pageChangeListener =new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            rotation = 0;
            guidePercent.setText((position + 1) + "/" + imgUrls.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 图片的额适配器
     */
    private class ImageAdapter extends PagerAdapter {

        private List<String> datas = new ArrayList<String>();
        private LayoutInflater inflater;
        private Context context;
        private ImageSize imageSize;
        private ImageView smallImageView = null;

        public void setDatas(List<String> datas) {
            if (datas != null)
                this.datas = datas;
        }

        public void setImageSize(ImageSize imageSize) {
            this.imageSize = imageSize;
        }

        public ImageAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (datas == null) return 0;
            return datas.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            if (view != null) {
                final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);
                //点击退出当前界面
                imageView.setOnPhotoTapListener(new OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(ImageView view, float x, float y) {
                        ImagePagerActivity.this.finish();
                    }
                });

//                if (imageSize != null) {
//                    //预览imageView
//                    smallImageView = new ImageView(context);
//                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageSize.getWidth(), imageSize.getHeight());
//                    layoutParams.gravity = Gravity.CENTER;
//                    smallImageView.setLayoutParams(layoutParams);
//                    smallImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                    ((FrameLayout) view).addView(smallImageView);
//                }

                //loading
                final LinearLayout loading = new LinearLayout(context);
                loading.setOrientation(LinearLayout.VERTICAL);
                loading.setGravity(Gravity.CENTER);

                //进度条
                final ProgressBar loadingProgress = new ProgressBar(context);
                //文字提醒
                final TextView loadingText = new TextView(context);
                //设置文字的样式
                loadingText.setText("加载中...");
                loadingText.setTextColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams loadingTextLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingTextLP.gravity = Gravity.CENTER;
                loadingText.setLayoutParams(loadingTextLP);
                //添加到一个布局
                loading.addView(loadingProgress);
                loading.addView(loadingText);

                ((FrameLayout) view).addView(loading);

                final String imgurl = datas.get(position);
                Glide.with(context)
                        .load(imgurl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存多个尺寸
                        .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
                        .error(R.drawable.ic_error_pictrue)
                        .into(new GlideDrawableImageViewTarget(imageView) {
                            //加载开始
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                               /* if(smallImageView!=null){
                                    smallImageView.setVisibility(View.VISIBLE);
                                    Glide.with(context).load(imgurl).into(smallImageView);
                                }*/
                                loading.setVisibility(View.VISIBLE);
                            }

                            //加载失败
                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                super.onLoadFailed(e, errorDrawable);
                                /*if(smallImageView!=null){
                                    smallImageView.setVisibility(View.GONE);
                                }*/
                                loading.setVisibility(View.GONE);
                            }

                            //加载成功
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                loading.setVisibility(View.GONE);
                                /*if(smallImageView!=null){
                                    smallImageView.setVisibility(View.GONE);
                                }*/
                            }
                        });
                //添加到container中
                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


        //用来保存当前的View
        private View mCurrentView;

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mCurrentView = (View) object;
        }

        /**
         * 返回当前的V
         */
        public View getPrimaryItem() {
            return mCurrentView;
        }


    }


    /**
     * 图片大小类（用来获取传过来的图片的宽高）
     */
    public static class ImageSize implements Serializable {

        private int width;
        private int height;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
