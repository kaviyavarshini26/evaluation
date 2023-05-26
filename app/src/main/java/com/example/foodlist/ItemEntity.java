package com.example.foodlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "items")



public class ItemEntity {



        @PrimaryKey(autoGenerate = true)
        private int id;

        private String itemName;



    public ItemEntity(String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }




    }

