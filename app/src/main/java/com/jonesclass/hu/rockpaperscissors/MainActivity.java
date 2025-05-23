package com.jonesclass.hu.rockpaperscissors;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder dialogBuilder;
    String styleChoice = "Creative";
    Button playButton;
    Button instructionsButton;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playButton = findViewById(R.id.button_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGameIntent = new Intent (getApplicationContext(), GameActivity.class);
                startGameIntent.putExtra("com.jonesclass.hu.RockPaperScissors.OPTION", styleChoice);
                startActivity(startGameIntent);
            }
        });

        Button optionsButton = findViewById(R.id.button_options);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOptionDialog();
            }
        });

        instructionsButton = findViewById(R.id.button_instructions);
        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.setTitle("Instructions");
                dialogBuilder.setMessage(message);
                dialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void selectOptionDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Choose your game style");
        //dialogBuilder.setMessage("Hello World.");
        /*
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        */
        final String[] STYLES = {"Traditional", "Creative"};
        dialogBuilder.setSingleChoiceItems(STYLES, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                styleChoice = STYLES[which];
                if (styleChoice.equals("Traditional")) {
                    Toast.makeText(MainActivity.this, "Scared of Change?", Toast.LENGTH_SHORT).show();
                    message = "Rock beats scissors, scissors beat paper, and paper beats rock.";
                } else {
                    Toast.makeText(MainActivity.this, "Feeling Adventurous?", Toast.LENGTH_SHORT).show();
                    message = "This is inspired by a game I created in 3rd grade. Penguin beats ice, ice beats shark, and shark beats penguin.";
                }
                playButton.setVisibility(View.VISIBLE);
                instructionsButton.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Option Not Changed", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }
}