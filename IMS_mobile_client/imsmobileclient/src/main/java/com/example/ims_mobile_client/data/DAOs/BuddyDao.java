package com.example.ims_mobile_client.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ims_mobile_client.data.entities.BuddyEntity;

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
}
