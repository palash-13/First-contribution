package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] position = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};

    int active = 0;

    boolean activeGame = true;

    public void click(View view){

        ImageView iv = (ImageView) view;



        int tagPosition = Integer.parseInt(iv.getTag().toString());

        if(position[tagPosition] == 2 && activeGame) {
            position[tagPosition] = active;

            Log.i("Tag", iv.getTag().toString());

            iv.animate().alpha(1).setDuration(500);

            //0:captain america,1:Iron man,2: empty block

            if (active == 0) {
                iv.setImageResource(R.drawable.captainamerica);
                active = 1;
            } else {
                iv.setImageResource(R.drawable.ironman);
                active = 0;
            }


            for (int[] winningPosition : winningPositions) {
                if (position[winningPosition[0]] == position[winningPosition[1]] && position[winningPosition[1]] == position[winningPosition[2]] && position[winningPosition[0]] != 2) {

                    activeGame = false;

                    String winner = "";

                    if (active == 1) {
                        winner = "Captain America";
                    } else {
                        winner = "Iron Man";
                    }
                    TextView tvResult  = (TextView) findViewById(R.id.tvResult);

                    Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

                    tvResult.setVisibility(View.VISIBLE);

                    btnPlayAgain.setVisibility(View.VISIBLE);

                    tvResult.setText("Winner is " + winner + " Love u 3000");

                }
            }
        }
    }

    public void playAgain(View view) {
        TextView tvResult  = (TextView) findViewById(R.id.tvResult);

        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        tvResult.setVisibility(View.INVISIBLE);

        btnPlayAgain.setVisibility(View.INVISIBLE);

        String winner = "null";

        tvResult.setText("Winner is " + winner + " Love u 3000");

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.setImageDrawable(null);
        }
        for (int i = 0; i < position.length; i++){
            position[i] = 2;
        }
        active = 1;

        activeGame = true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}