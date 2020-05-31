package com.example.recognition.adapters;

import com.example.recognition.components.Recognition;
import com.example.recognition.types.Favorite;
import com.example.recognition.types.Options;
import com.example.recognition.types.Data;
import com.example.recognition.types.Event;
import com.example.recognition.interfaces.IViewAdapter;
import java.io.Serializable;
import java.util.List;

public class ViewAdapter implements IViewAdapter, Serializable {
    private Recognition mRecognition;
    private Event<Data> mGetRequestEvent;
    private Event<String> mInfoEvent;
    public ViewAdapter(Recognition recognition) {
        mRecognition = recognition;
        mGetRequestEvent = new Event<>();
        mInfoEvent = new Event<>();
    }
    @Override
    public String[] getModels() {
        return mRecognition.getModels();
    }
    @Override
    public void startRequest(String imageUri, int modelCode) {
        mRecognition.startRequest(imageUri, modelCode);
    }
    @Override
    public void stopRequest() {
        mRecognition.stopRequest();
    }
    @Override
    public Event<Data> getRequestEvent() {
        return mGetRequestEvent;
    }
    @Override
    public Data[] getLastRequest() {
        return mRecognition.getLastRequest();
    }
    @Override
    public void addToFavorites() {
        mRecognition.addToFavorites();
    }
    @Override
    public void removeFromFavorite(int index) {
        mRecognition.removeFromFavorites(index);
    }
    @Override
    public List<String> getFavoriteImages() {
        return mRecognition.getFavoritesImages();
    }
    @Override
    public Favorite getFavoriteData(int index) {
        return mRecognition.getFavoriteData(index);
    }
    @Override
    public Options getOptions() {
        return mRecognition.getOptions();
    }
    @Override
    public void setOptions(Options options) {
        mRecognition.setOptions(options);
    }
    @Override
    public Event<String> getInfoEvent() {
        return mInfoEvent;
    }
}
