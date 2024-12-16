package view;
import controller.Settings;
import controller.user.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static controller.user.User.currentuser;
import static controller.user.User.userlist;
import static view.LevelFrame.nowlist;
public class StartFrame extends JFrame {
    static JLabel title;
    Loginframe loginframe;
    static JButton loginbutton,settingbutton,normalgamebutton,spacialgamebutton;
    static StartFrame startframe;
    JPanel toppanel,bottompanel;
    public static void start(){
        startframe = new StartFrame();
        User.getuserlist();
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
                if(currentuser!=-1) userlist.get(currentuser).framelistsave=nowlist;
//                User.saveuserlist();
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setLayout(null);
        toppanel = new JPanel();
        toppanel.setLayout(new GridBagLayout());
        toppanel.setOpaque(false);
        toppanel.setSize(this.getWidth(),this.getHeight());
        toppanel.setLocation(0,-30);
        bottompanel = new JPanel();
        bottompanel.setSize(this.getWidth(),this.getHeight());
        bottompanel.setLocation(0,-30);

        JLabel background=new JLabel();
//        this.getContentPane().setBackground(Color.YELLOW);
        Image back =(new ImageIcon("src/model/data/image//back.png")).getImage().getScaledInstance(1080, 720, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(back));
        bottompanel.add(background);
        //title
        title = new JLabel();
        title.setText("推 箱 子");
        title.setForeground(new Color(173, 216, 230));
        title.setFont(new Font("华文彩云",Font.BOLD,150));
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
        toppanel.add(title,titlelocation);
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
        toppanel.add(loginbutton,loginbuttonlocation);
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
        toppanel.add(settingbutton,settingbuttonlocation);
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
        Image modle0icon_0 =(new ImageIcon("src/model/data/image/modle0_0.png")).getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        Image modle0icon_1 =(new ImageIcon("src/model/data/image/modle0_1.png")).getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH);
        normalgamebutton.setText("普通模式");
        GridBagConstraints normalgamebuttonlocation=new GridBagConstraints();
        normalgamebuttonlocation.gridx=5;
        normalgamebuttonlocation.gridy=4;
        normalgamebuttonlocation.weightx = 2.0;
        normalgamebuttonlocation.weighty = 2.0;
        normalgamebuttonlocation.ipadx = 100;
        normalgamebuttonlocation.ipady = 100;
//        normalgamebuttonlocation.fill=GridBagConstraints.BOTH;
        toppanel.add(normalgamebutton,normalgamebuttonlocation);
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
        toppanel.add(spacialgamebutton,spacialgamebuttonlocation);
        spacialgamebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                spacialgame();

            }
        });
        this.add(toppanel);
        this.add(bottompanel);
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
    public static void closelevelframe(){
        startframe.setEnabled(true);
    }
}
