package com.example.qrcodescannerapp

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val webview=findViewById<WebView>(R.id.webview)
        val url=intent.getStringExtra("url")

        webview.webViewClient= WebViewClient()
        webview.settings.javaScriptEnabled=true
if(url !=null){
    webview.loadUrl(url)
}
    }
}