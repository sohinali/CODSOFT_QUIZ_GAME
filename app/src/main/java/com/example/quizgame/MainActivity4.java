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

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

    TextView tv1;
    TextView tv2;
    Button ans_A1, ans_B1, ans_C1, ans_D1;
    Button submit_Btn1;

    int score=0;
    int totalQuestion = QuestionAnswer1.question1.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv1 = findViewById(R.id.total1);
        tv2 = findViewById(R.id.question1);
        ans_A1 = findViewById(R.id.ans_A1);
        ans_B1 = findViewById(R.id.ans_B1);
        ans_C1 = findViewById(R.id.ans_C1);
        ans_D1 = findViewById(R.id.ans_D1);
        submit_Btn1 = findViewById(R.id.submit_btn1);


        ans_A1.setOnClickListener(this);
        ans_B1.setOnClickListener(this);
        ans_C1.setOnClickListener(this);
        ans_D1.setOnClickListener(this);
        submit_Btn1.setOnClickListener(this);

        tv1.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }     @Override
        public void onClick(View view) {

            ans_A1.setBackgroundColor(Color.WHITE);
            ans_B1.setBackgroundColor(Color.WHITE);
            ans_C1.setBackgroundColor(Color.WHITE);
            ans_D1.setBackgroundColor(Color.WHITE);

            Button clickedButton = (Button) view;
            if(clickedButton.getId()==R.id.submit_btn1){
                if(selectedAnswer.equals(QuestionAnswer1.correctAnswers[currentQuestionIndex])){
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

            tv2.setText(QuestionAnswer1.question1[currentQuestionIndex]);
            ans_A1.setText(QuestionAnswer1.choices[currentQuestionIndex][0]);
            ans_B1.setText(QuestionAnswer1.choices[currentQuestionIndex][1]);
            ans_C1.setText(QuestionAnswer1.choices[currentQuestionIndex][2]);
            ans_D1.setText(QuestionAnswer1.choices[currentQuestionIndex][3]);

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
