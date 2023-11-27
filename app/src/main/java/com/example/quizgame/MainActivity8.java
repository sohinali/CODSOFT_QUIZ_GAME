package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener {

    TextView tx1;
    TextView tx2;
    Button ans_A5, ans_B5, ans_C5, ans_D5;
    Button submit_Btn5;

    int score=0;
    int totalQuestion = QuestionAnswer5.question5.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        tx1 = findViewById(R.id.total5);
        tx2 = findViewById(R.id.question5);
        ans_A5 = findViewById(R.id.ans_A5);
        ans_B5 = findViewById(R.id.ans_B5);
        ans_C5 = findViewById(R.id.ans_C5);
        ans_D5 = findViewById(R.id.ans_D5);
        submit_Btn5 = findViewById(R.id.submit_btn5);


        ans_A5.setOnClickListener(this);
        ans_B5.setOnClickListener(this);
        ans_C5.setOnClickListener(this);
        ans_D5.setOnClickListener(this);
        submit_Btn5.setOnClickListener(this);

        tx1.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }     @Override
    public void onClick(View view) {

        ans_A5.setBackgroundColor(Color.WHITE);
        ans_B5.setBackgroundColor(Color.WHITE);
        ans_C5.setBackgroundColor(Color.WHITE);
        ans_D5.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn5){
            if(selectedAnswer.equals(QuestionAnswer5.correctAnswers[currentQuestionIndex])){
                score++;
                Toast.makeText(this,"Correct Answer" , Toast.LENGTH_SHORT).show();
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        tx2.setText(QuestionAnswer5.question5[currentQuestionIndex]);
        ans_A5.setText(QuestionAnswer5.choices[currentQuestionIndex][0]);
        ans_B5.setText(QuestionAnswer5.choices[currentQuestionIndex][1]);
        ans_C5.setText(QuestionAnswer5.choices[currentQuestionIndex][2]);
        ans_D5.setText(QuestionAnswer5.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }
    @Override
    public void onBackPressed() {
        // Assuming HomeActivity.class is your home activity
        Intent intent = new Intent(this, nevigation.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // This flag clears the back stack
        startActivity(intent);
        finish(); // Finish the current activity


    }



}
