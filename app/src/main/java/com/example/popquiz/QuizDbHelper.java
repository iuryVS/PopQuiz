package com.example.popquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PopQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "+
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("De quem é a famosa frase “Penso, logo existo”?", "A - Platão", "B - Galileu Galilei", "C - Descartes","D - Sócrates", 3);
        addQuestion(q1);
        Question q2 = new Question("De onde é a invenção do chuveiro elétrico?", "A - Brasil", "B - França", "C - Inglaterra", "D - Itália", 1);
        addQuestion(q2);
        Question q3 = new Question("Qual o menor país do mundo?", "A - Malta", "B - Nauru", "C - Mônaco","D - Vaticano", 4);
        addQuestion(q3);
        Question q4 = new Question("Qual o maior país do mundo?", "A - China", "B - Rússia", "C - Índia","D - Estados Unidos", 2);
        addQuestion(q4);
        Question q5 = new Question("Quantas casas decimais tem o número pi?", "A - Duas", "B - Centenas", "C - Milhares","D - Trilhares", 4);
        addQuestion(q5);
        Question q6 = new Question("Qual o número mínimo de jogadores numa partida de futebol?", "A - 8", "B - 10", "C - 7","D - 5", 3);
        addQuestion(q6);
        Question q7 = new Question("Quanto tempo a luz do Sol demora para chegar à Terra?", "A - 12 minutos", "B - 1 dia", "C - 12 horas","D - 8 minutos", 4);
        addQuestion(q7);
        Question q8 = new Question("Em que período da pré-história o fogo foi descoberto?", "A - Neolítico", "B - Paleolítico", "C - Idade dos Metais","D - Período da Pedra Polida", 2);
        addQuestion(q8);
        Question q9 = new Question("Qual a montanha mais alta do Brasil?", "A - Pico da Neblina", "B - Pico Paraná", "C - Monte Roraima","D - Pico Maior de Friburgo", 1);
        addQuestion(q9);
        Question q10 = new Question("Quem foi o primeiro homem a pisar na Lua?", "A - Yuri Gagarin", "B - Neil Armstrong", "C - Charles Conrad","D - Buzz Aldrin", 2);
        addQuestion(q10);
    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
