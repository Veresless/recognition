package com.example.recognition.adapters;

import com.example.recognition.components.SharedPreferencesHelper;
import com.example.recognition.interfaces.ISaverAdapter;
import com.example.recognition.interfaces.IParserAdapter;
import com.example.recognition.types.Favorite;
import com.example.recognition.types.Options;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class SaverAdapter implements ISaverAdapter {
    private SharedPreferencesHelper mSaver;
    public SaverAdapter(SharedPreferencesHelper saver) {
        mSaver = saver;
    }

    @Override
    public Favorite[] getFavorites() {
        return new Favorite[0];
    }

    @Override
    public void setFavorites(Favorite[] favorites) {

    }

    @Override
    public Options getOptions() {
        return null;
    }

    @Override
    public void setOptions(Options options) {

    }
}
