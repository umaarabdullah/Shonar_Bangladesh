package com.example.shonarbangladesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ClothsActivity extends AppCompatActivity {

    private String TAG = "ClothsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloths);

        // set listener to the arrow buttons to navigate between screens
        Button rightArrowButton = findViewById(R.id.clothsActivityRightArrow);
        Button leftArrowButton = findViewById(R.id.clothsActivityLeftArrow);

        rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: right button");
                startActivity(new Intent(ClothsActivity.this, AboutActivity.class));
            }
        });
        leftArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: left button");
                startActivity(new Intent(ClothsActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_top, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch (item.getItemId()){

            case R.id.foodItem:
                Log.d(TAG, "onOptionsItemSelected: foodItem");
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);      // starts the food screen on click in menu option
                break;
            case R.id.clothsItem:
                Log.d(TAG, "onOptionsItemSelected: cloths item");
                intent = new Intent(this, ClothsActivity.class);
                startActivity(intent);      // starts the cloths screen on click in menu option
                break;
            case R.id.aboutItem:
                Log.d(TAG, "onOptionsItemSelected: about item");
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);      // starts the about us screen on click in menu option
                break;
            default:
                // code block
        }
        return super.onOptionsItemSelected(item);
    }
}