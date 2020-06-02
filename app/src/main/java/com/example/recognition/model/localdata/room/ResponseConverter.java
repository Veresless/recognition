package com.example.recognition.model.localdata.room;

import androidx.room.TypeConverter;

import com.example.recognition.types.DemographicsData;
import com.example.recognition.types.GeneralData;
import com.example.recognition.types.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseConverter {
    @TypeConverter
    public com.example.recognition.model.localdata.room.entity.Response toResponse(Response response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String data = response.getData();
        com.example.recognition.model.localdata.room.entity.Response entityResponse =  new com.example.recognition.model.localdata.room.entity.Response();
        entityResponse.setImageUri(response.getImage());
        entityResponse.setData(data);
        entityResponse.setModel(response.getModel());
        entityResponse.setFavorite(false);
        return entityResponse;
    }
    @TypeConverter
    public Response fromResponse (com.example.recognition.model.localdata.room.entity.Response response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Response generalResponse = new Response();
        generalResponse.setImage(response.getImageUri());
        generalResponse.setData(response.getData());
        generalResponse.setModel(response.getModel());
        return generalResponse;
    }
}
