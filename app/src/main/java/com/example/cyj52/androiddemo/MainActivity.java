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

import com.example.serviceDemo.MyBindService;
import com.example.serviceDemo.MyStartService;

public class MainActivity extends Activity {

    Button b1,b2,b3,b4;
    Intent in;
    MyBindService myservice;

    private ServiceConnection sConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myservice = ((MyBindService.LocalBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);

        final Intent bindIn = new Intent(MainActivity.this,MyBindService.class);
        bindService(bindIn,sConn, Context.BIND_AUTO_CREATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in = new Intent(MainActivity.this, MyStartService.class);
                startService(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(in);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setAction("MyService");
                in.setPackage(getPackageName());
                startService(in);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myservice.test();
            }
        });
    }
}
