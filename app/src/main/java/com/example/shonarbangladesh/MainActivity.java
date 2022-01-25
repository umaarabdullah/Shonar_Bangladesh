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

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private int inputPrice = 0;
    private int foodOrClothsChoice = 3;
    /***
     * AC restaurant Vat 10%, non AC 7%, Cloths Big Showroom 7.5% online shopping 5%
     ***/
    private double vat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.splashScreenTheme);        // enabling splash screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set listener to the arrow buttons to navigate between screens
        Button rightArrowButton = findViewById(R.id.mainActivityRightArrow);
        Button leftArrowButton = findViewById(R.id.mainActivityLeftArrow);

        rightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: rightButton");
                startActivity(new Intent(MainActivity.this, ClothsActivity.class));
            }
        });
        leftArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: leftButton");
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
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