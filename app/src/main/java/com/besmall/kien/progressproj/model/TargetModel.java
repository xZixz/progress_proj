package com.besmall.kien.progressproj.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by kien on 09/03/2018.
 */

@Entity
public class TargetModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(DateConverter.class)
    private Date createdAt;

    private String targetName;

    public TargetModel(String targetName, Date createdAt) {
        this.targetName = targetName;
        this.createdAt = createdAt;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }
}
