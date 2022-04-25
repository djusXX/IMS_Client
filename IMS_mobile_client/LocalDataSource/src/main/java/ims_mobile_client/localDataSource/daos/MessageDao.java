package ims_mobile_client.localDataSource.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ims_mobile_client.localDataSource.entities.LocalMessage;

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalMessage localMessage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<LocalMessage> messages);

    @Delete
    void delete(LocalMessage localMessage);

    @Query("delete from messages_table")
    void deleteAll();

    @Query("select * from messages_table")
    List<LocalMessage> getAll();

    @Query("select * from messages_table where (sipUriFROM = :usrSipUri or sipUriTO = :usrSipUri)")
    List<LocalMessage> getMessagesFor(String usrSipUri);

    @Query("select * from messages_table where ((sipUriFROM = :usrSipUri and sipUriTO = :buddySipUri) or (sipUriFROM = :buddySipUri and sipUriTO = :usrSipUri))")
    List<LocalMessage> getMessagesFor(String usrSipUri, String buddySipUri);
}
