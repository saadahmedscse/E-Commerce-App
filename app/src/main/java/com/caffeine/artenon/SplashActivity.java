package com.caffeine.artenon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.caffeine.artenon.Signing.GetStartedActivity;
import com.caffeine.artenon.View.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }

                else {
                    startActivity(new Intent(SplashActivity.this, GetStartedActivity.class));
                    finish();
                }
            }
        }, 1000);
    }
}