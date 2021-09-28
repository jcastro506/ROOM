package com.example.fullbackendarchitecture.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fullbackendarchitecture.model.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    //ALL CRUD HANDLED HERE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * FROM contact_table")
    LiveData<List<Contact>> getAllContacts();

}
