package controller;

import javax.swing.*;

import view.character.Item;
import view.character.Hero;
import view.character.Wall;
import view.character.Box;

import java.io.Serializable;

public class TestFrame extends JFrame implements Serializable {
    public TestFrame() {
        Item item = new Hero("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg") {};
        Hero hero = new Hero("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg");
        Box box = new Box("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg");
        Wall wall = new Wall("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg");
        setSize(400,300);
        setLocation(100,200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(item); add(hero); add(wall);
        item.setBounds(2,3,50,50);
        hero.setBounds(100,200,50,50);
        wall.setBounds(40,40,30,30);
        item.activate();
        hero.activate();
        wall.activate();
        wall.setBounds(100,100,30,30);
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                super.keyTyped(e);
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_W -> item.setLocation(getX(), getY() - 10);
//                    case KeyEvent.VK_S -> item.setLocation(getX(), getY() + 10);
//                    case KeyEvent.VK_A -> item.setLocation(getX() - 10, getY());
//                    case KeyEvent.VK_D -> item.setLocation(getX() + 10, getY());
//                }
//            }
//        });
        setVisible(true);
    }

}
