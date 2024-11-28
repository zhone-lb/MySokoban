package model;

import java.awt.Point;

public class Map {
    public int row, col;
    public Point[] hero;
    public Point[] box;
    public Point[] wall;
    public Point[] target;
    public Map(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Map(int row, int col, Point[] hero, Point[] box, Point[] rigid, Point[] target) {
        this.row = row;
        this.col = col;
        this.hero = hero;
        this.box = box;
        this.wall = rigid;
        this.target = target;
    }
}
