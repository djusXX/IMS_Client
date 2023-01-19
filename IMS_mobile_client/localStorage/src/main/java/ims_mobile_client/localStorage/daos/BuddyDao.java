package ims_mobile_client.localStorage.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ims_mobile_client.localStorage.models.LocalBuddy;
import io.reactivex.Completable;

@Dao
public interface BuddyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(LocalBuddy localBuddy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<LocalBuddy> buddies);

    @Delete
    void delete(LocalBuddy localBuddy);

    @Query("delete from buddies_table")
    void deleteAll();

    @Query("select * from buddies_table")
    List<LocalBuddy> getAll();

    @Query("select * from buddies_table where userSipUri = :usrSipUri")
    List<LocalBuddy> getBuddiesFor(String usrSipUri);

    @Query("select * from buddies_table where (userSipUri = :usrSipUri and buddySipUri = :buddySipUri)")
    LocalBuddy getBuddy(String usrSipUri, String buddySipUri);
}
