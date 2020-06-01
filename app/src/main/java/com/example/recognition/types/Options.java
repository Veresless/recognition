package com.example.recognition.types;

public class Options {
    protected int threshold;
    Options(int threshold) {
        this.threshold = threshold;
    }
    Options() {
        threshold = 0;
    }
    int getThreshold() {
        return threshold;
    }
    void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
