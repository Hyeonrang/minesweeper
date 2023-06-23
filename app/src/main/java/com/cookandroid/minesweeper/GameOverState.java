package com.cookandroid.minesweeper;

public class GameOverState extends State{
    public GameOverState() {}
    public int getState() {
        return GameObserver.GAME_OVER;
    }
}
