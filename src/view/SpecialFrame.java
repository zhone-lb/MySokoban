package view;

import controller.editor.MapEditor;
import controller.level.MultiHero;
import controller.level.NormalFrame;
import controller.user.User;
import view.character.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SpecialFrame extends JFrame {
    public Button[] button;
    public ArrayList<NormalFrame> frames;
    public SpecialFrame() {
        setLayout(null);
        setSize(1000,600);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3,3,100,80));
        add(panel);
        panel.setSize(800,400);
        panel.setLocation(100,100);
        panel.setOpaque(false);
//        panel.setBackground(new Color(120,31,34));

        JLabel background=new JLabel();
        background.setSize(1080,720);
        Image backimage =(new ImageIcon("src/model/data/image//levelback.png")).getImage().getScaledInstance(1080, 720, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(backimage));

        JLabel error = new JLabel("特殊模式");
        error.setFont(new Font("华文彩云",Font.BOLD,58));
        error.setForeground(new Color(255,0,100));
        add(error);
        error.setLocation(370,20);
        error.setSize(400,60);
        error.setVisible(true);

        frames = new ArrayList<>();
        if(User.currentuser == -1) {
            for (int i = 0; i < 9; i++) {
                if(User.userlist.get(User.getuser("admin")).specialList.get(i) == null) frames.add(null);
                else frames.add(User.userlist.get(User.getuser("admin")).specialList.get(i).clone());
            }
        }
        else {
            for (int i = 0; i < 9; i++) {
                if(User.userlist.get(User.currentuser).specialList.get(i) == null) frames.add(null);
                else frames.add(User.userlist.get(User.currentuser).specialList.get(i).clone());
            }
        }


        button = new Button[9];
        button[0] = new Button("src\\model\\data\\image\\button0_0.png","src\\model\\data\\image\\button0_1.png");
        button[0].setSize(50,30);
        panel.add(button[0]);
        button[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MapEditor mapEditor = new MapEditor();
                            if(frames.get(0) == null) frames.set(0,mapEditor.CreateFrame());
                            else frames.set(0,mapEditor.ModifyFrame(frames.get(0)));
                        }
                    });
                    thread.start();
                }
                else {
                    if(frames.get(0) != null) frames.get(0).activate();
                }
            }
        });
        button[0].activate();

        button[1] = new Button("src\\model\\data\\image\\button1_0.png","src\\model\\data\\image\\button1_1.png");
        button[1].setSize(50,30);
        panel.add(button[1]);
        button[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MapEditor mapEditor = new MapEditor();
                            if(frames.get(1) == null) frames.set(1,mapEditor.CreateFrame());
                            else frames.set(1,mapEditor.ModifyFrame(frames.get(1)));
                        }
                    });
                    thread.start();
                }
                else {
                    if(frames.get(1) != null) frames.get(1).activate();
                }
            }
        });
        button[1].activate();

        button[2] = new Button("src\\model\\data\\image\\button2_0.png","src\\model\\data\\image\\button2_1.png");
        button[2].setSize(50,30);
        panel.add(button[2]);
        button[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MapEditor mapEditor = new MapEditor();
                            if(frames.get(2) == null) frames.set(2,mapEditor.CreateFrame());
                            else frames.set(2,mapEditor.ModifyFrame(frames.get(2)));
                        }
                    });
                    thread.start();
                }
                else {
                    if(frames.get(1) != null) frames.get(2).activate();
                }
            }
        });
        button[2].activate();

        button[3] = new Button("src\\model\\data\\image\\button3_0.png","src\\model\\data\\image\\button3_1.png");
        button[3].setSize(50,30);
        panel.add(button[3]);
        button[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frames.get(3).activate();
            }
        });
        button[3].activate();

        button[4] = new Button("src\\model\\data\\image\\button4_0.png","src\\model\\data\\image\\button4_1.png");
        button[4].setSize(50,30);
        panel.add(button[4]);
        button[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frames.get(4).activate();
            }
        });
        button[4].activate();

        button[5] = new Button("src\\model\\data\\image\\button5_0.png","src\\model\\data\\image\\button5_1.png");
        button[5].setSize(50,30);
        panel.add(button[5]);
        button[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frames.get(5).activate();
            }
        });
        button[5].activate();

        button[6] = new Button("src\\model\\data\\image\\button6_0.png","src\\model\\data\\image\\button6_1.png");
        button[6].setSize(60,30);
        panel.add(button[6]);
        button[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frames.get(6).activate();
            }
        });
        button[6].activate();

        button[7] = new Button("src\\model\\data\\image\\button7_0.png","src\\model\\data\\image\\button7_1.png");
        button[7].setSize(50,30);
        panel.add(button[7]);
        button[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frames.get(7).activate();
            }
        });
        button[7].activate();


        button[8] = new Button("src\\model\\data\\image\\button8_0.png","src\\model\\data\\image\\button8_1.png");
        button[8].setText("2048模式 2");
        button[8].setSize(50,30);
        panel.add(button[8]);
        button[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frames.get(8).activate();
            }
        });
        button[8].activate();


        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setEnabled(true);
        setResizable(false);
        setVisible(true);

        for (int i = 0; i < 9; i++) {
            button[i].activate();
        }
        add(background);
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            StartFrame.closespecialframe();
            if(User.currentuser != -1) {
                User.userlist.get(User.currentuser).specialList = new ArrayList<>();
                for (int i = 0; i < frames.size(); i++) {
                    if(frames.get(i) == null) User.userlist.get(User.currentuser).specialList.add(null);
                    else User.userlist.get(User.currentuser).specialList.add(frames.get(i).clone());
                }
            }
//            if(User.currentuser!=-1&&JOptionPane.showConfirmDialog(null, "是否要保存此次游玩数据？", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
//                for(int i=0;i<frames.size();++i){
//                    User.userlist.get(User.currentuser).framelistsave.set(i,frames.get(i).clone());
//                }
//                User.saveuserlist();
//            }
           setVisible(false);
        }
    }
}
