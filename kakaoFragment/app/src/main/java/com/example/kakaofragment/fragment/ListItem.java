package com.example.kakaofragment.fragment;

import java.io.Serializable;

public class ListItem implements Serializable {
    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    private int profile;
    private String id;
    private String status;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public ListItem(int profile, String id, String status) {
        this.profile = profile;
        this.id = id;
        this.status = status;
    }
}
