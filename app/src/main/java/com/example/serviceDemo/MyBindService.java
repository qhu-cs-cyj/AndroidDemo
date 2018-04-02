package com.example.serviceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBindService extends Service {
    private final IBinder myBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public MyBindService getService(){
            return MyBindService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service Started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 50;i++){
                    System.out.println("count = " + i);
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                        throw new RuntimeException("Interrupted",e);
                    }
                }
            }
        }).start();
    }
}
