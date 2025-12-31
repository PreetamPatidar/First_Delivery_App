package com.example.dealer1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDataBase extends RoomDatabase {

    private static ProductDataBase instance;

    public abstract ProductDataDao productDataDao();

    public static synchronized ProductDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProductDataBase.class,
                    "ProductDB"
            ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

