package com.example.numbersystemconvertercompleteapp;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {





        private EditText decimal;
        private EditText binary;
        private EditText octal;
        private EditText hexa;
        private Button button;
        private String value;
        private TextWatcher textWatcher;

        private boolean isUpdating = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            decimal = findViewById(R.id.decimal);
            binary = findViewById(R.id.binary);
            octal = findViewById(R.id.octal);
            hexa = findViewById(R.id.hexa);
            button = findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearFields();
                }
            });

            // Decimal TextWatcher
            decimal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!isUpdating) {
                        value = decimal.getText().toString().trim();
                        if (!value.isEmpty()) {
                            convertFromDecimal();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) { }
            });

            // Binary TextWatcher
            binary.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!isUpdating) {
                        value = binary.getText().toString().trim();
                        if (!value.isEmpty()) {
                            convertFromBinary();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) { }
            });

            // Octal TextWatcher
            octal.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!isUpdating) {
                        value = octal.getText().toString().trim();
                        if (!value.isEmpty()) {
                            convertFromOctal();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) { }
            });

            // Hexadecimal TextWatcher
            hexa.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!isUpdating) {
                        value = hexa.getText().toString().trim();
                        if (!value.isEmpty()) {
                            convertFromHex();
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) { }
            });
        }

        private void clearFields() {
            isUpdating = true; // Prevent circular updates
            decimal.setText("");
            binary.setText("");
            octal.setText("");
            hexa.setText("");
            isUpdating = false;
        }

        private void convertFromDecimal() {
            try {
                isUpdating = true;
                long num = Long.parseLong(value);
                binary.setText(Long.toBinaryString(num));
                octal.setText(Long.toOctalString(num));
                hexa.setText(Long.toHexString(num).toUpperCase());
                isUpdating = false;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        private void convertFromBinary() {
            try {
                isUpdating = true;
                long num = Long.parseLong(value, 2); // Parse binary
                decimal.setText(String.valueOf(num));
                octal.setText(Long.toOctalString(num));
                hexa.setText(Long.toHexString(num).toUpperCase());
                isUpdating = false;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        private void convertFromOctal() {
            try {
                isUpdating = true;
                long num = Long.parseLong(value, 8); // Parse octal
                decimal.setText(String.valueOf(num));
                binary.setText(Long.toBinaryString(num));
                hexa.setText(Long.toHexString(num).toUpperCase());
                isUpdating = false;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        private void convertFromHex() {
            try {
                isUpdating = true;
                long num = Long.parseLong(value, 16); // Parse hexadecimal
                decimal.setText(String.valueOf(num));
                binary.setText(Long.toBinaryString(num));
                octal.setText(Long.toOctalString(num));
                isUpdating = false;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }




















