package com.example.foodlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse1 {
    @SerializedName("categories")
    private List<Category> savedList;
    public CategoryResponse1(List<Category> remoteList){
        this.savedList=savedList;
    }

    public List<Category> getSavedList() {
        return savedList;
    }

    public void setSavedList(List<Category> savedList) {
        this.savedList = savedList;
    }

    @Override
    public String toString() {
        return "CategoryResponse1{" +
                "savedList=" + savedList +
                '}';
    }
}
