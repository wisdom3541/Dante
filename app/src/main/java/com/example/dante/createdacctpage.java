package com.example.dante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class createdacctpage extends AppCompatActivity {

    ImageView done;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createdacctpage);

        done = findViewById(R.id.createdacctbtn);
        intent = new Intent(createdacctpage.this,welcome.class);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });
    }
}
