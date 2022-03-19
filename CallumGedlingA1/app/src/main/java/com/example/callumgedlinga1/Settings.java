package com.example.callumgedlinga1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 1234;
    Button distance, weight, currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        distance = findViewById(R.id.distance);
        weight = findViewById(R.id.weight);
        currency = findViewById(R.id.energy);
    }

    public void distanceClicked(View view){

        //used to pass a new conversion variable and text for the input and output text fields
        double conversionVariable = 2.54;

        Intent data = new Intent();
        data.putExtra("conversionVariable", conversionVariable);
        data.putExtra("inputText", "Enter Distance In CM To Convert To INCH");
        data.putExtra("outputText", "Converted Value Will Appear Here");
        setResult(RESULT_OK, data);
        finish();
    }


    public void energyClicked(View view){

        //used to pass a new conversion variable and text for the input and output text fields
        double conversionVariable = 0.8;

        Intent data = new Intent();
        data.putExtra("conversionVariable", conversionVariable);
        data.putExtra("inputText", "Enter Actual Power (kW) To Convert To Apparent Power (kVA)");
        data.putExtra("outputText", "Converted Value Will Appear Here");
        setResult(RESULT_OK, data);
        finish();
    }

    public void weightClicked(View view){

        //used to pass a new conversion variable and text for the input and output text fields
        double conversionVariable = 0.45359237;

        Intent data = new Intent();
        data.putExtra("conversionVariable", conversionVariable);
        data.putExtra("inputText", "Enter Weight In KG To Convert To LB");
        data.putExtra("outputText", "Converted Value Will Appear Here");
        setResult(RESULT_OK, data);
        finish();
    }


}