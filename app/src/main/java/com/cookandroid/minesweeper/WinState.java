package com.cookandroid.minesweeper;

public class WinState extends State {
    public WinState() {}

    public int getState() {
        return GameObserver.WIN;
    }
}
