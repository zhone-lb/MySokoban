package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartFrame extends JFrame {
    JLabel title;
    Loginframe loginframe;
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
        titlelocation.gridx=3;
        titlelocation.gridy=0;
        titlelocation.gridheight=3;
        titlelocation.anchor = GridBagConstraints.CENTER;
        title.setBackground(Color.YELLOW);
        this.add(title,titlelocation);
//        title.setLocation((int)((this.getWidth()-title.getWidth())/2),
//                (int)((this.getHeight()/3-title.getHeight())/2));
        title.setFont(new Font("Songti SC", Font.BOLD, 80));

        //loginbutton
        Loginframe loginframe = new Loginframe();
        JButton loginbutton = new JButton();
        loginbutton.setText("登录/注册");
        loginbutton.setSize(80,30);
        GridBagConstraints loginbuttonlocation=new GridBagConstraints();
        loginbuttonlocation.gridx=3;
        loginbuttonlocation.gridy=5;
        loginbuttonlocation.anchor = GridBagConstraints.CENTER;
        this.add(loginbutton,loginbuttonlocation);
        loginbutton.setHorizontalAlignment(JButton.CENTER);
        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginframe.setVisible(true);
//                setEnabled(false);

            }
        });
    }
}
