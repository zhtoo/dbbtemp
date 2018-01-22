package com.hs.doubaobao.model.detail.pictrue.group;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hs.doubaobao.R;
import com.hs.doubaobao.model.detail.pictrue.PictrueAdapter;
import com.hs.doubaobao.utils.log.Logger;

import java.util.List;

import static in.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px;

/**
 * 作者：zhanghaitao on 2017/12/8 14:36
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class GroupAdapter extends RecyclerView.Adapter {

    private final String TAG = getClass().getSimpleName();


    public static final int ITEM_TYPE_HEADER = 0;//头布局
    public static final int ITEM_TYPE_CONTENT = 1;//条目布局

    private LayoutInflater mLayoutInflater;

    private Context context;
    private List<PictureMessage> list;

    public GroupAdapter(Context context, List<PictureMessage> list) {
        this.context = context;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
        //分组
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).setGroup(1);
            } else {
                int precedeType = list.get(i - 1).getCategory();
                int currentType = list.get(i).getCategory();
                //前一个参数的组别
                int group = list.get(i - 1).getGroup();
                if (precedeType != currentType) {
                    list.get(i).setGroup(group + 1);
                } else {
                    list.get(i).setGroup(group);
                }
            }
        }
    }


    /**
     * 判断当前item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        PictureMessage pictureMessage = list.get(position);
        int titleType = pictureMessage.getTitleType();
        if (titleType == 0) {
            //组标题
            return ITEM_TYPE_HEADER;
        } else {
            //组图片
            return ITEM_TYPE_CONTENT;
        }

    }

    /**
     * 根据下标计算position在组中位置（childPosition）
     *
     * @param groupPosition 所在的组
     * @param position      下标
     * @return 子项下标 childPosition
     */
    public int getChildPositionForPosition(int groupPosition, int position) {
//        if (groupPosition < mStructures.size()) {
//            int itemCount = countGroupRangeItem(0, groupPosition + 1);
//            GroupStructure structure = mStructures.get(groupPosition);
//            int p = structure.getChildrenCount() - (itemCount - position)
//                    + (structure.hasFooter() ? 1 : 0);
//            if (p >= 0) {
//                return p;
//            }
//        }
        return -1;
    }

    /**
     * 根据下标计算position所在的组（groupPosition）
     *
     * @param position 下标
     * @return 组下标 groupPosition
     */
    public int getGroupPositionForPosition(int position) {
        return list.get(position).getGroup();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_pictrue_title_fragment, parent, false));
        } else {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_pictrue_fragment, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.setData(position);
        } else {
            ContentViewHolder viewHolder = (ContentViewHolder) holder;
            viewHolder.setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        //处理StaggeredGridLayout，保证组头和组尾占满一行。
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }


    private void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams)
                    holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.detail_picture_title_text);
        }

        public void setData(final int position) {
            mTitle.setText(list.get(position).getName());
        }

    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView gridImage;
        private int scaleSize;


        public ContentViewHolder(View itemView) {
            super(itemView);
            gridImage = (ImageView) itemView.findViewById(R.id.iv_grid);
            int screenWidth = getWidthPixels();
            int space = dp2px(4);
            scaleSize = (screenWidth - (4 + 1) * space) / 4;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridImage.getLayoutParams();
            params.width = scaleSize;
            params.height = scaleSize;

        }

        public void setData(final int position) {

            if(list.get(position).getName().equals("空图片")){
                Glide.with(context)
                        .load(R.drawable.ic_video_null_bg)
                        .into(gridImage);
                gridImage.setClickable(false);
            }else {
                Glide.with(context)
                        .load(list.get(position).getUrl())
                        .error(R.drawable.ic_error_pictrue_remind)
                        .into(gridImage);
                gridImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            Logger.e(TAG, "点击："+position);
                            Logger.e(TAG, "处理："+list.get(position).getListPosition());
                            listener.onItemClick(v,  list.get(position).getListPosition() );
                        }
                    }
                });
            }


        }
    }


    public static PictrueAdapter.onItemClickListener listener;

    public void setOnItemClickListener(PictrueAdapter.onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int postion);
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


}
