package com.example.recognition.model.localdata.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.recognition.model.localdata.room.ResponseConverter;
import com.example.recognition.types.GeneralResponse;

import java.util.List;

@Dao
@TypeConverters({ResponseConverter.class})
public interface GeneralResponseDao {
    @Query("SELECT * FROM GeneralResponse")
    LiveData<List<GeneralResponse>> getFavorites();
    @Query("SELECT * FROM GeneralResponse WHERE image = :image")
    LiveData<GeneralResponse> getFavorite(String image);
    @Insert
    void addResponse(GeneralResponse generalResponse);
    @Delete
    void removeResponse(GeneralResponse generalResponse);
}
