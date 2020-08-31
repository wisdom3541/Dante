package com.example.dante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class trade extends Fragment {

    String d1[];
    int imagesss[] = {R.drawable.googlecardimg,R.drawable.itunesimg,R.drawable.offgamersimg,R.drawable.amazonimg,R.drawable.vanillaimg,R.drawable.steamcard,R.drawable.btccard};
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.traderecycle, container, false);


        recyclerView = view.findViewById(R.id.recycletrade);

        d1 = getResources().getStringArray(R.array.card_names);

        adapter adapter = new adapter(getContext(),d1,imagesss);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}
