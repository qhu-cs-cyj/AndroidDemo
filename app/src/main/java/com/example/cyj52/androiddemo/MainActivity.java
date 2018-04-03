package com.example.cyj52.androiddemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.processthread.HandlerActivity;
import com.example.serviceDemo.MyBindService;
import com.example.serviceDemo.MyStartService;
import com.example.serviceDemo.ServiceActivity;
import com.example.webview.WebViewActivity;

public class MainActivity extends Activity {

    Button btnservice,btnhandle,btnwebview;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnservice = (Button)findViewById(R.id.btnService);
        btnhandle = (Button)findViewById(R.id.btnhandle);
        btnwebview = (Button)findViewById(R.id.btnwebview);

        btnservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(MainActivity.this,ServiceActivity.class);
                startActivity(in);
//                MainActivity.this.finish();
            }
        });

        btnhandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(MainActivity.this,HandlerActivity.class);
                startActivity(in);
//                MainActivity.this.finish();
            }
        });

        btnwebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(MainActivity.this,WebViewActivity.class);
                startActivity(in);
//                MainActivity.this.finish();
            }
        });
    }

}
