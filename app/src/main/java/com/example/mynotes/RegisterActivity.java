package com.example.mynotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.splashscreen.SplashScreen;


public class RegisterActivity extends AppCompatActivity {
    private EditText email, pass;
    private FirebaseAuth firebaseAuth;
    private SplashScreen splashScreen;

    @SuppressLint("MissingInflatedId") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashScreen = SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_register);
        splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
            @Override
            public boolean shouldKeepOnScreen() {
                return false;
            }
        });
        email = findViewById(R.id.email);

        pass = findViewById(R.id.pwd);
        Button btn_reg = findViewById(R.id.btn_register);
        Button btn_log = findViewById(R.id.btn_login1);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emi = email.getText().toString().trim();
                String pas = pass.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(emi, pas).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Регистрация неуспешна!", Toast.LENGTH_SHORT).show();
                    }
                    }
                });

            }
        });
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        });

    }
    public void showDialog(View v){
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }

}
