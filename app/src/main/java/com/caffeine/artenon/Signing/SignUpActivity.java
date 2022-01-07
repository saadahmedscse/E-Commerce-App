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

import com.caffeine.artenon.Model.UserDetails;
import com.caffeine.artenon.R;
import com.caffeine.artenon.View.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText email, pass, confirmPass;
    private TextView loginBtn, signUpBtn;
    private ProgressBar progressBar;
    private String Email, Password, ConfirmPassword;
    private static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    private DatabaseReference ref;
    private Dialog popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        gettingLayoutIDs();
        onClickListeners();
    }

    private void gettingLayoutIDs(){
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_password);
        signUpBtn = findViewById(R.id.btn_sign_up);
        loginBtn = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progress_bar);

        ref = FirebaseDatabase.getInstance("https://artenon-4039e-default-rtdb.firebaseio.com").getReference().child("Artenon").child("Users");
        popup = new Dialog(this);
    }

    private void assignItems(){
        Email = email.getText().toString();
        Password = pass.getText().toString();
        ConfirmPassword = confirmPass.getText().toString();
    }

    private void onClickListeners(){
        loginBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });

        signUpBtn.setOnClickListener(view -> {
            assignItems();
            if (informationIsValid()){
                signUpBtn.setText("");
                progressBar.setVisibility(View.VISIBLE);
                signUpUser();
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

        else if (Password.length() < 8 || Password.length() > 20){
            pass.setError("Password should be 8 to 20 characters long");
            v = false;
        }

        else if (ConfirmPassword.isEmpty()){
            confirmPass.setError("Field cannot be empty");
            v = false;
        }

        else if (!ConfirmPassword.equals(Password)){
            confirmPass.setError("Password didn't match");
            v = false;
        }

        return v;
    }

    private void signUpUser(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                UserDetails user = new UserDetails(FirebaseAuth.getInstance().getUid(), Email, Password, Long.toString(System.currentTimeMillis()));
                if (task.isSuccessful()){
                    ref.child(FirebaseAuth.getInstance().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                                finish();
                            }

                            else {
                                progressBar.setVisibility(View.GONE);
                                signUpBtn.setText("Sign Up");
                                showPopupDialog(
                                        "An error occurred while creating your account, please try again later"
                                );
                            }
                        }
                    });
                }

                else {
                    progressBar.setVisibility(View.GONE);
                    signUpBtn.setText("Sign Up");
                    showPopupDialog(
                            "An error occurred while creating your account or the email you entered is already registered"
                    );
                }
            }
        });
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