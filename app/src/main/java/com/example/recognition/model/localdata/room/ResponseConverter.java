package com.example.recognition.model.localdata.room;

import androidx.room.TypeConverter;

import com.example.recognition.model.localdata.room.entity.GeneralResponse;
import com.example.recognition.types.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseConverter {
    @TypeConverter
    public GeneralResponse toResponse(Response response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String data = response.getData();
        GeneralResponse entityGeneralResponse =  new GeneralResponse();
        entityGeneralResponse.setImage(response.getImage());
        entityGeneralResponse.setData(data);
        entityGeneralResponse.setModel(response.getModel());
        entityGeneralResponse.setFavorite(false);
        return entityGeneralResponse;
    }
    @TypeConverter
    public Response fromResponse (GeneralResponse response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Response generalResponse = new Response();
        generalResponse.setImage(response.getImage());
        generalResponse.setData(response.getData());
        generalResponse.setModel(response.getModel());
        return generalResponse;
    }
}
