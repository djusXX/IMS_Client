package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.domain.entities.BuddyEntity;

import java.util.List;

@Dao
public interface BuddyDao {
    @Insert
    void insert(BuddyEntity buddyEntity);

    @Insert
    void insert(List<BuddyEntity> buddies);

    @Delete
    void delete(BuddyEntity buddyEntity);

    @Query("delete from buddies_table")
    void deleteAll();

    @Query("select * from buddies_table")
    List<BuddyEntity> getAll();

    @Query("select * from buddies_table where userSipUri = :usrSipUri")
    List<BuddyEntity> getBuddiesFor(String usrSipUri);

    @Query("select * from buddies_table where (userSipUri = :usrSipUri and buddySipUri = :buddySipUri)")
    BuddyEntity getBuddy(String usrSipUri, String buddySipUri);
}
