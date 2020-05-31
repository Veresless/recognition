package com.example.recognition.components;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recognition.interfaces.IViewAdapter;

public class DataController {
    private static DataController mInstance = null;
    private MutableLiveData<IViewAdapter> mAdapter;
    private DataController() {

    }
    public static DataController getInstance() {
        if (null == mInstance) {
            mInstance = new DataController();
        }
        return mInstance;
    }
    public LiveData<IViewAdapter> getAdapterLiveData() {
        return mAdapter;
    }
    public void setAdapter(IViewAdapter adapter) {
        mAdapter.setValue(adapter);
    }
}
