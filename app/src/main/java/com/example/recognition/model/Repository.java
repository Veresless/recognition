package com.example.recognition.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private Executor executorIO = Executors.newSingleThreadExecutor();
    private MutableLiveData<Boolean> loadStatus = new MutableLiveData<>();
    private LocalDataSorce localDataSorce;
    private RemouteDataSorce remouteDataSorce;
    public Repository(LocalDataSorce localDataSorce, RemouteDataSorce remouteDataSorce) {
        this.localDataSorce = localDataSorce;
        this.remouteDataSorce = remouteDataSorce;
    }
    public LiveData<List<String>> getModelList() {
        return null;
    }
    public LiveData<Boolean> getLoadStatus() {
        return loadStatus;
    }
}
