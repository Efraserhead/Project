package com.example.project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(Question question);

    @Delete
    void delete(Question question);

    @Update
    void update(Question question);


    @Query("SELECT * FROM question_table WHERE lessonId=:lessonNumber")
    List<Question> getLessonQuestions(int lessonNumber);


}
