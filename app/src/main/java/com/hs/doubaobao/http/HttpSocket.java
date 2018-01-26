package com.hs.doubaobao.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hs.doubaobao.base.BaseParams;
import com.hs.doubaobao.utils.MD5Util;
import com.hs.doubaobao.utils.log.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

import static com.hs.doubaobao.http.OKHttpWrap.getVersion;

/**
 * 作者：zhanghaitao on 2018/1/25 10:40
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class HttpSocket {

    //字段的分割符
    private static final String BOUNDARY = "androidUploadFileBySocket";

    //编码格式
    private static final String CHARSET_NAME = "UTF-8";

    private int progress;
    private Context context;

    public HttpSocket(Context context) {
        this.context = context;
    }


    public HttpSocket() {
    }


    /**
     * @param params       传递的普通参数
     * @param videoFile    需要上传的文件
     * @param videoName    需要上传文件表单中的名字
     * @param newVideoName 上传的文件名称，不填写将为uploadFile的名称
     * @param urlStr       上传的服务器的路径
     * @throws IOException
     */
    public void uploadFile(Map<String, Object> params,
                           String videoName, File videoFile, String newVideoName,
                           String pictureName, File pictureFile, String newPictureName,
                           String urlStr) throws IOException {
        if (newVideoName == null || newVideoName.trim().equals("")) {
            newVideoName = videoFile.getName();
        }

        if (newPictureName == null || newPictureName.trim().equals("")) {
            newPictureName = pictureFile.getName();
        }

        final String ts = String.valueOf(System.currentTimeMillis() / 1000);
        // appsecrt拼接ts的字符串后进行MD5（32）
        String signa = MD5Util.encode(BaseParams.APP_SECRET + ts, CHARSET_NAME);
        // 得到的MD5串拼接appkey再次MD5，所得结果转大写
        signa = MD5Util.encode(signa + BaseParams.APP_KEY, CHARSET_NAME).toUpperCase();
        params.put("appkey", BaseParams.APP_KEY);
        params.put("signa", signa);
        params.put("ts", ts);
        params.put("mobileType", BaseParams.MOBILE_TYPE);
        params.put("versionNumber", getVersion());
        //上传非空的userId
        if (!TextUtils.isEmpty(BaseParams.USER_ID)) {
            params.put("userId", BaseParams.USER_ID);
        }
        Logger.e("通用参数", params.toString());

        StringBuilder sb = new StringBuilder();
        /**
         * 普通的表单数据
         */
        if (params != null)
            for (String key : params.keySet()) {
                sb.append("--" + BOUNDARY + "\r\n");
                sb.append("Content-Disposition: form-data; name=\"" + key
                        + "\"" + "\r\n");
                sb.append("\r\n");
                sb.append(params.get(key) + "\r\n");
            }
        else {
            sb.append("\r\n");
        }
        /**
         * 上传图片文件的头
         */
        StringBuilder pictrueSB = new StringBuilder();
        pictrueSB.append("--" + BOUNDARY + "\r\n");
        pictrueSB.append("Content-Disposition: form-data; name=\"" + pictureName
                + "\"; filename=\"" + newPictureName + "\"" + "\r\n");
        pictrueSB.append("Content-Type: multipart/form-data" + "\r\n");// 如果服务器端有文件类型的校验，必须明确指定ContentType
        pictrueSB.append("\r\n");

        //text/x-markdown    image/jpeg    video/mpeg4
        /**
         * 上传视频文件的头
         */
        StringBuilder videoSB = new StringBuilder();
        videoSB.append("--" + BOUNDARY + "\r\n");
        videoSB.append("Content-Disposition: form-data; name=\"" + videoName
                + "\"; filename=\"" + newVideoName + "\"" + "\r\n");
        videoSB.append("Content-Type: multipart/form-data" + "\r\n");// 如果服务器端有文件类型的校验，必须明确指定ContentType
        videoSB.append("\r\n");

        byte[] headerInfo = sb.toString().getBytes("UTF-8");
        byte[] pictureInfo = pictrueSB.toString().getBytes("UTF-8");
        byte[] videoInfo = videoSB.toString().getBytes("UTF-8");
        byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");

        System.out.println(sb.toString());
        System.out.println(pictrueSB.toString());
        System.out.println(videoSB.toString());

        URL url = new URL(urlStr);
        Socket socket = new Socket(url.getHost(), url.getPort());
//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress(url.getHost(), port), 5*60*1000);//设置连接请求超时时间1s
        socket.setSoTimeout(5 * 60 * 1000);//设置读操作超时时间30 s


        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os, true, "UTF-8");


        // 写出请求头
        ps.println("POST " + urlStr + " HTTP/1.1");
       // ps.println("Content-Encoding: UTF-8");
        ps.println("Content-Type: multipart/form-data; boundary=" + BOUNDARY);
        ps.println("Content-Length: "
                + String.valueOf(
                        headerInfo.length           +
                        pictureInfo.length          +
                        videoInfo.length            +
                        videoFile.length()          +
                        pictureFile.length()        +
                        endInfo.length));
        ps.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        InputStream pictureIn = new FileInputStream(pictureFile);
        InputStream videoIn = new FileInputStream(videoFile);


        // 写出数据
        os.write(headerInfo);

        os.write(pictureInfo);

        byte[] buf = new byte[16 * 1024];
        int len;
        progress = 0;
        while ((len = pictureIn.read(buf)) != -1) {
            os.write(buf, 0, len);
            progress += len;
            Logger.e("图片进度", "" + progress);
        }

        os.write("\r\n".toString().getBytes("UTF-8"));

        os.write(videoInfo);


        byte[] viedeoBuf = new byte[ 16 * 1024];
        int viedeoLen;
        while ((viedeoLen = videoIn.read(viedeoBuf)) != -1) {
            os.write(viedeoBuf, 0, viedeoLen);
            progress += viedeoLen;
            Logger.e("视频进度", "" + progress);
        }

        //文件书写结束
        os.write(endInfo);



        InputStream inputStream = socket.getInputStream();

        // SocketChannel channel = socket.getChannel();

        //获取服务器返回的数据
        String s = streamToString(inputStream);

        String[] split = s.split("\r\n");
        for (int i = 0; i < split.length; i++) {
            Log.e("文件上传返回", split[i]);
        }
    //    pictureIn.close();
        videoIn.close();
        os.close();
        inputStream.close();
    }


    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("文件上传", e.toString());
            return null;
        }
    }


}
