package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.domain.entities.MessageEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(MessageEntity messageEntity);

    @Insert
    Completable insert(List<MessageEntity> messages);

    @Delete
    void delete(MessageEntity messageEntity);

    @Query("delete from messages_table")
    void deleteAll();

    @Query("select * from messages_table")
    Flowable<List<MessageEntity>> getAll();

    @Query("select * from messages_table where (sip_uri_FROM = :usrSipUri or sip_uri_TO = :usrSipUri)")
    Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri);

    @Query("select * from messages_table where ((sip_uri_FROM = :usrSipUri and sip_uri_TO = :buddySipUri) or (sip_uri_FROM = :buddySipUri and sip_uri_TO = :usrSipUri))")
    Flowable<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);
}
