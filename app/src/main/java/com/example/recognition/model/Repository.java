package com.example.recognition.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recognition.model.retrofit2.Response;
import com.example.recognition.types.Data;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private Executor executorIO = Executors.newSingleThreadExecutor();
    private MutableLiveData<Boolean> loadStatus = new MutableLiveData<>();
    private LocalDataSorce localDataSorce;
    private RemoteDataSource remoteDataSource;
    public Repository(LocalDataSorce localDataSorce, RemoteDataSource remoteDataSource) {
        this.localDataSorce = localDataSorce;
        this.remoteDataSource = remoteDataSource;
    }
    public LiveData<List<String>> getModelList() {
        return null;
    }
    public LiveData<List<Data>> getModelResponse(final String uri, final String model) {
        executorIO.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    retrofit2.Response<Response> response = remoteDataSource.fetchData(uri, model);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return null;
    }
    public LiveData<Boolean> getLoadStatus() {
        return loadStatus;
    }
}
