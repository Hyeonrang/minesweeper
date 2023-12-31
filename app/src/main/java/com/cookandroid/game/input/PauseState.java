package com.cookandroid.game.input;

import com.cookandroid.game.BoardProfile;
import com.cookandroid.minesweeper.MineSweeper;

public class PauseState extends State {

    public PauseState(BoardProfile profile, MineSweeper mineSweeper) {
        super(profile);
        this.mProfile = profile;
        this.mineSweeper = mineSweeper;
    }

    @Override
    public void onTouch(int x, int y) {
        if (smile_button.in(x, y)) {
            mineSweeper.play();
            return;
        }
        if (resume_button.in(x, y)) {
            mineSweeper.play();
            return;
        }
        if (new_game_button.in(x,y)) {
            mineSweeper.idle();
            return;
        }
    }
}