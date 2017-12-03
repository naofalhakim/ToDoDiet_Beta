package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 8/7/2017.
 */

public class Badge {
    private String nama, desc;
    private int obj, progress;

    public Badge(String nama, String desc, int obj, int progress) {
        this.nama = nama;
        this.desc = desc;
        this.obj = obj;
        this.progress = progress;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getObj() {
        return obj;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
