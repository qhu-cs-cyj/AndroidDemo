package com.example.sensorDemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cyj52.androiddemo.R;

import org.jetbrains.annotations.Nullable;

public class SensorActivity extends Activity {
    private Sensor accelerometer;
    private SensorEventListener sensorEventListener;
    private TextView text;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        text = (TextView)findViewById(R.id.sensor_textView);
        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                String msg = String.format("x: %8.4f\nY: %8.4f\nZ: %8.4f\n",event.values[0],event.values[1],event.values[2]);
                text.setText(msg);
                text.invalidate();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(sensorEventListener,accelerometer,SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener,accelerometer);
        super.onPause();
    }
}
