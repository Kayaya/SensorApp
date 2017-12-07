package com.example.kayaya.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager;
        sensorManager = (SensorManager)this.getSystemService(this.SENSOR_SERVICE);

        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] coordinates = new float[3];
       float x,y,z;

        coordinates = sensorEvent.values.clone();

        TextView x_coord = (TextView)findViewById(R.id.tv_x);
        TextView y_coord = (TextView)findViewById(R.id.tv_y);
        TextView z_coord = (TextView)findViewById(R.id.tv_z);

        x = coordinates[0];
        y = coordinates[1];
        z = coordinates[2];

        //Display the results in the text view

        x_coord.setText(String.valueOf(x));
        y_coord.setText(String.valueOf(y));
        z_coord.setText(String.valueOf(z));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
}
