package com.example.recognition.model.localdata.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.recognition.model.localdata.room.ResponseConverter;
import com.example.recognition.types.Response;

import java.util.List;

@Dao
@TypeConverters({ResponseConverter.class})
public interface ResponseDao {
    @Query("SELECT * FROM GeneralResponse")
    LiveData<List<Response>> getFavorites();
    @Query("SELECT * FROM GeneralResponse WHERE image = :image")
    LiveData<Response> getFavorite(String image);
    @Insert
    void addResponse(Response response);
    @Delete
    void removeResponse(Response response);
}
