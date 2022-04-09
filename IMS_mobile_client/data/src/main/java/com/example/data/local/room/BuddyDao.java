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
    LiveData<List<BuddyEntity>> getAll();

    @Query("select * from buddies_table where user_sip_uri = :usrSipUri")
    LiveData<List<BuddyEntity>> getBuddiesFor(String usrSipUri);

    @Query("select * from buddies_table where (user_sip_uri = :usrSipUri and buddy_sip_uri = :buddySipUri)")
    LiveData<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri);
}
