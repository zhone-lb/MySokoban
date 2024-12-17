package controller.level;

import model.algorithm.Map;
import model.algorithm.PathExplorer;
import model.config.UserConfig;
import view.Activator;
import view.character.*;
import view.character.Box;
import view.character.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game_2048 extends NormalFrame implements Serializable, Activator {
    public ImageIcon[] NumIcon;

    public Game_2048(Map originMap) {
        Init(originMap);
    }
    public void Init(Map myMap) {
        map = myMap; originMap = new Map(map.row, map.col, map.item.clone(), map.type.clone());
        row = map.row; col = map.col; size = 50;
        int tot = map.item.length;
        currentSite = 0;
        isActivated = false;
        item = new Item[map.item.length];
        past = new ArrayList<>();
        a = new int[col][row];
        for (int i = 0; i < map.item.length; i++) {
            a[map.item[i].x][map.item[i].y] |= map.type[i];
        }
        NumIcon = new ImageIcon[13];
        for (int i = 0; i < 13; i++) {
            try {
                if(i == 0 || i == 12) NumIcon[i] = new ImageIcon(ImageIO.read(new File("src\\model\\data\\image\\null.png")));
                else NumIcon[i] = new ImageIcon(ImageIO.read(new File("src\\model\\data\\image\\"+Integer.toString(1<<i)+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(()->{
            for (int i = 0; i < tot; i++) {
                if(map.type[i] < 0) item[i] = new Hero("src\\model\\data\\image\\"+Integer.toString(-map.type[i])+".png");
                else if(map.type[i] == 4096)  item[i] = new Box("src\\model\\data\\image\\Wall.png");
                else item[i] = new Box("src\\model\\data\\image\\"+map.type[i]+".png");
            }
            for (int i = 0; i < tot; i++) item[i].setId(i);
        });
    }

    public void reset() {
        map = new Map(originMap.row, originMap.col, originMap.item.clone(), originMap.type.clone());
        past = new ArrayList<>();
        a = new int[col][row];
        for (int i = 0; i < map.item.length; i++) {
            a[map.item[i].x][map.item[i].y] |= map.type[i];
        }
        repaint();
    }

    @Override
    public void activate() {
        SwingUtilities.invokeLater(()->{
            if(isActivated) {
                setVisible(true);
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
            for (int i = 0; i < item.length; i++) add(item[i]);
            for (int i = 0; i < item.length; i++) {
                item[i].setBounds(0,0, size, size);    //在repaint生效
                item[i].activate();
                if(map.type[i] < 0) currentSite = i;
            }

            exited = new Button("src\\model\\data\\image\\back_0.png","src\\model\\data\\image\\back_1.png");
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

            reset = new Button("src\\model\\data\\image\\restart_0.png","src\\model\\data\\image\\restart_1.png");
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

            hint = new Button("src\\model\\data\\image\\hint.png","src\\model\\data\\image\\hint.png");
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

            setEnabled(true);
            setFocusable(true);
            setVisible(true);
            repaint();
        });
    }

    @Override
    public NormalFrame clone() {
        Game_2048 frame = new Game_2048(map);
        frame.past = new ArrayList<>();
        for (int i = 0; i < past.size(); i++) frame.past.add(past.get(i));
        return frame;
    }

    public int[][] a;
    public final int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    public boolean inInterval(int x, int y) {
        return (0 <= x && x < col && 0 <= y && y < row);
    }
    public boolean isValid(int x,int y,int DIR) {
        return (inInterval(x+dir[DIR][0],y+dir[DIR][1]) && a[x+dir[DIR][0]][y+dir[DIR][1]] != 4096);
    }
    public boolean getBlocked(int x, int y, int DIR) {
        return (a[x+dir[DIR][0]][y+dir[DIR][1]] != 0);
    }
    public int log(int x) {
        for (int i = 0; i < 12; i++) {
            if((1 << i) == x) return i;
        }
        return 12;
    }

    public synchronized void update(int DIR) {
        if(item[currentSite].isMoving) return;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
        if(isValid(x,y,DIR)) {
//            PathExplorer.put();
            if(!getBlocked(x,y,DIR)) {
                past.add(DIR);
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y]; a[x][y] = 0;
            }
            else if(a[x][y] != -a[x+dir[DIR][0]][y+dir[DIR][1]]) {
                if(isValid(x+dir[DIR][0],y+dir[DIR][1],DIR) && a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] == 0) {
                    past.add(1 << 2 | DIR);
                    int boxSite = getBoxSite(new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]));
                    map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                    map.item[boxSite] = new Point(x+2*PathExplorer.dir[DIR][0], y+2*PathExplorer.dir[DIR][1]);
                    item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                    item[boxSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                    a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] = a[x+dir[DIR][0]][y+dir[DIR][1]]; a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y]; a[x][y] = 0;
                }
            }
            else {
                past.add(1 << 3 | DIR);
                int boxSite = getBoxSite(new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]));
                map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
                map.type[boxSite] = 0; item[boxSite].setCurrentImage(NumIcon[12]); item[boxSite].activate();
                item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
                map.type[currentSite] = map.type[currentSite] * 2; item[currentSite].setCurrentImage(NumIcon[log(-map.type[currentSite])]); item[currentSite].activate();
                a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y] * 2; a[x][y] = 0;
            }
            SwingUtilities.invokeLater(()->{
                step.setText(Integer.toString(past.size()));
                step.setIconTextGap(-(int) (step.getWidth() * (past.size() < 10 ? 0.64 : 0.75)));
            });
            checkSucceed();
        }
    }

    public void checkSucceed() {
        if(map.type[currentSite] == -2048) {
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
        int temp = DIR;
        DIR &= 3; DIR ^= 3;
        int x = map.item[currentSite].x, y = map.item[currentSite].y;
        if((temp & 4) != 0) {
            int boxSite = getBoxSite(new Point(x+PathExplorer.dir[DIR^3][0], y+PathExplorer.dir[DIR^3][1]));
            map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
            map.item[boxSite] = new Point(x, y);
            item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
            item[boxSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
            a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y]; a[x][y] = a[x+dir[DIR^3][0]][y+dir[DIR^3][1]]; a[x+dir[DIR^3][0]][y+dir[DIR^3][1]] = 0;
        }
        else if((temp & 8) != 0) {
            int boxSite = getBoxSite(new Point(x,y));
            map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
            map.type[boxSite] = -map.type[currentSite]/2; item[boxSite].setCurrentImage(NumIcon[log(map.type[boxSite])]); item[boxSite].activate();
            map.type[currentSite] = map.type[currentSite]/2; item[currentSite].setCurrentImage(NumIcon[log(-map.type[currentSite])]); item[currentSite].activate();
            item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
            a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y] / 2; a[x][y] = -a[x][y] / 2;
        }
        else {
            map.item[currentSite] = new Point(x+PathExplorer.dir[DIR][0], y+PathExplorer.dir[DIR][1]);
            item[currentSite].move(PathExplorer.dir[DIR][0]*size, PathExplorer.dir[DIR][1]*size, UserConfig.GAME_SPEED, UserConfig.REFRESH_RATE);
            a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y]; a[x][y] = 0;
        }
    }

    public void Hint() {
    }

    protected int getBoxSite(Point p) {
        for (int i = 0; i < item.length; i++) {
            if(map.type[i] >= 0 && map.item[i].equals(p)) return i;
        }
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.printf("%d ",a[i][j]);
            }
            System.out.println();
        }
        return -1;
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        currentSite = ((Hero) e.getSource()).getId();
        System.out.println(currentSite);
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
//        repaint();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            setVisible(false);
        }
    }
}
