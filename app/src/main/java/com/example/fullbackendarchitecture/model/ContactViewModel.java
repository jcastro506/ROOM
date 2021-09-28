package com.example.fullbackendarchitecture.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fullbackendarchitecture.data.Repository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    public static Repository repository;
    public LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allContacts = repository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public static void insert(Contact contact){
        repository.insert(contact);
    }
}
