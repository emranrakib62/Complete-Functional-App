package com.example.mixingapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

public class SecondFragment extends Fragment {

    private EditText decimal;
    private EditText binary;
    private EditText octal;
    private EditText hexa;
    private Button button;
    private String value;
    private boolean isUpdating = false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        decimal = view.findViewById(R.id.decimal);
        binary = view.findViewById(R.id.binary);
        octal = view.findViewById(R.id.octal);
        hexa = view.findViewById(R.id.hexa);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(view1 -> clearFields());

        decimal.addTextChangedListener(new ConverterTextWatcher(() -> convertFromDecimal()));
        binary.addTextChangedListener(new ConverterTextWatcher(() -> convertFromBinary()));
        octal.addTextChangedListener(new ConverterTextWatcher(() -> convertFromOctal()));
        hexa.addTextChangedListener(new ConverterTextWatcher(() -> convertFromHex()));

        return view;
    }

    private void clearFields() {
        isUpdating = true;
        decimal.setText("");
        binary.setText("");
        octal.setText("");
        hexa.setText("");
        isUpdating = false;
    }

    private void convertFromDecimal() {
        try {
            isUpdating = true;
            long num = Long.parseLong(decimal.getText().toString().trim());
            binary.setText(Long.toBinaryString(num));
            octal.setText(Long.toOctalString(num));
            hexa.setText(Long.toHexString(num).toUpperCase());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isUpdating = false;
        }
    }

    private void convertFromBinary() {
        try {
            isUpdating = true;
            long num = Long.parseLong(binary.getText().toString().trim(), 2);
            decimal.setText(String.valueOf(num));
            octal.setText(Long.toOctalString(num));
            hexa.setText(Long.toHexString(num).toUpperCase());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isUpdating = false;
        }
    }

    private void convertFromOctal() {
        try {
            isUpdating = true;
            long num = Long.parseLong(octal.getText().toString().trim(), 8);
            decimal.setText(String.valueOf(num));
            binary.setText(Long.toBinaryString(num));
            hexa.setText(Long.toHexString(num).toUpperCase());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isUpdating = false;
        }
    }

    private void convertFromHex() {
        try {
            isUpdating = true;
            long num = Long.parseLong(hexa.getText().toString().trim(), 16);
            decimal.setText(String.valueOf(num));
            binary.setText(Long.toBinaryString(num));
            octal.setText(Long.toOctalString(num));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isUpdating = false;
        }
    }

    private class ConverterTextWatcher implements TextWatcher {
        private final Runnable conversionMethod;

        public ConverterTextWatcher(Runnable conversionMethod) {
            this.conversionMethod = conversionMethod;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!isUpdating && !s.toString().trim().isEmpty()) {
                conversionMethod.run();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
}
