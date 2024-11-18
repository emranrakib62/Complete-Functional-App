package com.example.metaad;

import android.app.Application;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;

public class myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AudienceNetworkAds.initialize(this);
        AdSettings.addTestDevice("f97582dd-b404-4f38-9dcb-42389010e32b");

    }
}
