package com.example.firstInApp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.cyj52.androiddemo.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    ViewPager viewPager;
    MyNewAdapter adapter;
    List<View> views;

    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_viewpager);

        btn = (Button)findViewById(R.id.firstIn_btn);

        //不能添加点击事件？？？
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                onDestroy();
//            }
//        });

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        views = new ArrayList<View>();
        LayoutInflater inflater  = LayoutInflater.from(getApplicationContext());

        View view1 = inflater.inflate(R.layout.hello_tab1,null);
        View view2 = inflater.inflate(R.layout.hello_tab2,null);
        View view3 = inflater.inflate(R.layout.hello_tab3,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        adapter = new MyNewAdapter(views);
        viewPager.setAdapter(adapter);


    }

    @Override
    protected void onDestroy() {
        SharedPreferences preferences = getSharedPreferences(
                "pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
        super.onDestroy();
    }

}
