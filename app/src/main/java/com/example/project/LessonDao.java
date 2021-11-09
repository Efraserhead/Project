package com.example.project;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LessonDao {

    @Insert
    void insert(Lesson lesson);

    @Delete
    void delete(Lesson lesson);

    @Update
    void update(Lesson lesson);


    @Query("SELECT * FROM lesson_table WHERE id=:lessonChoice")
    LiveData<Lesson> getLesson(int lessonChoice);

    @Query("SELECT * FROM lesson_table WHERE level=:level")
    LiveData<List<Lesson>> getCategoryLessons(int level);



}
