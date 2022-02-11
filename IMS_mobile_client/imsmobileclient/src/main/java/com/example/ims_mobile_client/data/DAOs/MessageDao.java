package com.example.ims_mobile_client.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ims_mobile_client.data.Entities.MessageEntity;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(MessageEntity messageEntity);

    @Delete
    void delete(MessageEntity messageEntity);

    @Query("delete from messages_table")
    void deleteAll();

    @Query("select * from messages_table")
    LiveData<List<MessageEntity>> getAll();
}
