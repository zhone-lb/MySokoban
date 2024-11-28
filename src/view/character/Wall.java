package view.character;

import view.Activator;

import java.io.Serializable;

public class Wall extends Item implements Activator, Serializable {
    public Wall(String filename) {
        super(filename);
    }

    @Override
    public void activate() {
        super.activate();
    }

    @Override
    public void move(int dx, int dy, long time, int rate) {

    }
}
