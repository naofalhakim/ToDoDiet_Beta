package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 8/3/2017.
 */

public class BCA {

    private String date;
    private String serial_number;
    private String bca_weight;
    private String fat_percent;
    private String fat_mass;
    private String ffm;
    private String muscle_mass;
    private String tbw;
    private String tbw_percent;
    private String bone_mass;
    private String bmr;
    private String mbolic_age;
    private String visceral_fat_rating;
    private String bca_bmi;
    private String ideal_body_weight;
    private String degree_of_obesity_percent;
    private String fat_bot_range_percent;
    private String fat_top_range_percent;
    private String fat_mass_bot_range_percent;
    private String fat_mass_top_range_percent;

    public BCA(String date, String serial_number, String bca_weight) {
        this.date = date;
        this.serial_number = serial_number;
        this.bca_weight = bca_weight;
    }

    public BCA(String date, String serial_number, String bca_weight, String fat_percent, String fat_mass, String ffm, String muscle_mass, String tbw, String tbw_percent, String bone_mass, String bmr, String mbolic_age, String visceral_fat_rating, String bca_bmi, String ideal_body_weight, String degree_of_obesity_percent, String fat_bot_range_percent, String fat_top_range_percent, String fat_mass_bot_range_percent, String fat_mass_top_range_percent) {
        this.date = date;
        this.serial_number = serial_number;
        this.bca_weight = bca_weight;
        this.fat_percent = fat_percent;
        this.fat_mass = fat_mass;
        this.ffm = ffm;
        this.muscle_mass = muscle_mass;
        this.tbw = tbw;
        this.tbw_percent = tbw_percent;
        this.bone_mass = bone_mass;
        this.bmr = bmr;
        this.mbolic_age = mbolic_age;
        this.visceral_fat_rating = visceral_fat_rating;
        this.bca_bmi = bca_bmi;
        this.ideal_body_weight = ideal_body_weight;
        this.degree_of_obesity_percent = degree_of_obesity_percent;
        this.fat_bot_range_percent = fat_bot_range_percent;
        this.fat_top_range_percent = fat_top_range_percent;
        this.fat_mass_bot_range_percent = fat_mass_bot_range_percent;
        this.fat_mass_top_range_percent = fat_mass_top_range_percent;
    }


    public BCA(String serial_number, String date) {
        this.serial_number = serial_number;
        this.date = date;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBca_weight() {
        return bca_weight;
    }

    public String getFat_percent() {
        return fat_percent;
    }

    public String getFat_mass() {
        return fat_mass;
    }

    public String getFfm() {
        return ffm;
    }

    public String getMuscle_mass() {
        return muscle_mass;
    }

    public String getTbw() {
        return tbw;
    }

    public String getTbw_percent() {
        return tbw_percent;
    }

    public String getBone_mass() {
        return bone_mass;
    }

    public String getBmr() {
        return bmr;
    }

    public String getMbolic_age() {
        return mbolic_age;
    }

    public String getVisceral_fat_rating() {
        return visceral_fat_rating;
    }

    public String getBca_bmi() {
        return bca_bmi;
    }

    public String getIdeal_body_weight() {
        return ideal_body_weight;
    }

    public String getDegree_of_obesity_percent() {
        return degree_of_obesity_percent;
    }

    public String getFat_bot_range_percent() {
        return fat_bot_range_percent;
    }

    public String getFat_top_range_percent() {
        return fat_top_range_percent;
    }

    public String getFat_mass_bot_range_percent() {
        return fat_mass_bot_range_percent;
    }

    public String getFat_mass_top_range_percent() {
        return fat_mass_top_range_percent;
    }
}
