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
    protected int x, y, width, height;
    protected ImageIcon originImage;
    protected double ratio;
    public Character(String filename) {
        try {
            originImage = new ImageIcon(ImageIO.read(new File(filename)));
            width = originImage.getIconWidth();
            height = originImage.getIconHeight();
            ratio = 1.0; x = 0; y = 0;
            setIcon(originImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意：执行该activate()方法前，先将此组件添加至父容器
     */
    @Override
    public void activate() {
        setBounds(x,y,width,height);
        setBorder(null);
        setEnabled(true);
        setFocusable(false);
        setVisible(true);
    }

    public void scale(double ratio) {
        this.ratio = ratio;
        width = (int)((originImage.getIconWidth() * ratio) + 0.5);
        height = (int)((originImage.getIconHeight() * ratio) + 0.5);
        BufferedImage bufferedImage = (BufferedImage)(originImage.getImage());
        setIcon(new ImageIcon(bufferedImage.getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        setBounds(getX(),getY(),width,height);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

}
