//package com.example.ims_mobile_client.data.DAOs;
//
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//
//import com.example.ims_mobile_client.data.entities.SipContactEntity;
//
//import java.util.List;
//
//@Dao
//public interface SipContactDao {
//
//    @Query("SELECT * FROM contacts_tab WHERE accountSipUri = :accountID")
//    LiveData<List<SipContactEntity>> getContactsFor(String accountID);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(SipContactEntity sipContactEntity);
//
//    @Delete
//    void delete(SipContactEntity sipContactEntity);
//}
