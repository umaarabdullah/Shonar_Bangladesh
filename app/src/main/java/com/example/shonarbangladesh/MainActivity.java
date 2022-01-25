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
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /***
     * AC restaurant Vat 10%, non AC restaurant 7%, Cloths Big Showroom 7.5% online shopping 5%
     ***/

    private final String TAG = "MainActivity";
    private int inputPrice = 0;
    private double outputPrice = 0.00;
    private TextView outputPriceTextView;

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


        // linking the input components and calculating vat and observing/listening user clicks
        EditText inputACRestPrice = findViewById(R.id.ACRestInputPrice);

        inputACRestPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputPrice(Integer.parseInt(inputACRestPrice.getText().toString()));
                calulateVatIncludedPriceForACRestaurant(inputPrice);
                Log.d(TAG, "onClick: outputprice " + Double.toString(getOutputPrice()));
                outputPriceTextView = findViewById(R.id.outputPriceTK);
                outputPriceTextView.setText( (String) Double.toString(getOutputPrice()));
            }
        });

        EditText inputNonACRetPrice = findViewById(R.id.nonACRestInputPrice);

        inputNonACRetPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputPrice(Integer.parseInt(inputNonACRetPrice.getText().toString()));
                calulateVatIncludedPriceForNonACRestaurant(inputPrice);
                outputPriceTextView = findViewById(R.id.outputPriceTK);
                outputPriceTextView.setText( (String) Double.toString(getOutputPrice()));
            }
        });

    }

    private void calulateVatIncludedPriceForACRestaurant(int inputPrice) {

        double vatInclPrice = inputPrice;
        vatInclPrice = vatInclPrice + vatInclPrice*0.10;
        setOutputPrice(vatInclPrice);
    }

    private void calulateVatIncludedPriceForNonACRestaurant(int inputPrice) {

        double vatInclPrice = inputPrice;
        vatInclPrice = vatInclPrice + vatInclPrice*0.07;
        setOutputPrice(vatInclPrice);
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

    public int getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(int inputPrice) {
        this.inputPrice = inputPrice;
    }

    public double getOutputPrice() {
        return outputPrice;
    }

    public void setOutputPrice(double outputPrice) {
        this.outputPrice = outputPrice;
    }
}