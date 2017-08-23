package com.jiyun.qcloud.dashixummoban.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MovieBean {


    private String code;
    private DataBean data;
    private String msg;
    private String showMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public static class DataBean {
        private List<TrailersBean> trailers;

        public List<TrailersBean> getTrailers() {
            return trailers;
        }

        public void setTrailers(List<TrailersBean> trailers) {
            this.trailers = trailers;
        }

        public static class TrailersBean {
            /**
             * coverImg : http://img5.mtime.cn/mg/2017/05/04/171548.23436952.jpg
             * hightUrl : https://vfx.mtime.cn/Video/2017/08/21/mp4/170821215958499913.mp4
             * id : 67224
             * movieId : 236404
             * movieName : 《芳华》偶像版预告片
             * rating : -1
             * summary : 冯小刚讲述文工团"大时代"
             * type : ["剧情"]
             * url : https://vfx.mtime.cn/Video/2017/08/21/mp4/170821215958499913_480.mp4
             * videoLength : 129
             * videoTitle : 芳华 偶像版预告片
             */

            private String coverImg;
            private String hightUrl;
            private int id;
            private int movieId;
            private String movieName;
            private double rating;
            private String summary;
            private String url;
            private int videoLength;
            private String videoTitle;
            private List<String> type;

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getHightUrl() {
                return hightUrl;
            }

            public void setHightUrl(String hightUrl) {
                this.hightUrl = hightUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMovieId() {
                return movieId;
            }

            public void setMovieId(int movieId) {
                this.movieId = movieId;
            }

            public String getMovieName() {
                return movieName;
            }

            public void setMovieName(String movieName) {
                this.movieName = movieName;
            }

            public double getRating() {
                return rating;
            }

            public void setRating(double rating) {
                this.rating = rating;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getVideoLength() {
                return videoLength;
            }

            public void setVideoLength(int videoLength) {
                this.videoLength = videoLength;
            }

            public String getVideoTitle() {
                return videoTitle;
            }

            public void setVideoTitle(String videoTitle) {
                this.videoTitle = videoTitle;
            }

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }
        }
    }
}
