package com.example.completenewsapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {
    ImageView cover;
    TextView tvdes, title;
    FloatingActionButton fab;
    public static String Title = "";
    public static String Description = "";
    public static Bitmap my_bitmap = null;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cover = findViewById(R.id.cover);
        tvdes = findViewById(R.id.tvdes);
        title = findViewById(R.id.title);
        fab = findViewById(R.id.fab);

        tvdes.setText(Description);
        title.setText(Title);

        if (my_bitmap != null)
            cover.setImageBitmap(my_bitmap);
        textToSpeech =new TextToSpeech(MainActivity2.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String text=tvdes.getText().toString();
                textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });
    }
}
