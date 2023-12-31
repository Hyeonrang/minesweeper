package com.cookandroid.minesweeper;

public interface Board {
    public void init();
    public void setBoardInfo(int boardWidth, int boardHeight, int boomCount);
    public int getUnusedFlagCount();
    public boolean isOpen(int x, int y);
    public void open(int x, int y);
    public void setFlag(int x, int y);
    public boolean hasBoom(int x, int y);
    public boolean isFinish();
    public int[][] getBoard();

    public void getGameState(GameInfo gameInfo);
    public void setGameState(GameInfo gameInfo);
}