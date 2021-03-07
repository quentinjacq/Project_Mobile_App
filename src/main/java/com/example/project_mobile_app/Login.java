package com.example.project_mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import androidx.biometric.BiometricPrompt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    //UI Views

    private TextView authStatusTv;
    private Button authBtn;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Init UI Views
        authStatusTv = findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);

        //Init biometric
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //error authentification, stop tasks that requires auth
                authStatusTv.setText("Authentification error");
                Toast.makeText(Login.this, "Authentification error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Authentification succeed, continue tasks that requires auth
                authStatusTv.setText("Authentification succeed !");
                Toast.makeText(Login.this, "Authentification succeed !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //failed authentificating, stop tasks that requires auth
                authStatusTv.setText("Authentification failed...");
                Toast.makeText(Login.this, "Authentification failed...", Toast.LENGTH_SHORT).show();
            }
        });

        //Setup title, description on auth dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentification")
                .setSubtitle("Login using finerprint authentification")
                .setSubtitle("Login using finerprint authentification")
                .setDeviceCredentialAllowed(true)
                //.setNegativeButtonText("UserApp Password")
                .build();

        //Handle authBtn start authentification
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show auth dialog
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }
}