package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by my301s on 2017/8/17.
 */

public class HeadResult {

    /**
     * result : 上传头像成功
     * url : http://localhost:8080/FileUploadDemo/files/b.jpeg
     */

    private String result;
    private String url;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
