package com.example.repository.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.repository.data.entities.BuddyEntity;

import java.util.List;

@Dao
public interface BuddyDao {
    @Insert
    void insert(BuddyEntity buddyEntity);

    @Delete
    void delete(BuddyEntity buddyEntity);

    @Query("delete from buddies_table")
    void deleteAll();

    @Query("select * from buddies_table")
    LiveData<List<BuddyEntity>> getAll();

    @Query("select * from buddies_table where user_sip_uri = :usrSipUri")
    LiveData<List<BuddyEntity>> getBuddiesFor(String usrSipUri);
}