package com.example.recognition.model;

import com.example.recognition.model.clarify.BaseResponse;
import com.example.recognition.types.Response;

public class ResponseConverter {
    public static <T extends BaseResponse> Response getResponse(retrofit2.Response<T> response, String model) {
        switch (model) {
            case "General" :

                break;
            case "Demographics":

                break;
            case "Color":

                break;
            default:

                break;
        }
        return null;
    }
}
