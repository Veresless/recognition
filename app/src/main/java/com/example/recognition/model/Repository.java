package com.example.recognition.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recognition.model.clarify.GeneralResponse;
import com.example.recognition.types.GeneralData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    private Executor executorIO = Executors.newSingleThreadExecutor();
    private MutableLiveData<Boolean> loadStatus = new MutableLiveData<>();
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;
    public Repository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }
    public LiveData<List<String>> getModelList() {
        return null;
    }
    public LiveData<List<GeneralData>> getModelResponse(final String uri, final String model) {
        executorIO.execute(new Runnable() {
            @Override
            public void run() {
                try {
                            retrofit2.Response<GeneralResponse> response = remoteDataSource.fetchGeneralData(uri);
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
