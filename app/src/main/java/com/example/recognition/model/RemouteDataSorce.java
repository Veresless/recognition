package com.example.recognition.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

public class RemouteDataSorce {
    private String getRealPathFromURI(Context context, String uri) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(Uri.parse(uri));
        String id = wholeID.split(":")[1];
        String[] column = {
                MediaStore.Images.Media.DATA
        };
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }
}
