package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Loginframe extends JFrame {
    JTextField username,password,passwordagain;
    JButton signin,signup,abandon;
    JLabel title,usernamelabel,passwordlabel,passwordagainlabel;
    public Loginframe() {
        this.setSize(540,360);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.setLayout(new GridBagLayout());
        this.setUndecorated(true);

//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        title=new JLabel("登录/注册");
        title.setFont(new Font("微软雅黑",Font.BOLD,40));
        GridBagConstraints titleset =new GridBagConstraints();
        titleset.gridx=0;
        titleset.gridy=0;
        titleset.gridwidth=3;
        titleset.weightx = 3.0;
        titleset.weighty = 1.0;
        this.add(title,titleset);

        usernamelabel=new JLabel("用户名");
        usernamelabel.setFont(new Font("微软雅黑",Font.BOLD,20));
        GridBagConstraints usernamelabelset =new GridBagConstraints();
        usernamelabelset.gridx=0;
        usernamelabelset.gridy=1;
        usernamelabelset.weightx = 1.0;
        usernamelabelset.weighty = 1.0;
        this.add(usernamelabel, usernamelabelset);

        username= new JTextField();
        ;
        GridBagConstraints usernameset =new GridBagConstraints();
        usernameset.gridx=1;
        usernameset.gridy=1;
        usernameset.gridwidth=2;
        usernameset.weightx = 1.0;
        usernameset.weighty = 1.0;
        username.setPreferredSize(new Dimension(200,30));
        this.add(username, usernameset);

        passwordlabel=new JLabel("密码");
        passwordlabel.setFont(new Font("微软雅黑",Font.BOLD,20));
        GridBagConstraints passwordlabelset =new GridBagConstraints();
        passwordlabelset.gridx=0;
        passwordlabelset.gridy=2;
        passwordlabelset.weightx = 1.0;
        passwordlabelset.weighty = 1.0;
        this.add(passwordlabel, passwordlabelset);

        password= new JTextField();
        GridBagConstraints passwordset =new GridBagConstraints();
        passwordset.gridx=1;
        passwordset.gridy=2;
        passwordset.gridwidth=2;
        passwordset.weightx = 1.0;
        passwordset.weighty = 1.0;
        password.setPreferredSize(new Dimension(200,30));
        this.add(password, passwordset);

        passwordagainlabel=new JLabel("重复密码");
        passwordagainlabel.setFont(new Font("微软雅黑",Font.BOLD,20));
        GridBagConstraints passwordagainlabelset =new GridBagConstraints();
        passwordagainlabelset.gridx=0;
        passwordagainlabelset.gridy=3;
        passwordagainlabelset.weightx = 1.0;
        passwordagainlabelset.weighty = 1.0;
        this.add(passwordagainlabel, passwordagainlabelset);
        passwordagainlabel.setVisible(false);

        passwordagain= new JTextField();
        GridBagConstraints passwordagainset =new GridBagConstraints();
        passwordagainset.gridx=1;
        passwordagainset.gridy=3;
        passwordagainset.gridwidth=2;
        passwordagainset.weightx = 1.0;
        passwordagainset.weighty = 1.0;
        passwordagain.setPreferredSize(new Dimension(200,30));
        this.add(passwordagain, passwordagainset);
        passwordagain.setVisible(false);

        signin=new JButton("登录");
        signin.setFont(new Font("微软雅黑",Font.BOLD,15));
        GridBagConstraints signinset =new GridBagConstraints();
        signinset.gridx=0;
        signinset.gridy=4;
        signinset.weightx = 1.0;
        signinset.weighty = 1.0;
        this.add(signin,signinset);

        signup=new JButton("注册");
        signup.setFont(new Font("微软雅黑",Font.BOLD,15));
        GridBagConstraints signupset =new GridBagConstraints();
        signupset.gridx=1;
        signupset.gridy=4;
        signupset.weightx = 1.0;
        signupset.weighty = 1.0;
        this.add(signup,signupset);
        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordagain.setVisible(true);
                passwordagainlabel.setVisible(true);
            }
        });

        abandon=new JButton("返回");
        abandon.setFont(new Font("微软雅黑",Font.BOLD,15));
        GridBagConstraints abandonset =new GridBagConstraints();
        abandonset.gridx=2;
        abandonset.gridy=4;
        abandonset.weightx = 1.0;
        abandonset.weighty = 1.0;
        this.add(abandon,abandonset);
        abandon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Loginframe.this.setVisible(false);
            }
        });
    }

}
