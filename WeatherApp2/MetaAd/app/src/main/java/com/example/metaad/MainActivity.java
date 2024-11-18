package com.example.metaad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class MainActivity extends AppCompatActivity {
Button button;
    LinearLayout adContainer;
    private AdView adView;
    TextView tvdisplay;
    private InterstitialAd interstitialAd;
public  static final String TAG="fullscrenad";
    int banneradclicked=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        adContainer=findViewById(R.id.banner_container);
        tvdisplay=findViewById(R.id.tvdisplay);
         button=findViewById(R.id.button);


loadbannerad();
loudinterestialad();

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(interstitialAd!=null){
            interstitialAd.show();
        }

    }
});



    }

   ///.........................................................................

private void loudinterestialad(){

    interstitialAd = new InterstitialAd(this, "YOUR_PLACEMENT_ID");
    InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
        @Override
        public void onInterstitialDisplayed(Ad ad) {
            // Interstitial ad displayed callback
            Log.e(TAG, "Interstitial ad displayed.");
        }

        @Override
        public void onInterstitialDismissed(Ad ad) {
            // Interstitial dismissed callback
            Log.e(TAG, "Interstitial ad dismissed.");
        }

        @Override
        public void onError(Ad ad, AdError adError) {
            // Ad error callback
            Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
        }

        @Override
        public void onAdLoaded(Ad ad) {
            // Interstitial ad is loaded and ready to be displayed
            Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
            // Show the ad

        }

        @Override
        public void onAdClicked(Ad ad) {
            // Ad clicked callback
            Log.d(TAG, "Interstitial ad clicked!");
        }

        @Override
        public void onLoggingImpression(Ad ad) {
            // Ad impression logged callback
            Log.d(TAG, "Interstitial ad impression logged!");
        }
    };
    interstitialAd.loadAd(
            interstitialAd.buildLoadAdConfig()
                    .withAdListener(interstitialAdListener)
                    .build());





}

    private  void loadbannerad(){

        adView = new AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);

        adContainer.addView(adView);
        adView.loadAd();

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
// Ad error callback
                tvdisplay.append("/n"+adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
// Ad loaded callback
                tvdisplay.append("/n ad loaded");
            }

            @Override
            public void onAdClicked(Ad ad) {
// Ad clicked callback
                banneradclicked++;
                tvdisplay.append("/n on clicked"+banneradclicked);

                if(banneradclicked>=2){
                   if(adView!=null) adView.destroy();
                   adContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Ad impression logged callback
            }
        };

        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());


    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


}