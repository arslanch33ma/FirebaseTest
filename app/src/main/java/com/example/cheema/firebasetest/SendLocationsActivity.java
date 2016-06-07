package com.example.cheema.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SendLocationsActivity extends AppCompatActivity {

    Button btnStart ;
    Button btnStop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send_locations);

       btnStop = (Button) findViewById(R.id.btnStopService);
       btnStart = (Button) findViewById(R.id.btnStartService);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getApplicationContext(), SendLocationsService.class);
                startService(start);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), SendLocationsService.class));
            }
        });


    }



}
