package com.example.shonarbangladesh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "MainActivity";
    private int inputPrice = 0;
    private int foodOrClothsChoice = 3;
    private double vat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.splashScreenTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // radio group listener
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                onRadioButtonSelection(radioGroup,checkedId);
            }
        });

        EditText editText = findViewById(R.id.inputPrice);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputPriceString = editText.getText().toString();
                // handling an empty string case
                if(inputPriceString.equals(""))
                    return;

                inputPrice = Integer.parseInt(inputPriceString);

                if(vat == 0)
                    return;

                // calculation of vat price
                double output = inputPrice;
                output = output + output*vat/100;
                String outputString = Double.toString(output);
                outputString = outputString + " TK";

                TextView priceWithVat = findViewById(R.id.priceWithVat);
                priceWithVat.setText(getResources().getString(R.string.price_with_vat));

                TextView outputPrice = findViewById(R.id.outputPrice);
                outputPrice.setText(outputString);
            }
        });

    }

    private void onRadioButtonSelection(RadioGroup radioGroup, int checkedId) {

        RadioButton selectedRadioButton;

        selectedRadioButton = findViewById(checkedId);

        Toast.makeText(MainActivity.this, selectedRadioButton.getText(),Toast.LENGTH_SHORT).show();

        setDropDownList(selectedRadioButton.getText().toString());
    }

    private void setDropDownList(String category) {

        Spinner dropDownList = findViewById(R.id.shopCategoryDropDown);
        String[] items = null;

        if(category.equals(getResources().getString(R.string.food))){
            items = new String[]{getResources().getString(R.string.ac_restaurants)
                    , getResources().getString(R.string.non_ac_restaurants)};
            foodOrClothsChoice = 1;
            Log.d(TAG, "setDropDownList: radioButtonCheckfood");
        }

        else if(category.equals(getResources().getString(R.string.cloths))){
            items = new String[]{getResources().getString(R.string.big_showrooms)
                    , getResources().getString(R.string.online_shopping)};
            foodOrClothsChoice = 2;
            Log.d(TAG, "setDropDownList: radioButtonCheckcloths");
        }

        if(items!=null){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            dropDownList.setAdapter(adapter);
        }

        dropDownList.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(foodOrClothsChoice == 1){
            switch (position) {
                case 0:
                    vat = 10;   // ac restaurants vat
                    Log.d(TAG, "onItemClick: ac restaurants");
                    break;
                case 1:
                    vat = 7;   // non ac restaurants vat
                    Log.d(TAG, "onItemClick: non ac restaurants");
                    break;
            }
        }
        if(foodOrClothsChoice == 2){
            switch (position) {
                case 0:
                    vat = 7.5;   // big showroom vat
                    Log.d(TAG, "onItemClick: big showrooms");
                    break;
                case 1:
                    vat = 5;   // online shop vat
                    Log.d(TAG, "onItemClick: online");
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}