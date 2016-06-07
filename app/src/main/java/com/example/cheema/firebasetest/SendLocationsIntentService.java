package com.example.cheema.firebasetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by aarshad on 6/7/16.
 */
public class SendLocationsIntentService extends IntentService {

    public SendLocationsIntentService()
    {
        super("SendLocationsIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i("FirebaseTest","Intent Service Started ") ;
        Toast.makeText(getApplicationContext(),"Intent Service Started", Toast.LENGTH_SHORT);


    }
}
