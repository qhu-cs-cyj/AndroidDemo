package com.example.webview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.cyj52.androiddemo.R;

import org.jetbrains.annotations.Nullable;

public class WebViewActivity extends Activity {
    private WebView wv;
    private EditText et;

    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.webview);

        et = (EditText)findViewById(R.id.webviewet1);
        Button btn = (Button)findViewById(R.id.webviewbtn1);
        wv = (WebView)findViewById(R.id.webView1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.loadUrl(et.getText().toString());
                wv.setWebViewClient(new WebViewClient(){

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        et.setText(url);
                        return true;
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        if(pd == null)
                            pd = ProgressDialog.show(WebViewActivity.this,"网页装载","Page Loading");
                        else
                            pd.show();

                        super.onPageStarted(view,url,favicon);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        if(pd != null)
                            pd.hide();

                        super.onPageFinished(view, url);
                    }
                });

                wv.setWebChromeClient(new WebChromeClient(){

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        WebViewActivity.this.setProgress(newProgress * 1000);
                    }

                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        System.out.println(title);
                        super.onReceivedTitle(view, title);
                    }
                });
            }
        });
    }
}
