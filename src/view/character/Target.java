package view.character;

import view.Activator;

import java.io.Serializable;

public class Target extends Item implements Serializable, Activator {
    public Target(String filename) {
        super(filename);
    }
}
