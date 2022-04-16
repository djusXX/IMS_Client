package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.domain.entities.CallEntity;

import java.util.List;

@Dao
public interface CallDao {

    @Insert
    void insert(CallEntity callEntity);

    @Insert
    void insert(List<CallEntity> calls);

    @Delete
    void delete(CallEntity callEntity);

    @Query("delete from calls_table")
    void deleteAll();

    @Query("select * from calls_table")
    List<CallEntity> getAll();

    @Query("select * from calls_table where (sipUriTO = :usrSipUri or sipUriFROM = :usrSipUri)")
    List<CallEntity> getCallsFor(String usrSipUri);

    @Query("select * from calls_table where ((sipUriFROM = :usrSipUri and sipUriTO = :buddySipUri) or (sipUriFROM = :buddySipUri and sipUriTO = :usrSipUri))")
    List<CallEntity> getCallsFor(String usrSipUri, String buddySipUri);
}
