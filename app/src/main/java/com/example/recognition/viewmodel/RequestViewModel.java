package com.example.recognition.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.recognition.model.Repository;

import java.util.List;

public class RequestViewModel extends ViewModel {
    private Repository repository;
    private MediatorLiveData<List<String>> modelList = new MediatorLiveData<>();
    private MutableLiveData<Integer> modelCode = new MutableLiveData<>();
    private MutableLiveData<String> imageUri = new MutableLiveData<>();

    public RequestViewModel(Repository repository) {
        this.repository = repository;
        modelList.addSource(repository.getModelList(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                modelList.setValue(strings);
            }
        });
    }
    public LiveData<List<String>> getModelsList() {
        return modelList;
    }
    public void setModelCode(int modelCode) {
         this.modelCode.setValue(modelCode);
    }
    public LiveData<Integer> getModelCode() {
        return modelCode;
    }
    public void setImageUri(String imageUri) {
        this.imageUri.setValue(imageUri);
    }
    public LiveData<String> getImageUri() {
        return imageUri;
    }
}
