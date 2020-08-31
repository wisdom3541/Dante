package com.example.dante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class thankyoupage extends AppCompatActivity {

    ImageView xicon;
    Intent intent;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.thankyou);

            xicon = findViewById(R.id.xicon);
            intent = new Intent(thankyoupage.this,fragmentsholder.class);

            xicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
    }
}
