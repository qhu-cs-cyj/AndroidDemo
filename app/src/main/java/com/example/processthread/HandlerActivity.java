package com.example.processthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cyj52.androiddemo.R;

import org.jetbrains.annotations.Nullable;

public class HandlerActivity extends Activity {
    TextView tv;
    SeekBar seekbar;
    int i;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    tv.setText(String.valueOf(msg.arg1));
                    seekbar.setProgress(msg.arg1);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handle_msg);
        i = 0;
        tv = (TextView)findViewById(R.id.tv);
        seekbar = (SeekBar)findViewById(R.id.seekbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<100){
                    Message m = Message.obtain();
                    m.what = 1;
                    m.arg1 = 1;
                    handler.sendMessage(m);

                    i++;
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
