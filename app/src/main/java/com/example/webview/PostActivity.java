package com.example.webview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.cyj52.androiddemo.R;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostActivity extends Activity {
    TextView posttv;
    String urlString;
    private Handler handler= new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    posttv.setText(msg.obj.toString());
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        posttv = (TextView)findViewById(R.id.posttv);
        urlString = "http://www.baidu.com";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setUseCaches(false);
                    connection.connect();

                    OutputStream os = connection.getOutputStream();
                    OutputStreamWriter ow = new OutputStreamWriter(os);
                    BufferedWriter writer = new BufferedWriter(ow);
                    writer.write("page=1");
                    writer.flush();
                    writer.close();
                    ow.close();
                    os.close();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isreader = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isreader);
                    String line = null;
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine()) != null){
                        sb.append(line).append("\n");
                    }

                    reader.close();
                    isreader.close();
                    is.close();
                    connection.disconnect();

                    Message m = new Message();
                    m.what = 2;
                    m.obj = sb;
                    handler.sendMessage(m);
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
