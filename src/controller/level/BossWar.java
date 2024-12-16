package controller.level;

import model.algorithm.PathExplorer;
import view.Activator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Queue;

public class BossWar extends NormalFrame implements Serializable, Activator {
    protected Thread BossMover, HeroMover;
    protected int[][] fa, myDir;
    public static final int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);

    }
    protected void BossAI() {

    }
    public boolean inInterval(int x, int y) {
        return (0 <= x && x < col && 0 <= y && y < row);
    }
    protected synchronized void BossExplorer(int x, int y) {
        boolean[][] vis = new boolean[col][row];
        myDir = new int[col][row];

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(x,y));
        vis[x][y] = true;
        while(!q.isEmpty()) {
            Point p = q.getFirst(); q.removeFirst();
            if((PathExplorer.a[p.x][p.y] & 1) != 0) {
                for(; p.equals(new Point(x,y)); p = new Point(p.x+dir[fa[p.x][p.y]^3][0],p.y+dir[fa[p.x][p.y]^3][1])) {
                    myDir[p.x+dir[fa[p.x][p.y]^3][0]][p.y+dir[fa[p.x][p.y]^3][1]] = fa[p.x][p.y];//////???????
                }
            }
            for (int i = 0; i < 4; i++) { //initiate the PathExplorer
                if(inInterval(p.x+dir[i][0],p.y+dir[i][1]) && (PathExplorer.a[p.x+dir[i][0]][p.y+dir[i][1]] & 6) == 0) {
                    if(vis[p.x+dir[i][0]][p.y+dir[i][1]]) continue;
                    fa[p.x+dir[i][0]][p.y+dir[i][1]] = i;
                    vis[p.x+dir[i][0]][p.y+dir[i][1]] = true;

                }
            }
        }
    }
}
