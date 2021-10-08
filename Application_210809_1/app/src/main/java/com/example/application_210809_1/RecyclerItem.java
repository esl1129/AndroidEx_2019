package com.example.application_210809_1;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItem {
    private String profile;
    private String id;
    private String status;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

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


    public RecyclerItem(String profile, String id, String status) {
        this.profile = profile;
        this.id = id;
        this.status = status;
    }
}
