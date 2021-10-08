package com.test.fragmentsample.adapter;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.annotation.NonNull;

class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "phonenum")
    public String phonenum;


    @ColumnInfo(name = "password")
    public String Password;

    public String getPassword() {
        return Password;
    }
}