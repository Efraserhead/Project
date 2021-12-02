package com.example.project;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface LessonDao {

    @Insert
    void insert(Lesson lesson);


    @Delete
    void delete(Lesson lesson);

    @Update
    void update(Lesson lesson);


    @Query("SELECT * FROM lesson_table WHERE id=:lessonChoice")
    Lesson getLesson(int lessonChoice);

    @Query("SELECT * FROM lesson_table WHERE categoryId=:level")
    Cursor getCategoryLessons(int level);


}
