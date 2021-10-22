package com.tehjos.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawablesManager {
    private final List<Integer> list;

    public DrawablesManager() {
        list = new ArrayList<>();
        int[] drawableIDs = {
                R.drawable.baseball,
                R.drawable.catchy,
                R.drawable.catchy,
                R.drawable.swing,
                R.drawable.field,
                R.drawable.helmet
        };
        for (int drawableID : drawableIDs) {
            list.add(drawableID);
            list.add(drawableID);
        }
    }

    public void shuffle() {
        Collections.shuffle(list);
    }

    public boolean isTheSame(int tag1, int tag2) {
        return list.get(tag1).equals(list.get(tag2));
    }

    public int getResId(int tag) {
        return list.get(tag);
    }
}
