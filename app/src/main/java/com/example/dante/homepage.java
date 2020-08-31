package com.example.dante;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.paperdb.Paper;

public  class homepage extends Fragment {

    public static  int index = 1;
    ImageView amazon,appstore,offgamer,itunes;
    TextView transaction,username;
    Intent intent1;
    String tag;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage, container, false);

        amazon =(ImageView) view.findViewById(R.id.amazonimg);
        appstore =(ImageView) view.findViewById(R.id.appstore);
        offgamer = view.findViewById(R.id.offgamers);
        transaction = view.findViewById(R.id.transactions);
        username = view.findViewById(R.id.username);
        itunes = view.findViewById(R.id.itunes);
        intent1 =new Intent(getContext(),transactionpage.class);

       // String name = getActivity().getIntent().getStringExtra("email");
      //  String altname = name;
        username.setText(Utils.useremail);

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        appstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                Log.e(tag, String.valueOf(index));
                Intent i = new Intent(v.getContext(), carddetailspage.class);
                i.putExtra("number",index);
                startActivity(i);
            }
        });

        itunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                Log.e(tag, String.valueOf(index));
                Intent i = new Intent(v.getContext(), carddetailspage.class);
                i.putExtra("number",index);
                startActivity(i);
            }
        });

        offgamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 2;
                Log.e(tag, String.valueOf(index));
                Intent i = new Intent(v.getContext(), carddetailspage.class);
                i.putExtra("number",index);
                startActivity(i);
            }
        });

        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 3;
                Log.e(tag, String.valueOf(index));
                Intent i = new Intent(v.getContext(), carddetailspage.class);
                i.putExtra("number",index);
                startActivity(i);
            }
        });


    return view;
    }

}
