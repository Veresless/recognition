package com.example.recognition.interfaces;

import com.example.recognition.types.Data;
import com.example.recognition.types.Event;

public interface IModelAdapter {
     String[] getModels();
     void sendRequest(String image, int modelCode);
     Event<Data> getData();
}
