package com.example.serviceDemo;

import android.app.IntentService;
import android.content.Intent;

import org.jetbrains.annotations.Nullable;

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try{
            Thread.sleep(20000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
