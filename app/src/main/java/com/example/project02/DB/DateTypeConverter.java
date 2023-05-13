package com.example.project02.DB;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {
    @TypeConverter
    public Long converDateToLong(Date date){
        return date.getTime();
    }
    @TypeConverter
    public Date convertLongToDate(long epoch){
        return new Date(epoch);
    }
}
