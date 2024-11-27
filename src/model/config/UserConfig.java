package model.config;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class UserConfig implements Serializable {
    public static int MOVE_UP = KeyEvent.VK_UP;
    public static int MOVE_DOWN = KeyEvent.VK_DOWN;
    public static int MOVE_LEFT = KeyEvent.VK_LEFT;
    public static int MOVE_RIGHT = KeyEvent.VK_RIGHT;
    public static int REFRESH_RATE = 5;
    public static int GAME_SPEED = 500;
    protected int myMOVE_UP,myMOVE_DOWN,myMOVE_LEFT,myMOVE_RIGHT;
    protected int myREFRESH_RATE,myGAME_SPEED;
    public void reset() {
        MOVE_UP = myMOVE_UP;
        MOVE_DOWN = myMOVE_DOWN;
        MOVE_LEFT = myMOVE_LEFT;
        MOVE_RIGHT = myMOVE_RIGHT;
        REFRESH_RATE = myREFRESH_RATE;
        GAME_SPEED = myGAME_SPEED;
    }

    public void setMyMOVE_UP(int myMOVE_UP) {
        this.myMOVE_UP = myMOVE_UP;
        reset();
    }

}
