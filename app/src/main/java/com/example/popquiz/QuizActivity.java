package com.example.popquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;
    private List<Question> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    public static int score;
    private boolean answered;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.responder);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()|| rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Selecione uma resposta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }
    private void showNextQuestion() {
           rbGroup.clearCheck();

        if (questionCounter < 5) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            answered = false;
            buttonConfirmNext.setText("RESPONDER");

        } else {
            finishQuiz();
        }
    }
    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;

        }

        showSolution();
    }

    private void showSolution() {

        switch (currentQuestion.getAnswerNr()) {
            case 1:

                textViewQuestion.setText("Item A era a resposta correta. PTS: "+score);
                break;
            case 2:

                textViewQuestion.setText("Item B era a resposta correta. PTS: "+score);
                break;
            case 3:

                textViewQuestion.setText("Item C era a resposta correta. PTS: "+score);
                break;
            case 4:

                textViewQuestion.setText("Item D era a resposta correta. PTS: "+score);
                break;
        }

        if (questionCounter < 5) {
            buttonConfirmNext.setText("PRÓXIMA");
        } else {
            buttonConfirmNext.setText("FIM DE JOGO");

        }
    }
    private void finishQuiz() {
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == buttonConfirmNext){

                    startActivity(new Intent(getApplicationContext(),
                            ResultActivity.class));
                    finish();
                }
            }
        });
    }

}
