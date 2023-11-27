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

public class MainActivity6 extends AppCompatActivity implements View.OnClickListener {

    TextView TV1;
    TextView TW1;
    Button ans_A3, ans_B3, ans_C3, ans_D3;
    Button submit_Btn3;

    int score=0;
    int totalQuestion = QuestionAnswer3.question3.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        TV1 = findViewById(R.id.total3);
        TW1 = findViewById(R.id.question3);
        ans_A3 = findViewById(R.id.ans_A3);
        ans_B3 = findViewById(R.id.ans_B3);
        ans_C3 = findViewById(R.id.ans_C3);
        ans_D3 = findViewById(R.id.ans_D3);
        submit_Btn3 = findViewById(R.id.submit_btn3);


        ans_A3.setOnClickListener(this);
        ans_B3.setOnClickListener(this);
        ans_C3.setOnClickListener(this);
        ans_D3.setOnClickListener(this);
        submit_Btn3.setOnClickListener(this);

        TV1.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }     @Override
    public void onClick(View view) {

        ans_A3.setBackgroundColor(Color.WHITE);
        ans_B3.setBackgroundColor(Color.WHITE);
        ans_C3.setBackgroundColor(Color.WHITE);
        ans_D3.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn3){
            if(selectedAnswer.equals(QuestionAnswer3.correctAnswers[currentQuestionIndex])){
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

        TW1.setText(QuestionAnswer3.question3[currentQuestionIndex]);
        ans_A3.setText(QuestionAnswer3.choices[currentQuestionIndex][0]);
        ans_B3.setText(QuestionAnswer3.choices[currentQuestionIndex][1]);
        ans_C3.setText(QuestionAnswer3.choices[currentQuestionIndex][2]);
        ans_D3.setText(QuestionAnswer3.choices[currentQuestionIndex][3]);

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

    void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
