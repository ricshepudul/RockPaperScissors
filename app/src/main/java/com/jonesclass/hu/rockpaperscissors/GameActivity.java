package com.jonesclass.hu.rockpaperscissors;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView aiChoiceImageView;
    TextView aiChoiceTextView;
    TextView messageTextView;
    TextView winTextView;
    int playerChoice;
    int aiChoice;

    String style;
    String[] icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        aiChoiceImageView = findViewById(R.id.imageView_aiChoice);
        aiChoiceTextView = findViewById(R.id.textView_aiChoice);
        messageTextView = findViewById(R.id.textView_message);
        winTextView = findViewById(R.id.textView_win);

        ImageButton rockImageButton = findViewById(R.id.imageButton_rock);
        rockImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiChoice();
                playerChoice = 1;
                messageTextView.setText("Your Choice: " + icons[0]);
                findWinner();
            }
        });

        ImageButton paperImageButton = findViewById(R.id.imageButton_paper);
        paperImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiChoice();
                playerChoice = 2;
                messageTextView.setText("Your Choice: " + icons[1]);
                findWinner();
            }
        });

        ImageButton scissorsImageButton = findViewById(R.id.imageButton_scissors);
        scissorsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiChoice();
                playerChoice = 3;
                messageTextView.setText("Your Choice: " + icons[2]);
                findWinner();
            }
        });


        style = getIntent().getStringExtra("com.jonesclass.hu.RockPaperScissors.OPTION");
        assert style != null;
        if (style.equals("Creative")) {
            icons = new String[]{"shark", "ice", "penguin"};
            rockImageButton.setImageResource(R.drawable.shark);
            paperImageButton.setImageResource(R.drawable.ice);
            scissorsImageButton.setImageResource(R.drawable.penguin);
            aiChoiceImageView.setImageResource(R.drawable.shark);
        } else {
            icons = new String[]{"rock", "paper", "scissors"};
        }

    }

    public void aiChoice() {
        Random rNumber = new Random();
        int number = rNumber.nextInt(3);
        if (number == 0) {
            if (style.equals("Creative")) {
                aiChoiceImageView.setImageResource(R.drawable.shark);
            } else {
                aiChoiceImageView.setImageResource(R.drawable.rock);
            }
            aiChoice = 1;
            aiChoiceTextView.setText("AI Choice: " + icons[0]);
        } else if (number == 1) {
            if (style.equals("Creative")) {
                aiChoiceImageView.setImageResource(R.drawable.ice);
            } else {
                aiChoiceImageView.setImageResource(R.drawable.paper);
            }
            aiChoice = 2;
            aiChoiceTextView.setText("AI Choice: " + icons[1]);
        } else {
            if (style.equals("Creative")) {
                aiChoiceImageView.setImageResource(R.drawable.penguin);
            } else {
                aiChoiceImageView.setImageResource(R.drawable.scissors);
            }
            aiChoice = 3;
            aiChoiceTextView.setText("AI Choice: " + icons[2]);
        }
    }

    public void findWinner() {
        if (playerChoice == aiChoice) {
            winTextView.setText("Tie!");
        } else if ((playerChoice - aiChoice == 1) || (playerChoice - aiChoice == -2)) {
            winTextView.setText("You Win!");
        } else {
            winTextView.setText("You Lose!");
        }
    }
}