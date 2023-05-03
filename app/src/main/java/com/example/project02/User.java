package com.example.project02;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDataBase;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    private boolean mIsAdmin;
    private boolean mIsSeller;
    private String mUsername;
    private String mPassword;

    public User(boolean isAdmin, boolean isSeller, String username, String password ){
        mIsAdmin = isAdmin;
        mIsSeller = isSeller;
        mUsername = username;
        mPassword = password;
    }



    @Override
    public String toString() {
        return "user{" +
                "mLoginId=" + mUserId +
                ", mIsAdmin=" + mIsAdmin +
                ", mIsSeller=" + mIsSeller +
                ", mPassword=" + mPassword +
                ", mUsername='" + mUsername + '\'' +
                '}';
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public boolean isAdmin() {
        return mIsAdmin;
    }

    public void setAdmin(boolean admin) {
        mIsAdmin = admin;
    }

    public boolean isSeller() {
        return mIsSeller;
    }

    public void setSeller(boolean seller) {
        mIsSeller = seller;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
