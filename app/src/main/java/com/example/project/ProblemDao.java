package com.example.project;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProblemDao {


    @Insert
    void insert(Problem problem);

    @Delete
    void delete(Problem problem);

    @Update
    void update(Problem problem);


    @Query("SELECT * FROM problem_table WHERE lessonNo=:lessonNo AND level=:level")
    LiveData<List<Problem>> getLessonProblems(int level, int lessonNo);

    @Query("SELECT * FROM problem_table")
    LiveData<List<Problem>> getProblems();


}
