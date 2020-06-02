package com.example.recognition.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.recognition.model.localdata.room.DataBase;

import java.util.List;

public class LocalDataSource {
    private DataBase dataBase;
    public LocalDataSource(Context context) {
        dataBase = Room.databaseBuilder(context, DataBase.class, "MyDB").build();
    }
    public LiveData<List<String>> getModels() {
        return dataBase.modelsDao().getModels();
    }
}
