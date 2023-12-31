package com.cookandroid.game.input;

import com.cookandroid.game.BoardProfile;
import com.cookandroid.minesweeper.MineSweeper;

public class IdleState extends State {

    public IdleState(BoardProfile profile, MineSweeper mineSweeper) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
    }

    @Override
    public void onTouch(int x, int y) {
        if (smile_button.in(x, y) || start_game_button.in(x,y)) {
            mineSweeper.play();
            return;
        }
    }
}