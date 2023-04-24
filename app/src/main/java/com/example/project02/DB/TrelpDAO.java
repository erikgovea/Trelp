package com.example.project02.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02.user;

import java.util.List;

@Dao
public interface TrelpDAO {

    @Insert
    void insert(user... user);

    @Update
    void update(user... user);

    @Delete
    void delete(user user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<user> getUserByUsername(String username);


    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<user> getUserById(int logId);
}
