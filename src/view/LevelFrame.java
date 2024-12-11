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
    static ArrayList<NormalFrame> nowlist;
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
                StartFrame.colselevelframe();
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
                    nowlist=User.userlist.get(User.getuser("admin")).framelistsave;
                }
            }
        });
        leftpanel.add(restart,restartlocation);

        this.add(leftpanel,BorderLayout.WEST);

        rightpanel=new JPanel();
        rightpanel.setPreferredSize(new Dimension(this.getWidth()/4*3,this.getHeight()));
//        rightpanel.setBackground(Color.YELLOW);
        rightpanel.setLayout(new GridBagLayout());
        for(int i=1;i<=5;i++){
            JButton game=new JButton();
            game.setText("game"+i);
            game.setFont(new Font("Times New Roman",Font.BOLD,40));
            GridBagConstraints location=new GridBagConstraints();
            location.gridx=0;
            location.gridy=i;
            location.weightx=1;
            location.weighty=1;
//            game.setOpaque(false);
//            game.setContentAreaFilled(false);
            game.setBorder(null);
            game.setPreferredSize(new Dimension(this.getWidth()/5*3,this.getHeight()/6));
            game.setBackground(Color.GREEN);
            game.setVisible(true);
            int finalI = i;
            game.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    nowlist.get(finalI).activate();
                }
            });
            rightpanel.add(game,location);

        }
        this.add(rightpanel,BorderLayout.EAST);
    }
    public static void openlevelFrame() {
        levelframe=new LevelFrame();
        if(User.currentuser==-1) nowlist=User.userlist.get(User.getuser("admin")).framelistsave;
        else if(User.userlist.get(User.currentuser).error==true){
            if(JOptionPane.showConfirmDialog(null, "存档不存在或已被损坏，确认将重置存档", "确认", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                nowlist=User.userlist.get(User.getuser("admin")).framelistsave;
            }
            else{
                return;
            }
        }
        else nowlist=User.userlist.get(User.currentuser).framelistsave;
        levelframe.setVisible(true);
    }

}
