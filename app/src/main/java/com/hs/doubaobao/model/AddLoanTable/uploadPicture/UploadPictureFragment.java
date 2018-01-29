package com.hs.doubaobao.model.AddLoanTable.uploadPicture;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hs.doubaobao.R;
import com.hs.doubaobao.model.AddLoanTable.ApplyInfoBean;
import com.hs.doubaobao.utils.ScreenSizeUtils;
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
    private List<Integer> positionArr = new ArrayList<>();
    private List<Boolean> deleteList = new ArrayList<>();

    public List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> getSelectPaths() {
        return selectPaths;
    }

    public void setSelectPaths(int category, boolean showDelete) {
        //去重逻辑
//        if (this.selectPaths.size() > 0) {
//            for (int i = 0; i < newSelectPaths.size(); i++) {
//                String newPath = newSelectPaths.get(i).toString();
//                for (int j = 0; j < this.selectPaths.size(); j++) {
//                    String oldPath = selectPaths.get(j).toString();
//                    if (newPath.equals(oldPath)) {
//                        selectPaths.remove(j);
//                    }
//                }
//            }
//        }
        //this.selectPaths.addAll(newSelectPaths);

        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> pictureList
                = bean.getResData().getBorrowdataModel().getPictureList();

        if (pictureList == null || pictureList.size() == 0) return;

        positionArr.clear();
        selectPaths.clear();
        deleteList.clear();

        for (int i = 0; i < pictureList.size(); i++) {

            if (pictureList.get(i).getCategory() == category) {
                selectPaths.add(pictureList.get(i));
                positionArr.add(i);
                deleteList.add(showDelete);
            }
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }

    }

    private List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> selectPaths = new ArrayList<>();
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
            if (denide == PackageManager.PERMISSION_DENIED) {
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
        mAdapter = new SampleAdapter(getContext(), null, deleteList);
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

        @Override
        public void deletePictrueClick(int position) {

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
                detelePiture(position);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void detelePiture(int position) {
        int integer = positionArr.get(position);
        ApplyInfoBean bean = ApplyInfoBean.getInstance();
        List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> pictureList
                = bean.getResData().getBorrowdataModel().getPictureList();

        pictureList.remove(integer);
        selectPaths.remove(position);
        mAdapter.notifyDataSetChanged();
    }


    static class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> imagePaths;
        private Context context;

        private List<Boolean> deleteList;

        public SampleAdapter(Context context, List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> imagePaths) {
            this.context = context;
            this.imagePaths = imagePaths;
        }

        public SampleAdapter(Context context, List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> imagePaths, List<Boolean> deleteList) {
            this.imagePaths = imagePaths;
            this.context = context;
            this.deleteList = deleteList;
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
                    path = imagePaths.get(position).getPathTure();
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

        public void updateData(List<ApplyInfoBean.ResDataBean.BorrowdataModelBean.PictureListBean> paths) {
            imagePaths = paths;
            notifyDataSetChanged();
        }

        // ViewHolder
        private class GridImageViewHolder extends RecyclerView.ViewHolder {

            private ImageView gridDelete;
            private ImageView gridImage;
            private int scaleSize;

            GridImageViewHolder(View itemView) {
                super(itemView);
                gridImage = (ImageView) itemView.findViewById(R.id.iv_choose);
                gridDelete = (ImageView) itemView.findViewById(R.id.iv_delete);

                int screenWidth = PickUtils.getInstance(context).getWidthPixels();
                int space = PickUtils.getInstance(context).dp2px(PickConfig.ITEM_SPACE);
                scaleSize = (screenWidth - (4 + 1) * space) / 4;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridImage.getLayoutParams();
                params.width = scaleSize;
                params.height = scaleSize;
            }

            void bindItem(final String path, final int position) {

                if (imagePaths != null && position == imagePaths.size()) {
                    gridDelete.setVisibility(View.GONE);
                    gridImage.setImageResource(R.drawable.ic_photo_added);
                    gridImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    gridImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null&&imagePaths != null && position == imagePaths.size()) {
                                listener.pictrueClick();
                            }
                        }
                    });
                } else {
                    Boolean showDelete = deleteList.get(position);
                    if (showDelete) {
                        gridDelete.setVisibility(View.VISIBLE);
                    } else {
                        gridDelete.setVisibility(View.GONE);
                    }

                    gridDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.deletePictrueClick(position);
                            }
                        }
                    });

                    if (!TextUtils.isEmpty(path)) {
                        gridImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(context)
                                .load(path)
                                .into(gridImage);
                    }
                }
            }
        }

        public onItemClickListener listener;

        public void setOnItemClickListener(onItemClickListener listener) {
            this.listener = listener;
        }

        public interface onItemClickListener {
            void pictrueClick();

            void deletePictrueClick(int position);
        }
    }
}
