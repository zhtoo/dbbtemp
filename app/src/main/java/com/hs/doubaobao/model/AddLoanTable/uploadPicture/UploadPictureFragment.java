package com.hs.doubaobao.model.AddLoanTable.uploadPicture;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hs.doubaobao.R;
import com.hs.doubaobao.utils.ToastUtil;
import com.werb.pickphotoview.PickPhotoView;
import com.werb.pickphotoview.adapter.SpaceItemDecoration;
import com.werb.pickphotoview.util.PickConfig;
import com.werb.pickphotoview.util.PickUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：zhanghaitao on 2018/1/8 16:48
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class UploadPictureFragment extends Fragment {


    private final String TAG = getClass().getSimpleName();

    public RecyclerView getmRecycler() {
        return mRecycler;
    }

    @BindView(R.id.upload_picture_recycler)
    RecyclerView mRecycler;
    Unbinder unbinder;
    private SampleAdapter mAdapter;


    public List<String> getSelectPaths() {
        return selectPaths;
    }

    public void setSelectPaths(List<String> newSelectPaths) {

        if (this.selectPaths.size() > 0) {
            for (int i = 0; i < newSelectPaths.size(); i++) {
                String newPath = newSelectPaths.get(i).toString();
                for (int j = 0; j < this.selectPaths.size(); j++) {
                    String oldPath = selectPaths.get(j).toString();
                    if (newPath.equals(oldPath)) {
                        selectPaths.remove(j);
                    }
                }
            }
        }
        this.selectPaths.addAll(newSelectPaths);
    }

    private List<String> selectPaths = new ArrayList<>();
    private UploadPictureActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_picture, null, false);
        unbinder = ButterKnife.bind(this, view);
        initPickPhoto(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 初始化选择图片
     */
    private void startPickPhoto() {
        activity = (UploadPictureActivity) getActivity();

        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        //定义一个变量 记录当前权限的状态
        int checkSlfePermission = PackageManager.PERMISSION_GRANTED;

        for (int i = 0; i < permissions.length; i++) {
            int denide = ContextCompat.checkSelfPermission(getContext(), permissions[i]);
            if(denide == PackageManager.PERMISSION_DENIED){
                checkSlfePermission = PackageManager.PERMISSION_DENIED;
            }
        }

        //当前么有相应的权限
        if (checkSlfePermission == PackageManager.PERMISSION_DENIED) {
            //申请权限 （弹出一个申请权限的对话框）
            ToastUtil.showToast("您还没有授权应用相应的权限，该功能暂不可用。");
        } else
            //申请到了权限
            if (checkSlfePermission == PackageManager.PERMISSION_GRANTED) {
                new PickPhotoView.Builder(activity)
                        .setPickPhotoSize(100)//设置可选择图片的张数
                        .setShowCamera(true)//是否可以调用照相机
                        .setSpanCount(3)//设置每行显示的张数
                        .setLightStatusBar(true)
                        .setStatusBarColor("#ffffff")
                        .setToolbarColor("#ffffff")
                        .setToolbarIconColor("#000000")
                        .start();
            }

    }

    private void initPickPhoto(View view) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(new SpaceItemDecoration(PickUtils.getInstance(getContext())
                .dp2px(PickConfig.ITEM_SPACE), 4));
        mAdapter = new SampleAdapter(getContext(), null);
        mRecycler.setAdapter(mAdapter);
        if (selectPaths != null) {
            mAdapter.updateData(selectPaths);
        }
        mAdapter.setOnItemClickListener(listener);
    }

    private SampleAdapter.onItemClickListener listener = new SampleAdapter.onItemClickListener() {
        @Override
        public void pictrueClick() {
            startPickPhoto();
        }
    };

    static class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> imagePaths;
        private Context context;

        public SampleAdapter(Context context, List<String> imagePaths) {
            this.context = context;
            this.imagePaths = imagePaths;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GridImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pictrue_choose, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String path = "";
            if (imagePaths != null) {
                if (imagePaths.size() > position) {
                    path = imagePaths.get(position);
                }
            }
            GridImageViewHolder gridImageViewHolder = (GridImageViewHolder) holder;
            gridImageViewHolder.bindItem(path, position);
        }


        @Override
        public int getItemCount() {
            if (imagePaths != null) {
                return imagePaths.size() + 1;
            } else {
                return 1;
            }
        }

        public void updateData(List<String> paths) {
            imagePaths = paths;
            notifyDataSetChanged();
        }

        // ViewHolder
        private class GridImageViewHolder extends RecyclerView.ViewHolder {

            private ImageView gridImage;
            private int scaleSize;

            GridImageViewHolder(View itemView) {
                super(itemView);
                gridImage = (ImageView) itemView.findViewById(R.id.iv_choose);

                int screenWidth = PickUtils.getInstance(context).getWidthPixels();
                int space = PickUtils.getInstance(context).dp2px(PickConfig.ITEM_SPACE);
                scaleSize = (screenWidth - (4 + 1) * space) / 4;
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) gridImage.getLayoutParams();
                params.width = scaleSize;
                params.height = scaleSize;
            }

            void bindItem(final String path, int position) {
                if (!TextUtils.isEmpty(path)) {
                    Glide.with(context)
                            .load(path.startsWith("http") ? path :Uri.parse( "file://" + path))
                            .into(gridImage);
                } else {
                    gridImage.setImageResource(R.drawable.ic_photo_added);
                    gridImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    gridImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.pictrueClick();
                            }
                        }
                    });
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


}
