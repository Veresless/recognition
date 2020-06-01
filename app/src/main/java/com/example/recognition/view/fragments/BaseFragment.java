package com.example.recognition.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.recognition.interfaces.IViewAdapter;

public class BaseFragment extends Fragment {
    protected View mView;
    protected IViewAdapter mAdapter;
    protected Action<String> infoAction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return mView;
    }
    protected void init() {
        DataController.getInstance().getAdapterLiveData().observe(getViewLifecycleOwner(), new Observer<IViewAdapter>() {
            @Override
            public void onChanged(IViewAdapter iViewAdapter) {
                mAdapter = iViewAdapter;
            }
        });
        infoAction = new Action<String>() {
            @Override
            public void onEventFired(String... args) {
                Toast.makeText(getContext(), args[0], Toast.LENGTH_SHORT).show();
            }
        };
        mAdapter.getInfoEvent().addListener(infoAction);
    }
    @Override
    public void onResume() {
        super.onResume();
        mAdapter.getInfoEvent().addListener(infoAction);
    }
    @Override
    public void onStop() {
        super.onStop();
        mAdapter.getInfoEvent().removeListener(infoAction);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter.getInfoEvent().removeListener(infoAction);
    }
}
