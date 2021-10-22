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


    private int numClicked = 0;
    private int turnedCardOne;
    private int turnedCardTwo;
    private int tagClickedOne, layoutKeyOne;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12;
    private static boolean[][] pairCheck;
    private View previousView;
    List<Integer> layoutOrder = new ArrayList<>(Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5));




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.shuffle(layoutOrder);
        pairCheck = PairCheck.pairCheck;




        image1= findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);
        image8 = findViewById(R.id.imageView8);
        image9 = findViewById(R.id.imageView9);
        image10 = findViewById(R.id.imageView10);
        image11 = findViewById(R.id.imageView11);
        image12 = findViewById(R.id.imageView12);


        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);
        image9.setOnClickListener(this);
        image10.setOnClickListener(this);
        image11.setOnClickListener(this);
        image12.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int tagClicked = Integer.parseInt(view.getTag().toString());

        if (numClicked == 0 && PairCheck.pairCheck[tagClicked / 3][tagClicked % 3] == false) {
            int layoutKey = Integer.parseInt(view.getTag().toString());
            int drawableID = IconProducer.drawableIDs[layoutOrder.get(layoutKey)];
            view.setBackgroundResource(drawableID);
            turnedCardOne = drawableID;
            PairCheck.pairCheck[tagClicked / 3][tagClicked % 3] = true;
            int tagClickedOne = tagClicked;
            int layoutKeyOne = layoutKey;
            previousView = view;

            numClicked++;

        } else if (numClicked == 1 && PairCheck.pairCheck[tagClicked / 3][tagClicked % 3] == false) {
            int layoutKey = Integer.parseInt(view.getTag().toString());
            int drawableID = IconProducer.drawableIDs[layoutOrder.get(layoutKey)];
            view.setBackgroundResource(drawableID);
            turnedCardTwo = drawableID;
            PairCheck.pairCheck[tagClicked / 3][tagClicked % 3] = true;
            numClicked = 0;

            if (turnedCardOne != turnedCardTwo) {
                view.setBackgroundResource(R.drawable.cardback);
                previousView.setBackgroundResource(R.drawable.cardback);

                PairCheck.pairCheck[tagClickedOne / 3][tagClickedOne % 3] = false;
                PairCheck.pairCheck[tagClickedOne / 3][tagClickedOne % 3] = false;
                turnedCardOne = 0;
                turnedCardTwo = 1;


            } else {

                turnedCardTwo = 1;
                turnedCardOne = 0;
            }


        }


    }
}


