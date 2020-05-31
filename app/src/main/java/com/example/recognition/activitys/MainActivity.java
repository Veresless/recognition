package com.example.recognition.activitys;

import android.os.Bundle;
import com.example.recognition.R;
import com.example.recognition.adapters.ModelAdapter;
import com.example.recognition.adapters.SaverAdapter;
import com.example.recognition.adapters.ViewAdapter;
import com.example.recognition.components.DataController;
import com.example.recognition.components.Parser;
import com.example.recognition.components.Recognition;
import com.example.recognition.components.ClarifaiRequestManager;
import com.example.recognition.adapters.ParserAdapter;
import com.example.recognition.components.SharedPreferencesHelper;

public class MainActivity extends SingleFragmentActivity {

    private Recognition recognition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelAdapter modelAdapter = new ModelAdapter(
                new ClarifaiRequestManager(getString(R.string.API_KEY), getContentResolver())
        );
        ParserAdapter parserAdapter = new ParserAdapter(new Parser());
        SaverAdapter SaverAdapter = new SaverAdapter(new SharedPreferencesHelper());
        ViewAdapter viewAdapter = new ViewAdapter(recognition);
        recognition = new Recognition(modelAdapter, viewAdapter, SaverAdapter);
        DataController.getInstance().setAdapter(viewAdapter);
    }
    @Override
    protected void onDestroy() {
        recognition.Save();
        super.onDestroy();
    }
}
