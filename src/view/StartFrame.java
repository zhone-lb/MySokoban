package view;
import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    JLabel title;
    Loginpanel loginpanel;
    public StartFrame() {
        this.setTitle("推箱子");
        this.setSize(1080,720);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,3));
        title = new JLabel();
        title.setText("推箱子");
        title.setSize(600,200);
        this.add(title, BorderLayout.NORTH);
        title.setFont(new Font("Songti SC", Font.BOLD, 80));
        title.setHorizontalAlignment(JLabel.CENTER);
        loginpanel = new Loginpanel();
        this.add(loginpanel, BorderLayout.CENTER);
    }
}
