package com.example.recognition.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;;
import com.example.recognition.model.retrofit2.APIService;
import com.example.recognition.model.retrofit2.Request;
import com.example.recognition.model.retrofit2.GeneralResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    public enum Model {
        GENERAL,
        DEMOGRAPHICS,
        COLOR
    }
    private final String prefix = "$(base64 ";
    private final String postfix = ")";
    private String apiKey;
    private APIService service;
    private Context context;
    public RemoteDataSource(Context context, String apiKey) {
        this.context = context;
        this.apiKey = apiKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);
    }
    public retrofit2.Response<GeneralResponse> fetchGeneralData(String uri) throws IOException {
        String path = getRealPathFromURI(context, Uri.parse(uri));
        Call<GeneralResponse> response = service.sendRequest(apiKey, getModelName(Model.GENERAL), new Request(
                new ArrayList<Request.Inputs>(
                        Arrays.asList(
                                new Request.Inputs(
                                        new Request.Inputs.Data(
                                                new Request.Inputs.Data.Image(
                                                        prefix  + path + postfix
                                                )
                                        )
                                )
                        )
                )
        ));
        return  response.execute();
    }
    public retrofit2.Response<GeneralResponse> fetchDemographicData(String uri) throws IOException {
        String path = getRealPathFromURI(context, Uri.parse(uri));
        Request request = new Request(
                new ArrayList<Request.Inputs>(
                        Arrays.asList(
                                new Request.Inputs(
                                        new Request.Inputs.Data(
                                                new Request.Inputs.Data.Image(
                                                        prefix  + path + postfix
                                                )
                                        )
                                )
                        )
                )
        );
        Call<GeneralResponse> response = service.sendRequest(apiKey, getModelName(Model.DEMOGRAPHICS), request);
        return  response.execute();
    }
    public List<String> getModels() {
        List<String> listModels = new ArrayList<>();
        Model[] models = Model.values();
        for (Model model : models) {
            listModels.add(model.name().substring(0, 1) + model.name().toLowerCase().substring(1));
        }
        return listModels;
    }
    private String getModelName(Model model) {
        return model.name().substring(0, 1) + model.name().toLowerCase().substring(1);
    }
    private String getRealPathFromURI(Context context, Uri uri) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);
        String id = wholeID.split(":")[1];
        String[] column = {
                MediaStore.Images.Media.DATA
        };
        String sel = MediaStore.Images.Media._ID + "=?";
        Cursor cursor = context.getContentResolver()
                .query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column,
                        sel,
                        new String[]{
                                id
                        },
                        null
                );
        int columnIndex = cursor.getColumnIndex(column[0]);
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }
}