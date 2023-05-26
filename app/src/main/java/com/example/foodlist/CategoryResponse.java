package com.example.foodlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("categories")
   private List<Category> remoteList;
    public CategoryResponse(List<Category> remoteList){
        this.remoteList=remoteList;
    }

    public List<Category> getRemoteList() {
        return remoteList;
    }

    public void setRemoteList(List<Category> remoteList) {
        this.remoteList = remoteList;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "remoteList=" + remoteList +
                '}';
    }
}
