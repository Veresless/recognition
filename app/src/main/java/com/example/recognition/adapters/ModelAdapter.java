package com.example.recognition.adapters;

import com.example.recognition.types.Action;
import com.example.recognition.components.ClarifaiRequestManager;
import com.example.recognition.types.Data;
import com.example.recognition.types.Event;
import com.example.recognition.interfaces.IModelAdapter;

public class ModelAdapter implements IModelAdapter {
    private Event<Data> mRequestEvent;
    private ClarifaiRequestManager mModel;
    public ModelAdapter(ClarifaiRequestManager model) {
        mRequestEvent = new Event<>();
        mModel = model;
        mModel.getRequest().addListener(new Action<Data>() {
            @Override
            public void onEventFired(Data... args) {
                mRequestEvent.fire(args);
            }
        });
    }
    @Override
    public String[] getModels() {
        return mModel.getModels();
    }
    @Override
    public void sendRequest(String image, int modelCode){
        mModel.makeRequest(image, modelCode);
    }
    @Override
    public Event<Data> getData() {
        return mRequestEvent;
    }
}
