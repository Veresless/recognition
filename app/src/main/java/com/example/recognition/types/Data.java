package com.example.recognition.types;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static class Frame {
        protected double top;
        protected double left;
        protected double bottom;
        protected double right;
        public Frame() {

        }
        public Frame(double top, double left, double bottom, double right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
        public double getTop() {
            return top;
        }
        public void setTop(double top) {
            this.top = top;
        }
        public double getLeft() {
            return left;
        }
        public void setLeft(double left) {
            this.left = left;
        }
        public double getBottom() {
            return bottom;
        }
        public void setBottom(double bottom) {
            this.bottom = bottom;
        }
        public double getRight() {
            return right;
        }
        public void setRight(double right) {
            this.right = right;
        }
    }
    protected Frame frame;
    public Frame getFrame() {
        return frame;
    }
    public void setFrame(Frame frame) {
        this.frame = frame;
    }
    public static class Field {
        protected String name;
        protected double value;
        public Field(String name, double value) {
            this.name = name;
            this.value = value;
        }
        public String getName() {
            return name;
        }
        public double getValue() {
            return value;
        }
    }
    protected Field[] fields;
    public Field[] getFields() {
        return fields;
    }
    public void setFields(Field[] fields) {
        this.fields = fields;
    }
    public Data() {
        fields = null;
        frame = null;
    }
    public Data(Frame frame) {
        fields = null;
        this.frame = frame;
    }
    public Data(Field[] fields) {
        this.fields = fields;
        frame = null;
    }
    public Data(Field[] fields, Frame frame) {
        this.fields = fields;
        this.frame = frame;
    }
}
