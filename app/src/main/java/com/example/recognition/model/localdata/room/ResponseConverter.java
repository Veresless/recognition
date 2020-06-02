package com.example.recognition.model.localdata.room;

import androidx.room.TypeConverter;

import com.example.recognition.types.GeneralResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseConverter {
    @TypeConverter
    public com.example.recognition.model.localdata.room.entity.GeneralResponse toResponse(GeneralResponse generalResponse) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String data = generalResponse.getData();
        com.example.recognition.model.localdata.room.entity.GeneralResponse entityGeneralGeneralResponse =  new com.example.recognition.model.localdata.room.entity.GeneralResponse();
        entityGeneralGeneralResponse.setImage(generalResponse.getImage());
        entityGeneralGeneralResponse.setData(data);
        entityGeneralGeneralResponse.setModel(generalResponse.getModel());
        entityGeneralGeneralResponse.setFavorite(false);
        return entityGeneralGeneralResponse;
    }
    @TypeConverter
    public GeneralResponse fromResponse (com.example.recognition.model.localdata.room.entity.GeneralResponse response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setImage(response.getImage());
        generalResponse.setData(response.getData());
        generalResponse.setModel(response.getModel());
        return generalResponse;
    }
}
