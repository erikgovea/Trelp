package com.example.project02;


import android.content.Intent;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.REVIEW_TABLE)

public class ReviewTable  {

    @PrimaryKey(autoGenerate = true)
    public Integer mUserId;

    public String mUserInput;
    public Date mDate;

    public ReviewTable(String mUserInput, Integer userId, Date mDate) {
        this.mUserInput = mUserInput;
        this.mUserId = userId;
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        String output;
        output = mUserInput;
        output += " - ";
        output += mUserId;
        output += ". Date: ";
        output += getmDate();
        output += "\n";

        return output;

    }

    public String getmUserInput() {
        return mUserInput;
    }

    public void setmUserInput(String mUserInput) {
        this.mUserInput = mUserInput;
    }

    public Integer getmUserId() {
        return mUserId;
    }

    public void setmUserId(Integer mUserId) {
        this.mUserId = mUserId;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
