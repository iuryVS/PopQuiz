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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText email;
    private Button button_register;
    private Button button_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.signup_email_input);
        password = (EditText) findViewById(R.id.signup_password_input);
        button_register = (Button) findViewById(R.id.button_register);
        button_return = (Button) findViewById(R.id.button_return);
        mAuth = FirebaseAuth.getInstance();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_register){
                    RegisterUser();

                }
            }
        });
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_return){
                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));
                    finish();
                }
            }
        });
    }


    public void RegisterUser() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Um campo está vazio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Um campo está vazio", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                //start Profile Activity here
                                Toast.makeText(RegisterActivity.this, "Registrado com Sucesso!",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Erro ao Registrar, tente novamente!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}