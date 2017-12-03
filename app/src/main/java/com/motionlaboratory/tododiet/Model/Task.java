package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 8/5/2017.
 */

public class Task {
    private String type,due_date;
    //kategori 1(exercise), 2(food)
    private int id,kategori,done;

    public Task(String type, String due_date, int id, int kategori, int done) {
        this.type = type;
        this.due_date = due_date;
        this.id = id;
        this.kategori = kategori;
        this.done = done;
    }

    public Task(String type, String due_date, int done) {
        this.type = type;
        this.due_date = due_date;
        this.done = done;
    }

    public Task() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKategori() {
        return kategori;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
