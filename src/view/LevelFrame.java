package view;

import controller.level.NormalFrame;
import controller.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LevelFrame extends JFrame{
    public static LevelFrame levelframe;
    public static ArrayList<NormalFrame> nowlist;
    JPanel leftpanel,rightpanel,allpanel,backpanel;

    public LevelFrame() {
        this.setSize(1080,720);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.setUndecorated(true);
        this.setLayout(null);
        JLabel background=new JLabel();
//        this.getContentPane().setBackground(Color.YELLOW);
        Image backimage =(new ImageIcon("src/model/data/image//levelback.png")).getImage().getScaledInstance(1080, 720, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(backimage));
        backpanel=new JPanel();
        backpanel.add(background);
        allpanel=new JPanel();
        allpanel.setLayout(new BorderLayout());
        leftpanel=new JPanel();
        leftpanel.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()));
        leftpanel.setOpaque(false);
        leftpanel.setLayout(new GridBagLayout());
        JLabel title=new JLabel();
        title.setPreferredSize(new Dimension(108, 360));
        Image titleimage =(new ImageIcon("src/model/data/image/leveltitle.png")).getImage().getScaledInstance(108, 360, Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(titleimage));
        title.setBorder(null);
        GridBagConstraints titlelocation=new GridBagConstraints();
        titlelocation.gridx=0;
        titlelocation.gridy=1;
        titlelocation.weightx=1;
        titlelocation.weighty=3;
        leftpanel.add(title,titlelocation);
        JButton back=new JButton();
        back.setText("返回");
        back.setPreferredSize((new Dimension(150,150)));
        Image backimage_0 =(new ImageIcon("src/model/data/image/back_0.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Image backimage_1 =(new ImageIcon("src/model/data/image/back_1.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        back.setIcon(new ImageIcon(backimage_0));
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorder(null);
        GridBagConstraints backlocation=new GridBagConstraints();
        backlocation.gridx=1;
        backlocation.gridy=0;
        backlocation.weightx=1;
        backlocation.weighty=1;
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StartFrame.closelevelframe();
                if(User.currentuser!=-1&&JOptionPane.showConfirmDialog(null, "是否要保存此次游玩数据？", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    User.userlist.get(User.currentuser).framelistsave.clear();
                    for(int i=0;i<nowlist.size();++i){
                        User.userlist.get(User.currentuser).framelistsave.add(nowlist.get(i).clone());
                    }
                    User.saveuserlist();
                }
                levelframe.setVisible(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(new ImageIcon(backimage_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(new ImageIcon(backimage_0));
            }
        });
        leftpanel.add(back,backlocation);
        JButton restart=new JButton();
        restart.setPreferredSize(new Dimension(150,150));
        Image restartimage_0 =(new ImageIcon("src/model/data/image/restart_0.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Image restartimage_1 =(new ImageIcon("src/model/data/image/restart_1.png")).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        restart.setIcon(new ImageIcon(restartimage_0));
        restart.setOpaque(false);
        restart.setContentAreaFilled(false);
        restart.setBorder(null);
        GridBagConstraints restartlocation=new GridBagConstraints();
        restartlocation.gridx=1;
        restartlocation.gridy=3;
        restartlocation.weightx=1;
        restartlocation.weighty=1;
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(JOptionPane.showConfirmDialog(null, "确认要重置吗？", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    nowlist.clear();
                    for (int i = 0; i < User.userlist.get(User.getuser("admin")).framelistsave.size(); ++i) {
                        nowlist.add(User.userlist.get(User.getuser("admin")).framelistsave.get(i).clone());

                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                restart.setIcon(new ImageIcon(restartimage_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restart.setIcon(new ImageIcon(restartimage_0));
            }
        });
        leftpanel.add(restart,restartlocation);

        allpanel.add(leftpanel,BorderLayout.WEST);

        rightpanel=new JPanel();
        rightpanel.setOpaque(false);
        rightpanel.setPreferredSize(new Dimension(this.getWidth()/4*3,this.getHeight()));
//        rightpanel.setBackground(Color.YELLOW);
        rightpanel.setLayout(new GridBagLayout());

        JButton game1 = new JButton();
        Image game1_0 =(new ImageIcon("src/model/data/image/game1_0.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        Image game1_1 =(new ImageIcon("src/model/data/image/game1_1.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        game1.setIcon(new ImageIcon(game1_0));
        game1.setOpaque(false);
        game1.setContentAreaFilled(false);
        game1.setBorder(null);
        GridBagConstraints location1 = new GridBagConstraints();
        location1.gridx = 0;
        location1.gridy = 1;
        location1.weightx = 1;
        location1.weighty = 1;
        game1.setBorder(null);
        game1.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight() / 6));
        game1.setBackground(Color.GREEN);
        game1.setVisible(true);
        game1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nowlist.get(0).activate();
                setEnabled(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                game1.setIcon(new ImageIcon(game1_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                game1.setIcon(new ImageIcon(game1_0));
            }
        });
        rightpanel.add(game1, location1);

        JButton game2 = new JButton();
        Image game2_0 =(new ImageIcon("src/model/data/image/game2_0.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        Image game2_1 =(new ImageIcon("src/model/data/image/game2_1.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        game2.setIcon(new ImageIcon(game2_0));
        game2.setOpaque(false);
        game2.setContentAreaFilled(false);
        game2.setBorder(null);
        GridBagConstraints location2 = new GridBagConstraints();
        location2.gridx = 0;
        location2.gridy = 2;
        location2.weightx = 1;
        location2.weighty = 1;
        game2.setBorder(null);
        game2.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight() / 6));
        game2.setBackground(Color.GREEN);
        game2.setVisible(true);
        game2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nowlist.get(1).activate();
                setEnabled(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                game2.setIcon(new ImageIcon(game2_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                game2.setIcon(new ImageIcon(game2_0));
            }
        });
        rightpanel.add(game2, location2);

        JButton game3 = new JButton();
        Image game3_0 =(new ImageIcon("src/model/data/image/game3_0.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        Image game3_1 =(new ImageIcon("src/model/data/image/game3_1.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        game3.setIcon(new ImageIcon(game3_0));
        game3.setOpaque(false);
        game3.setContentAreaFilled(false);
        game3.setBorder(null);
        GridBagConstraints location3 = new GridBagConstraints();
        location3.gridx = 0;
        location3.gridy = 3;
        location3.weightx = 1;
        location3.weighty = 1;
        game3.setBorder(null);
        game3.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight() / 6));
        game3.setBackground(Color.GREEN);
        game3.setVisible(true);
        game3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nowlist.get(2).activate();
                setEnabled(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                game3.setIcon(new ImageIcon(game3_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                game3.setIcon(new ImageIcon(game3_0));
            }
        });
        rightpanel.add(game3, location3);

        JButton game4 = new JButton();
        Image game4_0 =(new ImageIcon("src/model/data/image/game4_0.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        Image game4_1 =(new ImageIcon("src/model/data/image/game4_1.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        game4.setIcon(new ImageIcon(game4_0));
        game4.setOpaque(false);
        game4.setContentAreaFilled(false);
        game4.setBorder(null);
        GridBagConstraints location4 = new GridBagConstraints();
        location4.gridx = 0;
        location4.gridy = 4;
        location4.weightx = 1;
        location4.weighty = 1;
        game4.setBorder(null);
        game4.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight() / 6));
        game4.setBackground(Color.GREEN);
        game4.setVisible(true);
        game4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nowlist.get(3).activate();
                setEnabled(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                game4.setIcon(new ImageIcon(game4_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                game4.setIcon(new ImageIcon(game4_0));
            }
        });
        rightpanel.add(game4, location4);

        JButton game5 = new JButton();
        Image game5_0 =(new ImageIcon("src/model/data/image/game5_0.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        Image game5_1 =(new ImageIcon("src/model/data/image/game5_1.png")).getImage().getScaledInstance(650, 120, Image.SCALE_SMOOTH);
        game5.setIcon(new ImageIcon(game5_0));
        game5.setOpaque(false);
        game5.setContentAreaFilled(false);
        game5.setBorder(null);
        GridBagConstraints location5 = new GridBagConstraints();
        location5.gridx = 0;
        location5.gridy = 5;
        location5.weightx = 1;
        location5.weighty = 1;
        game5.setBorder(null);
        game5.setPreferredSize(new Dimension(this.getWidth() / 5 * 3, this.getHeight() / 6));
        game5.setBackground(Color.GREEN);
        game5.setVisible(true);
        game5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nowlist.get(4).activate();
                setEnabled(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                game5.setIcon(new ImageIcon(game5_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                game5.setIcon(new ImageIcon(game5_0));
            }
        });
        rightpanel.add(game5, location5);

        allpanel.add(rightpanel,BorderLayout.EAST);
        allpanel.setOpaque(false);
        backpanel.setSize(this.getWidth(),this.getHeight());
        backpanel.setLocation(0,0);
        allpanel.setSize(this.getWidth(),this.getHeight());
        allpanel.setLocation(0,0);
        this.add(allpanel);
        this.add(backpanel);
    }
    public static void openlevelFrame() {
        nowlist=new ArrayList<>();
        System.out.println(User.getuser("admin"));
        if(User.currentuser==-1){
            for(int i=0;i<User.userlist.get(User.getuser("admin")).framelistsave.size();++i){
                nowlist.add(User.userlist.get(User.getuser("admin")).framelistsave.get(i).clone());
            }
        }
        else if(User.userlist.get(User.currentuser).error){
            if(JOptionPane.showConfirmDialog(null, "存档不存在或已被损坏，确认将重置存档", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                for(int i=0;i<User.userlist.get(User.getuser("admin")).framelistsave.size();++i){
                    nowlist.add(User.userlist.get(User.getuser("admin")).framelistsave.get(i).clone());
                }
                User.userlist.get(User.currentuser).error=false;
            }
            else{
                StartFrame.closelevelframe();;
                return;
            }
        }
        else{
            for(int i=0;i<User.userlist.get(User.currentuser).framelistsave.size();++i){
                nowlist.add(User.userlist.get(User.currentuser).framelistsave.get(i).clone());
            }
        }
        levelframe.setVisible(true);
    }
    public static void closenormalframe() {
        levelframe.setEnabled(true);
        levelframe.requestFocus();
    }
}
