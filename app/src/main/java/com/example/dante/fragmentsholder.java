package com.example.dante;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class fragmentsholder extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentsholder);

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new homepage()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
                case R.id.homebtn:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new homepage()).commit();
                   break;
                case R.id.trade:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new trade()).commit();
                    break;
                case R.id.settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new settings()).commit();
                    break;
            }

            return true;
        }
    }
