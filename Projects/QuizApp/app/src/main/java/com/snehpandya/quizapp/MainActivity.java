package com.snehpandya.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View view) {

        RadioButton ansOne = (RadioButton) findViewById(R.id.q1a1);
        boolean isa1 = ansOne.isChecked();

        RadioButton ansFour = (RadioButton) findViewById(R.id.q2a2);
        boolean isa4 = ansFour.isChecked();

        RadioButton ansSix = (RadioButton) findViewById(R.id.q3a2);
        boolean isa6 = ansSix.isChecked();

        RadioButton ansSeven = (RadioButton) findViewById(R.id.q4a1);
        boolean isa7 = ansSeven.isChecked();

        RadioButton ansNine = (RadioButton) findViewById(R.id.q5a1);
        boolean isa9 = ansNine.isChecked();

        RadioButton ansEleven = (RadioButton) findViewById(R.id.q6a1);
        boolean isa11 = ansEleven.isChecked();

        EditText answerField = (EditText) findViewById(R.id.answer_edittext);
        String ans = answerField.getText().toString();

        CheckBox lamborghiniCheckbox = (CheckBox) findViewById(R.id.lamborghini_checkbox);
        boolean isLamborghini = lamborghiniCheckbox.isChecked();

        CheckBox ferrariCheckbox = (CheckBox) findViewById(R.id.ferrari_checkbox);
        boolean isFerrari = ferrariCheckbox.isChecked();

        int calculate = calculateAnswer(isa1, isa4, isa6, isa7, isa9, isa11, isLamborghini, isFerrari, ans);
        String calculateMessage = createSummary(ans, calculate);

        displayMessage(calculateMessage);
    }

    public int calculateAnswer(boolean ans1, boolean ans4, boolean ans6, boolean ans7, boolean ans9, boolean ans11, boolean aLamborghini, boolean aFerrari, String answer1) {
        int answer = 0;

        if (ans1) {
            answer = answer + 1;
        }
        if (ans4) {
            answer = answer + 1;
        }
        if (ans6) {
            answer = answer + 1;
        }
        if (ans7) {
            answer = answer + 1;
        }
        if (ans9) {
            answer = answer + 1;
        }
        if (ans11) {
            answer = answer + 1;
        }
        if (aFerrari && aLamborghini) {
            answer = answer + 1;
        }
        if (Objects.equals(answer1, "Jack Ma")) {
            answer = answer + 1;
        } else {
            Toast.makeText(this, "Please type correct answer!", Toast.LENGTH_SHORT).show();
        }
        return answer;
    }

    public String createSummary(String answer, int calculate) {
        String messageIn = "Hello !";
        messageIn += "\nYour answer is: " + answer;
        messageIn += "\nRight answers: " + calculate;
        Toast.makeText(this, "You have answered " + calculate + " correct answers", Toast.LENGTH_SHORT).show();
        return messageIn;
    }

    public void reset(View view) {
        displayMessage(null);
    }

    public void displayMessage(String message) {
        this.message = message;
        TextView summaryTextView = (TextView) findViewById(R.id.summary_textview);
        if (summaryTextView != null) {
            summaryTextView.setText(message);
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}