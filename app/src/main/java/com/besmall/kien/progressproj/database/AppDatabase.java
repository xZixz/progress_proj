package com.besmall.kien.progressproj.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.besmall.kien.progressproj.dao.TargetDao;
import com.besmall.kien.progressproj.model.TargetModel;

/**
 * Created by kien on 09/03/2018.
 */

@Database(entities = {TargetModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static String DATABASE_NAME = "progress_proj_database";

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
               if (INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
               }
            }
        }
        return INSTANCE;
    }

    public abstract TargetDao targetModelDao();
}
