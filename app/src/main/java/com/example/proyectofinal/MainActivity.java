package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    ImageView menuImage;

    DrawerLayout drawer;

    CardView btnActivities,
            btnGrades,
            btnClassmates;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        btnActivities = findViewById(R.id.btnActivities);
        btnGrades = findViewById(R.id.btnGrades);
        btnClassmates = findViewById(R.id.btnClassmates);

        menuImage = findViewById(R.id.menuImage);

        drawer =  findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Actividades", Toast.LENGTH_SHORT).show();
            }
        });

        btnGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Grades", Toast.LENGTH_SHORT).show();
            }
        });

        btnClassmates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mates", Toast.LENGTH_SHORT).show();
            }
        });

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.navHome);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navHome:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navAccount:
                Toast.makeText(this, "account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navSettings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navLogOut:
                //Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
                logoutFromApp();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void logoutFromApp(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
