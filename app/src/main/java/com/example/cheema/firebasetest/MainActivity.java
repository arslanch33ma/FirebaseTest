package com.example.cheema.firebasetest;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView textView ;
    Button btnSunny;
    Button btnFoggy ;
    Button btnRunService ;
    Button btnPushChild;
    Button btnRunIntentService ;


    Firebase baseURL ;
    Firebase fRef ;
    Firebase messagesRef  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

         textView = (TextView) findViewById(R.id.textViewCondition);
         btnSunny = (Button) findViewById(R.id.btnSunny);
         btnFoggy = (Button) findViewById(R.id.btnFoggy);
        btnRunService = (Button) findViewById(R.id.btnRunService);
        btnPushChild = (Button) findViewById(R.id.btnPushChild);
        btnRunIntentService = (Button) findViewById(R.id.btnRunIntentService);



        baseURL = new Firebase("https://torrid-inferno-2386.firebaseio.com");

        fRef = new Firebase("https://torrid-inferno-2386.firebaseio.com/List");
        messagesRef = baseURL.child("messages");




        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> mp = dataSnapshot.getValue(Map.class);
                String hello = mp.get("hello");
                String world = mp.get("world");
                Toast toast = Toast.makeText(getApplicationContext(), hello + " : " + world, Toast.LENGTH_LONG);
                toast.show();
                Log.v("Json Values: ", hello + " : "+ world);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        fRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String text = dataSnapshot.getValue(String.class);
                textView.setText(text);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        btnFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fRef.setValue("Foggy");
            }

        });
        btnSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fRef.setValue("Sunny");
            }
        });
        btnRunService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), SendLocationsActivity.class);
                startActivity(i);

            }
        });
        btnPushChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Working ... Accessing subchilds.
//                final HashMap<String, Object> map1 = new HashMap<>();
//                map1.put("/user1rsvp/hey/", "going");
//                map1.put("name", "jru");

               // messagesRef.updateChildren(map1);


            }
        });
        btnRunIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SendLocationsIntentService.class);
                startService(i);
            }
        });






    }
}
