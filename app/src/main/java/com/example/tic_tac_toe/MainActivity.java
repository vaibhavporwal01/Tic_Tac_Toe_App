package com.example.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    //Player representation
    //0-X
    //1-O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State meaning
    //0-X
    //1-O
    //2-Null

    //Defining Winning Positions- total 8..storing them in a 2D array
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6} };
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());


        if(gameState[tappedImage] == 2 && gameActive){   //Block is empty..represented by 2..then
                                                         // replace it with active player point
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if(activePlayer == 0){
                img.setImageResource(R.drawable.pngwing);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's- Turn Tap To Play");
            }else{
                img.setImageResource(R.drawable.hi);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's- Turn Tap To Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        //Check if any player has won
        for(int [] winPositions: winPositions){
            if(gameState[winPositions[0]] == gameState[winPositions[1]] &&
                    gameState[winPositions[1]] == gameState[winPositions[2]] &&
                    gameState[winPositions[0]] != 2){
                //Somebody has won - Find out who Won
                String winnerStr;
                gameActive = false;
                if(gameState[winPositions [0]] == 0){
                    winnerStr = "X has won";
                    Toast.makeText(this, "X has Won", Toast.LENGTH_SHORT).show();
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }else{
                    winnerStr = "O has Won";
                    Toast.makeText(this, "O has Won", Toast.LENGTH_SHORT).show();
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            }
        }

        //Condition for a draw
        boolean draw = true;
        for (int state : gameState){
            if(state == 2 ){
                draw = false;
                break;
            }
        }

        if(draw && gameActive){
            gameActive = false;  //Game do not move fwd
            TextView status = findViewById(R.id.status);
            status.setText("It's a Draw");
        }

    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0 ;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0); //0 means no img
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        Toast.makeText(this, "Resetting Game", Toast.LENGTH_SHORT).show();

        TextView status = findViewById(R.id.status);
        status.setText("X's- Turn Tap To Play");

    }

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
    }
}