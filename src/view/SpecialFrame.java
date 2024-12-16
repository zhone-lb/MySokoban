package view;

import view.character.Button;

import javax.swing.*;
import java.awt.*;

public class SpecialFrame extends JFrame {
    public JButton[] button;
    public SpecialFrame() {
        setLayout(null);
        setSize(1000,600);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3,3,50,30));
        add(panel);
        panel.setSize(800,400);
        panel.setLocation(100,100);
        panel.setBackground(new Color(120,31,34));

        JLabel error = new JLabel("特殊模式");
        error.setFont(new Font("微软雅黑",Font.BOLD,28));
        add(error);
        error.setLocation(430,20);
        error.setSize(200,60);
        error.setVisible(true);

        button = new JButton[9];
        for (int i = 0; i < 3; i++) {
            button[i] = new JButton(new ImageIcon("src\\model\\data\\image\\Wall.png"));
            button[i].setSize(50,30);
            panel.add(button[i]);
            button[i].addActionListener(e->{

            });
        }

        for (int i = 3; i < 6; i++) {
            button[i] = new JButton(new ImageIcon("src\\model\\data\\image\\Wall.png"));
            button[i].setSize(50,30);
            panel.add(button[i]);
            button[i].addActionListener(e->{

            });
        }

        for (int i = 6; i < 9; i++) {
            button[i] = new JButton(new ImageIcon("src\\model\\data\\image\\Wall.png"));
            button[i].setSize(50,30);
            panel.add(button[i]);
            button[i].addActionListener(e->{

            });
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setEnabled(true);
        setResizable(false);
        setVisible(true);
    }
}
