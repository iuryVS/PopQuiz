package com.example.popquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoverActivity extends AppCompatActivity {

    private EditText email;
    private  Button reset;
    private  FirebaseAuth firebaseAuth;
    private  Button button_return;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recover);
            email = (EditText) findViewById(R.id.email_input);
            reset = (Button) findViewById(R.id.button_reset);
            button_return = (Button) findViewById(R.id.button_return);

            firebaseAuth = FirebaseAuth.getInstance();

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

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Email = email.getText().toString();

                    if(TextUtils.isEmpty(Email)){
                        Toast.makeText(getApplicationContext(),"Campo vazio!",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    firebaseAuth.sendPasswordResetEmail(Email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Link para recuperação de senha enviado por email!",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Erro ao enviar",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });


        }

    }
