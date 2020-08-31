package com.example.dante;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public  class settings extends Fragment {

    TextView settings,contactus;
    Intent intent,intent2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settingspage, container, false);

        settings = (TextView)view.findViewById(R.id.settings);
        contactus = (TextView)view.findViewById(R.id.contactus);
        intent = new Intent(getContext(),changesettings.class);
        intent2 = new Intent(getContext(), com.example.dante.contactus.class);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        return view;
    }
}