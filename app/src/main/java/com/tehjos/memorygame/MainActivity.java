package com.tehjos.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean singleCardIsOpen = false;
    private final boolean[][] openedCards = {
            {false, false, false},
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };

    private View previousView;
    private final DrawablesManager drawablesManager = new DrawablesManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawablesManager.shuffle();

        final ImageView[][] imageViews = new ImageView[4][3];
        imageViews[0][0] = findViewById(R.id.imageView1);
        imageViews[0][1] = findViewById(R.id.imageView2);
        imageViews[0][2] = findViewById(R.id.imageView3);
        imageViews[1][0] = findViewById(R.id.imageView4);
        imageViews[1][1] = findViewById(R.id.imageView5);
        imageViews[1][2] = findViewById(R.id.imageView6);
        imageViews[2][0] = findViewById(R.id.imageView7);
        imageViews[2][1] = findViewById(R.id.imageView8);
        imageViews[2][2] = findViewById(R.id.imageView9);
        imageViews[3][0] = findViewById(R.id.imageView10);
        imageViews[3][1] = findViewById(R.id.imageView11);
        imageViews[3][2] = findViewById(R.id.imageView12);

        for (ImageView[] imageView : imageViews) {
            for (int j = 0; j < imageViews[0].length; j++) {
                imageView[j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int tagClicked = Integer.parseInt(view.getTag().toString());
        int row = tagClicked / 3;
        int col = tagClicked % 3;

        // card is already open
        if (openedCards[row][col]) return;

        // open the card
        view.setBackgroundResource(drawablesManager.getResId(tagClicked));

        if (singleCardIsOpen) {
            int previousTag = Integer.parseInt(previousView.getTag().toString());
            if (isTheSameCard(tagClicked, previousTag)) {
                // leave both cards open
                openedCards[row][col] = true;
            } else {
                // close both cards
                closeCards(view, previousView);
                openedCards[previousTag / 3][previousTag % 3] = false;
            }
            previousView = null;
        } else {
            openedCards[row][col] = true;
            previousView = view;
        }
        singleCardIsOpen = !singleCardIsOpen;
    }

    private boolean isTheSameCard(int tag1, int tag2) {
        return drawablesManager.isTheSame(tag1, tag2);
    }

    private void closeCards(View ... views) {
        // TODO: implement a delay while closing the cards
        for (View view : views) {
            view.setBackgroundResource(R.drawable.cardback);
        }
    }

}


