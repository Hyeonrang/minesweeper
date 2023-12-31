package com.cookandroid.game.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.util.Log;

import com.cookandroid.game.BoardProfile;
import com.cookandroid.game.MineSweeperView;

import com.cookandroid.minesweeper.GameObserver;
import com.cookandroid.minesweeper.MineSweeper;
import com.cookandroid.minesweeper.R;

public class UiManagerImpl implements UiManager, GameObserver {
    final String TAG = "UiManagerImpl";
    Bitmap[] mTile;
    Context mContext;
    BoardProfile mProfile;
    MineSweeperView.ViewListener listener;

    State uiState;
    State idleState;
    State playState;
    State pauseState;
    State winState;
    State gameoverState;

    int VIBRATE_TIMER = 300;

    public UiManagerImpl(Context context, BoardProfile profile, MineSweeper mineSweeper) {
        mContext = context;
        mProfile = profile;
        beforeInitState();
        initState(mineSweeper);
        mineSweeper.register(this);
    }

    private void initState(MineSweeper mineSweeper) {
        idleState = new IdleState(mProfile, mineSweeper, mTile);
        playState = new PlayState(mProfile, mineSweeper, mTile);
        pauseState = new PauseState(mProfile, mineSweeper, mTile);
        winState = new WinState(mProfile, mineSweeper, mTile);
        gameoverState = new GameOverState(mProfile, mineSweeper, mTile);
        uiState = idleState;
    }

    @Override
    public void setListener(MineSweeperView.ViewListener listener) {
        this.listener = listener;
    }

    private void beforeInitState() {
        loadImage();
    }

    private void loadImage() {
        mTile = new Bitmap[NUMBER_0+10];
        mTile[0] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n0);
        mTile[1] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n1);
        mTile[2] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n2);
        mTile[3] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n3);
        mTile[4] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n4);
        mTile[5] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n5);
        mTile[6] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n6);
        mTile[7] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n7);
        mTile[8] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n8);
        mTile[9] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.n9);
        mTile[10] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.boom);
        mTile[11] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.crash);
        mTile[FLAG] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.flag);
        mTile[QUESTION] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.question);
        mTile[COVER] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cover);

        mTile[IDLE_FACE] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.idle_face);
        mTile[SMILE_FACE] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.play_face);
        mTile[BOOM_FACE] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.boom_face);
        mTile[WIN_FACE] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.win_face);
        mTile[PAUSE_FACE] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pause_face);

        mTile[NEW_GAME] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.newgame);
        mTile[RESUME] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.resume);
        mTile[START_GAME] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.start);

        mTile[FLAG_BOOM_BTN] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.boom_key);
        mTile[BOOM_FLAG_BTN] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.flag_key);

        mTile[NUMBER_0] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn0);
        mTile[NUMBER_0+1] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn1);
        mTile[NUMBER_0+2] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn2);
        mTile[NUMBER_0+3] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn3);
        mTile[NUMBER_0+4] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn4);
        mTile[NUMBER_0+5] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn5);
        mTile[NUMBER_0+6] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn6);
        mTile[NUMBER_0+7] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn7);
        mTile[NUMBER_0+8] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn8);
        mTile[NUMBER_0+9] = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tn9);
    }


    @Override
    public void Notify(int new_state) {
        Log.d(TAG, "New State:" + new_state);
        switch(new_state) {
            case GameObserver.IDLE:
                uiState = idleState;
                break;
            case GameObserver.PLAY:
                uiState = playState;
                listener.update();
                break;
            case GameObserver.PAUSE:
                uiState = pauseState;
                break;
            case GameObserver.WIN:
                uiState = winState;
                break;
            case GameObserver.GAME_OVER:
                uiState = gameoverState;
                makeVibration();
                break;
            default:
                Log.e(TAG,"Error! Unexpected state");
                uiState = idleState;
                break;
        }
    }

    private void makeVibration() {
        Vibrator vibrator = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_TIMER);
    }

    @Override
    public void onDraw(Canvas canvas) {
        uiState.onDraw(canvas);
    }
}