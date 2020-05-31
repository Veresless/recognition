package com.example.recognition.components;

import com.example.recognition.interfaces.ISaverAdapter;
import com.example.recognition.types.Action;
import com.example.recognition.types.Favorite;
import com.example.recognition.types.Options;
import com.example.recognition.types.Data;
import com.example.recognition.interfaces.IModelAdapter;
import com.example.recognition.interfaces.IViewAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recognition {
    private IModelAdapter mModel;
    private IViewAdapter mView;
    private ISaverAdapter mSaver;

    private Data[] mLastData;
    private String mLastImageUri;
    private int mLastModelCode;
    private String[] mModelArray;
    private List<Favorite> mFavorites;
    private Options mOptions;

    private boolean mIsRequestDone;

    public Recognition(IModelAdapter model, IViewAdapter view, ISaverAdapter saver) {
        mModel = model;
        mView = view;
        mSaver = saver;
        mLastData = null;
        mLastModelCode = -1;
        mLastImageUri = null;
        mModelArray = mModel.getModels();
        mModel.getData().addListener(new Action<Data>() {
            @Override
            public void onEventFired(Data... args) {
                getRequest(args);
            }
        });
        mFavorites = Arrays.asList(mSaver.getFavorites());
        mOptions = mSaver.getOptions();
        mIsRequestDone = true;
    }
    private void getRequest(Data[] data) {
        if (null != data) {
            mLastData = data;
            if (!mIsRequestDone) {
                mView.getRequestEvent().fire(data);
            }
        } else {
            mView.getInfoEvent().fire("Request Failed: Check Internet connection");
        }
        mIsRequestDone = true;

    }
    public String[] getModels() {
        return mModelArray;
    }
    public void startRequest(String imageUri, int modelCode) {
        if (modelCode >= 0 && modelCode < mModelArray.length) {
            if (mLastImageUri == imageUri && mLastModelCode == modelCode && mIsRequestDone) {
                getRequest(mLastData);
            } else {
                mLastImageUri = imageUri;
                mLastModelCode = modelCode;
                mModel.sendRequest(imageUri, modelCode);
                mIsRequestDone = false;
            }
        } else {
            mView.getInfoEvent().fire("Request Error: incorrect request model");
        }
    }
    public void stopRequest() {
        mIsRequestDone = true;
    }
    public Data[] getLastRequest() {
        return mLastData;
    }
    public void addToFavorites() {
        boolean isExist = false;
        final int length = mFavorites.size();
        for (Favorite favorite: mFavorites) {
            if (favorite.getImage() == mLastImageUri
                    && favorite.getRequestData() == mLastData
                    && favorite.getModel() == mModelArray[mLastModelCode]) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            mFavorites.add(new Favorite(mLastImageUri, mLastData, mModelArray[mLastModelCode]));
        }
    }
    public void removeFromFavorites(int index) {
        mFavorites.remove(index);
    }
    public List<String> getFavoritesImages() {
        List<String> imagesUri = new ArrayList<>();
        for (Favorite favorite: mFavorites) {
            imagesUri.add(favorite.getImage());
        }
        return  imagesUri;
    }
    public Favorite getFavoriteData(int index) {
        return mFavorites.get(index);
    }
    public Options getOptions() {
        return mOptions;
    }
    public void setOptions(Options options) {
        mOptions = options;
    }
    public void Save() {
        mSaver.setFavorites((Favorite[]) mFavorites.toArray());
        mSaver.setOptions(mOptions);
    }
}
