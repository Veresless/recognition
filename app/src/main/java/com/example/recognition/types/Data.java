package com.example.recognition.types;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static class Frame {
        private double mTop;
        private double mLeft;
        private double mBottom;
        private double mRight;
        public Frame() {

        }
        public Frame(double top, double left, double bottom, double right) {
            mTop = top;
            mLeft = left;
            mBottom = bottom;
            mRight = right;
        }
        public double getTop() {
            return mTop;
        }
        public void setTop(double top) {
            mTop = top;
        }
        public double getLeft() {
            return mLeft;
        }
        public void setLeft(double left) {
            mLeft = left;
        }
        public double getBottom() {
            return mBottom;
        }
        public void setBottom(double bottom) {
            mBottom = bottom;
        }
        public double getRight() {
            return mRight;
        }
        public void setRight(double right) {
            mRight = right;
        }
    }
    private Frame mFrame;
    public Frame getCrop() {
        return mFrame;
    }
    public void setCrop(Frame frame) {
        mFrame = frame;
    }
    public static class Field {
        private String mName;
        private double mValue;
        public Field(String name, double value) {
            mName = name;
            mValue = value;
        }
        public String getName() {
            return  mName;
        }
        public double getValue() {
            return mValue;
        }
    }
    private List<Field> mFields;
    public void addConcept(Field field) {
        mFields.add(field);
    }
    public void removeConcept(Field field) {
        mFields.remove(field);
    }
    public List<Field> getConcepts() {
        return mFields;
    }
    public Data() {
        mFields = new ArrayList<>();
        mFrame = null;
    }
    public Data(Frame frame) {
        mFields = new ArrayList<>();
        mFrame = frame;
    }
    public Data(List<Field> fields) {
        mFields = fields;
        mFrame = null;
    }
    public Data(List<Field> fields, Frame frame) {
        mFields = fields;
        mFrame = frame;
    }
}
