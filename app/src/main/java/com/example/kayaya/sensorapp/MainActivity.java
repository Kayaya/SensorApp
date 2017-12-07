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
    Sensor accel, mfield;
    float[] coordinates = new float[3];
    float[] mfield_values = new float[3];
    float[] orientation_matrix =  new float[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager;
        sensorManager = (SensorManager)this.getSystemService(this.SENSOR_SERVICE);

        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mfield = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, mfield, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == accel){
            //Exponential

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
        if(sensorEvent.sensor == mfield){
            mfield_values = sensorEvent.values.clone();
        }
        //SensorManager.getRotationMatrix (orientationMatrix, inclinationMatrix, accelerometerValues, magneticFieldValues);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
}
