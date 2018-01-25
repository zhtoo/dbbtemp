package com.hs.doubaobao.model.AddLoanTable.uploadVideo;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hs.doubaobao.R;
import com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick.LoadedImage;
import com.hs.doubaobao.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：zhanghaitao on 2018/1/17 10:12
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class UploadVideoFragment extends Fragment {

    @BindView(R.id.upload_video_recycler)
    RecyclerView mRecycler;
    Unbinder unbinder;

    private MyAdapter mAdapter;
    private List<CharSequence> videoPaths = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_video, null, false);

        unbinder = ButterKnife.bind(this, view);
        mAdapter = new MyAdapter(getContext(), videoPaths);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(mAdapter);
        return view;
    }

    /////////////////////////////////////////////////////////////////////
//////加载图片START
/////////////////////////////////////////////////////////////////////
    //添加图片
    private void addImage(LoadedImage... value) {

        for (LoadedImage image : value) {
            mAdapter.addPhoto(image);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void loadImages() {
        mAdapter.getVideoThumbnail().clear();
        new LoadImages().execute();
    }

    class LoadImages extends AsyncTask<Object, LoadedImage, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Bitmap bitmap = null;
            for (int i = 0; i < videoPaths.size(); i++) {
                //   bitmap = getLocalVideoBitmap(videoPaths.get(i).toString());
                bitmap = getVideoThumbnail(videoPaths.get(i).toString(), 480, 270, MediaStore.Video.Thumbnails.MINI_KIND);
                if (bitmap != null) {
                    publishProgress(new LoadedImage(bitmap));
                }
            }
            return null;
        }


        /**
         * 获取视频缩略图
         */
        private Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
            long mStartTime = System.currentTimeMillis();
            Bitmap bitmap = null;
            bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            long mEndTime = System.currentTimeMillis();
            Logger.e("获取视频耗时", "" + (mEndTime - mStartTime));
            return bitmap;
        }


        @Override
        public void onProgressUpdate(LoadedImage... value) {
            addImage(value);
        }

    }


    /////////////////////////////////////////////////////////////////////
    //////适配器START
    /////////////////////////////////////////////////////////////////////

    public RecyclerView getRecycler() {
        return mRecycler;
    }

    public void setVideoPaths(ArrayList<CharSequence> videoPaths1) {
//        if (this.videoPaths.size() > 0) {
//            for (int i = 0; i < videoPaths1.size(); i++) {
//                String newPath = videoPaths1.get(i).toString();
//                for (int j = 0; j < this.videoPaths.size(); j++) {
//                    String oldPath = videoPaths.get(j).toString();
//                    if (newPath.equals(oldPath)) {
//                        videoPaths.remove(j);
//                    }
//                }
//            }
//        }
        this.videoPaths.addAll(videoPaths1);
       // Logger.e("videoPaths长度",""+videoPaths.size());
        loadImages();
    }

    static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<CharSequence> videoPaths;

        public List<LoadedImage> getVideoThumbnail() {
            return videoThumbnail;
        }

        private List<LoadedImage> videoThumbnail;
        private Context context;

        public MyAdapter(Context context, List<CharSequence> videoPaths) {
            this.context = context;
            this.videoPaths = videoPaths;
            videoThumbnail = new ArrayList<>();
        }

        /*
        RecyclerView.Adapter里面的
        onCreateViewHolder()方法和
        onBindViewHolder()方法
        对时间都非常敏感。
        类似I/O读写，Bitmap解码一类的耗时操作，
        最好不要在它们里面进行。
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video_choose, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String path = "";
            if (videoPaths != null) {
                if (videoPaths.size() > position) {
                    path = videoPaths.get(position).toString();
                }
            }
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.bindItem(path, position);
        }


        @Override
        public int getItemCount() {
            if (videoThumbnail != null) {
                return videoThumbnail.size();
            } else {
                return 0;
            }
        }

        public void addPhoto(LoadedImage image) {
            Logger.e(
                    "来吧来吧","我的好孩子"
            );
            videoThumbnail.add(image);
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImage;
            private TextView mVideoName;


            MyViewHolder(View itemView) {
                super(itemView);
                mImage = (ImageView) itemView.findViewById(R.id.upload_video_image);
                mVideoName = (TextView) itemView.findViewById(R.id.upload_video_name);

            }

            void bindItem(final String path, int position) {
                if (!TextUtils.isEmpty(path)) {
                    String[] split = path.split("/");
                    String videoName = split[split.length - 1];
                    mVideoName.setText(videoName);
                    mImage.setImageBitmap(videoThumbnail.get(position).getBitmap());
                }
            }


        }

        public onItemClickListener listener;

        public void setOnItemClickListener(onItemClickListener listener) {
            this.listener = listener;
        }

        public interface onItemClickListener {
            void pictrueClick();
        }

    }


    /**
     * 获取本地视频的第一帧
     *
     * @param localPath
     * @return
     */
    public static Bitmap getLocalVideoBitmap(String localPath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(localPath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime(1);

            bitmap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
