package com.example.popquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PopQuiz extends AppCompatActivity {
    
    private Button iniciar;
    private TextView user;
    private Button fechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_quiz);
        iniciar = (Button) findViewById(R.id.iniciar);
        fechar = (Button) findViewById(R.id.fechar);
        user = findViewById(R.id.user);
        user.setText((CharSequence) MainActivity.user);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == iniciar){
                    startActivity(new Intent(getApplicationContext(),
                            QuizActivity.class));
                    finish();
                }
            }
        });

        fechar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });

    }
}

