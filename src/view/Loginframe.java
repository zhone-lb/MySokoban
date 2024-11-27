package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Loginframe extends JFrame {
    JTextField username = new JTextField("用户名");
    JTextField password = new JTextField("密码");
    JButton signin=new JButton();
    JButton signup=new JButton();
    public Loginframe() {
        this.setSize(540,360);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                (int)((screenSize.getHeight()-this.getHeight())/2));
//        this.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        })

        username.setSize(100,50);
        password.setSize(100,50);
        signin.setSize(1000,50);
        signup.setSize(100,50);
//        this.add(username);
//        this.add(password);
//        this.add(signin);
//        this.add(signup);
    }
}
