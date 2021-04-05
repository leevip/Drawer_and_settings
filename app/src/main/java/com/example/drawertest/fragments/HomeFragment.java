package com.example.drawertest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drawertest.R;
import com.google.android.material.textfield.TextInputEditText;


public class HomeFragment extends Fragment {

    boolean editState;
    TextInputEditText editText;
    TextView textView;
    String text;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        editText = (TextInputEditText) view.findViewById(R.id.text_edit);
        textView = (TextView) view.findViewById(R.id.text_view);
        try {
            editState = getArguments().getBoolean("Switch");
            if (!editState) {
                textView.setText(text);
            }
            editText.setFocusable(editState);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        text = editText.getText().toString();
    }
}