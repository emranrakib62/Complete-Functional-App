package com.example.completedigitaltajbihapp;





import android.annotation.SuppressLint;
import android.icu.text.BreakIterator;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tvdisplay;
    Button badd, bsub, breset, baddla, baddsu, baddallah;
    int count = 0;
    TextToSpeech textToSpeech;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        tvdisplay = findViewById(R.id.tvdisplay);
        badd = findViewById(R.id.badd);
        bsub = findViewById(R.id.bsub);
        breset = findViewById(R.id.breset);
        baddla = findViewById(R.id.baddla);
        baddsu = findViewById(R.id.baddsu);
        baddallah = findViewById(R.id.baddallah);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, new OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Set the language to English
                    int langResult = textToSpeech.setLanguage(Locale.US);
                    if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle missing language data or unsupported language
                    }
                }
            }
        });

        // Button click listeners with text-to-speech
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvdisplay.setText(String.valueOf(count));
                textToSpeech.speak("আলহামদুলিল্লাহ", TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });

        baddla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvdisplay.setText(String.valueOf(count));
                textToSpeech.speak("লা ইলাহা ইল্লাল্লাহ", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        baddsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvdisplay.setText(String.valueOf(count));
                textToSpeech.speak("সুবাহান আল্লাহ", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        baddallah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvdisplay.setText(String.valueOf(count));
                textToSpeech.speak("আল্লাহু আকবার", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        // Subtract button functionality
        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    tvdisplay.setText(String.valueOf(count));
                }
            }
        });

        // Reset button functionality
        breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                tvdisplay.setText(String.valueOf(count));
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Shutdown TextToSpeech when the activity is destroyed
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }}