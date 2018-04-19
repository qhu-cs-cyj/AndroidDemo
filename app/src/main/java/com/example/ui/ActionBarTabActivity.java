package com.example.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cyj52.androiddemo.R;

/*
* 实现导航标签效果
* */
public class ActionBarTabActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        TextFragment afragment = new TextFragment();
        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("文字");
        tab1.setIcon(R.drawable.ic_launcher_background);
        tab1.setTabListener(new ListenA(afragment));
        actionBar.addTab(tab1);


    }

    private class ListenA implements ActionBar.TabListener{
        private TextFragment mfragment;
        public ListenA(TextFragment fragment){
            mfragment = fragment;
        }


        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.add(R.id.fragment_content,mfragment,null);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(mfragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    private class ListenB implements ActionBar.TabListener{
        private TextFragment mfragment;
        public ListenB(TextFragment fragment){
            mfragment = fragment;
        }


        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.add(R.id.fragment_content,mfragment,null);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(mfragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
}


