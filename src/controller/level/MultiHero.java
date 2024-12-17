package controller.level;

import model.algorithm.Map;
import model.algorithm.PathExplorer;
import model.config.UserConfig;
import view.Activator;
import view.character.Button;
import view.character.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class MultiHero extends NormalFrame implements Serializable, Activator {
    ImageIcon currentHero, inactiveHero;
    ArrayList<Integer> pastHero;
    public MultiHero(Map originMap) {
        super(originMap);
        pastHero = new ArrayList<>();
        PathExplorer.Init(originMap);
        isActivated = false;
        try {
            currentHero = new ImageIcon(ImageIO.read(new File("src\\model\\data\\image\\hero3.png")));
            inactiveHero = new ImageIcon(ImageIO.read(new File("src\\model\\data\\image\\hero_no.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void activate() {
        SwingUtilities.invokeLater(()->{
            if(isActivated) {
                setVisible(true);
                PathExplorer.Init(map);
                repaint();
                return;
            }
            size = 50;
            isActivated = true;
            enableEvents(AWTEvent.KEY_EVENT_MASK);
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
            enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
            setSize(1000,600);
            setLocationRelativeTo(null);
            setLayout(null);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 0) add(item[i]);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 1) add(item[i]);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 2) add(item[i]);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 3) add(item[i]);

            currentSite = -1;
            for (int i = 0; i < item.length; i++) {
                item[i].setBounds(0,0, size, size);    //在repaint生效
                item[i].activate();
                if(map.type[i] == 0) {
                    if(currentSite != -1) {
                        item[currentSite].setCurrentImage(inactiveHero);
                        item[currentSite].activate();
                    }
                    currentSite = i;
                    item[currentSite].setCurrentImage(currentHero);
                    item[currentSite].activate();
                }
            }

            exited = new view.character.Button("src\\model\\data\\image\\back_0.png","src\\model\\data\\image\\back_1.png");
            add(exited);
            exited.setBounds(0,0,size,size);
            exited.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    setVisible(false);
                }
            });
            exited.activate();

            reset = new view.character.Button("src\\model\\data\\image\\restart_0.png","src\\model\\data\\image\\restart_1.png");
            add(reset);
            reset.setBounds(0,0,size,size);
            reset.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    reset();
                }
            });
            reset.activate();

            hint = new view.character.Button("src\\model\\data\\image\\hint.png","src\\model\\data\\image\\hint.png");
            add(hint);
            hint.setBounds(0,0,size,size);
            hint.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Hint();
                }
            });
            hint.activate();

            withdraw = new Button("src\\model\\data\\image\\undo.png","src\\model\\data\\image\\undo.png");
            add(withdraw);
            withdraw.setBounds(0,0,size,size);
            withdraw.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    withdraw();
                }
            });
            withdraw.activate();

            step = new Item("src\\model\\data\\image\\background.png") {};
            add(step);
            step.setBounds(0,0,size,size);
            step.activate();

            background = new Item("src\\model\\data\\image\\normalbackground.png") {};
            add(background);
            background.setBounds(0,0,size*(col+1), size*(row+1));
            background.activate();

            column = new Item("src\\model\\data\\image\\woodground.jpg") {};
            add(column);
            column.setBounds(0,0,size*(col+1), size*(row+1));
            column.activate();

            HighLight = new Item() {};
            add(HighLight);
            HighLight.setVisible(false);
            HPos = new Point(-1,-1);


            PathExplorer.Init(originMap);

            setEnabled(true);
            setFocusable(true);
            setVisible(true);
            repaint();
        });
    }
    public synchronized void update(int DIR) {
        if(item[currentSite].isMoving) return;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
        HeroFacing(DIR);
        if(PathExplorer.isValid(x,y,DIR)) {
//            PathExplorer.put();
            if(!PathExplorer.getBlocked(x,y,DIR)) {
                past.add(DIR);
                pastHero.add(currentSite);
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                PathExplorer.refresh(x,y,DIR,false);
            }
            else {
                past.add(1 << 2 | DIR);
                pastHero.add(currentSite);
                int boxSite = getBoxSite(new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]));
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                map.item[boxSite] = new Point(x+2*PathExplorer.dir[DIR][0], y+2*PathExplorer.dir[DIR][1]);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                item[boxSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                PathExplorer.refresh(x,y,DIR,true);
            }
            SwingUtilities.invokeLater(()->{
                step.setText(Integer.toString(past.size()));
                step.setIconTextGap(-(int) (step.getWidth() * (past.size() < 10 ? 0.64 : 0.75)));
            });
            checkSucceed();
        }
    }
    public synchronized void withdraw() {
        if(past.isEmpty()) return;
        if(item[currentSite].isMoving) return;
        int DIR = past.getLast(); past.removeLast();
        item[currentSite].setCurrentImage(inactiveHero);
        item[currentSite].activate();
        currentSite = pastHero.getLast(); pastHero.removeLast();
        item[currentSite].setCurrentImage(currentHero);
        item[currentSite].activate();
        SwingUtilities.invokeLater(()->{
            step.setText(Integer.toString(past.size()));
            step.setIconTextGap(-(int) (step.getWidth() * (past.size() < 10 ? 0.64 : 0.75)));
        });
        boolean isBlocked = ((DIR & 4) != 0);
        DIR &= 3; DIR ^= 3;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
        HeroFacing(DIR);
        if(PathExplorer.isValid2(x,y,DIR)) {
//            PathExplorer.put();
            if(!isBlocked) {
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                PathExplorer.refresh2(x,y,DIR,false);
            }
            else {
                int boxSite = getBoxSite(new Point(x+PathExplorer.dir[DIR^3][0], y+PathExplorer.dir[DIR^3][1]));
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                map.item[boxSite] = new Point(x, y);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                item[boxSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                PathExplorer.refresh2(x,y,DIR,true);
            }
        }
    }

    @Override
    public void Hint() {

    }

    @Override
    public NormalFrame clone() {
        MultiHero frame = new MultiHero(map.clone());
        frame.originMap = originMap.clone();
        frame.pastHero = new ArrayList<>();
        frame.past = new ArrayList<>();
        for (int i = 0; i < past.size(); i++) frame.past.add(past.get(i));
        for (int i = 0; i < pastHero.size(); i++) frame.pastHero.add(pastHero.get(i));
        return frame;
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if(e.getID() == MouseEvent.MOUSE_CLICKED) {
            int x = (e.getX()-size-size/2-borderX), y = (e.getY()-size/2 -size/2 -borderY - 30);
            System.out.println(e.getPoint().toString()+x+" "+y+" "+size);
            if(x >= 0 && x < col*size && y >= 0 && y < row*size) {
                x /= size; y /= size;
                for (int i = 0; i < item.length; i++) {
                    if(map.item[i].equals(new Point(x,y)) && map.type[i] == 0) {
                        item[currentSite].setCurrentImage(inactiveHero);
                        item[currentSite].activate();
                        currentSite = i;
                        item[currentSite].setCurrentImage(currentHero);
                        item[currentSite].activate();
                    }
                }
            }
        }
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            setVisible(false);
        }
    }
}
