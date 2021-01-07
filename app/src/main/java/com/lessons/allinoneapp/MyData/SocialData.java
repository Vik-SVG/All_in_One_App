package com.lessons.allinoneapp.MyData;

public class SocialData {
    public int imgId;
    public String txt;

    public SocialData(int imgId, String txt) {
        this.imgId = imgId;
        this.txt = txt;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
