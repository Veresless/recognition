package com.example.recognition.model.localdata.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.recognition.model.localdata.room.ResponseConverter;
import com.example.recognition.types.Response;

import java.util.List;

@Dao
@TypeConverters({ResponseConverter.class})
public interface ResponseDao {
    @Query("SELECT * FROM response WHERE 1 = is_favorite")
    LiveData<List<Response>> getFavorites();
    @Query("SELECT * FROM response WHERE 0 = is_favorite")
    LiveData<Response> getLastResponse();
    @Insert
    void addResponse(com.example.recognition.model.localdata.room.entity.Response response);
    @Update
    void updateResponse(com.example.recognition.model.localdata.room.entity.Response response);
    @Delete
    void removeResponse(com.example.recognition.model.localdata.room.entity.Response response);
}
