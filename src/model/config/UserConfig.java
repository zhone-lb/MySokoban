package model.config;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class UserConfig implements Serializable {
    public static int MOVE_UP = KeyEvent.VK_UP;
    public static int MOVE_DOWN = KeyEvent.VK_DOWN;
    public static int MOVE_LEFT = KeyEvent.VK_LEFT;
    public static int MOVE_RIGHT = KeyEvent.VK_RIGHT;
    public static int HINT = KeyEvent.VK_T;
    public static int WITHDRAW = KeyEvent.VK_Z;
    public static int REFRESH_RATE = 5;
    public static int GAME_SPEED = 200;
    protected int myMOVE_UP,myMOVE_DOWN,myMOVE_LEFT,myMOVE_RIGHT;
    protected int myREFRESH_RATE,myGAME_SPEED,myHINT,myWITHDRAW;
    public UserConfig() {
        myMOVE_UP = KeyEvent.VK_UP;
        myMOVE_DOWN = KeyEvent.VK_DOWN;
        myMOVE_LEFT = KeyEvent.VK_LEFT;
        myMOVE_RIGHT = KeyEvent.VK_RIGHT;
        myGAME_SPEED = 200;
        myHINT = KeyEvent.VK_T;
        myREFRESH_RATE = 5;
        myWITHDRAW = KeyEvent.VK_Z;
        reset();
    }
    public void reset() {
        MOVE_UP = myMOVE_UP;
        MOVE_DOWN = myMOVE_DOWN;
        MOVE_LEFT = myMOVE_LEFT;
        MOVE_RIGHT = myMOVE_RIGHT;
        REFRESH_RATE = myREFRESH_RATE;
        GAME_SPEED = myGAME_SPEED;
        HINT = myHINT;
        WITHDRAW = myWITHDRAW;
    }

    public void setMyMOVE_UP(int myMOVE_UP) {
        this.myMOVE_UP = myMOVE_UP;
        reset();
    }

    public void setMyMOVE_DOWN(int myMOVE_DOWN) {
        this.myMOVE_DOWN = myMOVE_DOWN;
        reset();
    }

    public void setMyMOVE_LEFT(int myMOVE_LEFT) {
        this.myMOVE_LEFT = myMOVE_LEFT;
        reset();
    }

    public void setMyMOVE_RIGHT(int myMOVE_RIGHT) {
        this.myMOVE_RIGHT = myMOVE_RIGHT;
        reset();
    }

    public void setMyGAME_SPEED(int myGAME_SPEED) {
        this.myGAME_SPEED = myGAME_SPEED;
        reset();
    }

    public void setMyWITHDRAW(int myWITHDRAW) {
        this.myWITHDRAW = myWITHDRAW;
        reset();
    }

    public void setMyREFRESH_RATE(int myREFRESH_RATE) {
        this.myREFRESH_RATE = myREFRESH_RATE;
        reset();
    }

    public void setMyHINT(int myHINT) {
        this.myHINT = myHINT;
        reset();
    }
}
