package com.naganath.cs478.project2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import static com.naganath.cs478.project2.MainActivity.WEB_KEY;

public class WebsiteDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_display);
        Intent intent = getIntent();
        String website_uri = intent.getExtras().getString(WEB_KEY);
        WebView webView = findViewById(R.id.webview1);
        webView.loadUrl(Uri.parse(website_uri).toString());
    }
}
