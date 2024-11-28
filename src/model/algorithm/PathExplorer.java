package model.algorithm;

import jdk.jfr.Unsigned;
import model.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * 还有道具！！！
 */
public class PathExplorer {
    protected static final int INF = 1000000000;
    public static final int RIGHT = 0, LEFT = 1, DOWN = 2, UP = 3;
    protected static final int dir[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    protected static int row, col;
    protected static int[][] a;
    protected static java.util.HashMap<Long, Integer> f;
    protected static java.util.HashMap<Long, Integer> trans;
    public static void path(Map map) {
        Init(map);
        int x = map.hero[0].x, y = map.hero[0].y;
        dfs(x, y);
    }
    protected static void dfs(int x, int y) {
        long now = getNum();
        f.put(now,INF);
        if(isFinished()) {
            f.put(now, 0);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if(isValid(x,y,i)) {
                if((a[x+dir[i][0]][y+dir[i][1]] & 2) == 0) {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    x += dir[i][0]; y += dir[i][1];
                }
                else {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                    x += dir[i][0]; y += dir[i][1];
                }
                long nxt = getNum();
                if(f.get(nxt) == null) dfs(x, y);
                if(f.get(nxt) + 1 < f.get(now)) {
                    f.put(now, f.get(nxt));
                    trans.put(now, i);
                }
                if((a[x+dir[i][0]][y+dir[i][1]] & 2) == 0) {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    x -= dir[i][0]; y -= dir[i][1];
                }
                else {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                    x -= dir[i][0]; y -= dir[i][1];
                }
            }
        }
    }
    public static int getHint(int x, int y) {
        return trans.get(getNum());
    }
    public static boolean isFailed(int x, int y) {
        return (trans.get(getNum()) == null);
    }
    public static boolean isFinished() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if((a[i][j] & 8) != 0 && (a[i][j] & 2) == 0) return false;
            }
        }
        return true;
    }
    public static boolean isValid(int x,int y,int DIR) {
        return (    inInterval(x+dir[DIR][0],y+dir[DIR][1])
                &&  inInterval(x+2*dir[DIR][0],y+2*dir[DIR][1])
                &&  (a[x+dir[DIR][0]][y+dir[DIR][1]] & 4) == 0
                &&  (
                        (a[x+dir[DIR][0]][y+dir[DIR][1]] & 2) == 0 ||
                        (a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] & 6) == 0
                    )
               );
    }
    protected static boolean inInterval(int x, int y) {
        return (0 <= x && x < row && 0 <= y && y <col);
    }
    protected static long getNum() {
        return getHash(998244353L) << 32L | getHash(1000000007L);
    }
    protected static long getHash(long mod) {
        long ans = 0, b = 137L;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans=(ans*b+a[i][j])%mod;
            }
        }
        return ans;
    }
    protected static void Init(Map map) {
        row = map.row; col = map.col;
        a = new int[row][col];
        f = new HashMap<>();
        trans = new HashMap<>();
        for (Point p : map.hero) a[p.x][p.y] |= 1;
        for (Point p : map.box) a[p.x][p.y] |= 2;
        for (Point p : map.wall) a[p.x][p.y] |= 4;
        for (Point p : map.target) a[p.x][p.y] |= 8;
        f.put(getNum(),INF);
    }
}
