package com.hs.doubaobao.model.detail.video;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.doubaobao.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 作者：zhanghaitao on 2017/9/20 09:15
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class VideoAdapter extends RecyclerView.Adapter {

    private final Context context;
    //  private List<String> mVideoNameList;


    private ArrayList<VideoMessage> list;

    public VideoAdapter(Context context, ArrayList<VideoMessage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     /*   int screenWidth = getWidthPixels();
        int scaleSize = screenWidth / 3;

        mVideoContainer = new LinearLayout(context);
        mVideoContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                scaleSize,scaleSize
        );
        mVideoContainer.setLayoutParams(layoutParams);
        mVideoContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                scaleSize,
                scaleSize * 173 / 229
        );
        imageView.setLayoutParams(layoutParams1);
        imageView.setImageResource(R.drawable.ic_video_pictures);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        textView =   new TextView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                scaleSize,
                scaleSize * 56 / 229
        );
        textView.setLayoutParams(layoutParams2);
        textView.setSingleLine(true);
        textView.setMaxWidth(scaleSize*7/10);
        textView.setBackgroundResource(R.drawable.ic_video_pictures_txt);
        textView.setPadding(20,0,40,12);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        mVideoContainer.addView(imageView);
        mVideoContainer.addView(textView);*/


        View view = LayoutInflater.from(context).inflate(R.layout.item_video_fragment, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mVideoContainer;
        private LinearLayout mTitle;
        private TextView mTitleText;
        private RelativeLayout mVideoPicContainer;
        private ImageView mVideoImage;
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //  itemView.findViewById(R.id.video_pic);
            mVideoContainer = (LinearLayout) itemView.findViewById(R.id.detail_video_container);
            mVideoPicContainer = (RelativeLayout) itemView.findViewById(R.id.video_pic_container);
            mVideoImage = (ImageView) itemView.findViewById(R.id.video_pic);
            mTitle = (LinearLayout) itemView.findViewById(R.id.detail_video_title);
            mTitleText = (TextView) itemView.findViewById(R.id.detail_video_title_text);
            textView = (TextView) itemView.findViewById(R.id.video_text);

        }

        public void setData(final int position) {

            if (position > 0) {
                int precedeType = list.get(position - 1).getCategory();
                int currentType = list.get(position).getCategory();
                if (precedeType == currentType) {
                    mTitle.setVisibility(View.GONE);
                }
            }
            String idex  = "8面审9家访10签约11其他";
            String s = String.valueOf(list.get(position).getCategory()).trim();
            int local =  idex.indexOf(s)+s.length();
            mTitleText.setText(idex.substring(local,local+2));

            //添加文字
            String name = list.get(position).getName();
            if(TextUtils.isEmpty(name)){
                mVideoPicContainer.setVisibility(View.GONE);
            }else {
                textView.setText(name);
            }

            mVideoContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(list.get(position).getUrl());
                    }
                }
            });
        }
    }

    public static onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(String path);
    }

    /**
     * 获取屏幕像素
     *
     * @return
     */
    public int getWidthPixels() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Configuration cf = context.getResources().getConfiguration();
        int ori = cf.orientation;
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
            return displayMetrics.heightPixels;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {// 竖屏
            return displayMetrics.widthPixels;
        }
        return 0;
    }

    public int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 服务器返回url，通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            //Bitmap bitmap = retriever.getFrameAtTime(timeMs * 1000, MediaMetadataRetriever.OPTION_CLOSEST);
            bitmap = retriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

}