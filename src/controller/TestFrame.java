package controller;

import javax.swing.*;

import view.character.Button;
import view.character.Character;
import view.character.Hero;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class TestFrame extends JFrame implements Serializable {
    public TestFrame() {
        Character character = new Hero("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg") {};
        Hero hero = new Hero("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg");
        setSize(400,300);
        setLocation(100,200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(character); add(hero);
        character.setBounds(2,3,50,50);
        hero.setBounds(100,200,50,50);
        character.activate();
        hero.activate();
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_W -> character.setLocation(getX(), getY() - 10);
//                    case KeyEvent.VK_S -> character.setLocation(getX(), getY() + 10);
//                    case KeyEvent.VK_A -> character.setLocation(getX() - 10, getY());
//                    case KeyEvent.VK_D -> character.setLocation(getX() + 10, getY());
//                }
//            }
//        });
        setVisible(true);
    }

}
