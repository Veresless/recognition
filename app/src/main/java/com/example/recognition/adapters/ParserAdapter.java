package com.example.recognition.adapters;

import com.example.recognition.components.Parser;
import com.example.recognition.interfaces.IParserAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class ParserAdapter implements IParserAdapter {
    private Parser mParser;
    public ParserAdapter(Parser parser) {
        mParser = parser;
    }
    @Override
    public <T> T toObject(String json, Class<T> clazz) throws IOException {
        return mParser.toObject(json, clazz);
    }
    @Override
    public <T> String toString(T data) throws JsonProcessingException {
        return mParser.toString(data);
    }
}
