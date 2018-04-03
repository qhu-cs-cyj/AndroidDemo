package com.example.processthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import java.util.logging.LogRecord;

//非UI线程中添加Looper对象并实现线程间通信
public class LooperThread extends Thread {
    public Handler mHandler;


    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        Looper.loop();
    }
}
