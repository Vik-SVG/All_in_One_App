package com.lessons.allinoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.companion.BluetoothLeDeviceFilter;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lessons.allinoneapp.MyConstants.AllConstants;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    private WebView webView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progress_bar);
        refreshLayout = findViewById(R.id.refresh_layout);
        webView = findViewById(R.id.webview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My webview");
        getSupportActionBar().setSubtitle(AllConstants.LINK);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initWebview();


    }

    private void initWebview() {

        webView.loadUrl(AllConstants.LINK);
        webView.getSettings().supportZoom();
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new MyWebChromeClient(this));

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                AllConstants.LINK = url;
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(getSupportActionBar()).setSubtitle(url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DetailActivity.this, "Loading error " + errorCode + " "+ failingUrl, Toast.LENGTH_SHORT).show();
            }
        });

webView.clearCache(true);
webView.clearHistory();
webView.getSettings().setJavaScriptEnabled(true);
webView.setHorizontalScrollBarEnabled(true);

    }


    private class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }
    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }

    }
}