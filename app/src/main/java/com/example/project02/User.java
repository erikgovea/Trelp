package com.example.project02;


import android.widget.Button;

import androidx.room.Entity;
import androidx.room.Ignore;
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

    public String truckName;
    private String mDesc;

    private String mImage;


    private boolean mIsBanned;

    public User(boolean isAdmin, Boolean isSeller,String username, String password, boolean isBanned, String truckName, String desc, String image){
        mIsAdmin = isAdmin;
        mIsSeller = isSeller;
        mUsername = username;
        mPassword = password;
        mIsBanned = isBanned;

        mDesc = desc;
        mImage =  image;

    }




    @Override
    public String toString() {
        return "user{" +
                "mLoginId=" + mUserId +
                ", mIsAdmin=" + mIsAdmin +
                ", mIsSeller=" + mIsSeller +
                ", mPassword=" + mPassword +
                ", mIsBanned=" + mIsBanned +
                ", mTruckname=" + truckName +
                ", mImage=" + mImage +

                ", mDesc=" + mDesc +
                ", mUsername='" + mUsername + '\'' +
                '}';
    }



    public boolean isBanned() {
        return mIsBanned;
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

    public String getTruckname() {
        return truckName;
    }

    public void setTruckname(String mTruckname) {
        this.truckName = mTruckname;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        this.mDesc = mDesc;}

    public String getImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}



