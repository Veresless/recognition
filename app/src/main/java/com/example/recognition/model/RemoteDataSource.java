package com.example.recognition.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;;
import com.example.recognition.model.retrofit2.APIService;
import com.example.recognition.model.retrofit2.Request;
import com.example.recognition.model.retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private static final String BASE_URL = "https://api.clarifai.com/";
    private String apiKey;
    private APIService service;
    private Context context;
    public RemoteDataSource(Context context, String apiKey) {
        this.context = context;
        this.apiKey = apiKey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);
    }
    public retrofit2.Response<Response> fetchData(String uri, String model) throws IOException {
        String path = getRealPathFromURI(context, Uri.parse(uri));
        Call<Response> response = service.sendRequest(apiKey, model, new Request(
                new ArrayList<Request.Inputs>(
                        Arrays.asList(
                                new Request.Inputs(
                                        new Request.Inputs.Data(
                                                new Request.Inputs.Data.Image(
                                                        "base64" + path
                                                )
                                        )
                                )
                        )
                )
        ));
        return  response.execute();
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
/*
    JsonChannel channel = ClarifaiChannel.INSTANCE.getJsonChannel();
    V2Grpc.V2BlockingStub stub = V2Grpc.newBlockingStub(channel)
            .withCallCredentials(
                    new ClarifaiCallCredentials(apiKey)
            );
    private String makeRequest(String path, String model) throws IOException {
        PostWorkflowResultsResponse postWorkflowResultsResponse = stub.postWorkflowResults(
                PostWorkflowResultsRequest.newBuilder()
                        .setWorkflowId(model)
                        .addInputs(
                                Input.newBuilder()
                                        .setData(com.clarifai.grpc.api.Data.newBuilder()
                                                .setImage(Image.newBuilder()
                                                        .setBase64(
                                                                ByteString
                                                                        .copyFrom(
                                                                                Files.readAllBytes(
                                                                                        new File(path).toPath()
                                                                                )
                                                                        )
                                                        )
                                                )
                                        )
                        )
                        .build()
        );
        if (postWorkflowResultsResponse.getStatus().getCode() != StatusCode.SUCCESS)
        {
            return null;
        } else {
        List<com.clarifai.grpc.api.Data> data = new ArrayList<>();
        for (WorkflowResult result: postWorkflowResultsResponse.getResultsList()) {
        for (Output output: result.getOutputsList()) {
        if (output.getData().isInitialized()) {
        for (Region region : output.getData().getRegionsList()) {
        com.clarifai.grpc.api.Data.Frame frame = null;
        BoundingBox box = region.getRegionInfo().getBoundingBox();
        if (box.isInitialized()) {
        frame = new com.clarifai.grpc.api.Data.Frame(box.getTopRow(), box.getLeftCol(), box.getBottomRow(), box.getRightCol());
        }
        List<com.clarifai.grpc.api.Data.Field> concepts = new ArrayList<>();
        for (Concept concept : region.getData().getConceptsList()) {
        concepts.add(new com.clarifai.grpc.api.Data.Field(concept.getName(), concept.getValue()));
        }
        data.add(new com.clarifai.grpc.api.Data(concepts, frame));
        }
        } else {
        List<com.clarifai.grpc.api.Data.Field> concepts = new ArrayList<>();
        for (Concept concept : output.getData().getConceptsList()) {
        concepts.add(new com.clarifai.grpc.api.Data.Field(concept.getName(), concept.getValue()));
        }
        data.add(new com.clarifai.grpc.api.Data(concepts));
        }
        }
        }
        return (com.clarifai.grpc.api.Data[]) data.toArray();
 */