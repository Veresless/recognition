package com.example.recognition.interfaces;

import com.example.recognition.types.Favorite;
import com.example.recognition.types.Options;
import com.example.recognition.types.Data;
import com.example.recognition.types.Event;

import java.util.List;

public interface IViewAdapter {
    String[] getModels();
    void startRequest(String imageUri, int modelCode);
    void stopRequest();
    Event<Data> getRequestEvent();
    Data[] getLastRequest();
    void addToFavorites();
    void removeFromFavorite(int index);
    List<String> getFavoriteImages();
    Favorite getFavoriteData(int index);
    Options getOptions();
    void setOptions(Options options);
    Event<String> getInfoEvent();
}
