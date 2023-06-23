package com.cookandroid.minesweeper;

public class PauseState extends State {
    public PauseState() {}
    public int getState() {
        return GameObserver.PAUSE;
    }
}
