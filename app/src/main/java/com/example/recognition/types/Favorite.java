package com.example.recognition.types;

public class Favorite {
    private String mImageUri;
    private Data[] mData;
    private String mModel;
    public Favorite() {

    }
    public Favorite(String imageUri, Data[] data, String model) {
        mImageUri = imageUri;
        mData = data;
        mModel = model;
    }
    public String getImage() {
        return mImageUri;
    }
    public void setImage(String image) {
        mImageUri = image;
    }
    public Data[] getRequestData() {
        return mData;
    }
    public void setRequestData(Data[] data) {
        mData = data;
    }
    public String getModel() {
        return mModel;
    }
    public void setModel(String model) {
        mModel = model;
    }
}
