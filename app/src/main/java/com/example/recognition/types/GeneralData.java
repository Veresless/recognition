package com.example.recognition.types;

public class GeneralData {
    public static class Field {
        private String name;
        private double value;
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
    private Field[] fields;
    public Field[] getFields() {
        return fields;
    }
    public void setFields(Field[] fields) {
        this.fields = fields;
    }
}
