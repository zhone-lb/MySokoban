package view;

import javax.swing.*;
import java.awt.*;

public class Loginpanel extends JPanel {
    JTextField username = new JTextField("用户名");
    JTextField password = new JTextField("密码");
    JButton signin=new JButton();
    JButton signup=new JButton();
    public Loginpanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        username.setSize(100,50);
        password.setSize(100,50);
        signin.setSize(1000,50);
        signup.setSize(100,50);
        this.add(username);
        this.add(password);
        this.add(signin);
        this.add(signup);
    }
}
