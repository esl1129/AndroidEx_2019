package com.test.fragmentsample.adapter;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT password FROM user where phonenum = pnum")
    String getPassword(String pnum);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}

