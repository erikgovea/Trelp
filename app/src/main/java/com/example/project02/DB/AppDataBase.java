package com.example.project02.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.project02.ReviewTable;
import com.example.project02.User;

//code from gymlog from Dr.C that i tried to convert to mine from the videos
@Database(entities = {User.class, ReviewTable.class}, version = 4)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "user.db";
    public static final String USER_TABLE = "USER_TABLE";

    public static final String REVIEW_TABLE = "review_table";

    private static volatile AppDataBase instance;

    private static final Object LOCK = new Object();

    public abstract TrelpDAO TrelpDAO();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class ,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
