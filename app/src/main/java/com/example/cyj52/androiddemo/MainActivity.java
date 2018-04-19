package com.example.cyj52.androiddemo;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.firstInApp.GuideActivity;
import com.example.firstInApp.MyNewAdapter;
import com.example.processthread.HandlerActivity;
import com.example.serviceDemo.MyBindService;
import com.example.serviceDemo.MyStartService;
import com.example.serviceDemo.ServiceActivity;
import com.example.webview.WebViewActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainActivity extends Activity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private TextView txtv_buttom1,txtv_buttom2,txtv_buttom3;
    private List<TextView> txtvs;
    private List<View> views;
    private MyNewAdapter myNewAdapter;
    LocalActivityManager manager;

    Button btnservice,btnhandle,btnwebview;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFirstIn = false;
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        if(isFirstIn){
            Intent intent_guide = new Intent();
            intent_guide.setClass(MainActivity.this, GuideActivity.class);
            startActivity(intent_guide);
        }
//        //方法一
//        Locale local = new Locale("zh", "CN");
////        Locale locale = Locale.SIMPLIFIED_CHINESE;
//        ResourceBundle bundle = ResourceBundle.getBundle("main.properties",local);
//
//        //方法二
//        Properties properties = new Properties();
////        InputStream is = Classname.class.getClassLoader().getResourceAsStream("Message.properties");
//        InputStream is2 = Context.getAssets().open("main.properties");
//        try {
//            properties.load(is2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String value = properties.getProperty("isFirstIn");


        manager  =new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);
        findView();

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

    private void findView(){
        txtvs = new ArrayList<TextView>();
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(this);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for(int i = 0;i<group.getChildCount();i++){
                    if(group.getChildAt(i).getId() == checkedId){
                        viewPager.setCurrentItem(i);
                        whilteLineVisibility(i);
                        break;
                    }
                }
            }
        });

        txtv_buttom1 = (TextView)findViewById(R.id.txtv_buttom1);
        txtv_buttom2 = (TextView)findViewById(R.id.txtv_butto2);
        txtv_buttom3 = (TextView)findViewById(R.id.txtv_buttom3);

        txtvs.add(txtv_buttom1);
        txtvs.add(txtv_buttom2);
        txtvs.add(txtv_buttom3);
        setPage();
    }

    private void whilteLineVisibility(int position){
        for(int i=0;i<txtvs.size();i++){
            if(i == position){
                txtvs.get(i).setVisibility(View.VISIBLE);
            }else{
                txtvs.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setPage(){
        views = new ArrayList<View>();
        Intent in1 = new Intent(getApplicationContext(),FirstActivity.class);
        views.add(getView("first",in1));
        Intent in2 = new Intent(getApplicationContext(),FirstActivity.class);
        views.add(getView("second",in2));
        Intent in3 = new Intent(getApplicationContext(),FirstActivity.class);
        views.add(getView("third",in3));

        myNewAdapter = new MyNewAdapter(views);
        viewPager.setAdapter(myNewAdapter);
        viewPager.setCurrentItem(0);
    }

    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }
}
