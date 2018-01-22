package com.hs.doubaobao.bean;

import java.util.List;

/**
 * 作者：zhanghaitao on 2017/9/26 10:13
 * 邮箱：820159571@qq.com
 *
 * @describe:视频bean
 */

public class VideoBean {


    /**
     * resCode : 1
     * resData : {"picList":[{"addTime":"","addUser":"","borrowId":167,"category":8,"id":2208,"isDelete":1,"name":"borrowVideo-1709260073733952.mp4","path":"http://192.168.1.246:8085/borrowVideo/1712071461834605-000000/video.html","pathTure":"http://192.168.1.246:8085/borrowVideo/1712071461834605-000000/video.html","size":0,"type":"2","updateTime":"","updateUser":""},{"addTime":"","addUser":"","borrowId":167,"category":8,"id":2209,"isDelete":1,"name":"约束.mp4","path":"http://192.168.1.246:8085/borrowVideo/1712071337749105-000000/video.html","pathTure":"http://192.168.1.246:8085/borrowVideo/1712071337749105-000000/video.html","size":0,"type":"2","updateTime":"","updateUser":""},{"addTime":"","addUser":"","borrowId":167,"category":9,"id":2210,"isDelete":1,"name":"1710100152427093.mp4","path":"http://192.168.1.246:8085/borrowVideo/1712070910742962-000000/video.html","pathTure":"http://192.168.1.246:8085/borrowVideo/1712070910742962-000000/video.html","size":0,"type":"2","updateTime":"","updateUser":""}]}
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
        private List<PicListBean> picList;

        public List<PicListBean> getPicList() {
            return picList;
        }

        public void setPicList(List<PicListBean> picList) {
            this.picList = picList;
        }

        public static class PicListBean {
            /**
             * addTime :
             * addUser :
             * borrowId : 167
             * category : 8
             * id : 2208
             * isDelete : 1
             * name : borrowVideo-1709260073733952.mp4
             * path : http://192.168.1.246:8085/borrowVideo/1712071461834605-000000/video.html
             * pathTure : http://192.168.1.246:8085/borrowVideo/1712071461834605-000000/video.html
             * size : 0
             * type : 2
             * updateTime :
             * updateUser :
             */

            private String addTime;
            private String addUser;
            private int borrowId;
            private int category;
            private int id;
            private int isDelete;
            private String name;
            private String path;
            private String pathTure;
            private int size;
            private String type;
            private String updateTime;
            private String updateUser;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getAddUser() {
                return addUser;
            }

            public void setAddUser(String addUser) {
                this.addUser = addUser;
            }

            public int getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(int borrowId) {
                this.borrowId = borrowId;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }
        }
    }
}
