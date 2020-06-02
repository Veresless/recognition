package com.example.recognition.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recognition.model.remoutdata.ColorResponse;
import com.example.recognition.model.remoutdata.DemographicsResponse;
import com.example.recognition.model.remoutdata.GeneralResponse;
import com.example.recognition.types.Response;

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
        localDataSource.setModels(remoteDataSource.getModels());
        return localDataSource.getModels();
    }
    public LiveData<Response> getModelResponse(final String uri, final String model) {
        executorIO.execute(new Runnable() {
            @Override
            public void run() {
                localDataSource.removeLastFromLocalData();
                try {
                    switch (RemoteDataSource.Model.valueOf(model.toUpperCase())){
                        case GENERAL:
                            localDataSource.addResponse(
                                ResponseConverter.getResponse(
                                        remoteDataSource.<GeneralResponse>fetchData(uri, model),
                                        model
                                ));
                            break;
                        case DEMOGRAPHICS:
                            localDataSource.addResponse(
                                    ResponseConverter.getResponse(
                                            remoteDataSource.<DemographicsResponse>fetchData(uri, model),
                                            model
                                    ));
                            break;
                        case COLOR:
                            localDataSource.addResponse(
                                    ResponseConverter.getResponse(
                                            remoteDataSource.<ColorResponse>fetchData(uri, model),
                                            model
                                    ));
                            break;
                        default: break;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return localDataSource.getLastResponse();
    }
    public LiveData<List<Response>> getFavorites() {
        return localDataSource.getFavorites();
    }
    public void makeLastResponseFavorite() {
        executorIO.execute(new Runnable() {
            @Override
            public void run() {
                localDataSource.addLastToFavorite();
            }
        });
    }

    public LiveData<Boolean> getLoadStatus() {
        return loadStatus;
    }
}
