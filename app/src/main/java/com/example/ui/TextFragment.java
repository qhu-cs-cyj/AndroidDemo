package com.example.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyj52.androiddemo.R;

public class TextFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View text = inflater.inflate(R.layout.text_fragment_item,container,false);
        return text;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
