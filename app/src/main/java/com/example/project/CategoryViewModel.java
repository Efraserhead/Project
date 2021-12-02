package com.example.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private final Repository repository;
    Category category;
    List<Category> categories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insert(Category category) {
        repository.insertCategory(category);
    }

    public void delete(Category category) {
        repository.deleteCategory(category);
    }

    public void update(Category category) {
        repository.updateCategory(category);
    }

    public Category getCategory(int categoryChoice) {
        category = repository.getCategory(categoryChoice);
        return category;
    }

    public List<Category> getCategories() {
        categories = repository.getAllCategories();
        return categories;
    }
}
