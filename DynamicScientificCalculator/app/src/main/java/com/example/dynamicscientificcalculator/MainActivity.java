package com.example.dynamicscientificcalculator;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String input = "";
    private String operator = "";
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // or your XML file name

        resultTextView = findViewById(R.id.resultTextView);

        // Numbers and dot
        setNumericOnClickListener(R.id.button0, "0");
        setNumericOnClickListener(R.id.button1, "1");
        setNumericOnClickListener(R.id.button2, "2");
        setNumericOnClickListener(R.id.button3, "3");
        setNumericOnClickListener(R.id.button4, "4");
        setNumericOnClickListener(R.id.button5, "5");
        setNumericOnClickListener(R.id.button6, "6");
        setNumericOnClickListener(R.id.button7, "7");
        setNumericOnClickListener(R.id.button8, "8");
        setNumericOnClickListener(R.id.button9, "9");
        setNumericOnClickListener(R.id.button00, "00");
        setNumericOnClickListener(R.id.buttonDot, ".");

        // Operators
        setOperatorOnClickListener(R.id.buttonAdd, "+");
        setOperatorOnClickListener(R.id.buttonSub, "-");
        setOperatorOnClickListener(R.id.buttonMul, "*");
        setOperatorOnClickListener(R.id.buttonDiv, "/");
        setOperatorOnClickListener(R.id.buttonPercent, "%");

        // AC, C, Equal
        findViewById(R.id.buttonAC).setOnClickListener(v -> {
            input = "";
            operator = "";
            firstNumber = 0;
            resultTextView.setText("");
        });

        findViewById(R.id.buttonC).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                resultTextView.setText(input);
            }
        });

        findViewById(R.id.buttonEqual).setOnClickListener(v -> {
            calculateResult();
        });
    }

    private void setNumericOnClickListener(int buttonId, String value) {
        NeumorphButton button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            input += value;
            resultTextView.setText(input);
        });
    }

    private void setOperatorOnClickListener(int buttonId, String op) {
        NeumorphButton button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (!input.isEmpty()) {
                firstNumber = Double.parseDouble(input);
                operator = op;
                input = "";
                resultTextView.setText(operator);
            }
        });
    }

    private void calculateResult() {
        if (!input.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(input);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
                case "%":
                    result = firstNumber % secondNumber;
                    break;
            }
            input = String.valueOf(result);
            resultTextView.setText(input);
            operator = "";
        }
    }
}
