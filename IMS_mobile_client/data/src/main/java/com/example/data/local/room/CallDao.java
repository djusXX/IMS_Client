package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.domain.entities.CallEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface CallDao {

    @Insert
    Completable insert(CallEntity callEntity);

    @Insert
    Completable insert(List<CallEntity> calls);

    @Delete
    void delete(CallEntity callEntity);

    @Query("delete from calls_table")
    void deleteAll();

    @Query("select * from calls_table")
    Flowable<List<CallEntity>> getAll();

    @Query("select * from calls_table where (sip_uri_TO = :usrSipUri or sip_uri_FROM = :usrSipUri)")
    Flowable<List<CallEntity>> getCallsFor(String usrSipUri);

    @Query("select * from calls_table where ((sip_uri_FROM = :usrSipUri and sip_uri_TO = :buddySipUri) or (sip_uri_FROM = :buddySipUri and sip_uri_TO = :usrSipUri))")
    Flowable<List<CallEntity>> getCallsFor(String usrSipUri, String buddySipUri);
}
