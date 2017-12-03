package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 7/18/2017.
 */

public class ContentTemp {

    private String judul, content, image;

    public ContentTemp(String judul, String content, String image) {
        this.judul = judul;
        this.content = content;
        this.image = image;
    }

    public ContentTemp(String judul, String content) {
        this.judul = judul;
        this.content = content;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
