package com.bonggohriday.bonggofitness;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class admin_details {
    private  String name;
    private  String given_Name;
    private String email;
    private String family_Name;

    public admin_details(String name, String given_Name, String email, String family_Name) {
        this.name = name;
        this.given_Name = given_Name;
        this.email = email;
        this.family_Name =family_Name;
    }

    public admin_details() {
    }

    public String getName() {
        return name;
    }

    public String getgiven_Name() {
        return given_Name;
    }

    public String getEmail() {
        return email;
    }

    public String getFamily_Name() {
        return family_Name;
    }
}
