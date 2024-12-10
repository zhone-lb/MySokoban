package view;

import javax.swing.*;
import java.awt.*;

public class LevelFrame extends JFrame{
    public static LevelFrame levelframe=new LevelFrame();
    JFrame frame;


    public LevelFrame() {
        this.setSize(1080,720);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)((screenSize.getWidth()-this.getWidth())/2),
                (int)((screenSize.getHeight()-this.getHeight())/2));
        this.setUndecorated(true);

        this.setLayout(new BorderLayout());
        JPanel leftpanel=new JPanel();
        leftpanel.setPreferredSize(new Dimension(this.getWidth()/4,720));
        leftpanel.setBackground(Color.BLUE);
        this.add(leftpanel,BorderLayout.WEST);

        JScrollPane scrollpanel=new JScrollPane();
        JPanel rightpanel=new JPanel();
        scrollpanel.add(rightpanel);
        this.add(scrollpanel,BorderLayout.EAST);
        rightpanel.setPreferredSize(new Dimension(this.getWidth()/4*3,720));
        rightpanel.setBackground(Color.YELLOW);
        rightpanel.setLayout(new GridLayout());


    }
    public static void openlevelFrame() {
        levelframe.setVisible(true);
    }

}
