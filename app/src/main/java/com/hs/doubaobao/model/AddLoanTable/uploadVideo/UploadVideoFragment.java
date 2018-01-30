package com.hs.doubaobao.model.AddLoanTable.uploadVideo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hs.doubaobao.R;
import com.hs.doubaobao.model.AddLoanTable.ApplyInfoBean;
import com.hs.doubaobao.utils.ScreenSizeUtils;

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
    private List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean> videoPaths = new ArrayList<>();
    private List<Integer> positionArr  = new ArrayList<>();
    private List<Boolean> deleteList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_video, null, false);

        unbinder = ButterKnife.bind(this, view);
        mAdapter = new MyAdapter(getContext(), videoPaths,deleteList);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(llm);
        mRecycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(listener);
        return view;
    }

/////////////////////////////////////////////////////////////////////
/////删除逻辑
/////////////////////////////////////////////////////////////////////

    MyAdapter.onItemClickListener listener =new MyAdapter.onItemClickListener() {
        @Override
        public void pictrueClick() {

        }

        @Override
        public void deleteVideoClick(int position) {
            showDeteleDialog(position);
        }
    };

    private void showDeteleDialog(final int position) {
        final Dialog dialog = new Dialog(getContext(), R.style.NormalDialogStyle);
        View view = View.inflate(getContext(), R.layout.dialog_normal, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.25f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenWidth() * 0.75f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deteleVideo(position);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void deteleVideo(int position) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean> videoList
                = bean.getResData().getBorrowdataModel().getVideoList();

        ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean
                pictureListBean = videoPaths.get(position);

        for (int i = 0; i < videoList.size(); i++) {
            ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean
                    listBean = videoList.get(i);

            if (listBean.getCategory() == pictureListBean.getCategory() &&
                    listBean.getName().equals(pictureListBean.getName()) &&
                    listBean.getPath().equals(pictureListBean.getPath()) &&
                    listBean.getPathTure().equals(pictureListBean.getPathTure())
                    ) {
                videoList.remove(i);
            }
        }
        videoPaths.remove(position);
        mAdapter.notifyDataSetChanged();
    }
    /////////////////////////////////////////////////////////////////////
    //////适配器START
    /////////////////////////////////////////////////////////////////////

    public RecyclerView getRecycler() {
        return mRecycler;
    }

    public void setVideoPaths(int  category,boolean showDelete) {
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean> videoList
                = bean.getResData().getBorrowdataModel().getVideoList();

        if(videoList == null ||videoList.size()==0)return;

        positionArr.clear();
        videoPaths.clear();
        deleteList.clear();

        for (int i = 0; i < videoList.size(); i++) {

            if(videoList.get(i).getCategory() == category){
                videoPaths.add(videoList.get(i));
                positionArr.add(i);
                deleteList.add(showDelete);
            }
        }
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

    static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;

        private List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean> videoPaths;

        private List<Boolean> deleteList;


        public MyAdapter(Context context, List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.VideoListBean> videoPaths, List<Boolean> deleteList) {
            this.context = context;
            this.videoPaths = videoPaths;
            this.deleteList = deleteList;
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
                    path = videoPaths.get(position).getPathTure();
                }
            }
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.bindItem(path, position);
        }


        @Override
        public int getItemCount() {
            if (videoPaths != null) {
                return videoPaths.size();
            } else {
                return 0;
            }
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImage;
            private ImageView mDetele;
            private TextView mVideoName;


            MyViewHolder(View itemView) {
                super(itemView);
                mImage = (ImageView) itemView.findViewById(R.id.upload_video_image);
                mDetele = (ImageView) itemView.findViewById(R.id.upload_video_delete);
                mVideoName = (TextView) itemView.findViewById(R.id.upload_video_name);

            }

            void bindItem(final String path, final int position) {
                Boolean showDelete = deleteList.get(position);
                if (showDelete) {
                    mDetele.setVisibility(View.VISIBLE);
                } else {
                    mDetele.setVisibility(View.GONE);
                }

                mDetele.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.deleteVideoClick(position);
                        }
                    }
                });

                if (!TextUtils.isEmpty(path)) {
                    mVideoName.setText(videoPaths.get(position).getName());
                    Glide.with(context)
                            .load(path)
                            .into(mImage);

                }
            }


        }

        public onItemClickListener listener;

        public void setOnItemClickListener(onItemClickListener listener) {
            this.listener = listener;
        }

        public interface onItemClickListener {
            void pictrueClick();
            void deleteVideoClick(int position);
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
