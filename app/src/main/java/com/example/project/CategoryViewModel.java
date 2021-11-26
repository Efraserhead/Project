package com.example.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
    }

    public void insert(Category category) {
        categoryRepository.insert(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void update(Category category) {
        categoryRepository.update(category);
    }

    public Category getCategory(int categoryChoice) {
        return category = categoryRepository.getCategory(categoryChoice);
    }

    public List<Category> getCategories() {
        return categories = categoryRepository.getAll();
    }
}
