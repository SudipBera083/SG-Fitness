package com.bonggohriday.bonggofitness;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    private  String name;
    private  String description;
    private String img_no;

    public Users() {
    }

    public Users(String name, String description, String img_no) {
        this.name = name;
        this.description = description;
        this.img_no = img_no;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImg_no() {
        return img_no;
    }
}
