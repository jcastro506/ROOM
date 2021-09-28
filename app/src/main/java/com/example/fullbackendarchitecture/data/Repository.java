package com.example.fullbackendarchitecture.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fullbackendarchitecture.model.Contact;
import com.example.fullbackendarchitecture.util.RoomDatabase.ContactRoomDatabase;

import java.util.List;

public class Repository {

    private ContactDAO contactDAO;
    private LiveData<List<Contact>> allContacts;
    ContactRoomDatabase contactRoomDatabase;

    public Repository(Application application) {
        contactRoomDatabase = ContactRoomDatabase.getInstance(application);
        contactDAO = contactRoomDatabase.contactDAO();
        allContacts = contactDAO.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts(){ return allContacts; }

    public void insert(Contact contact) {
        ContactRoomDatabase.dataBaseWriter.execute(() -> contactDAO.insert(contact));
    }

    public void delete(Contact contact) { contactDAO.delete(contact);}
    public void deleteAll() { contactDAO.deleteAll(); }


}
