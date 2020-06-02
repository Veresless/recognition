package com.example.recognition.model;

import com.example.recognition.model.remoutdata.BaseResponse;
import com.example.recognition.types.GeneralResponse;

public class ResponseConverter {
    public static <T extends BaseResponse> GeneralResponse getResponse(retrofit2.Response<T> response, String model) {
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
