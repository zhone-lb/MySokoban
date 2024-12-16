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
        item = new Item[map.item.length];
        past = new ArrayList<>();
        a = new int[col][row];
        for (int i = 0; i < map.item.length; i++) {
            a[map.item[i].x][map.item[i].y] |= map.type[i];
        }
        NumIcon = new ImageIcon[13];
        for (int i = 0; i < 13; i++) {
            try {
                if(i%2 == 0) NumIcon[i] = new ImageIcon(ImageIO.read(new File("src\\model\\data\\image\\background.png")));
                else NumIcon[i] = new ImageIcon(ImageIO.read(new File("src\\model\\data\\image\\Guide.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(()->{
            for (int i = 0; i < tot; i++) {
                if(map.type[i] < 0) item[i] = new Hero("src\\model\\data\\image\\Guide.png");
                else item[i] = new Box("src\\model\\data\\image\\Guide.png");
            }
            for (int i = 0; i < tot; i++) item[i].setId(i);
        });
    }

    public void reset() {
        map = new Map(originMap.row, originMap.col, originMap.item.clone(), originMap.type.clone());
        past = new ArrayList<>();

        repaint();
    }

    @Override
    public void activate() {
        SwingUtilities.invokeLater(()->{
            size = 50;
            enableEvents(AWTEvent.KEY_EVENT_MASK);
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
            enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
            setSize(1000,600);
            setLocationRelativeTo(null);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (int i = 0; i < item.length; i++) add(item[i]);
            for (int i = 0; i < item.length; i++) {
                item[i].setBounds(0,0, size, size);    //在repaint生效
                item[i].activate();
                if(map.type[i] < 0) currentSite = i;
            }

            exited = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(exited);
            exited.setBounds(0,0,size,size);
            exited.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

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
        if(map.type[currentSite] == 2048) {
            System.out.println("You win");
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
            a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] = a[x+dir[DIR][0]][y+dir[DIR][1]]; a[x+dir[DIR][0]][y+dir[DIR][1]] = a[x][y]; a[x][y] = 0;
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
    public synchronized void repaint() {
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
        repaint();
    }
}
