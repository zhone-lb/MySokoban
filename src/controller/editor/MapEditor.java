package controller.editor;

import controller.level.NormalFrame;
import model.algorithm.Map;
import view.character.*;
import view.character.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MapEditor {
    public static Map map;
    public static Map finalMap;
    public synchronized NormalFrame ModifyFrame(NormalFrame frame) {
        map = null;
        finalMap = new Map(0,0);
        EditFrame editFrame = new EditFrame(frame.originMap);
        editFrame.activate();
        while (finalMap.row == 0) {
            try {
                wait(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return (finalMap.row == -1 ? frame : new NormalFrame(finalMap));
    }
    public synchronized NormalFrame CreateFrame() {
        map = null;
        System.out.println(SwingUtilities.isEventDispatchThread());
        JFrame jFrame = new JFrame();
        jFrame.setName("设置");
        jFrame.setSize(400, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finalMap = new Map(-1,-1);
                jFrame.dispose();
            }
        });
        jFrame.setLayout(null);
        jFrame.setFocusable(true);
        jFrame.setVisible(true);
        jFrame.requestFocus();

        JLabel title = new JLabel("请设置地图大小");
        title.setFont(new Font("微软雅黑",Font.BOLD,20));
        jFrame.add(title);
        title.setBounds(120,0,200,30);
        title.setVisible(true);

        JPanel table = new JPanel(new GridLayout(3,2,20,20));
        jFrame.add(table);
        table.setVisible(true);
        table.setBounds(20,40,350,200);
//        table.setBackground(new Color(100,20,100));

        JLabel text1 = new JLabel("请输入行数");
        text1.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.add(text1);
        text1.setSize(40,40);
        text1.setVisible(true);

        JTextField button1 = new JTextField();
        button1.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.add(button1);
        button1.setSize(40,40);
        button1.setVisible(true);

        JLabel text2 = new JLabel("请输入列数");
        text2.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.add(text2);
        text2.setSize(40,40);
        text2.setVisible(true);

        JTextField button2 = new JTextField();
        button2.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.add(button2);
        button2.setSize(40,40);
        button2.setVisible(true);

        JButton confirm = new JButton("确定");
        confirm.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.add(confirm);
        confirm.setSize(40,40);
        confirm.setVisible(true);
        confirm.addActionListener(e->{
            if(getInteger(button1.getText()) > 0 && getInteger(button2.getText()) > 0) {
                map = new Map(getInteger(button1.getText()), getInteger(button2.getText()));
                EditFrame editFrame = new EditFrame(map);
                editFrame.activate();
                jFrame.dispose();
            }
            else {
                JDialog dialog = new JDialog(jFrame);
                dialog.setLayout(new FlowLayout());
                dialog.setSize(200,100);
                dialog.setLocationRelativeTo(jFrame);
                dialog.setVisible(true);
                JLabel error = new JLabel("非法输入，请重试");
                error.setFont(new Font("微软雅黑",Font.BOLD,18));
                dialog.add(error);
                error.setSize(40,40);
                error.setVisible(true);
                button1.setText("");
                button2.setText("");
            }
        });


        JButton cancel = new JButton("取消");
        cancel.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.add(cancel);
        cancel.setSize(40,40);
        cancel.setVisible(true);
        cancel.addActionListener(e->{
            finalMap = new Map(-1,-1);
            jFrame.dispose();
        });

        table.revalidate();
        table.repaint();

        finalMap = new Map(0,0);
        while (finalMap.row == 0) {
            try {
                wait(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return (finalMap.row == -1 ? null : new NormalFrame(finalMap));
    }
    protected static int getInteger(String s) {
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
            ret = ret * 10 + (s.charAt(i) - '0');
        }
        return ret;
    }

    public static Map getFinalMap() {
        return finalMap;
    }
}
