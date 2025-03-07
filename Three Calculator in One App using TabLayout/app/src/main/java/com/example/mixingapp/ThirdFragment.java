package com.example.mixingapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.DecimalFormat;

public class ThirdFragment extends Fragment {

    private static final char Addition = '+';
    private static final char Subtraction = '-';
    private static final char Multiplication = '*';
    private static final char Division = '/';
    private static final char Percent = '%';

    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = view.findViewById(R.id.input);
        outputDisplay = view.findViewById(R.id.output);

        initializeButtons(view);
        return view;
    }

    private void initializeButtons(View view) {
        int[] buttonIds = { R.id.zero, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.point };
        String[] buttonValues = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." };

        for (int i = 0; i < buttonIds.length; i++) {
            MaterialButton button = view.findViewById(buttonIds[i]);
            String value = buttonValues[i];
            button.setOnClickListener(v -> inputDisplay.append(value));
        }

        view.findViewById(R.id.plus).setOnClickListener(v -> handleOperation(Addition, "+"));
        view.findViewById(R.id.minus).setOnClickListener(v -> handleOperation(Subtraction, "-"));
        view.findViewById(R.id.division).setOnClickListener(v -> handleOperation(Division, "/"));
        view.findViewById(R.id.multiply).setOnClickListener(v -> handleOperation(Multiplication, "*"));
        view.findViewById(R.id.percent).setOnClickListener(v -> handleOperation(Percent, "%"));

        view.findViewById(R.id.Clear).setOnClickListener(v -> clearInput());
        view.findViewById(R.id.off).setOnClickListener(v -> requireActivity().finish());
        view.findViewById(R.id.equal).setOnClickListener(v -> evaluateResult());
    }

    private void handleOperation(char operation, String symbol) {
        calculate();
        currentSymbol = operation;
        outputDisplay.setText(decimalFormat.format(firstValue) + symbol);
        inputDisplay.setText(null);
    }

    private void evaluateResult() {
        calculate();
        outputDisplay.setText(decimalFormat.format(firstValue));
        firstValue = Double.NaN;
        currentSymbol = '0';
    }

    private void calculate() {
        if (!Double.isNaN(firstValue)) {
            try {
                secondValue = Double.parseDouble(inputDisplay.getText().toString());
                inputDisplay.setText(null);

                switch (currentSymbol) {
                    case Addition: firstValue += secondValue; break;
                    case Subtraction: firstValue -= secondValue; break;
                    case Multiplication: firstValue *= secondValue; break;
                    case Division: firstValue /= secondValue; break;
                    case Percent: firstValue %= secondValue; break;
                }
            } catch (Exception ignored) {}
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception ignored) {}
        }
    }

    private void clearInput() {
        if (inputDisplay.getText().length() > 0) {
            CharSequence currentText = inputDisplay.getText();
            inputDisplay.setText(currentText.subSequence(0, currentText.length() - 1));
        } else {
            firstValue = Double.NaN;
            secondValue = Double.NaN;
            inputDisplay.setText("");
            outputDisplay.setText("");
        }
    }
}