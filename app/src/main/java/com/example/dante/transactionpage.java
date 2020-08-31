package com.example.dante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class transactionpage extends AppCompatActivity {

    RecyclerView recyclerView;
    String t1[];
    String date ="xxxx-xx-xx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactionpage);

        recyclerView = findViewById(R.id.transactionrecycletrade);

        t1 = getResources().getStringArray(R.array.transactions);

        trasactionAdapter adapter = new trasactionAdapter(transactionpage.this,t1,date);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(transactionpage.this));


    }
}
