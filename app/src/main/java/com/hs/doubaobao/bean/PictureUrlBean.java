package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2018/1/26 11:37
 * 邮箱：820159571@qq.com
 *
 * @describe:
 */

public class PictureUrlBean {


    /**
     * resCode : 1
     * resData : {"pictureList":[{"category":1,"name":"1801261688256116","path":"http://192.168.1.103:8083/borrow/1801261688256116-FFD8FF/view.html","pathTure":"http://192.168.1.103:8083/imgTemp/1801261688256116-FFD8FF/view.html","size":229508,"type":"1"}]}
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
        private List<PictureListBean> pictureList;

        public List<PictureListBean> getPictureList() {
            return pictureList;
        }

        public void setPictureList(List<PictureListBean> pictureList) {
            this.pictureList = pictureList;
        }

        public static class PictureListBean {
            /**
             * category : 1
             * name : 1801261688256116
             * path : http://192.168.1.103:8083/borrow/1801261688256116-FFD8FF/view.html
             * pathTure : http://192.168.1.103:8083/imgTemp/1801261688256116-FFD8FF/view.html
             * size : 229508
             * type : 1
             */

            private int category;
            private String name;
            private String path;
            private String pathTure;
            private int size;
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

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
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
