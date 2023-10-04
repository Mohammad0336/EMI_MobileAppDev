package com.example.mortgage_payment_calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private EditText principalMortgage, interestRate, amortizationPeriod; // User inputs to calculate monthly payment
    private TextView resultText; // Output text for calculation of monthly payment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variables to store user input
        principalMortgage = findViewById(R.id.mortgageText);
        interestRate = findViewById(R.id.interestText);
        amortizationPeriod = findViewById(R.id.amortizationText);

        // Declaration of button that invokes function for calculating monthly payment
        Button calculateButt = findViewById(R.id.button);

        // Variable to post result of calculateSum()
        resultText = findViewById(R.id.resulttext);

        calculateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateSum();
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            // Launches reference Page when Floating action button is clicked
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://tools.td.com/mortgage-payment-calculator/"));
            startActivity(intent);
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateSum() {
        try { // try block to throw error if any user input does not match required type or null
            // Parses user input into double to be able to perform arithmetic operations on
            double x = Double.parseDouble(principalMortgage.getText().toString());
            double y = Double.parseDouble(interestRate.getText().toString()) / 1200;
            double z = Double.parseDouble(amortizationPeriod.getText().toString());

            // Function for monthly mortgage calculation
            double sum = (x * y * Math.pow(1 + y, z)) / (Math.pow(1 + y, z) - 1);

            // Post results into text window if no errors
            resultText.setText("Monthly Payment: $" + String.format("%.2f", sum));
        } catch (NumberFormatException e) {
            // 
            resultText.setText("Invalid input.");
        }
    }

}