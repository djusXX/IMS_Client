package com.example.ims_mobile_client.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ims_mobile_client.data.Entities.CallEntity;

import java.util.List;

@Dao
public interface CallDao {

    @Insert
    void insert(CallEntity callEntity);

    @Delete
    void delete(CallEntity callEntity);

    @Query("delete from calls_table")
    void deleteAll();

    @Query("select * from calls_table")
    LiveData<List<CallEntity>> getAll();
}
