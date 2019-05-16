package com.example.rps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        String userChoice = null;
        int clickedId = v.getId();

        if (clickedId == R.id.btnPaper) {
            userChoice = "Paper";
        }
        else if (clickedId == R.id.btnRock) {
            userChoice = "Rock";
        }
        else if (clickedId == R.id.btnScissors) {
            userChoice = "Scissors";
        }


        Random rand = new Random();
        int computerInt = rand.nextInt(2);

        String computerChoice = null;
        switch (computerInt)
        {
            case 0:
                computerChoice = "Paper";
                break;
            case 1:
                computerChoice = "Rock";
                break;
            case 2:
                computerChoice = "Scissors";
                break;
        }

        String results = null;
        if (userChoice == computerChoice)
        {
            results = "Tied!";
        }
        else if ((userChoice == "Paper" && computerChoice == "Rock")
                || (userChoice == "Rock" && computerChoice == "Scissors")
                || (userChoice == "Scissors" && computerChoice == "Paper"))
        {
            results = "Win!";
        }
        else
        {
            results = "Lose!";
        }

        Toast.makeText(MainActivity.this, results, Toast.LENGTH_SHORT).show();
    }
}
