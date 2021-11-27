package com.example.project;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static String fromDate(Date nextAvailable) {
        Gson gson = new Gson();
        String json = gson.toJson(nextAvailable);
        return json;
    }

    @TypeConverter
    public static Date toDate(String nextAvailable) {
        Type dateType = new TypeToken<Date>(){}.getType();
        return new Gson().fromJson(nextAvailable,dateType);
    }

}
