package com.example.serviceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyStartService extends Service {
    Thread t;

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service Stopped");
        t.interrupt();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service Started");

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i = 0;i < 50;i++){
                        System.out.println("count = " + i);
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            throw new RuntimeException("Interrupted",e);
                        }
                    }
                }catch (RuntimeException e ){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
