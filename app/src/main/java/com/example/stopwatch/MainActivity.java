package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long stopOffset;
    private boolean running;

    Button startButton;
    Button pauseButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);

        startButton = findViewById(R.id.start_btn);
        pauseButton = findViewById(R.id.pause_btn);
        resetButton = findViewById(R.id.reset_btn);

        startButton.setOnClickListener(this::start);
        resetButton.setOnClickListener(this::reset);
        pauseButton.setOnClickListener(this::pause);

    }

    private void start(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - stopOffset);
            chronometer.start();
            running = true;

            pauseButton.setText("Pause");
        }
    }

    private void pause(View v) {
        if (running) {
            // PAUSE
            chronometer.stop();
            stopOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;

            pauseButton.setText("Resume");
        }
        else {
            // RESUME
            chronometer.setBase(SystemClock.elapsedRealtime() - stopOffset);
            chronometer.start();
            running = true;

            pauseButton.setText("Pause");
        }
    }

    private void stop(View v) {
        if (running) {
            chronometer.stop();
            stopOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    private void reset(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        stopOffset = 0;
        running = false;
        pauseButton.setText("Resume");
    }


}