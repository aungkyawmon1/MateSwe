package com.example.mateswe.model;

public class Category {
    private int id, categoryIcon;
    private String categoryName;

    public Category(String categoryName, int categoryIcon){
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
    }
    public Category(int id, String categoryName, int categoryIcon){
        this.id = id;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(int categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
