package com.example.callumgedlinga1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public double unit;
    public double convertedValue;
    private double conversionVariable;
    public String appInfo;
    public String outputText;
    TextView newValue;
    EditText valueToConvert;
    Button convert, settings, reset;
    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.####");



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversionVariable = 0.45359237;
        valueToConvert = findViewById(R.id.valueToConvert);
        newValue = findViewById(R.id.convertedValue);
        convert = findViewById(R.id.convert);
        settings = findViewById(R.id.settings);
        reset = findViewById(R.id.reset);
        convert.setOnClickListener(v -> ConvertValue());

        if (savedInstanceState != null){

            //used to ensure all of the values necessary for conversion are kept the same and displayed correctly in their respective textviews
            unit = savedInstanceState.getDouble("unit");
            String valueEntered = "" + unit;
            valueToConvert.setText(valueEntered);

            convertedValue = savedInstanceState.getDouble("convertedValue");
            newValue.setText(REAL_FORMATTER.format(convertedValue));

            conversionVariable = savedInstanceState.getDouble("conversionVariable");
        }


    }


    public void ConvertValue() {

        //error handling for empty inputs and string inputs
        if (valueToConvert.length() == 0)
        {
            valueToConvert.setError("Enter a value");
        }

        else{
            try {
                String valueEntered = valueToConvert.getText().toString();
                unit = Double.parseDouble(valueEntered); //acquire value entered by user
                convertedValue = unit /  conversionVariable; //conversion equation
                newValue.setText(REAL_FORMATTER.format(convertedValue));
            }
            catch (Exception NumberFormatException){
                valueToConvert.setError("Please Enter a number");
            }

        }

}

    public void settingsClicked(View view) {

        //used to take the user to the settings screen
        Intent intent = new Intent(this, Settings.class);
        startActivityForResult(intent, Settings.SETTINGS_REQUEST);
    }

    public void resetButtonClicked(View view){

        //changes the text fields based on the current setting and resets the input variable
        if (conversionVariable == 0.45359237){

            String resetTextView = "Enter Weight In KG To Convert To LB";
            String resetEditText = "Converted Value Will Appear Here";
            newValue.setText(resetEditText);
            valueToConvert.setText(resetTextView);
            unit = 0;
        }

        else if (conversionVariable == 2.54){

            String resetTextView = "Enter Distance In CM To Convert To INCH";
            String resetEditText = "Converted Value Will Appear Here";
            newValue.setText(resetEditText);
            valueToConvert.setText(resetTextView);
            unit = 0;
        }

        else {

            String resetTextView = "Enter Actual Power (kW) To Convert To Apparent Power (kVA)";
            String resetEditText = "Converted Value Will Appear Here";
            newValue.setText(resetEditText);
            valueToConvert.setText(resetTextView);
            unit = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //gets information from the settings screen and assigns it to variables in mainActivity
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Settings.SETTINGS_REQUEST){
            if(resultCode == RESULT_OK){
                assert data != null;
                conversionVariable = data.getDoubleExtra("conversionVariable", 0.45359237 );
                appInfo = data.getStringExtra("inputText");
                outputText = data.getStringExtra("outputText");
                valueToConvert.setText(appInfo);
                newValue.setText(outputText);
            }
        }

    }

    public double getValueToConvert(){
        return unit;
    }

    public double getConvertedValue(){return convertedValue;}

    public double getConversionVariable(){return conversionVariable;}

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstance) {

        //Saves the variable values for when the screen is rotated
        super.onSaveInstanceState(savedInstance);
        savedInstance.putDouble("unit", getValueToConvert());
        savedInstance.putDouble("convertedValue", getConvertedValue());
        savedInstance.putDouble("conversionVariable", getConversionVariable());
    }

}