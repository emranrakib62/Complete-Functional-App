package com.example.completedigitaltajbihapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvdisplay, textview;
    View badd, bsub, breset, baddla, baddsu, baddallah, bmute;
    int count = 0;
    boolean isMuted = false;
    TextToSpeech textToSpeech;
    Animation animation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        tvdisplay = findViewById(R.id.tvdisplay);
        badd = findViewById(R.id.badd);
        baddla = findViewById(R.id.baddla);
        baddsu = findViewById(R.id.baddsu);
        baddallah = findViewById(R.id.baddallah);
        bsub = findViewById(R.id.bsub);
        breset = findViewById(R.id.breset);
        bmute = findViewById(R.id.bmute);
        textview = findViewById(R.id.textview);

        // Header animation
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_to_left);
        textview.startAnimation(animation);

        Animation displayAnim = AnimationUtils.loadAnimation(this, R.anim.top_to_down);
        tvdisplay.startAnimation(displayAnim);



        tvdisplay = findViewById(R.id.tvdisplay);
        displayAnim = AnimationUtils.loadAnimation(this, R.anim.top_to_down);
        tvdisplay.startAnimation(displayAnim);



        // Text-to-Speech setup
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(new Locale("bn", "BD"));
            }
        });

        // Animate buttons
        animateButtonsSmoothly();

        // Add button actions
        badd.setOnClickListener(v -> handleButtonClick("আলহামদুলিল্লাহ", badd));
        baddla.setOnClickListener(v -> handleButtonClick("লা ইলাহা ইল্লাল্লাহ", baddla));
        baddsu.setOnClickListener(v -> handleButtonClick("সুবাহান আল্লাহ", baddsu));
        baddallah.setOnClickListener(v -> handleButtonClick("আল্লাহু আকবার", baddallah));

        bsub.setOnClickListener(v -> {
            if (count > 0) {
                count--;
                tvdisplay.setText(String.valueOf(count));
            }
        });

        breset.setOnClickListener(v -> resetAll());

        bmute.setOnClickListener(v -> {
            isMuted = !isMuted;
            TextView muteText = (TextView) bmute.findViewById(android.R.id.text1);
            if (muteText != null) {
                muteText.setText(isMuted ? "UNMUTE SOUND" : "MUTE SOUND");
            }
        });
    }

    // Handle individual button click and disable others
    private void handleButtonClick(String text, View clickedButton) {
        count++;
        tvdisplay.setText(String.valueOf(count));

        if (!isMuted && textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }

        // Disable other three add buttons
        disableOtherAddButtons(clickedButton);
    }

    // Disable other add buttons except the clicked one
    private void disableOtherAddButtons(View clickedButton) {
        if (clickedButton != badd) badd.setEnabled(false);
        if (clickedButton != baddla) baddla.setEnabled(false);
        if (clickedButton != baddsu) baddsu.setEnabled(false);
        if (clickedButton != baddallah) baddallah.setEnabled(false);

        // Keep the clicked one enabled
        clickedButton.setEnabled(true);
    }

    // Reset everything
    private void resetAll() {
        count = 0;
        tvdisplay.setText(String.valueOf(count));

        // Enable all buttons
        badd.setEnabled(true);
        baddla.setEnabled(true);
        baddsu.setEnabled(true);
        baddallah.setEnabled(true);
    }

    // Animate buttons from bottom to top
    private void animateButtonsSmoothly() {
        View[] buttons = {badd, baddla, baddsu, baddallah, bsub, breset, bmute};

        for (int i = 0; i < buttons.length; i++) {
            View button = buttons[i];
            button.setVisibility(View.INVISIBLE);
            int delay = i * 2000;

            button.postDelayed(() -> {
                button.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_to_top);
                button.startAnimation(anim);
            }, delay);
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
