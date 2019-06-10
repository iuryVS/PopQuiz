package com.example.popquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    public static String user;
    private EditText password;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button button;
    private Button newuser;
    private Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.login_email_input);
        password = (EditText) findViewById(R.id.login_password_input);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        button = (Button) findViewById(R.id.login);
        newuser = (Button) findViewById(R.id.newuser);
        reset = (Button) findViewById(R.id.esquecisenha);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button) {
                    LoginUser();
                }
            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == newuser){
                    startActivity(new Intent(getApplicationContext(),
                            RegisterActivity.class));
                    finish();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == reset){
                    startActivity(new Intent(getApplicationContext(),
                            RecoverActivity.class));
                    finish();
                }
            }
        });

    }
    public void LoginUser () {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Campo está vazio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Campo está vazio", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();
                            finish();
                            user = email.getText().toString().trim();
                            startActivity(new Intent(getApplicationContext(),
                                    PopQuiz.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Erro ao fazer Login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}