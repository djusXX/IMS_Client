package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.domain.entities.BuddyEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BuddyDao {
    @Insert
    Completable insert(BuddyEntity buddyEntity);

    @Insert
    Completable insert(List<BuddyEntity> buddies);

    @Delete
    void delete(BuddyEntity buddyEntity);

    @Query("delete from buddies_table")
    void deleteAll();

    @Query("select * from buddies_table")
    Flowable<List<BuddyEntity>> getAll();

    @Query("select * from buddies_table where user_sip_uri = :usrSipUri")
    Flowable<List<BuddyEntity>> getBuddiesFor(String usrSipUri);

    @Query("select * from buddies_table where (user_sip_uri = :usrSipUri and buddy_sip_uri = :buddySipUri)")
    Flowable<BuddyEntity> getBuddy(String usrSipUri, String buddySipUri);
}
