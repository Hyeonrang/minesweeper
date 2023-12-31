package com.cookandroid.game.input;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.cookandroid.game.BoardProfile;
import com.cookandroid.minesweeper.MineSweeper;

public class GameOverState extends State {

    public GameOverState(BoardProfile profile, MineSweeper mineSweeper) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
    }

    @Override
    public void onTouch(int x, int y) {
        if (smile_button.in(x, y)) {
            mineSweeper.idle();
            return;
        }
    }
}