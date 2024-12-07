package controller.editor;

import controller.level.NormalFrame;
import model.algorithm.PathExplorer;
import view.character.*;
import model.algorithm.Map;
import view.character.Box;
import view.character.Button;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class EditFrame extends NormalFrame {
    public int[][] a;
    public Item Highlight;
    public Button[] alter;
    public Point HPos;
    public EditFrame(Map originMap) {
        row = originMap.row;
        col = originMap.col;
        a = new int[col][row];
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
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            Highlight = new Item("src\\model\\data\\image\\background.png") {};
            add(Highlight);
            Highlight.setBounds(0,0,size,size);
            Highlight.activate();
            Highlight.setVisible(false);

            alter = new Button[4];
            alter[0] = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(alter[0]);
            alter[0].setBounds(0,0,size,size);
            alter[0].activate();
            alter[0].setVisible(false);
            alter[0].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    a[HPos.x][HPos.y] ^= 1;
                    repaint();
                }
            });


            hint = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(hint);
            hint.setBounds(0,0,size,size);
            hint.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    synchronized (MapEditor.finalMap) {
                        MapEditor.finalMap = getMap(a);
                    }
                    dispose();
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
                    synchronized (MapEditor.finalMap) {
                        MapEditor.finalMap = new Map(-1, -1);
                    }
                    dispose();
                }
            });
            withdraw.activate();


            alter[1] = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(alter[1]);
            alter[1].setBounds(0,0,size,size);
            alter[1].activate();
            alter[1].setVisible(false);
            alter[1].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    a[HPos.x][HPos.y] ^= 2;
                    repaint();
                }
            });

            alter[2] = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(alter[2]);
            alter[2].setBounds(0,0,size,size);
            alter[2].activate();
            alter[2].setVisible(false);
            alter[2].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    a[HPos.x][HPos.y] ^= 4;
                    repaint();
                }
            });

            alter[3] = new Button("src\\model\\data\\image\\background.png","src\\model\\data\\image\\background.png");
            add(alter[3]);
            alter[3].setBounds(0,0,size,size);
            alter[3].activate();
            alter[3].setVisible(false);
            alter[3].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    a[HPos.x][HPos.y] ^= 8;
                    repaint();
                }
            });

            column = new Item("src\\model\\data\\image\\background.png") {};
            add(column);
            column.setBounds(0,0,size*(col+1), size*(row+1));
            column.activate();

            background = new Item("src\\model\\data\\image\\background.png") {};
            add(background);
            background.setBounds(0,0,size*(col+1), size*(row+1));
            background.activate();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int x = (e.getX()-size-size/2-borderX), y = (e.getY()-size/2 -size/2 -borderY - 30);
                    System.out.println(e.getPoint().toString()+x+" "+y+" "+size);
                    if(x >= 0 && x < col*size && y >= 0 && y < row*size) {
                        x /= size; y /= size;
                        if(HPos != null && HPos.x == x && HPos.y == y) {
                            HPos = null;
                            repaint();
                        }
                        else {
                            HPos = new Point(x,y);
//                            Highlight.setVisible(true);
//                            for (int i = 0; i < 4; i++) alter[i].setVisible(true);
//                            Highlight.setBounds(0,0,size,size);
                            Highlight.setBounds(x * size + size / 2 + size + borderX,y * size + size / 2 + size / 2 + borderY, size, size);
                            repaint();
                        }
                    }
                }
            });


            setEnabled(true);
            setFocusable(true);
            setVisible(true);
            repaint();
        });
    }

    @Override
    public synchronized void repaint() {
        SwingUtilities.invokeLater(()->{
            Init(getMap(a));
            Dimension d = getSize();
            d.height = (int)(0.88*d.height); d.width = (int)(0.98*d.width);
            size = (int)Math.min(d.width / (3+col+row/2.0), d.height / (row+2.0));
            FullX = (d.width / (3+col+row/2.0) < d.height / (row+2.0));
            borderX = (FullX ? 0 : (int)((d.width - size * (3+col+row/2.0))/2.0));
            borderY = (FullX ? (int)((d.height - size * (row+2.0))/2.0) : 0);
            System.out.println(borderX + " "+ borderY);
//            size = Math.min(d.width / (col+2), d.height / (row+2));
            for (int i = 0; i < item.length; i++) {
                add(item[i]);
                item[i].setBounds(map.item[i].x * size + size / 2 + size + borderX,map.item[i].y * size + size / 2 + size / 2 + borderY, size, size);
                item[i].activate();
            }

            remove(background);
            add(background);
            background.setBounds(size + borderX, size/2 + borderY,size*(col+1),size*(row+1));
            background.activate();

            column.setBounds(size*(col+1) + size + borderX, size/2 + borderY,(int)(size*row/2.0+0.5),size*(row+1));
            column.activate();

            hint.setBounds((int)(size*(col+2+0.1)) + borderX, (int)(size*(row+1.5-3*row/25.0-0.2)) + borderY, (int)(size*(row/4.0-0.1)),3*size*row/25);
            hint.activate();

            withdraw.setBounds((int)(size*(col+2+row/2.0-0.1 - (row/4.0-0.1))) + borderX, (int)(size*(row+1.5-3*row/25.0-0.2)) + borderY, (int)(size*(row/4.0-0.1)),3*row*size/25);
            withdraw.activate();

            alter[0].setBounds((int)(size*(col+2+0.3)) + borderX, (int)(size*(row/2.0-1.5+1)) + borderY, (int)(size*(row/2.0-0.8)/2), (int)(size*(row/2.0-0.8)/2));
            alter[0].activate();

            alter[1].setBounds((int)(size*(col+2+ (row/2.0-0.8)/2 + 0.4)) + borderX, (int)(size*(row/2.0-1.5+1)) + borderY, (int)(size*(row/2.0-0.8)/2), (int)(size*(row/2.0-0.8)/2));
            alter[1].activate();

            alter[2].setBounds((int)(size*(col+2+0.3)) + borderX, (int)(size*(row/2.0-1.5+1+(row/2.0-0.8)/2+0.4)) + borderY, (int)(size*(row/2.0-0.8)/2), (int)(size*(row/2.0-0.8)/2));
            alter[2].activate();

            alter[3].setBounds((int)(size*(col+2+ (row/2.0-0.8)/2 + 0.4)) + borderX, (int)(size*(row/2.0-1.5+1+(row/2.0-0.8)/2+0.4)) + borderY, (int)(size*(row/2.0-0.8)/2), (int)(size*(row/2.0-0.8)/2));
            alter[3].activate();


            if(HPos != null) Highlight.setBounds(HPos.x * size + size / 2 + size + borderX,HPos.y * size + size / 2 + size / 2 + borderY, size, size);
            Highlight.activate();

            if(HPos == null) {
                Highlight.setVisible(false);
                for (int i = 0; i < 4; i++) {
                    alter[i].setVisible(false);
                }
            }

            setEnabled(true);
            setFocusable(true);
            setVisible(true);
        });
    }
    protected Map getMap(int[][] a) {
        ArrayList<Point> item = new ArrayList<>();
        ArrayList<Integer> type = new ArrayList<>();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if((a[i][j]&1) != 0) {item.add(new Point(i,j)); type.add(0);}
                if((a[i][j]&2) != 0) {item.add(new Point(i,j)); type.add(1);}
                if((a[i][j]&4) != 0) {item.add(new Point(i,j)); type.add(2);}
                if((a[i][j]&8) != 0) {item.add(new Point(i,j)); type.add(3);}
            }
        }
        Point[] pt = new Point[item.size()];
        int[] tp = new int[type.size()];
        for (int i = 0; i < item.size(); i++) pt[i] = item.get(i);
        for (int i = 0; i < type.size(); i++) tp[i] = type.get(i);
        return new Map(row,col,pt,tp);
    }

    @Override
    public void Init(Map myMap) {
        map = myMap;
        row = map.row; col = map.col;
        size = 50;
        int tot = map.item.length;
        currentSite = 0;
        if(item != null) for (int i = 0; i < item.length; i++) remove(item[i]);
        item = new Item[tot];
        past = new ArrayList<>();
        for (int i = 0; i < tot; i++) {
            switch (map.type[i]) {
                case 0 -> item[i] = new Hero("src\\model\\data\\image\\Guide.png");
                case 1 -> item[i] = new Box("src\\model\\data\\image\\Box.jpg");
                case 2 -> item[i] = new Wall("src\\model\\data\\image\\Wall.png");
                case 3 -> item[i] = new Target("src\\model\\data\\image\\Target.png");
            }
        }
        for (int i = 0; i < tot; i++) item[i].setId(i);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if(HPos == null || e.getID() != KeyEvent.KEY_RELEASED) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1 -> a[HPos.x][HPos.y] ^= 1;
            case KeyEvent.VK_2 -> a[HPos.x][HPos.y] ^= 2;
            case KeyEvent.VK_3 -> a[HPos.x][HPos.y] ^= 4;
            case KeyEvent.VK_4 -> a[HPos.x][HPos.y] ^= 8;
        }
        repaint();
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {

    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            MapEditor.finalMap = new Map(-1,-1);
            dispose();
        }
    }
}
