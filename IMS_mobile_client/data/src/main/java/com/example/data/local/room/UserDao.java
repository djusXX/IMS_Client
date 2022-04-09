package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.domain.entities.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

    @Query("select * from local_users_table where usrSipUri = :usrSipUri")
    UserEntity getUser(String usrSipUri);
}
