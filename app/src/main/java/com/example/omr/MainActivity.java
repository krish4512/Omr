package com.example.omr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int noOfQuestions=20;
    ImageButton Camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen f = new FullScreen(this);
        setContentView(R.layout.activity_main);
    Camera= findViewById(R.id.ib_camera);
        Camera.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }

        });

    }
    public void displayAnswerKey(View view){
        Intent omrKeyActivity = new Intent(this, OMRKeyActivity.class);

        omrKeyActivity.putExtra("noOfQuestions", noOfQuestions);
        startActivity(omrKeyActivity);
    }
}