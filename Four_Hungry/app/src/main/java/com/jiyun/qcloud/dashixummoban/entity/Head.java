package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by my301s on 2017/8/17.
 */

public class Head {

    /**
     * code : 0
     * data : {"result":"上传头像成功","url":"http://localhost:8080/FileUploadDemo/files/b.jpeg"}
     */

    private String code;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
