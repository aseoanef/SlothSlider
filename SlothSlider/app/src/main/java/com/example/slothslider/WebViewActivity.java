package com.example.slothslider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;


public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private int id;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = (Integer) bundle.getInt("ID-WEB");


        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        if (id==1){
            webView.loadUrl("https://www.imdb.com/privacy?ref_=ft_pvc");
        }else{
            if (id==2){
                webView.loadUrl("https://help.imdb.com/imdb");
            }

        }
        if (id==3){
            webView.loadUrl("https://www.imdb.com/privacy?ref_=ft_pvc");
        }


    }
}
