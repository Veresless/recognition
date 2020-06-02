package com.example.recognition.types;

public class Response {
    protected String imageUri;
    protected BaseData[] data;
    protected String model;
    public String getImage() {
        return imageUri;
    }
    public void setImage(String image) {
        imageUri = image;
    }
    public BaseData[] getData() {
        return data;
    }
    public void setData(BaseData[] data) {
        this.data = data;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
