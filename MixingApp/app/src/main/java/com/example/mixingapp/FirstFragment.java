package com.example.mixingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mixingapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // Parameters for fragment initialization
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText gradeInput, creditInput;
    private Button addButton, calculateButton, resetButton;
    private TextView resultTextView;
    private double totalWeightedGrade = 0.0, totalCredits = 0.0;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     * using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Initialize views
        gradeInput = view.findViewById(R.id.editTextGrade);
        creditInput = view.findViewById(R.id.editTextCredit);
        addButton = view.findViewById(R.id.buttonAdd);
        calculateButton = view.findViewById(R.id.buttonCalculate);
        resetButton = view.findViewById(R.id.buttonReset);
        resultTextView = view.findViewById(R.id.textViewResult);

        // Add button functionality
        addButton.setOnClickListener(v -> {
            try {
                double credit = Double.parseDouble(creditInput.getText().toString());
                double grade = Double.parseDouble(gradeInput.getText().toString());

                totalWeightedGrade += grade * credit;
                totalCredits += credit;

                Toast.makeText(getContext(), "Added Credit: " + credit + "\nGrade: " + grade, Toast.LENGTH_SHORT).show();

                // Clear inputs
                creditInput.setText("");
                gradeInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        });

        // Calculate button functionality
        calculateButton.setOnClickListener(v -> {
            if (totalCredits > 0) {
                double cgpa = totalWeightedGrade / totalCredits;
                resultTextView.setText(String.format("Your CGPA: %.2f", cgpa));
            } else {
                Toast.makeText(getContext(), "No credits added yet!", Toast.LENGTH_SHORT).show();
            }
        });

        // Reset button functionality
        resetButton.setOnClickListener(v -> {
            totalWeightedGrade = 0.0;
            totalCredits = 0.0;

            gradeInput.setText("");
            creditInput.setText("");
            resultTextView.setText("");

            Toast.makeText(getContext(), "Data reset", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
