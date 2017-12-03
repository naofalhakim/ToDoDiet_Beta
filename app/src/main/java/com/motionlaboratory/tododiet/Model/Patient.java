package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 7/19/2017.
 */

public class Patient {
    private String nama, alamat, email, password, username;
    private int gender,age;
    private String id;

    public Patient(String id, String nama) {
        this.nama = nama; this.id = id;
    }

    public Patient(String username ,String nama, String alamat, int gender, String email, int age) {
        this.nama = nama;
        this.username = username;
        this.alamat = alamat;
        this.gender = gender;
        this.email = email;
        this.age = age;
    }

    public Patient() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}