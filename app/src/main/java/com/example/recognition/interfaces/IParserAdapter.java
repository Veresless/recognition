package com.example.recognition.interfaces;

import com.example.recognition.types.Data;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface IParserAdapter {
    <T> T toObject(String json, Class<T> clazz) throws IOException;
    <T> String toString(T data) throws JsonProcessingException;
}
