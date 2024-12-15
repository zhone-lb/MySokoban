package controller.level;

import model.algorithm.Map;
import view.Activator;

import java.io.Serializable;

public class Game_2048 extends NormalFrame implements Serializable, Activator {
    public Game_2048(Map originMap) {
        super(originMap);

    }
}
