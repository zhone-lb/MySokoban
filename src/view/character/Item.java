package view.character;

import model.util.MotionTimer;
import view.Activator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 *  各种游戏元素的父类<p>
 *  注意，ImageIO比较low，不能读取webp图片<p>
 *  注意，添加Character进入框架时，遵循如下操作：<p>
 *  add(component);<p>
 *  component.setBounds(x,y,width,height);<p>
 *  component.activate();
 */
public abstract class Item extends JLabel implements Serializable, Activator {
    protected int id;
    protected ImageIcon originImage;
    public boolean isMoving = false;
    public Item(String filename) {
        this.id = 0;
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

    public void scale(double ratio) {
        int width = (int)((originImage.getIconWidth() * ratio) + 0.5);
        int height = (int)((originImage.getIconHeight() * ratio) + 0.5);
        BufferedImage bufferedImage = (BufferedImage)(originImage.getImage());
        setIcon(new ImageIcon(bufferedImage.getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        setBounds(getX(),getY(),width,height);
    }


    /**
     * 注释里有另一种动画方案
     * @param dx x坐标偏移量
     * @param dy y坐标偏移量
     * @param time 移动总时长
     * @param rate 刷新率
     */
    public synchronized void move(int dx, int dy, long time, int rate) {
        if(isMoving) return;
        isMoving = true;
        double a1 = 2.0*dx/time, a2 = 2.0*dy/time;
//        double a1 = 6.0*dx/time/time/time, a2 = 6.0*dy/time/time/time;
        final int ox = getX(), oy = getY();
        MotionTimer motionTimer = new MotionTimer() {
            @Override
            public void run() {
                for (ticks = 0; ticks < time; ticks += rate) {
                    try {
                        Thread.sleep(rate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(ticks);
//                    System.out.println(SwingUtilities.isEventDispatchThread());
                    SwingUtilities.invokeLater(()-> {
                        setLocation(ox+(int)(2*ticks<=time?a1*ticks*ticks/time:2.0*a1*ticks-a1*ticks*ticks/time-0.5*a1*time),
                                    oy+(int)(2*ticks<=time?a2*ticks*ticks/time:2.0*a2*ticks-a2*ticks*ticks/time-0.5*a2*time));
//                            setLocation(ox+(int)(0.5*a1*time*ticks*ticks-1.0/3*a1*ticks*ticks*ticks+0.5),
//                                        oy+(int)(0.5*a2*time*ticks*ticks-1.0/3*a2*ticks*ticks*ticks+0.5));
                    });
                }
                SwingUtilities.invokeLater(()->{setLocation(ox+dx,oy+dy);});
//                System.out.println(getX()+" "+getY()+" "+ox+" "+oy);
                isMoving = false;
            }
        };
        Thread thread = new Thread(motionTimer);
        thread.start();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
