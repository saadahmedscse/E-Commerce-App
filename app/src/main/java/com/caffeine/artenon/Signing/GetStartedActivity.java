package com.caffeine.artenon.Signing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.caffeine.artenon.R;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        TextView getStarted = findViewById(R.id.btn_get_started);

        getStarted.setOnClickListener(view -> {
            startActivity(new Intent(GetStartedActivity.this, SignUpActivity.class));
            finish();
        });
    }
}