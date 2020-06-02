package com.example.recognition.model.room;

import androidx.room.TypeConverter;

import com.example.recognition.model.room.entity.Response;
import com.example.recognition.types.GeneralData;
import com.example.recognition.types.GeneralResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseConverter {
    @TypeConverter
    public Response toResponse(GeneralResponse response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String data = gson.toJson(response.getData());
        Response entityResponse =  new Response();
        entityResponse.setImageUri(response.getImage());
        entityResponse.setData(data);
        entityResponse.setModel(response.getModel());
        entityResponse.setFavorite(false);
        return entityResponse;
    }
    public GeneralResponse fromResponse (Response response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        GeneralResponse generalResponse = new GeneralResponse();
        GeneralData[] data = gson.fromJson(response.getData(), GeneralData[].class);
        generalResponse.setImage(response.getImageUri());
        generalResponse.setData(data);
        generalResponse.setModel(response.getModel());
        return generalResponse;
    }
}
