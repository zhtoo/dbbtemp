package com.hs.doubaobao.model.AddLoanTable.uploadVideo.VideoPick;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.util.List;

/**
 * 作者：zhanghaitao on 2018/1/18 09:13
 * 邮箱：820159571@qq.com
 *
 * @describe:待完善
 */

public class VideoThumbnail  extends AsyncTask<Object, LoadedImage, Object> {

   private int videoSize;
   private List<Video> listVideos;

    private int ThumbnailWidth = 480;
    private int ThumbnailHeight = 270;

    @Override
    protected Object doInBackground(Object... params) {
        Bitmap bitmap = null;
        for (int i = 0; i < videoSize; i++) {
            bitmap = getVideoThumbnail(listVideos.get(i).getPath(), ThumbnailWidth,ThumbnailHeight , MediaStore.Video.Thumbnails.MINI_KIND);
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
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }


    @Override
    public void onProgressUpdate(LoadedImage... value) {
       //addImage(value);
    }




}
