package com.example.project02.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project02.Rating;
import com.example.project02.ReviewTable;
import com.example.project02.User;

import java.util.List;

@Dao
public interface TrelpDAO {

    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE+ " WHERE mUsername = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserById(int userId);
    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mIsSeller = 1" )
    List<User> getSellerUsers();


    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getAllUsers();

    @Insert
    void insert(ReviewTable... review);

    @Update
    void update(ReviewTable... review);
    @Delete
    void delete(ReviewTable... review);
    //TODO: fix
    @Query("SELECT * FROM " + AppDataBase.REVIEW_TABLE + " WHERE mUserId = :userId")
    List<ReviewTable> getReviewByUsername(Integer userId);


    @Insert
    void insert(Rating... rating);
    @Update
    void update(Rating... rating);

    @Delete
    void delete(Rating... rating);
    @Query("SELECT * FROM "  + AppDataBase.RATING_TABLE + " WHERE mUserId = :userId")
    List< Rating> getRatingsByUsername(Integer userId);

    @Query("SELECT * FROM " + AppDataBase.RATING_TABLE + " WHERE mUserId = :userId ORDER BY mDate desc")
    List< Rating> getRatingsByDate(int userId);














}
