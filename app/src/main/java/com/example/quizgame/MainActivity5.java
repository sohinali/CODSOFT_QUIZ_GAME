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

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener {

    TextView Tv1;
    TextView Tv2;
    Button ans_A2, ans_B2, ans_C2, ans_D2;
    Button submit_Btn2;

    int score=0;
    int totalQuestion = QuestionAnswer2.question2.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Tv1 = findViewById(R.id.total2);
        Tv2 = findViewById(R.id.question2);
        ans_A2 = findViewById(R.id.ans_A2);
        ans_B2 = findViewById(R.id.ans_B2);
        ans_C2 = findViewById(R.id.ans_C2);
        ans_D2 = findViewById(R.id.ans_D2);
        submit_Btn2 = findViewById(R.id.submit_btn2);


        ans_A2.setOnClickListener(this);
        ans_B2.setOnClickListener(this);
        ans_C2.setOnClickListener(this);
        ans_D2.setOnClickListener(this);
        submit_Btn2.setOnClickListener(this);

        Tv1.setText("Total questions : " + totalQuestion);

        loadNewQuestion();
    }     @Override
    public void onClick(View view) {

        ans_A2.setBackgroundColor(Color.WHITE);
        ans_B2.setBackgroundColor(Color.WHITE);
        ans_C2.setBackgroundColor(Color.WHITE);
        ans_D2.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn2){
            if(selectedAnswer.equals(QuestionAnswer2.correctAnswers[currentQuestionIndex])){
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

        Tv2.setText(QuestionAnswer2.question2[currentQuestionIndex]);
        ans_A2.setText(QuestionAnswer2.choices[currentQuestionIndex][0]);
        ans_B2.setText(QuestionAnswer2.choices[currentQuestionIndex][1]);
        ans_C2.setText(QuestionAnswer2.choices[currentQuestionIndex][2]);
        ans_D2.setText(QuestionAnswer2.choices[currentQuestionIndex][3]);

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

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
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
