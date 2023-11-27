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

public class MainActivity7 extends AppCompatActivity implements View.OnClickListener {

    TextView Tx1;
    TextView Tx2;
    Button ans_A4, ans_B4, ans_C4, ans_D4;
    Button submit_Btn4;

    int score=0;
    int totalQuestion = QuestionAnswer4.question4.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        Tx1 = findViewById(R.id.total4);
        Tx2 = findViewById(R.id.question4);
        ans_A4 = findViewById(R.id.ans_A4);
        ans_B4 = findViewById(R.id.ans_B4);
        ans_C4 = findViewById(R.id.ans_C4);
        ans_D4 = findViewById(R.id.ans_D4);
        submit_Btn4 = findViewById(R.id.submit_btn4);


        ans_A4.setOnClickListener(this);
        ans_B4.setOnClickListener(this);
        ans_C4.setOnClickListener(this);
        ans_D4.setOnClickListener(this);
        submit_Btn4.setOnClickListener(this);

        Tx1.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }     @Override
    public void onClick(View view) {

        ans_A4.setBackgroundColor(Color.WHITE);
        ans_B4.setBackgroundColor(Color.WHITE);
        ans_C4.setBackgroundColor(Color.WHITE);
        ans_D4.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn4){
            if(selectedAnswer.equals(QuestionAnswer4.correctAnswers[currentQuestionIndex])){
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

        Tx2.setText(QuestionAnswer4.question4[currentQuestionIndex]);
        ans_A4.setText(QuestionAnswer4.choices[currentQuestionIndex][0]);
        ans_B4.setText(QuestionAnswer4.choices[currentQuestionIndex][1]);
        ans_C4.setText(QuestionAnswer4.choices[currentQuestionIndex][2]);
        ans_D4.setText(QuestionAnswer4.choices[currentQuestionIndex][3]);

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
