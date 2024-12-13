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
    JPanel leftpanel,rightpanel;

    public LevelFrame() {
        this.setSize(1080,720);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.setUndecorated(true);

        this.setLayout(new BorderLayout());
        leftpanel=new JPanel();
        leftpanel.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()));
        leftpanel.setBackground(Color.BLUE);
        leftpanel.setLayout(new GridBagLayout());
        JLabel title=new JLabel();
        title.setPreferredSize(new Dimension(this.getWidth()/10,this.getHeight()/6*3));
        GridBagConstraints titlelocation=new GridBagConstraints();
        titlelocation.gridx=0;
        titlelocation.gridy=1;
        titlelocation.weightx=1;
        titlelocation.weighty=3;
//        titlelocation.ipadx=1;
//        titlelocation.ipady=1;
        title.setBorder(BorderFactory.createLineBorder(Color.RED));
        leftpanel.add(title,titlelocation);
        JButton back=new JButton();
        back.setText("返回");
        back.setPreferredSize(new Dimension(this.getWidth()/10,this.getHeight()/6));
        GridBagConstraints backlocation=new GridBagConstraints();
        backlocation.gridx=1;
        backlocation.gridy=0;
        backlocation.weightx=1;
        backlocation.weighty=1;
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StartFrame.closelevelframe();
//                if(User.currentuser!=-1&&JOptionPane.showConfirmDialog(null, "是否要保存此次游玩数据？", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
//                    User.userlist.get(User.currentuser).framelistsave=(ArrayList<NormalFrame>) nowlist.clone();
//                    User.saveuserlist();
//                }
                levelframe.setVisible(false);
            }
        });
        leftpanel.add(back,backlocation);
        JButton restart=new JButton();
        restart.setText("重置");
        restart.setPreferredSize(new Dimension(this.getWidth()/10,this.getHeight()/6));
        GridBagConstraints restartlocation=new GridBagConstraints();
        restartlocation.gridx=1;
        restartlocation.gridy=3;
        restartlocation.weightx=1;
        restartlocation.weighty=1;
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(JOptionPane.showConfirmDialog(null, "确认要重置吗？", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    nowlist=(ArrayList<NormalFrame>) User.userlist.get(User.getuser("admin")).framelistsave.clone();
                }
            }
        });
        leftpanel.add(restart,restartlocation);

        this.add(leftpanel,BorderLayout.WEST);

        rightpanel=new JPanel();
        rightpanel.setPreferredSize(new Dimension(this.getWidth()/4*3,this.getHeight()));
//        rightpanel.setBackground(Color.YELLOW);
        rightpanel.setLayout(new GridBagLayout());

        JButton game1 = new JButton();
        game1.setText("game1");
        game1.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
        });
        rightpanel.add(game1, location1);

        JButton game2 = new JButton();
        game2.setText("game2");
        game2.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
        });
        rightpanel.add(game2, location2);

        JButton game3 = new JButton();
        game3.setText("game3");
        game3.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
        });
        rightpanel.add(game3, location3);

        JButton game4 = new JButton();
        game4.setText("game4");
        game4.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
        });
        rightpanel.add(game4, location4);

        JButton game5 = new JButton();
        game5.setText("game5");
        game5.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
        });
        rightpanel.add(game5, location5);

        this.add(rightpanel,BorderLayout.EAST);
    }
    public static void openlevelFrame() {
        levelframe=new LevelFrame();
        nowlist=new ArrayList<>();
        System.out.println(User.getuser("admin"));
        if(User.currentuser==-1) nowlist=(ArrayList<NormalFrame>)User.userlist.get(User.getuser("admin")).framelistsave.clone();
        else if(User.userlist.get(User.currentuser).error){
            if(JOptionPane.showConfirmDialog(null, "存档不存在或已被损坏，确认将重置存档", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                nowlist=(ArrayList<NormalFrame>)User.userlist.get(User.getuser("admin")).framelistsave.clone();
            }
            else{
                StartFrame.closelevelframe();;
                return;
            }
        }
        else nowlist=(ArrayList<NormalFrame>)User.userlist.get(User.currentuser).framelistsave.clone();
        levelframe.setVisible(true);
    }
    public static void closenormalframe() {
        levelframe.setEnabled(true);
    }
}
