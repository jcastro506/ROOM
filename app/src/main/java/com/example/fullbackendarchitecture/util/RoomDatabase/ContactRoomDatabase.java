package com.example.fullbackendarchitecture.util.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fullbackendarchitecture.data.ContactDAO;
import com.example.fullbackendarchitecture.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactDAO contactDAO();
    private static volatile ContactRoomDatabase INSTANCE;
    private static final int THREADS = 4;
    public static final ExecutorService dataBaseWriter = Executors.newFixedThreadPool(THREADS);

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dataBaseWriter.execute(() -> {
                        ContactDAO contactDAO = INSTANCE.contactDAO();
                        contactDAO.deleteAll();
                    });
                }
            };

    public static ContactRoomDatabase getInstance(final Context context) {
        if(INSTANCE == null){
            synchronized (ContactRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
