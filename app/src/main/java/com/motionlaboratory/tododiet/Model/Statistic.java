package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 8/4/2017.
 */

public class Statistic {
    private String id, patient_id;
    private int level;
    private double exp,gold,weight,height;

    public Statistic(String id, String patient_id, int level, double exp, double gold, double weight, double height) {
        this.id = id;
        this.patient_id = patient_id;
        this.level = level;
        this.exp = exp;
        this.gold = gold;
        this.weight = weight;
        this.height = height;
    }

    public Statistic(int level, double gold, double weight, double height) {
        this.level = level;
        this.gold = gold;
        this.weight = weight;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
