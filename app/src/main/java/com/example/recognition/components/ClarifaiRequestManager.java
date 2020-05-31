package com.example.recognition.components;

import com.example.recognition.types.Data;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import com.clarifai.channel.ClarifaiChannel;
import io.grpc.Channel;

import com.clarifai.credentials.ClarifaiCallCredentials;
import com.clarifai.grpc.api.*;
import com.clarifai.grpc.api.status.StatusCode;
import com.example.recognition.types.Event;
import com.google.protobuf.ByteString;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClarifaiRequestManager {
    final static private String[] mModels = {"Demographics"};
    private V2Grpc.V2BlockingStub stub;
    private Event<Data> mRequestEvent;
    private ContentResolver mContext;

    public ClarifaiRequestManager(String apiKey, ContentResolver context) {
        init(apiKey, context);
    }
    private void init(String apiKey, ContentResolver context) {
        Channel channel = ClarifaiChannel.INSTANCE.getJsonChannel();
        stub = V2Grpc.newBlockingStub(channel)
                .withCallCredentials(
                        new ClarifaiCallCredentials(apiKey)
                );
        mRequestEvent = new Event<>();
        mContext = context;
    }
    public void makeRequest(String imageUri, int index){
        new Request(mContext, imageUri, mModels[index]).execute();
    }
    public Event<Data> getRequest() {
        return mRequestEvent;
    }
    public final String[] getModels() {
        return mModels;
    }
    private Data[] asyncRequest (ContentResolver content, String imageUrl, String model) throws Exception{
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext , Uri.parse(imageUrl));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, outputStream);
        byte [] image = outputStream.toByteArray();
        PostWorkflowResultsResponse postWorkflowResultsResponse = stub.postWorkflowResults(
        PostWorkflowResultsRequest.newBuilder()
                .setWorkflowId(model)
                .addInputs(
                        Input.newBuilder()
                                .setData(com.clarifai.grpc.api.Data.newBuilder()
                                        .setImage(Image.newBuilder()
                                                    .setBase64(
                                                            ByteString
                                                                    .copyFrom(image)
                                                    )
                                        )
                                )
                )
                .build()
        );
        if (postWorkflowResultsResponse.getStatus().getCode() != StatusCode.SUCCESS)
        {
            return null;
        }
        else {
            List<Data> data = new ArrayList<>();
            for (WorkflowResult result: postWorkflowResultsResponse.getResultsList()) {
                for (Output output: result.getOutputsList()) {
                    if (output.getData().isInitialized()) {
                        for (Region region : output.getData().getRegionsList()) {
                            Data.Frame frame = null;
                            BoundingBox box = region.getRegionInfo().getBoundingBox();
                            if (box.isInitialized()) {
                                frame = new Data.Frame(box.getTopRow(), box.getLeftCol(), box.getBottomRow(), box.getRightCol());
                            }
                            List<Data.Field> concepts = new ArrayList<>();
                            for (Concept concept : region.getData().getConceptsList()) {
                                concepts.add(new Data.Field(concept.getName(), concept.getValue()));
                            }
                            data.add(new Data(concepts, frame));
                        }
                    } else {
                        List<Data.Field> concepts = new ArrayList<>();
                        for (Concept concept : output.getData().getConceptsList()) {
                            concepts.add(new Data.Field(concept.getName(), concept.getValue()));
                        }
                        data.add(new Data(concepts));
                    }
                }
            }
            return (Data[]) data.toArray();
        }
    }
    private class Request extends AsyncTask<Void, Void, Data[]> {
        private String mModel;
        private String mImageUrl;
        private ContentResolver content;
        Request(ContentResolver content, String imageUrl, String modelCode) {
            mModel = modelCode;
            mImageUrl = imageUrl;
            mContext = content;
        }
        @Override
        protected Data[] doInBackground(Void... voids) {
            try {
                return asyncRequest (mContext, mImageUrl,  mModel);
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(Data[] data) {
            super.onPostExecute(data);
            mRequestEvent.fire(data);
        }
    }
}
