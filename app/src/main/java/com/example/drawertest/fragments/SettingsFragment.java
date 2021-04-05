package com.example.drawertest.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.drawertest.MainActivity;
import com.example.drawertest.R;

public class SettingsFragment extends Fragment {

    private boolean switchState;

    public SettingsFragment() {
        switchState = true;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch textEditSwitch = view.findViewById(R.id.switch1);
        if (switchState){ textEditSwitch.toggle();}
        textEditSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchState = isChecked;
            }
        });
    }
    public boolean getSwitchState(){
        return switchState;
    }
}