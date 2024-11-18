package com.example.gpacalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText Grade, Credit;
    private Button buttonAdd, buttonCalculate, buttonReset;
    private TextView textViewResult;
    private double counter = 0.0, total_credit = 0.0, credit = 0.0, grade = 0.0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        textViewResult = findViewById(R.id.textViewResult);
        Grade = findViewById(R.id.editTextGrade);
        Credit = findViewById(R.id.editTextCredit);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonReset = findViewById(R.id.buttonReset);

        // Add grades and credits
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    credit = Double.parseDouble(Credit.getText().toString());
                    grade = Double.parseDouble(Grade.getText().toString());

                    // Accumulate weighted grades and total credits
                    counter += (grade * credit);
                    total_credit += credit;

                    Toast.makeText(getApplicationContext(), "Added Credit: " + credit + "\nGrade: " + grade, Toast.LENGTH_LONG).show();

                    // Clear input fields after adding
                    Credit.setText("");
                    Grade.setText("");
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    Toast.makeText(getApplicationContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Calculate CGPA
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (total_credit > 0) {
                    double result = counter / total_credit;
                    textViewResult.setText("Your CGPA: " + String.format("%.2f", result));
                } else {
                    Toast.makeText(getApplicationContext(), "No credits added yet!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Reset all data
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0.0;
                total_credit = 0.0;
                credit = 0.0;
                grade = 0.0;

                // Clear input fields and result
                Credit.setText("");
                Grade.setText("");
                textViewResult.setText("");

                Toast.makeText(getApplicationContext(), "Data reset", Toast.LENGTH_SHORT).show();
            }
        });
    }





}