package com.cookandroid.game.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.cookandroid.game.BoardProfile;
import com.cookandroid.minesweeper.MineSweeper;

public class PauseState extends State {

    public PauseState(BoardProfile profile, MineSweeper mineSweeper, Bitmap[] images) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
        this.paint = new Paint();
        this.images = images;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawSmileButton(UiManager.PAUSE_FACE, canvas, paint);

        int BLOCK_IMAGE_SIZE = mProfile.blockSize();
        int startX = mProfile.startX();
        int startY = mProfile.startY() + BLOCK_IMAGE_SIZE * 2;
        int boardW = mProfile.boardWidth();
        int boardH = mProfile.boardHeight();

        for (int i = 0; i < boardW; i++) {
            for (int j = 0; j < boardH; j++) {

                Rect rect = new Rect(i * BLOCK_IMAGE_SIZE + startX,
                        j * BLOCK_IMAGE_SIZE + startY,
                        i * BLOCK_IMAGE_SIZE + startX + BLOCK_IMAGE_SIZE,
                        j * BLOCK_IMAGE_SIZE + startY + BLOCK_IMAGE_SIZE);

                canvas.drawBitmap(images[UiManager.COVER], null, rect, paint);
            }
        }

        canvas.drawBitmap(images[UiManager.RESUME], null, resume_button.toRect(), paint);
        canvas.drawBitmap(images[UiManager.NEW_GAME], null, new_game_button.toRect(), paint);

        int block_size = mProfile.blockSize();
        paint.setTextSize(block_size/3);
        paint.setColor(Color.BLUE);
        canvas.drawText("Version: " + this.mProfile.getVersion(), this.mProfile.startX(), block_size * 12 + block_size/3, paint);
    }
}