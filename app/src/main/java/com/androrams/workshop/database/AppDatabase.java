package com.androrams.workshop.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.androrams.workshop.model.VideoDetails;

@Database(entities = {VideoDetails.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase = null;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "my-videos").build();
        }
        return appDatabase;
    }

    public abstract VideoDao videoDao();
}