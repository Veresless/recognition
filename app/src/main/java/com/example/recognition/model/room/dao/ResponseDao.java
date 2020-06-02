package com.example.recognition.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.recognition.model.room.ResponseConverter;
import com.example.recognition.model.room.entity.Response;
import com.example.recognition.types.GeneralResponse;

import java.util.List;

@Dao
@TypeConverters({ResponseConverter.class})
public interface ResponseDao {
    @Query("SELECT * FROM response WHERE 1 = is_favorite")
    LiveData<List<GeneralResponse>> getFavorites();
    @Query("SELECT * FROM response WHERE 0 = is_favorite")
    LiveData<GeneralResponse> getLastResponse();
    @Insert
    void addResponse(Response response);
    @Update
    void updateResponse(Response response);
    @Delete
    void removeResponse(Response response);
}
