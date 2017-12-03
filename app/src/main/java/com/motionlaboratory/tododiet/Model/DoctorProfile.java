package com.motionlaboratory.tododiet.Model;

/**
 * Created by naofal on 7/20/2017.
 */

public class DoctorProfile {
    private int id;
    private String full_name, clinic_location;

    public DoctorProfile(int id, String full_name, String clinic_location) {
        this.id = id;
        this.full_name = full_name;
        this.clinic_location = clinic_location;
    }

    public DoctorProfile(String full_name, String clinic_location) {
        this.full_name = full_name;
        this.clinic_location = clinic_location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getClinic_location() {
        return clinic_location;
    }

    public void setClinic_location(String clinic_location) {
        this.clinic_location = clinic_location;
    }
}
