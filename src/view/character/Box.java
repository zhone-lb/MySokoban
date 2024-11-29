package view.character;

import view.Activator;

import java.io.Serializable;

public class Box extends Item implements Serializable, Activator {
    public Box(String filename) {
        super(filename);
    }
}
