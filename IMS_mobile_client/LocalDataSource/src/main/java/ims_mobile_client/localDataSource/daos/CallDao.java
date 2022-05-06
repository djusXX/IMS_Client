package ims_mobile_client.localDataSource.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ims_mobile_client.localDataSource.models.LocalCall;

@Dao
public interface CallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalCall localCall);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<LocalCall> calls);

    @Delete
    void delete(LocalCall localCall);

    @Query("delete from calls_table")
    void deleteAll();

    @Query("select * from calls_table")
    List<LocalCall> getAll();

    @Query("select * from calls_table where (sipUriTO = :usrSipUri or sipUriFROM = :usrSipUri)")
    List<LocalCall> getCallsFor(String usrSipUri);

    @Query("select * from calls_table where ((sipUriFROM = :usrSipUri and sipUriTO = :buddySipUri) or (sipUriFROM = :buddySipUri and sipUriTO = :usrSipUri))")
    List<LocalCall> getCallsFor(String usrSipUri, String buddySipUri);
}
