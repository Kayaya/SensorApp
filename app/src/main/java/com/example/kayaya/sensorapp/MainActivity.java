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
    Sensor accelerometer, magneticField;
    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];
    float[] orientation_matrix =  new float[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager;
        sensorManager = (SensorManager)this.getSystemService(this.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == accelerometer){
            //Exponential

            float x,y,z;

            accelerometerValues = sensorEvent.values.clone();

            TextView x_coord = (TextView)findViewById(R.id.tv_x);
            TextView y_coord = (TextView)findViewById(R.id.tv_y);
            TextView z_coord = (TextView)findViewById(R.id.tv_z);

            x = accelerometerValues[0];
            y = accelerometerValues[1];
            z = accelerometerValues[2];

            //Display the results in the text view

            x_coord.setText(String.valueOf(x));
            y_coord.setText(String.valueOf(y));
            z_coord.setText(String.valueOf(z));
        }
        if(sensorEvent.sensor == magneticField){

            float azimuth,pitch,roll;

            magneticFieldValues = sensorEvent.values.clone();

            TextView azimuth_tv = (TextView)findViewById(R.id.tv_azimuth);
            TextView pitch_tv = (TextView)findViewById(R.id.tv_pitch);
            TextView roll_tv = (TextView)findViewById(R.id.tv_roll);

            azimuth = magneticFieldValues[0];
            pitch = magneticFieldValues[1];
            roll = magneticFieldValues[2];

            //Display the results in the text view
            azimuth_tv.setText(String.valueOf(azimuth));
            pitch_tv.setText(String.valueOf(pitch));
            roll_tv.setText(String.valueOf(roll));


        }
        //get rotation
        SensorManager.getRotationMatrix (orientation_matrix, null, accelerometerValues, magneticFieldValues);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
}
