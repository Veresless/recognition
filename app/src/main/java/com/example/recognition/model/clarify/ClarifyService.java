package com.example.recognition.model.clarify;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClarifyService {
    public static final String BASE_URL = "https://api.clarifai.com/";
    @Headers({"authorization: Key {apyKey}",
            "content-type: application/json"
    })
    @POST("v2/workflows/{workflowId}/results")
    <T extends BaseResponse> Call<T> sendRequest(@Path("apyKey") String apiKey, @Path("workflowId") String model, @Body Request request);
}
