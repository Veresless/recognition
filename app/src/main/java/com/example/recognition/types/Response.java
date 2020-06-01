package com.example.recognition.types;

import androidx.room.Embedded;

public class Response {
    protected String imageUri;
    protected Data[] data;
    protected String model;
    public Response() {

    }
    public Response(String imageUri, Data[] data, String model) {
        this.imageUri = imageUri;
        this.data = data;
        this.model = model;
    }
    public String getImage() {
        return imageUri;
    }
    public void setImage(String image) {
        imageUri = image;
    }
    public Data[] getRequestData() {
        return data;
    }
    public void setRequestData(Data[] data) {
        this.data = data;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
