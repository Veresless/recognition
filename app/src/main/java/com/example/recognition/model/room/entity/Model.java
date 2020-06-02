package com.example.recognition.model.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Model {
    @PrimaryKey
    private String model;
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
