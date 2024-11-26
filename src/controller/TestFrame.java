package controller;

import javax.swing.*;
import view.character.Character;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class TestFrame extends JFrame implements Serializable {
    public TestFrame() {
        Character character = new Character("C:\\Users\\Zhone\\Desktop\\QQ图片20241126114545.jpg") {};
        setSize(400,300);
        setLocation(100,200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(character);
//        character.setBounds(2,3,34,34);
        character.activate();
//        addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                super.mouseMoved(e);
//                SwingUtilities.invokeLater(()->{
//                    character.setLocation(400,300);
//                    character.scale(2.0);
//                });
//
//            }
//        });
        setVisible(true);
    }

}
