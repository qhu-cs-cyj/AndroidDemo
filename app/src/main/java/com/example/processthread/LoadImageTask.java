package com.example.processthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.RelativeLayout;

import com.example.cyj52.androiddemo.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LoadImageTask extends AsyncTask<String,Void,Bitmap> {
    //调用此对象的execute()方法执行耗时操作

    private RelativeLayout pmbrl;

    LoadImageTask(RelativeLayout pmbrl){
        this.pmbrl = pmbrl;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap image = null;
        try{
            System.out.println(strings[0]);
            URL url = new URL(strings[0]);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            image = BitmapFactory.decodeStream(is);
            is.close();
            return image;
        }catch (NullPointerException e){
            System.out.println("nullp");
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //下载为空，将控件背景设为默认图片
        if(bitmap == null){
            pmbrl.setBackgroundResource(R.drawable.ic_launcher_background);
        }else{
            BitmapDrawable d = new BitmapDrawable(bitmap);
            pmbrl.setBackgroundDrawable(d);
        }
    }
}
