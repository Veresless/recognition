package com.example.recognition.types;

public class Options {
    private int mThresholdValue;
    Options(int thresholdValue) {
        mThresholdValue = thresholdValue;
    }
    Options() {
        mThresholdValue = 0;
    }
    int getThresholdValue() {
        return mThresholdValue;
    }
    void setThresholdValue(int thresholdValue) {
        mThresholdValue = thresholdValue;
    }
}
