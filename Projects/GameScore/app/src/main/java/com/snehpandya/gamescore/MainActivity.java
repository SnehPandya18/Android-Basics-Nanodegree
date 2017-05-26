package com.snehpandya.gamescore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int foulTeamA = 0;
    int foulTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.snehpandya.gamescore.R.layout.activity_main);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(com.snehpandya.gamescore.R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(com.snehpandya.gamescore.R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void foulForTeamA(int foul) {
        TextView foulView = (TextView) findViewById(R.id.team_a_foul);
        foulView.setText(String.valueOf(foul));
    }

    public void foulForTeamB(int foul) {
        TextView foulView = (TextView) findViewById(R.id.team_b_foul);
        foulView.setText(String.valueOf(foul));
    }

    public void score1A(View view) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    public void score2A(View view) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    public void score3A(View view) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

    public void foul1A(View view) {
        foulTeamA = foulTeamA + 1;
        foulForTeamA(foulTeamA);
    }

    public void score1B(View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    public void score2B(View view) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    public void score3B(View view) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    public void foul1B(View view) {
        foulTeamB = foulTeamB + 1;
        foulForTeamB(foulTeamB);
    }

    public void reset(View view) {
        displayForTeamA(0);
        displayForTeamB(0);
        foulForTeamA(0);
        foulForTeamB(0);
        scoreTeamA = 0;
        scoreTeamB = 0;
        foulTeamA = 0;
        foulTeamB = 0;
    }
}
