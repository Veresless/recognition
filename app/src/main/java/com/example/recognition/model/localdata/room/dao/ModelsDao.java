package com.example.recognition.model.localdata.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recognition.model.localdata.room.entity.Model;

import java.util.List;

@Dao
public interface ModelsDao {
    @Query("SELECT model FROM model")
    LiveData<List<String>> getModels();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addModel(Model model);
    @Insert
    void addModels(List<Model> models);
    @Delete
    void removeModel(Model model);
}
