package view.character;

import view.Activator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public abstract class Character extends JLabel implements Serializable, Activator {
    protected ImageIcon originImage;
    public Character(String filename) {
        try {
            originImage = new ImageIcon(ImageIO.read(new File(filename)));
            setIcon(originImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意：先将此组件添加至父容器，然后在父容器内执行该组件的setBounds，最后执行该activate()方法
     */
    @Override
    public void activate() {
        scale(getWidth(), getHeight());
        setEnabled(true);
        setFocusable(false);
        setVisible(true);
    }


    public void scale(int width, int height) {
        BufferedImage bufferedImage = (BufferedImage)(originImage.getImage());
        setIcon(new ImageIcon(bufferedImage.getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        setBounds(getX(),getY(),width,height);
    }

//    public void scale(double ratio) {
//        int width = (int)((originImage.getIconWidth() * ratio) + 0.5);
//        int height = (int)((originImage.getIconHeight() * ratio) + 0.5);
//        BufferedImage bufferedImage = (BufferedImage)(originImage.getImage());
//        setIcon(new ImageIcon(bufferedImage.getScaledInstance(width,height,Image.SCALE_DEFAULT)));
//        setBounds(getX(),getY(),width,height);
//    }

}
