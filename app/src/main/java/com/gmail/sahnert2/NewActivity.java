package com.gmail.sahnert2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public final class NewActivity extends AppCompatActivity {
    private int startNum; // COULD MAKE DIFFERENT LEVELS AND CHANGE THE DIFFICULTY HERE(ie 10 instead of 100)
    private int secondNum;
    private int ans;
    private String operation;
    private static int score = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game); // app/src/main/res/layout/activity_new_game.xml
        //Instructions: perform the given operation on your number and round down to the nearest int
        generateNumber();
        Button checkAnswer = findViewById(R.id.checkAnswer);
        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userAnswer = findViewById(R.id.userAnswer);
                int compare = Integer.parseInt(userAnswer.getText().toString());
                if (compare == ans) {
                    generateNumber();
                    score++;
                } else {
                    endGame();
                }
            }
        });
    }
    private void generateNumber() {
        startNum = (int) (Math.random() * 100);
        secondNum = (int) (Math.random() * 100);
        switch (pemdas()) {
            case(1):
                operation = " multiplied by ";
                ans = (startNum * secondNum);
                break;
            case(2):
                operation = " divided by ";
                ans = (int) (startNum / secondNum);
                break;
            case(3):
                operation = " minus ";
                if (secondNum > startNum) {
                    int swap = secondNum;
                    secondNum = startNum;
                    startNum = swap;
                }
                ans = (startNum - secondNum);
                break;
            default:
                ans = (startNum + secondNum);
                operation = " plus ";
        }
        TextView tellTheUser = findViewById(R.id.inst);
        String instIntro = "Instructions - enter the answer to the following question below rounded down to the nearest integer: ";
        String tellUser = instIntro + Integer.toString(startNum) + operation + String.valueOf(secondNum) + " equals?";
        tellTheUser.setText(tellUser);
    }
    private int pemdas() {
        double decimal = (4 * Math.random());
        return (int) decimal;
    }
    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You were wrong :(... upload score?");
        builder.setNegativeButton("Upload", null); //need to put webAPI stuff
        builder.setPositiveButton("Go back to home", null); //just go back home
        builder.create().show();
    }
//    private void correct() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Correct!");
//        builder.setNegativeButton("Yes, continue!", null); //refresh somehow
//        builder.setPositiveButton("Go back to home", null); //just go back home
//        builder.create().show();
//    }
}
