package com.example.project;

import android.app.Application;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;

    private Category category;

    private List<Category> allCategories;

    public CategoryRepository(Application application) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        categoryDao = projectDatabase.categoryDao();
    }


    public void insert(Category category) {
        categoryDao.insert(category);
    }


    public void delete(Category category) {
        categoryDao.delete(category);
    }


    public void update(Category category){
        categoryDao.update(category);
    }


    public Category getCategory(int categoryChoice) {
        return category = categoryDao.getCategory(categoryChoice);
    }


    public List<Category> getAll() {
        return allCategories = categoryDao.getAll();
    }
}
