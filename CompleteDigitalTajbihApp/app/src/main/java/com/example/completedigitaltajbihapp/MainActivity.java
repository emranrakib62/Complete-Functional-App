package com.example.completedigitaltajbihapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tvdisplay;
    Button badd, bsub, breset, baddla, baddsu, baddallah, bmute;
    int count = 0,bcount=0,ccount=0,dcount=0;
    boolean isMuted = false; // To track the mute state
    TextToSpeech textToSpeech;
    TextView textview;
    Animation animation;

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
        bmute = findViewById(R.id.bmute); // Mute button
        textview = findViewById(R.id.textview);
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_to_left);

        textview.startAnimation(animation);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int langResult = textToSpeech.setLanguage(Locale.US);
                    if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Handle missing language data or unsupported language
                    }
                }
            }
        });

        // Button click listeners with text-to-speech (with mute check)
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvdisplay.setText(String.valueOf(count));
                if (!isMuted) {
                    textToSpeech.speak("আলহামদুলিল্লাহ", TextToSpeech.QUEUE_FLUSH, null, null);
                }

            }
        });

        baddla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bcount++;

                tvdisplay.setText(String.valueOf(bcount));
                if (!isMuted) {
                    textToSpeech.speak("লা ইলাহা ইল্লাল্লাহ", TextToSpeech.QUEUE_FLUSH, null, null);
                }

            }
        });

        baddsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ccount++;
                tvdisplay.setText(String.valueOf(ccount));
                if (!isMuted) {
                    textToSpeech.speak("সুবাহান আল্লাহ", TextToSpeech.QUEUE_FLUSH, null, null);
                }

            }
        });

        baddallah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dcount++;
                tvdisplay.setText(String.valueOf(dcount));
                if (!isMuted) {

                    textToSpeech.speak("আল্লাহু আকবার", TextToSpeech.QUEUE_FLUSH, null, null);
                }

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
                bcount=0;
                ccount=0;
                dcount=0;
                tvdisplay.setText(String.valueOf(count));
                tvdisplay.setText(String.valueOf(bcount));
                tvdisplay.setText(String.valueOf(ccount));
                tvdisplay.setText(String.valueOf(dcount));
            }
        });

        // Mute button functionality
        bmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMuted = !isMuted; // Toggle mute state
                bmute.setText(isMuted ? "UNMUTE" : "MUTE"); // Update button text

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
    }
}
