package com.example.project;

import android.icu.text.SimpleDateFormat;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    @TypeConverter
    public static String fromDate(Date lastCompleted) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        String lastCompletedAsString = sdf.format(lastCompleted);
        return lastCompletedAsString;
    }

    @TypeConverter
    public static Date toDate(String lastCompletedAsString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
            Date lastCompleted = sdf.parse(lastCompletedAsString);
            return lastCompleted;
        } catch (ParseException e) {
            return null;
        }
    }

}
