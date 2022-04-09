package com.example.data.local.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.domain.entities.MessageEntity;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MessageEntity messageEntity);

    @Insert
    void insert(List<MessageEntity> messages);

    @Delete
    void delete(MessageEntity messageEntity);

    @Query("delete from messages_table")
    void deleteAll();

    @Query("select * from messages_table")
    LiveData<List<MessageEntity>> getAll();

    @Query("select * from messages_table where (sipUriFROM = :usrSipUri or sipUriTO = :usrSipUri)")
    LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri);

    @Query("select * from messages_table where ((sipUriFROM = :usrSipUri and sipUriTO = :buddySipUri) or (sipUriFROM = :buddySipUri and sipUriTO = :usrSipUri))")
    LiveData<List<MessageEntity>> getMessagesFor(String usrSipUri, String buddySipUri);
}
