package com.example.recognition.types;

public class GeneralResponse {
    protected String imageUri;
    protected GeneralData[] data;
    protected String model;
    public String getImage() {
        return imageUri;
    }
    public void setImage(String image) {
        imageUri = image;
    }
    public GeneralData[] getData() {
        return data;
    }
    public void setData(GeneralData[] data) {
        this.data = data;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
