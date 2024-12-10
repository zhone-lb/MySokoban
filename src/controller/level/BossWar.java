package controller.level;

import view.Activator;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public class BossWar extends NormalFrame implements Serializable, Activator {
    protected Thread BossMover, HeroMover;

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);

    }
}
