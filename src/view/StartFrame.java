package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartFrame extends JFrame {
    JLabel title;
    Loginframe loginframe;
    JButton loginbutton,settingbutton,normalgamebutton,spacialgamebutton;
    public StartFrame() {


        //frame
        this.setTitle("推箱子");
        this.setSize(1080,720);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        //title
        title = new JLabel();
        title.setText("推箱子");
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
        title.setFont(new Font("Songti SC", Font.BOLD, 80));

        //loginbutton
        loginframe = new Loginframe();
        loginbutton = new JButton();
        loginbutton.setText("登录/注册");
        loginbutton.setSize(100,50);
        GridBagConstraints loginbuttonlocation=new GridBagConstraints();
        loginbuttonlocation.gridx=1;
        loginbuttonlocation.gridy=5;
        loginbuttonlocation.weightx = 1.0;
        loginbuttonlocation.weighty = 1.0;
        loginbuttonlocation.ipadx = 50;
        loginbuttonlocation.ipady = 50;
//        loginbuttonlocation.fill=GridBagConstraints.BOTH;
        this.add(loginbutton,loginbuttonlocation);
        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginframe.setVisible(true);
                //想实现一个登录界面打开后锁定主界面
//                StartFrame.this.setEnabled(false);

            }
        });

        //settingbutton
        settingbutton = new JButton();
        settingbutton.setText("更改设置");
        settingbutton.setSize(100,50);
        GridBagConstraints settingbuttonlocation=new GridBagConstraints();
        settingbuttonlocation.gridx=11;
        settingbuttonlocation.gridy=5;
        settingbuttonlocation.weightx = 1.0;
        settingbuttonlocation.weighty = 1.0;
        settingbuttonlocation.ipadx = 50;
        settingbuttonlocation.ipady = 50;
//        settingbuttonlocation.fill=GridBagConstraints.BOTH;
        this.add(settingbutton,settingbuttonlocation);
        settingbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                setting();

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
//                normalgame();

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
}
