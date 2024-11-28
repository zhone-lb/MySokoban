package controller.level;

import model.Map;
import view.Activator;
import view.character.*;
import model.config.UserConfig;

import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class NormalFrame extends JFrame implements Serializable, Activator {
    public int row, col;
    public Map map;
    public Item[] item;
    public Point[] location;
    public Hero currentHero;
    public NormalFrame(Map originMap) {
        Init(originMap);
    }
    public void Init(Map originMap) {
        map = originMap;
        row = map.row; col = map.col;
        int tot =map.hero.length+map.box.length+map.wall.length+map.target.length;
        item = new Item[tot]; int index = 0;
        for (int i = 0; i < map.hero.length; i++, index++) item[index] = new Hero("");
        for (int i = 0; i < map.box.length; i++, index++) item[index] = new Box("");
        for (int i = 0; i < map.wall.length; i++, index++) item[index] = new Wall("");
        for (int i = 0; i < map.target.length; i++, index++) item[index] = new Target("");
        activate();
    }
    @Override
    public void activate() {
        setLocation(200,100);
        setSize(400,300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resetMap();
        for (int i = 0; i < item.length; i++) {
            add(item[i]);
//            item[i].setBounds();    //根据布局设置合理大小和位置
            item[i].activate();
        }
        setEnabled(true);
        setFocusable(false);
        setVisible(true);
    }

    public void resetMap() {
        map = new Map(row, col);
        ArrayList<Point> hero = new ArrayList<>();
        ArrayList<Point> box = new ArrayList<>();
        ArrayList<Point> wall = new ArrayList<>();
        ArrayList<Point> target = new ArrayList<>();
        for (int i = 0; i < item.length; i++) {

        }
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        currentHero = (Hero) e.getSource();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if(e.getKeyCode() == UserConfig.MOVE_UP) {

        }
    }
}
