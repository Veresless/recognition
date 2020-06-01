package com.example.recognition.aplication;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.recognition.model.LocalDataSorce;
import com.example.recognition.model.RemouteDataSorce;
import com.example.recognition.model.Repository;
import com.example.recognition.viewmodel.ViewModelFactory;

public class App extends Application {
    private Repository repository;
    private ViewModelProvider.Factory factory;
    @Override
    public void onCreate() {
        super.onCreate();
        repository = new Repository(new LocalDataSorce(), new RemouteDataSorce());
        factory = new ViewModelFactory(repository);
    }
    ViewModelProvider.Factory getViewModeFactory(){
        return factory;
    }
}
