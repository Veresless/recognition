package com.example.recognition.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.recognition.model.room.dao.ModelsDao;
import com.example.recognition.model.room.dao.ResponseDao;
import com.example.recognition.model.room.entity.Model;
import com.example.recognition.model.room.entity.Response;
@Database(entities = {Model.class, Response.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract ModelsDao modelsDao();
    public abstract ResponseDao responseDao();
}
