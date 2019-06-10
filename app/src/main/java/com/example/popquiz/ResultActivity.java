package com.example.popquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView gameover;
    private TextView resultado;
        private Button inicio;
    private Button fechar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        gameover = findViewById(R.id.gameover);
        resultado = findViewById(R.id.resultado);
        inicio = (Button) findViewById(R.id.iniciar);
        fechar = (Button) findViewById(R.id.fechar);
        resultado.setText("Você acertou "+QuizActivity.score+" de 5 questões");



        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == inicio){
                    startActivity(new Intent(getApplicationContext(),
                            PopQuiz.class));
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
