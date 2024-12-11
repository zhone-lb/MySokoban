package view;
import controller.Settings;
import controller.user.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static controller.user.User.currentuser;

public class StartFrame extends JFrame {
    static JLabel title;
    Loginframe loginframe;
    static JButton loginbutton,settingbutton,normalgamebutton,spacialgamebutton;
    static StartFrame startframe;
    public static void start(){
        startframe = new StartFrame();
        startframe.setVisible(true);
    }
    public StartFrame() {

        //frame
        this.setTitle("推箱子");
        this.setSize(1080,720);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                User.saveuserlist();
                System.exit(0);
            }
        });
        this.setLayout(new GridBagLayout());

        //title
        title = new JLabel();
        title.setText("推箱子");
        title.setFont(new Font("微软雅黑",Font.BOLD,120));
        GridBagConstraints titlelocation=new GridBagConstraints();
        titlelocation.gridx=5;
        titlelocation.gridy=0;
        titlelocation.weightx = 3.0;
        titlelocation.weighty = 2.0;
        titlelocation.gridheight=2;
        titlelocation.gridwidth=3;
        titlelocation.fill=GridBagConstraints.BOTH;
        title.setHorizontalAlignment(JLabel.CENTER); // 设置水平居中
        title.setVerticalAlignment(JLabel.CENTER);
        this.add(title,titlelocation);
//        title.setLocation((int)((this.getWidth()-title.getWidth())/2),
//                (int)((this.getHeight()/3-title.getHeight())/2));
//        title.setFont(new Font("Songti SC", Font.BOLD, 80));

        //loginbutton
        loginframe = new Loginframe();
        loginbutton = new JButton();
        Image guest_0 =(new ImageIcon("src/model/data/image/guest_0.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        Image guest_1 =(new ImageIcon("src/model/data/image/guest_1.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        Image login_0 =(new ImageIcon("src/model/data/image/login_0.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        Image login_1 =(new ImageIcon("src/model/data/image/login_1.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        loginbutton.setIcon(new ImageIcon(guest_0));
        loginbutton.setSize(100,50);
        loginbutton.setOpaque(false);
        loginbutton.setContentAreaFilled(false);
        loginbutton.setBorder(null);
        loginbutton.setPreferredSize(new Dimension(120,120));
        GridBagConstraints loginbuttonlocation=new GridBagConstraints();
        loginbuttonlocation.gridx=1;
        loginbuttonlocation.gridy=5;
        loginbuttonlocation.weightx = 1.0;
        loginbuttonlocation.weighty = 1.0;
//        loginbuttonlocation.fill=GridBagConstraints.BOTH;
        this.add(loginbutton,loginbuttonlocation);
        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginframe.setVisible(true);
                loginframe.passwordagainlabel.setVisible(false);
                loginframe.passwordagain.setVisible(false);
                startframe.setEnabled(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(currentuser==-1){
                    loginbutton.setIcon(new ImageIcon(guest_1));
                }
                else{
                    loginbutton.setIcon(new ImageIcon(login_1));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(currentuser==-1){
                    loginbutton.setIcon(new ImageIcon(guest_0));
                }
                else{
                    loginbutton.setIcon(new ImageIcon(login_0));
                }
            }
        });

        //settingbutton
        settingbutton = new JButton();
        Image settingicon_0 =(new ImageIcon("src/model/data/image/setting.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        Image settingicon_1 =(new ImageIcon("src/model/data/image/setting_1.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        settingbutton.setIcon(new ImageIcon(settingicon_0));
        settingbutton.setOpaque(false);
        settingbutton.setContentAreaFilled(false);
        settingbutton.setBorder(null);
//        settingbutton.setText("更改设置");
        settingbutton.setPreferredSize(new Dimension(120,120));
        GridBagConstraints settingbuttonlocation=new GridBagConstraints();
        settingbuttonlocation.gridx=11;
        settingbuttonlocation.gridy=5;
        settingbuttonlocation.weightx = 1.0;
        settingbuttonlocation.weighty = 1.0;
//        settingbuttonlocation.fill=GridBagConstraints.BOTH;
        this.add(settingbutton,settingbuttonlocation);
        settingbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Settings.newsetting();
                startframe.setEnabled(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingbutton.setIcon(new ImageIcon(settingicon_1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingbutton.setIcon(new ImageIcon(settingicon_0));
            }
        });

        //normalgamebutton
        normalgamebutton = new JButton();
        normalgamebutton.setText("普通模式");
        GridBagConstraints normalgamebuttonlocation=new GridBagConstraints();
        normalgamebuttonlocation.gridx=5;
        normalgamebuttonlocation.gridy=4;
        normalgamebuttonlocation.weightx = 2.0;
        normalgamebuttonlocation.weighty = 2.0;
        normalgamebuttonlocation.ipadx = 100;
        normalgamebuttonlocation.ipady = 100;
//        normalgamebuttonlocation.fill=GridBagConstraints.BOTH;
        this.add(normalgamebutton,normalgamebuttonlocation);
        normalgamebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LevelFrame.openlevelFrame();
                startframe.setEnabled(false);
            }
        });

        //spacialgamebutton
        spacialgamebutton = new JButton();
        spacialgamebutton.setText("特殊模式");
        GridBagConstraints spacialgamebuttonlocation=new GridBagConstraints();
        spacialgamebuttonlocation.gridx=7;
        spacialgamebuttonlocation.gridy=4;
        spacialgamebuttonlocation.weightx = 2.0;
        spacialgamebuttonlocation.weighty = 2.0;
        spacialgamebuttonlocation.ipadx = 100;
        spacialgamebuttonlocation.ipady = 100;
//        spacialgamebuttonlocation.fill=GridBagConstraints.BOTH;
        this.add(spacialgamebutton,spacialgamebuttonlocation);
        spacialgamebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                spacialgame();

            }
        });
    }
//    public void loginframeclose{
//        loginframe=null;
//        this.setEnabled(true);
//    }
    public static void closeloginframe(){
        if(currentuser!=-1){
            Image login_0 =(new ImageIcon("src/model/data/image/login_0.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            loginbutton.setIcon(new ImageIcon(login_0));
        }
        startframe.setEnabled(true);
    }
    public static void closesettings(){
        startframe.setEnabled(true);
    }
    public static void colselevelframe(){
        startframe.setEnabled(true);
    }
}
