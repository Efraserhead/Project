package com.example.project;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);

    @Query("SELECT * FROM category_table WHERE id=:categoryChoice")
    Category getCategory(int categoryChoice);

    @Query("SELECT * FROM CATEGORY_TABLE")
    List<Category> getAll();
}
