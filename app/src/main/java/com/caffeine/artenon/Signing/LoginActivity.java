package com.caffeine.artenon.Signing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.caffeine.artenon.R;
import com.caffeine.artenon.View.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private TextView login, signUp;
    private String Email, Password;
    private ProgressBar progressBar;

    private Dialog popup;
    private static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.btn_sign_up);
        progressBar = findViewById(R.id.progress_bar);
        popup = new Dialog(this);

        signUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            finish();
        });

        login.setOnClickListener(view -> {
            Email = email.getText().toString();
            Password = pass.getText().toString();
            if (informationIsValid()){
                login.setText("");
                progressBar.setVisibility(View.VISIBLE);

                FirebaseAuth.getInstance().signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            login.setText("Login");
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            login.setText("Login");
                            showPopupDialog(
                                    "An error occurred while signing you in, or the password you entered is incorrect"
                            );
                        }
                    }
                });
            }
        });
    }

    private boolean informationIsValid(){
        boolean v = true;

        if (Email.isEmpty()){
            email.setError("Field cannot be empty");
            v = false;
        }

        else if (!Email.matches(EMAIL_PATTERN)){
            email.setError("Invalid email address");
            v = false;
        }

        else if (Password.isEmpty()){
            pass.setError("Field cannot be empty");
            v = false;
        }

        return v;
    }

    private void showPopupDialog(String m){
        popup.setContentView(R.layout.popup_dialog);
        popup.setCancelable(false);

        TextView message, ok;
        message = popup.findViewById(R.id.popup_message);
        ok = popup.findViewById(R.id.action_ok);
        message.setText(m);

        ok.setOnClickListener(view -> {
            popup.dismiss();
        });

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }
}