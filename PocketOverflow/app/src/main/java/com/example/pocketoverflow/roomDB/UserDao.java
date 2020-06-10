package com.example.pocketoverflow.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    public void registration(User user);

    @Query("SELECT * FROM users WHERE id = :userId")
    public User getUserById(Long userId);

    @Query("SELECT * FROM users WHERE username = :username")
    public User getUserByUsername(String username);
}
