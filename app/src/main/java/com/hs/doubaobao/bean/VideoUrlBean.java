package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2018/1/26 11:37
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class VideoUrlBean {


    /**
     * resCode : 1
     * resData : {"videoList":[{"category":8,"name":"1801260782554780","path":"http://192.168.1.103:8085/data/video/borrow/20180126/1801260782554780.mp4","pathTure":"http://192.168.1.103:8085/videoTemp/1801260782554780-FFD8FF/view.html","size":21392184,"type":"2"}]}
     * resMsg : SUCCESS
     */

    private int resCode;
    private ResDataBean resData;
    private String resMsg;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public ResDataBean getResData() {
        return resData;
    }

    public void setResData(ResDataBean resData) {
        this.resData = resData;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public static class ResDataBean {
        private List<VideoListBean> videoList;

        public List<VideoListBean> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<VideoListBean> videoList) {
            this.videoList = videoList;
        }

        public static class VideoListBean {
            /**
             * category : 8
             * name : 1801260782554780
             * path : http://192.168.1.103:8085/data/video/borrow/20180126/1801260782554780.mp4
             * pathTure : http://192.168.1.103:8085/videoTemp/1801260782554780-FFD8FF/view.html
             * size : 21392184
             * type : 2
             */

            private int category;
            private String name;
            private String path;
            private String pathTure;
            private long size;
            private String type;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getPathTure() {
                return pathTure;
            }

            public void setPathTure(String pathTure) {
                this.pathTure = pathTure;
            }

            public long getSize() {
                return size;
            }

            public void setSize(long size) {
                this.size = size;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
