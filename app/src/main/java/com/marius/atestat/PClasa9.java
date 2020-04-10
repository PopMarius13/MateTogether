package com.marius.atestat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class PClasa9 extends AppCompatActivity {

    public static final String EXTRA_SCORE="extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 180000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView getTextViewQuestionCount;
    private TextView textViewCountDown;
    private  TextView textDificultate;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button button;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaulCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private  int questionCounter;
    private  int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pclasa9);


        AdView madview;
        madview = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9513540471540461~7846055766");
        AdRequest adRequest = new AdRequest.Builder().build();
        madview.loadAd(adRequest);


        textViewQuestion= findViewById(R.id.intrebare);
            textViewScore=findViewById(R.id.nota);
            textViewCountDown=findViewById(R.id.time);
            getTextViewQuestionCount=findViewById(R.id.nrproblema);
            textDificultate = findViewById(R.id.text_dificultate);
            rbGroup=findViewById(R.id.radiogroup);
            rb1=findViewById(R.id.A);
            rb2=findViewById(R.id.B);
            rb3=findViewById(R.id.C);
            button=findViewById(R.id.confirm);

            textColorDefaultRb = rb1.getTextColors();
            textColorDefaulCd = textViewCountDown.getTextColors();

            Intent intent = getIntent();
            String dificultate = intent.getStringExtra(ProblemeActivity.EXTRA_DIFICULTATE);
            textDificultate.setText("Dificultate: "+ dificultate);

            if(savedInstanceState == null) {
                QuizDbHelper dbHelper = new QuizDbHelper(this);
                questionList = dbHelper.getQuestions( dificultate);
                questionCountTotal = questionList.size();
                Collections.shuffle(questionList);
                showNextQuestion();
            }else {
                questionList=  savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
                questionCountTotal = questionList.size();
                questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
                currentQuestion =  questionList.get(questionCounter - 1 );
                score = savedInstanceState.getInt(KEY_SCORE);
                timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
                answered = savedInstanceState.getBoolean(KEY_ANSWERED);

                if(!answered){
                    starCountDown();
                }else{
                    updateCountDownText();
                    showSolution();
                }
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!answered) {

                        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                            checkAnswer();
                        } else {
                            Toast.makeText(PClasa9.this, "Te rog selecteză un răspuns", Toast.LENGTH_SHORT).show();
                        }
                    }
                        else {
                            showNextQuestion();
                        }
                }
            });
    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter<questionCountTotal)
        {
            currentQuestion=questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            getTextViewQuestionCount.setText("Problema: " + questionCounter + "/" + questionCountTotal );
            answered = false;
            button.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            starCountDown();
        }
        else {
            finishQuiz();
        }
    }

    private  void starCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis /1000) /60;
        int second = (int) (timeLeftInMillis/1000)%60;

        String timeFormatted =String.format(Locale.getDefault(), "%02d:%02d",minutes,second);

        textViewCountDown.setText(timeFormatted);
        if(timeLeftInMillis<30000) {
            textViewCountDown.setTextColor(Color.RED);
        }else {
            textViewCountDown.setTextColor(textColorDefaulCd);
        }
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected =findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr= rbGroup.indexOfChild(rbSelected)+1;

        if(answerNr == currentQuestion.getAnswerNr())
        {
            score++;
            textViewScore.setText("Scor: "+ score);
        }

        showSolution();

    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Răspunsul 1 este corect");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Răspunsul 2 este corect");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Răspunsul 3 este corect");
                break;
        }

        if(questionCounter<questionCountTotal){
            button.setText("Next");
        }else{
            button.setText("Finish");
        }
    }

    private  void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 >System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(this,"Termină problemele",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer !=null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}