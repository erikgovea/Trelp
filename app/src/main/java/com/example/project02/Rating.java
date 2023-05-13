package com.example.project02;


import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import com.example.project02.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.RATING_TABLE)
public class Rating {
    @PrimaryKey(autoGenerate = true)
    public Integer mUserId;

    public float rating;
    public Date mDate;

    public Rating(Integer mUserId, float rating, Date mDate) {
        this.mUserId = mUserId;
        this.rating = rating;
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return "I would rate this: " + rating+ " - " + mUserId;
    }

    public Integer getmUserId() {
        return mUserId;
    }

    public void setmUserId(Integer mUserId) {
        this.mUserId = mUserId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
