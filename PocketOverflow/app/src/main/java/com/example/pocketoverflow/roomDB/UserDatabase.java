package com.example.pocketoverflow.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final int NUM_OF_THREADS = 4;
    static final ExecutorService databaseWithExecutor = Executors.newFixedThreadPool(NUM_OF_THREADS);
    private static volatile UserDatabase db;

    static UserDatabase getDb(final Context context) {
        if (db == null) {
            synchronized (UserDatabase.class) {
                db = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class, "user_database_1")
                        .build();
            }
        }
        return db;
    }

    public abstract UserDao dao();
}
