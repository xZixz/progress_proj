package com.besmall.kien.progressproj.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.besmall.kien.progressproj.model.DateConverter;
import com.besmall.kien.progressproj.model.TargetModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by kien on 09/03/2018.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface TargetDao {

    @Query("select * from TargetModel")
    LiveData<List<TargetModel>> getAllTargetItems();

    @Query("select * from TargetModel where id = :id")
    TargetModel getTargetItemById(String id);

    @Query("select count(*) from TargetModel")
    LiveData<Integer> getTargetModelCount();

    @Insert(onConflict = REPLACE)
    long addTargetModel(TargetModel targetModel);

    @Delete
    void deleteTarget(TargetModel targetModel);
}
