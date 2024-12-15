package controller.level;

import controller.Settings;
import model.algorithm.Map;
import model.algorithm.PathExplorer;
import view.Activator;
import view.LevelFrame;
import view.character.*;
import model.config.UserConfig;
import view.character.Box;
import view.character.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class NormalFrame extends JFrame implements Serializable, Activator,Cloneable {
    public int row, col, size, borderX, borderY;
    public Map map, originMap;
    public Item[] item;
    public Item background, column, reset, exited, hint, withdraw, step;
    public int currentSite;
    public boolean FullX, isActivated;
    public ArrayList<Integer> past;



    public static UserConfig userConfig;



    public NormalFrame() {}
    public NormalFrame(Map originMap) {
        Init(originMap);



//        Settings settings = new Settings();
        userConfig = new UserConfig();






    }
    public void Init(Map myMap) {
        map = myMap; originMap = map.clone();
        row = map.row; col = map.col; size = 50;
        int tot = map.item.length;
        currentSite = 0;
        isActivated = false;
        item = new Item[map.item.length];
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
        });
    }

    public void reset() {
        map = new Map(originMap.row, originMap.col, originMap.item.clone(), originMap.type.clone());
        past = new ArrayList<>();
        PathExplorer.Init(originMap);
        PathExplorer.path(originMap);
        repaint();
    }
    @Override
    public NormalFrame clone() {
        NormalFrame frame = new NormalFrame(map.clone());
        frame.originMap = originMap.clone();
        for (int i = 0; i < past.size(); i++) frame.past.add(past.get(i));
        return frame;
    }

    @Override
    public void activate() {
        SwingUtilities.invokeLater(()->{
//            item = new Item[map.item.length];
//            int tot = map.item.length;
//            for (int i = 0; i < tot; i++) {
//                switch (map.type[i]) {
//                    case 0 -> item[i] = new Hero("src\\model\\data\\image\\Guide.png");
//                    case 1 -> item[i] = new Box("src\\model\\data\\image\\Box.jpg");
//                    case 2 -> item[i] = new Wall("src\\model\\data\\image\\Wall.png");
//                    case 3 -> item[i] = new Target("src\\model\\data\\image\\Target.png");
//                }
//            }
//            for (int i = 0; i < tot; i++) item[i].setId(i);
            if(isActivated) {
                setVisible(true);
                PathExplorer.path(originMap);
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
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 0) add(item[i]);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 1) add(item[i]);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 2) add(item[i]);
            for (int i = 0; i < item.length; i++) if(map.type[i] == 3) add(item[i]);
            for (int i = 0; i < item.length; i++) {
                item[i].setBounds(0,0, size, size);    //在repaint生效
                item[i].activate();
                if(map.type[i] == 0) currentSite = i;
            }
            PathExplorer.path(map);

            exited = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(exited);
            exited.setBounds(0,0,size,size);
            exited.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    setVisible(false);
                    LevelFrame.closenormalframe();
                }
            });
            exited.activate();

            reset = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
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

            hint = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
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

            withdraw = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
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

            background = new Item("src\\model\\data\\image\\background.png") {};
            add(background);
            background.setBounds(0,0,size*(col+1), size*(row+1));
            background.activate();

            column = new Item("src\\model\\data\\image\\background.png") {};
            add(column);
            column.setBounds(0,0,size*(col+1), size*(row+1));
            column.activate();



            setEnabled(true);
            setFocusable(true);
            setVisible(true);
            repaint();
        });
    }

    public synchronized void update(int DIR) {
        if(item[currentSite].isMoving) return;
        if(PathExplorer.isFinished()) return;
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
            SwingUtilities.invokeLater(()->{
                step.setText(Integer.toString(past.size()));
                step.setIconTextGap(-(int) (step.getWidth() * (past.size() < 10 ? 0.64 : 0.75)));
            });
            checkSucceed();
            checkFailed();
        }
    }

    public void checkFailed() {
        if(PathExplorer.isFailed()) {
            JDialog dialog = new JDialog(this);
            dialog.setLayout(new FlowLayout());
            dialog.setSize(200,100);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            JLabel error = new JLabel("无路可走了，请回退");
            error.setFont(new Font("微软雅黑",Font.BOLD,18));
            dialog.add(error);
            error.setSize(40,40);
            error.setVisible(true);
        }
    }
    public void checkSucceed() {
        if(PathExplorer.isFinished()) {
            JDialog dialog = new JDialog(this);
            dialog.setLayout(new FlowLayout());
            dialog.setSize(200,100);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            JLabel error = new JLabel("你赢了！");
            error.setFont(new Font("微软雅黑",Font.BOLD,18));
            dialog.add(error);
            error.setSize(40,40);
            error.setVisible(true);
        }
    }
    public synchronized void withdraw() {
        if(past.isEmpty()) return;
        if(item[currentSite].isMoving) return;
        int DIR = past.getLast(); past.removeLast();
        SwingUtilities.invokeLater(()->{
            step.setText(Integer.toString(past.size()));
            step.setIconTextGap(-(int) (step.getWidth() * (past.size() < 10 ? 0.64 : 0.75)));
        });
        boolean isBlocked = ((DIR & 4) != 0);
        DIR &= 3; DIR ^= 3;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
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
            checkFailed();
        }
    }

    public void Hint() {
        if(PathExplorer.isFailed() || PathExplorer.isFinished()) return;
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
        if(!isVisible()) return;
        super.repaint();
        SwingUtilities.invokeLater(()->{
            Dimension d = getSize();
            d.height = (int)(0.88*d.height); d.width = (int)(0.98*d.width);
            size = (int)Math.min(d.width / (3+col+row/2.0), d.height / (row+2.0));
            FullX = (d.width / (3+col+row/2.0) < d.height / (row+2.0));
            borderX = (FullX ? 0 : (int)((d.width - size * (3+col+row/2.0))/2.0));
            borderY = (FullX ? (int)((d.height - size * (row+2.0))/2.0) : 0);
            System.out.println(borderX + " "+ borderY);
//            size = Math.min(d.width / (col+2), d.height / (row+2));
            for (int i = 0; i < item.length; i++) {
                item[i].setBounds(map.item[i].x * size + size / 2 + size + borderX,map.item[i].y * size + size / 2 + size / 2 + borderY, size, size);
                item[i].activate();
            }


            background.setBounds(size + borderX, size/2 + borderY,size*(col+1),size*(row+1));
            background.activate();

            column.setBounds(size*(col+1) + size + borderX, size/2 + borderY,(int)(size*row/2.0+0.5),size*(row+1));
            column.activate();

            exited.setBounds((int)(size*(col+2+row/2.0-0.1 -row/10.0))+borderX,borderY + size/2 + size/5,size*row/10,size*row/10);
            exited.activate();

            reset.setBounds((int)(size*(col+2+row/2.0-0.15 -row/5.0))+borderX,borderY + size/2 + size/5,size*row/10,size*row/10);
            reset.activate();

            step.setBounds((int)(size*(col+2+0.1)) + borderX, borderY + size/2 + size/5, (int)(size*(row/2.0-row/5.0-0.4)),row*size/4);
            step.activate();
            step.setText(Integer.toString(past.size()));
            step.setIconTextGap(-(int)(step.getWidth()*(past.size()<10?0.64:0.75)));
            step.setFont(new Font("微软雅黑",Font.BOLD,(int)(row*size/10.0)));

            hint.setBounds((int)(size*(col+2+0.1)) + borderX, (int)(size*(row+1.5-3*row/25.0-0.2)) + borderY, (int)(size*(row/4.0-0.1)),3*row*size/25);
            hint.activate();

            withdraw.setBounds((int)(size*(col+2+row/2.0-0.1 - (row/4.0-0.1))) + borderX, (int)(size*(row+1.5-3*row/25.0-0.2)) + borderY, (int)(size*(row/4.0-0.1)),3*row*size/25);
            withdraw.activate();

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
        repaint();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            setVisible(false);
            LevelFrame.closenormalframe();
        }
    }
}
