package controller;

import controller.level.NormalFrame;
import model.config.UserConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Settings extends JFrame implements Serializable {
    public int index;
    public JPanel table;
    public JLabel title;
    public JLabel[] text;
    public JButton[] button;
    public Settings() {
        setName("设置");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setFocusable(true);
        setVisible(true);
        requestFocus();
        index = -1;
        text = new JLabel[8];
        button = new JButton[8];

        title = new JLabel("设置");
        title.setFont(new Font("微软雅黑",Font.BOLD,35));
        add(title);
        title.setBounds(250,0,200,60);
        title.setVisible(true);

        table = new JPanel(new GridLayout(4,2,20,20));
        add(table);
        table.setVisible(true);
        table.setBounds(50,60,482,280);
//        table.setBackground(new Color(100,20,100));

        text[0] = new JLabel("向上移动");
        text[0].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[0]);
        text[0].setSize(60,40);
        text[0].setVisible(true);

        button[0] = new JButton(KeyEvent.getKeyText(UserConfig.MOVE_UP));
        button[0].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[0]);
        button[0].setSize(90,40);
        button[0].setVisible(true);
        button[0].setFocusable(false);
        button[0].addActionListener(e -> {
            button[0].setText("请键入");
            index = 0;
        });

        text[1] = new JLabel("向下移动");
        text[1].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[1]);
        text[1].setSize(60,40);
        text[1].setVisible(true);

        button[1] = new JButton(KeyEvent.getKeyText(UserConfig.MOVE_DOWN));
        button[1].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[1]);
        button[1].setSize(90,40);
        button[1].setVisible(true);
        button[1].setFocusable(false);
        button[1].addActionListener(e -> {
            button[1].setText("请键入");
            index = 1;
        });

        text[2] = new JLabel("向左移动");
        text[2].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[2]);
        text[2].setSize(60,40);
        text[2].setVisible(true);

        button[2] = new JButton(KeyEvent.getKeyText(UserConfig.MOVE_LEFT));
        button[2].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[2]);
        button[2].setSize(90,40);
        button[2].setVisible(true);
        button[2].setFocusable(false);
        button[2].addActionListener(e -> {
            button[2].setText("请键入");
            index = 2;
        });

        text[3] = new JLabel("向右移动");
        text[3].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[3]);
        text[3].setSize(60,40);
        text[3].setVisible(true);

        button[3] = new JButton(KeyEvent.getKeyText(UserConfig.MOVE_RIGHT));
        button[3].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[3]);
        button[3].setSize(90,40);
        button[3].setVisible(true);
        button[3].setFocusable(false);
        button[3].addActionListener(e -> {
            button[3].setText("请键入");
            index = 3;
        });

        text[4] = new JLabel("提示");
        text[4].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[4]);
        text[4].setSize(60,40);
        text[4].setVisible(true);

        button[4] = new JButton(KeyEvent.getKeyText(UserConfig.HINT));
        button[4].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[4]);
        button[4].setSize(90,40);
        button[4].setVisible(true);
        button[4].setFocusable(false);
        button[4].addActionListener(e -> {
            button[4].setText("请键入");
            index = 4;
        });

        text[5] = new JLabel("撤销");
        text[5].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[5]);
        text[5].setSize(60,40);
        text[5].setVisible(true);

        button[5] = new JButton(KeyEvent.getKeyText(UserConfig.WITHDRAW));
        button[5].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[5]);
        button[5].setSize(90,40);
        button[5].setVisible(true);
        button[5].setFocusable(false);
        button[5].addActionListener(e -> {
            button[5].setText("请键入");
            index = 5;
        });

        text[6] = new JLabel("动画刷新率");
        text[6].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[6]);
        text[6].setSize(60,40);
        text[6].setVisible(true);

        button[6] = new JButton(Integer.toString(UserConfig.REFRESH_RATE));
        button[6].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[6]);
        button[6].setSize(90,40);
        button[6].setVisible(true);
        button[6].setFocusable(false);
        button[6].addActionListener(e -> {
            button[6].setText("请键入");
            index = 6;
        });
        text[7] = new JLabel("动画时长");
        text[7].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(text[7]);
        text[7].setSize(60,40);
        text[7].setVisible(true);

        button[7] = new JButton(Integer.toString(UserConfig.GAME_SPEED));
        button[7].setFont(new Font("微软雅黑",Font.BOLD,17));
        table.add(button[7]);
        button[7].setSize(90,40);
        button[7].setVisible(true);
        button[7].setFocusable(false);
        button[7].addActionListener(e -> {
            button[7].setText("请键入");
            index = 7;
        });
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if(e.getID() != KeyEvent.KEY_PRESSED || index == -1) return;
        switch (index) {
            case 0 -> {
                NormalFrame.userConfig.setMyMOVE_UP(e.getKeyCode());
                button[index].setText(KeyEvent.getKeyText(UserConfig.MOVE_UP));
                index = -1;
            }
        }
    }
}
