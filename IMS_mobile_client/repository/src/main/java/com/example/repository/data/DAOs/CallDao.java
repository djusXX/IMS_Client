package com.example.repository.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.repository.data.entities.CallEntity;

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

    @Query("select * from calls_table where (sip_uri_TO = :usrSipUri or sip_uri_FROM = :usrSipUri)")
    LiveData<List<CallEntity>> getCallsFor(String usrSipUri);

    @Query("select * from calls_table where ((sip_uri_FROM = :usrSipUri and sip_uri_TO = :buddySipUri) or (sip_uri_FROM = :buddySipUri and sip_uri_TO = :usrSipUri))")
    LiveData<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
}
