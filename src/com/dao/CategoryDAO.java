package com.dao;

import java.util.List;

import com.entity.Category;

public interface CategoryDAO {
    public List<Category> getAllCategories() throws Exception;
    public Category getCategory(int id) throws Exception;
    public Category getCategoryByString(String categoryString) throws Exception;
    public int addCategory(Category category) throws Exception;
    public int updateCategory(Category category) throws Exception;
    public int deleteCategory(int id) throws Exception;
}
