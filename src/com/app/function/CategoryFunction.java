package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.CategoryDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.CategoryDAOMySqlImpl;
import com.entity.Category;

public class CategoryFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public CategoryFunction() {
    }

    public void editCategoryView(Category category) {
        System.out.println("1. Category\t: " + category.getCategory());
    }

    public Category editCategoryProcess(Category category, String nomorEdit, String valueEdit) {
        
        switch (nomorEdit) {
            case "1":
                category.setCategory(valueEdit);
                break;
        }

        CategoryDAO dao = new CategoryDAOMySqlImpl(MySqlConnection.getConnection());
        try {
           dao.updateCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

    public void showListCategoryView() {
        List<Category> listCategory = new ArrayList();
        CategoryDAO dao = new CategoryDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listCategory = dao.getAllCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ID\t\t Category");
        for(int x=0;x<listCategory.size();x++){
            System.out.println(
                ((Category)listCategory.get(x)).getId()
                +"\t\t"+((Category)listCategory.get(x)).getCategory());
        }

    }

    public void addNewCategoryView(){
        String menuChoose = "";
        Category category = new Category();
        System.out.println("Create New Category");

        System.out.println("Category : ");
        try {
            category.setCategory(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Save");
        System.out.println("2. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                CategoryDAO dao = new CategoryDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao.addCategory(category);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deleteCategoryView(int idCategory){
        int status=0;
        CategoryDAO dao = new CategoryDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteCategory(idCategory);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public Category getCategoryById(int categoryId) {
        Category getCategory = new Category();
        
        CategoryDAO dao = new CategoryDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            getCategory = dao.getCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getCategory;

    }
}
