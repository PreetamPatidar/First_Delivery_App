package com.example.dealer1;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class , OrderItem.class, CartItem.class}, version = 3)

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract ProductDao productDao();

    public abstract OrderDao orderDao();





    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "UserDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

