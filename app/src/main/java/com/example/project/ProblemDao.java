package com.example.project;



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

    @Insert
    void insertAll(Problem...problems);

    @Delete
    void delete(Problem problem);

    @Update
    void update(Problem problem);


    @Query("SELECT * FROM problem_table WHERE lessonId=:lessonNo")
    List<Problem> getLessonProblems(int lessonNo);

    @Query("SELECT * FROM problem_table WHERE level>0")
    List<Problem> getUnlockedProblems();

    @Query("SELECT * FROM problem_table")
    List<Problem> getProblems();


}
