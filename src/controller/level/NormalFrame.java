package controller.level;

import controller.Settings;
import model.Map;
import model.algorithm.PathExplorer;
import view.Activator;
import view.character.*;
import model.config.UserConfig;
import view.character.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class NormalFrame extends JFrame implements Serializable, Activator {
    public int row, col, size;
    public Map map;
    public Item[] item;
    public Item background;
    public int currentSite;
    public ArrayList<Integer> past;



    public static UserConfig userConfig;




    public NormalFrame(Map originMap) {
        Init(originMap);



        Settings settings = new Settings();
        userConfig = new UserConfig();






    }
    public void Init(Map originMap) {
        map = originMap;
        row = map.row; col = map.col; size = 50;
        int tot = map.item.length;
        currentSite = 0;
        item = new Item[tot];
        past = new ArrayList<>();
        SwingUtilities.invokeLater(()->{
            for (int i = 0; i < tot; i++) {
                switch (map.type[i]) {
                    case 0 -> item[i] = new Hero("src\\model\\data\\image\\Guide.png");
                    case 1 -> item[i] = new Box("src\\model\\data\\image\\Box.jpg");
                    case 2 -> item[i] = new Wall("src\\model\\data\\image\\Wall.png");
                    case 3 -> item[i] = new Target("src\\model\\data\\image\\Target.png");
                }
            }
            for (int i = 0; i < tot; i++) item[i].setId(i);
            activate();
        });
    }
    @Override
    public void activate() {
        SwingUtilities.invokeLater(()->{
            size = 50;
            enableEvents(AWTEvent.KEY_EVENT_MASK);
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
            enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
            setLocation(200,100);
            setSize(400,300);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (int i = 0; i < item.length; i++) {
                add(item[i]);
                item[i].setBounds(map.item[i].x*size+size/2 , map.item[i].y*size+size/2, size, size);    //根据布局设置合理大小和位置
                item[i].activate();
                if(map.type[i] == 0) currentSite = i;
            }
            PathExplorer.path(map);
            background = new Item("src\\model\\data\\image\\background.png") {};
            add(background);
            background.setBounds(0,0,size*(col+1), size*(row+1));
            background.activate();
            setEnabled(true);
            setFocusable(true);
            setVisible(true);
        });
    }

    public synchronized void update(int DIR) {
        if(item[currentSite].isMoving) return;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
        if(PathExplorer.isValid(x,y,DIR)) {
//            PathExplorer.put();
            if(!PathExplorer.getBlocked(x,y,DIR)) {
                past.add(DIR);
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                PathExplorer.refresh(x,y,DIR,false);
            }
            else {
                past.add(1 << 2 | DIR);
                int boxSite = getBoxSite(new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]));
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                map.item[boxSite] = new Point(x+2*PathExplorer.dir[DIR][0], y+2*PathExplorer.dir[DIR][1]);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                item[boxSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                PathExplorer.refresh(x,y,DIR,true);
            }
            checkSucceed();
            checkFailed();
        }
    }

    public void checkFailed() {
        if(PathExplorer.isFailed()) {
            System.out.println("You failed");
        }
    }
    public void checkSucceed() {
        if(PathExplorer.isFinished()) {
            System.out.println("You win");
        }
    }
    public synchronized void withdraw() {
        if(past.isEmpty()) return;
        if(item[currentSite].isMoving) return;
        int DIR = past.getLast(); past.removeLast();
        boolean isBlocked = ((DIR & 4) != 0);
        DIR &= 3; DIR ^= 3;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
        if(PathExplorer.isValid2(x,y,DIR)) {
//            PathExplorer.put();
            if(!PathExplorer.getBlocked2(x,y,DIR)) {
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
            checkFailed();
        }
    }

    public void Hint() {
        update(PathExplorer.getHint(map.item[currentSite].x, map.item[currentSite].y));
    }

    protected int getBoxSite(Point p) {
        for (int i = 0; i < item.length; i++) {
            if(map.type[i] == 1 && map.item[i].equals(p)) return i;
        }
        return -1;
    }

    @Override
    public synchronized void repaint() {
        super.repaint();
        SwingUtilities.invokeLater(()->{
            Dimension d = getSize();
            size = Math.min(d.width / (col+2), d.height / (row+2));
            for (int i = 0; i < item.length; i++) {
                item[i].setBounds(map.item[i].x * size + size / 2, map.item[i].y * size + size / 2, size, size);
                item[i].activate();
            }
            background.setBounds(0,0,size*(col+1),size*(row+1));
            background.activate();
            setEnabled(true);
            setFocusable(true);
            setVisible(true);
        });
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        currentSite = ((Hero) e.getSource()).getId();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        System.out.println(e.getSource().getClass());
        if(e.getID() != KeyEvent.KEY_PRESSED) return;
        if(e.getKeyCode() == UserConfig.MOVE_UP) update(PathExplorer.UP);
        if(e.getKeyCode() == UserConfig.MOVE_DOWN) update(PathExplorer.DOWN);
        if(e.getKeyCode() == UserConfig.MOVE_LEFT) update(PathExplorer.LEFT);
        if(e.getKeyCode() == UserConfig.MOVE_RIGHT) update(PathExplorer.RIGHT);
        if(e.getKeyCode() == UserConfig.HINT) Hint();
        if(e.getKeyCode() == UserConfig.WITHDRAW) withdraw();
    }

    @Override
    protected void processComponentEvent(ComponentEvent e) {
        super.processComponentEvent(e);
        System.out.println(e.getSource().getClass());
        repaint();
    }
}
