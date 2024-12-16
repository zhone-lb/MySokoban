package model.algorithm;

import java.awt.Point;
import java.io.Serializable;

public class Map implements Serializable {
    public int row, col;
    public Point[] item;
    public int[] type;
    public Map(int row, int col) {
        this.row = row;
        this.col = col;
        this.item = new Point[0];
        this.type = new int[0];
    }
    public Map(int row, int col, Point[] item, int[] type) {
        this.row = row;
        this.col = col;
        this.item = item;
        this.type = type;
    }
    @Override
    public Map clone() {
        return new Map(row,col,item.clone(),type.clone());
    }
}
