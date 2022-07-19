package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int result;
    int count = 0;
    int bestScore = 10;
    int round = 0;

    static int getRandomNumber(int max, int min) {
        return (int)((Math.random() * (max - min)) + min);
    }

    public void makeToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }

    public void scoreCounter(int score) {
        TextView highScore = (TextView) findViewById(R.id.textViewHigh);
        TextView scoreText = (TextView) findViewById(R.id.textViewRecent);
        scoreText.append(score + ", ");
        if(round == 0){
            bestScore = score;
        }
        if(score < bestScore){
            bestScore = score;
        }
        highScore.setText("Best score: " + bestScore);
    }

    public void restart(View view) {
        round++;
        EditText input = (EditText)findViewById(R.id.editId);
        input.setText("");
        TextView winText = (TextView) findViewById(R.id.textView4);
        winText.setText("");//This removes the congrats text after the restart
        scoreCounter(count);

        result = getRandomNumber(1,100);
        count = 0;
        TextView attemptText = (TextView) findViewById(R.id.textView3);
        attemptText.setText("");
    }

    public void clickFunction(View view) {
        int userGuessing;
        EditText variable = (EditText)findViewById(R.id.editId);
        try{
            userGuessing = Integer.parseInt(variable.getText().toString());
        } catch(NumberFormatException ex) {
            makeToast("Please enter a number");
            return;
        }

        TextView winText = (TextView) findViewById(R.id.textView4);
        winText.setText("");//This removes the congrats text after the restart

        TextView attemptText = (TextView) findViewById(R.id.textView3);
        count++;
        attemptText.setText("You tried: " + count + " times");
        //cheat code
        if (userGuessing == 0000) {
            makeToast("The answer is " + result);
        }

        if (userGuessing < result) {
            makeToast("Think of Higher Number, Try Again");
        }
        else if (userGuessing > result) {
            makeToast("Think of Lower Number, Try Again");
        }
        else {
            makeToast("Congratulations, "+ userGuessing + " is the Number.");
            winText.setText("Congratulations!");
            result = getRandomNumber(1,100);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int min = 1;
        int max = 100;
        result = getRandomNumber(min, max);
    }
}