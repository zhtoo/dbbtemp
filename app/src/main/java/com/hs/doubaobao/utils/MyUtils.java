package com.hs.doubaobao.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.hs.doubaobao.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhanghaitao on 2017/5/25.
 */

public class MyUtils {


    public static void removeAsterisk(TextView textView) {

        String text = textView.getText().toString().trim();
        if (text.contains("*")) {
            textView.setText(text.substring(1, text.length()));
        }
    }


    /**
     * 图片压缩处理
     *
     * @param path 本地图片路径
     * @return
     */
    public static File compressImage(String path) {

        //获取到bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //设置目标尺寸（以像素的宽度为标准）
        int Targetsize = 1500;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //选择最大值作为比较值（保证图片的压缩大小）
        int handleValue = width > height ? width : height;
        //循环计算压缩比
        int i = 1;
        while (handleValue / i > Targetsize) {
            i++;
        }

        //获取采样率压缩的对象
        //该方法会改变像素
        BitmapFactory.Options options = new BitmapFactory.Options();
       // options.inPreferredConfig =   Bitmap.Config.RGB_565;
        //设置压缩比例 原图的（1/i）
        options.inSampleSize = i;
        bitmap = BitmapFactory.decodeFile(path, options);


        //获取图片名称
        String[] split = path.split("/");
        File file = new File(MyApplication.getContext().getCacheDir(), split[split.length - 1]);
        //创建输出流
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("wechat", "压缩后图片的大小" + (bitmap.getByteCount() / 1024 / 1024) + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
        Log.d("文件大小", file.length() / 1024 + "kb");

        return file;
    }



    public static Bitmap compressImage(Bitmap bitmap) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bm = Bitmap.createScaledBitmap(bitmap,120,120,true);
        return bm;
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }


    public static Bitmap thumbnail(String  path, int maxWidth, int maxHeight,boolean autoRotate) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        options.inJustDecodeBounds = false;
        int sampleSize = calculateInSampleSize(options,maxWidth,maxHeight);
        options.inSampleSize =sampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable =true;
        options.inInputShareable = true;
        if(bitmap !=null&&!bitmap.isRecycled()){
            bitmap.recycle();
        }
        bitmap = BitmapFactory.decodeFile(path,options);
        return bitmap;
    }


    public static String save(Bitmap  bitmap, Bitmap.CompressFormat format, int quality,File desFile) {
        try{
            FileOutputStream out = new FileOutputStream(desFile);
            if(bitmap.compress(format,quality,out)){
                out.flush();
                out.close();
            }
            if(bitmap!=null&&!bitmap.isRecycled()){
                bitmap.recycle();
            }
            return  desFile.getAbsolutePath();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String save(Bitmap  bitmap, Bitmap.CompressFormat format, int quality,Context context) {
        if(!Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        File dir = new File(Environment.getExternalStorageDirectory()+
        "/"+context.getPackageName());
        if(!dir.exists()){
            dir.mkdir();
        }
        File desFile = new File(dir, UUID.randomUUID().toString());
        return save(bitmap,format,quality,desFile);
    }


    public void Test() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("resData", "recommendedBorrowList");
        map.put("addTime", "1495441666000");
        map.put("amount", "100000");
        map.put("apr", "13");
        map.put("name", "定向标某服装企业房押借款60万第二期");
    }


    /**
     * 将Map集合转换为json字符串
     */
    private String tojson(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        String jsonString = "{";
        for (String key : map.keySet()) {
            jsonString += "\"" + key + "\":\"" + map.get(key) + "\",";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 1);
        jsonString += "}";
        return jsonString;
    }


    /**
     * Map集合转换为目标Map
     */
    private Map getAimMap(Map<String, String> Nmap) {
        Map<String, String> aimMap = new LinkedHashMap<>();
        //获取到字典map
        Map<String, String> DMap = new LinkedHashMap<>();
        for (String key : DMap.keySet()) {
            String Dkey = DMap.get(key);
            for (String nkey : Nmap.keySet()) {
                if (Dkey.equals(nkey)) {
                    aimMap.put(key, Nmap.get(nkey));
                }
            }
        }
        return aimMap;
    }

}
