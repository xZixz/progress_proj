package com.besmall.kien.progressproj.model;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by kien on 09/03/2018.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
