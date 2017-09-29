package com.example.adityapattani.gitfit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StartWorkout extends AppCompatActivity implements SensorEventListener{

    TextView exercise_name, counter_layout;
    ImageView exercise_img;
    private static final int SENSOR_SENSITIVITY = 4;
    private SensorManager sensorManager;
    private Sensor prox;

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        prox = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        setTitle("Workout");
        Intent intent = getIntent();

        String exe_name = intent.getStringExtra("Value");
        exercise_name = (TextView) findViewById(R.id.exercise_name);
        exercise_img = (ImageView) findViewById(R.id.exe_img);
        counter_layout = (TextView) findViewById(R.id.exe_count);

        exercise_name.setText(exe_name + ":");

        int[] images_pushups = {};
        int[] images_punches = {};
        int[] images_mountainclimbing = {R.drawable.mountainclimbing_1, R.drawable.mountainclimbing_2};
        int[] images_overheadpunches = {};
        int[] images_thightaps = {};
        int[] images_shouldertaps = {};
        int[] images_heeltouch = {};
        int[] images_legraises = {};
        int[] images_halfplank = {R.drawable.half_plank};
        int[] images_sideplank = {R.drawable.side_plank};
        int[] images_squats = {};
        int[] images_lowkick = {};
        int[] images_highkick = {};
        int[] images_sidekick = {};
        int[] images_roundkick = {};
        int[] images_lunges = {};
        int[] resultant = {};

        switch (exe_name){
            case "Pushups":
                resultant = images_pushups;
                break;
            case "Punches":
                resultant = images_punches;
                break;
            case "Mountain Climbing":
                resultant = images_mountainclimbing;
                break;
            case "Overhead Punches":
                resultant = images_overheadpunches;
                break;
            case "Thigh Taps":
                resultant = images_thightaps;
                break;
            case "Shoulder Taps":
                resultant = images_shouldertaps;
                break;
            case "Heel Touch":
                resultant = images_heeltouch;
                break;
            case "Leg Raises":
                resultant = images_legraises;
                break;
            case "Half Plank":
                resultant = images_halfplank;
                break;
            case "Side Plank":
                resultant = images_sideplank;
                break;
            case "Squats":
                resultant = images_squats;
                break;
            case "Low Kick":
                resultant = images_lowkick;
                break;
            case "High Kick":
                resultant = images_highkick;
                break;
            case "Side Kick":
                resultant = images_sidekick;
                break;
            case "Round Kick":
                resultant = images_roundkick;
                break;
            case "Lunges":
                resultant = images_lunges;
                break;
        }

        final int[] resultant_arr = resultant;
        exercise_img.setImageResource(resultant_arr[0]);

        View alertView = getLayoutInflater().inflate(R.layout.alertbox_count, null);
        final EditText counter_alert = (EditText) alertView.findViewById(R.id.counter);

        AlertDialog.Builder builder = new AlertDialog.Builder(StartWorkout.this);
        builder.setPositiveButton("Go For it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String counter_value = counter_alert.getText().toString();
                if (counter_value.equals("")) {
                    Toast.makeText(StartWorkout.this,"Please set a count",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (Integer.parseInt(counter_value) <= 0){
                    Toast.makeText(StartWorkout.this,"Please set a count",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    counter_layout.setText(counter_value);
                    count = Integer.parseInt(counter_value);
                    dialog.dismiss();
                }

            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setView(alertView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                exercise_img.setImageResource(resultant_arr[i]);
                i++;
                if (i > resultant_arr.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 500);
            }
        };
        handler.postDelayed(runnable, 500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, prox, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                count--;
                counter_layout.setText(Integer.toString(count));
                if (counter_layout.getText().toString().equals("0")){
                    AlertDialog.Builder exitalert = new AlertDialog.Builder(StartWorkout.this);
                    exitalert.setPositiveButton("Great", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setTitle("You did it!");
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
