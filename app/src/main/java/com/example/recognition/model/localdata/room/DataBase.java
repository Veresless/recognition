package com.example.recognition.model.localdata.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.recognition.model.localdata.room.dao.ModelsDao;
import com.example.recognition.model.localdata.room.dao.ResponseDao;
import com.example.recognition.model.localdata.room.entity.Model;
import com.example.recognition.model.localdata.room.entity.GeneralResponse;
@Database(entities = {Model.class, GeneralResponse.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract ModelsDao modelsDao();
    public abstract ResponseDao responseDao();
}
