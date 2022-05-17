package ims_mobile_client.localStorage.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import ims_mobile_client.localStorage.models.LocalUser;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalUser localUser);

    @Delete
    void delete(LocalUser localUser);

    @Query("select * from local_users_table where usrSipUri = :usrSipUri")
    LocalUser getUser(String usrSipUri);

    @Query("select * from local_users_table order by lastLogged desc limit 1")
    LocalUser getLastLoggedUser();
}
