package com.example.cheema.firebasetest;

import com.firebase.client.Firebase;

/**
 * Created by Cheema on 6/4/16.
 */
public class Firebasetest extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

    }
}
