package com.example.dante;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class firstpage extends AppCompatActivity {

    Intent intent;
    TextView getstarted;
    ProgressBar loadinganim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        getstarted = (TextView)findViewById(R.id.start);
        loadinganim = (ProgressBar)findViewById(R.id.loadinganim);
        intent = new Intent(firstpage.this,welcome.class);

        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                //do  nothing
            }

            public void onFinish() {
                findViewById(R.id.loadinganim).setVisibility(View.GONE);
                getstarted.setVisibility(View.VISIBLE);
            }
        }.start();

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

}
