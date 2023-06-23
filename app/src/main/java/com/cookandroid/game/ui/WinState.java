package com.cookandroid.game.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.cookandroid.game.BoardProfile;
import com.cookandroid.minesweeper.MineSweeper;

public class WinState extends State {

    public WinState(BoardProfile profile, MineSweeper mineSweeper, Bitmap[] images) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
        this.paint = new Paint();
        this.images = images;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawSmileButton(UiManager.WIN_FACE, canvas, paint);
    }
}