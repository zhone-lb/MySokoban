package view.character;

import view.Activator;

import java.awt.event.*;
import java.io.Serializable;

public class Hero extends Item implements Activator, Serializable {
    public Hero(String filename) {
        super(filename);
    }

    @Override
    public void activate() {
        super.activate();
        setVisible(true);
        setFocusable(true);
        setEnabled(true);
//        addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                super.focusLost(e);
//                requestFocus();
//            }
//        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println("Typed " + e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> move(0,-100,500,5);
                    case KeyEvent.VK_S -> move(0,100,500,5);
                    case KeyEvent.VK_A -> move(-100,0,500,5);
                    case KeyEvent.VK_D -> move(100, 0, 500,5);
                }
            }
        });
        requestFocus();
    }
}
