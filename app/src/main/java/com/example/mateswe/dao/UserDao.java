package com.example.mateswe.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mateswe.entity.User;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where id = :id")
    User findByID(String id);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Query("SELECT COUNT(*) FROM user WHERE user_name= :user_name AND password = :password")
    int permit(String user_name, String password);

    @Query("SELECT * FROM user WHERE user_name= :user_name AND password = :password")
    User getUser(String user_name, String password);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
