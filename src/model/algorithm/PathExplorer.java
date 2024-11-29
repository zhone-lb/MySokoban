package model.algorithm;

import jdk.jfr.Unsigned;
import model.Map;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 还有道具！！！
 */
public class PathExplorer {
    protected static final int INF = 1000000000;
    public static final int LEFT = 0, UP = 1, DOWN = 2, RIGHT = 3;
    public static final int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    protected static int row, col;
    public static int[][] a;
    protected static ArrayList<Map> end;
    protected static HashSet<Long> vis;
    protected static HashMap<Long, Integer> f;
    protected static HashMap<Long, Integer> trans;
    public static void path(Map map) {
        row = map.row; col = map.col;
        a = new int[row][col];
        f = new HashMap<>();
        trans = new HashMap<>();
        end = new ArrayList<>();
        vis = new HashSet<>();
        Init(map);
        int x = map.item[0].x, y = map.item[0].y;
        dfs(x, y);
        for (int i = 0; i < end.size(); i++) {
            vis.clear();
            Init(end.get(i));
//            put();
            dfs2(getHero(end.get(i)).x, getHero(end.get(i)).y);
        }
        Init(map);
//        put();
    }
    protected static Point getHero(Map map) {
        for (int i = 0; i < map.item.length; i++) {
            if(map.type[i] == 0) return map.item[i];
        }
        return new Point(-1,-1);
    }
    protected static void dfs(int x, int y) {
        long now = getNum();
        f.put(now,INF);
        if(isFinished()) {
            f.put(now, 0);
            end.add(getMap());
            return;
        }
        for (int i = 0; i < 4; i++) {
            if(isValid(x,y,i)) {
                if((a[x+dir[i][0]][y+dir[i][1]] & 2) == 0) {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    long nxt = getNum();
                    if(f.get(nxt) == null) dfs(x+dir[i][0], y+dir[i][1]);
                    if(f.get(nxt) + 1 < f.get(now)) {
                        f.put(now, f.get(nxt) + 1);
                        trans.put(now, i);
                    }
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                }
                else {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                    long nxt = getNum();
                    if(f.get(nxt) == null) dfs(x+dir[i][0], y+dir[i][1]);
                    if(f.get(nxt) + 1 < f.get(now)) {
                        f.put(now, f.get(nxt) + 1);
                        trans.put(now, i);
                    }
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                }
            }
        }
    }
    protected static void dfs2(int x, int y) {
        long now = getNum();
        vis.add(now);
        for (int i = 0; i < 4; i++) {
            if(isValid2(x,y,i)) {
                if(inInterval(x+dir[i^3][0],y+dir[i^3][1]) && (a[x+dir[i^3][0]][y+dir[i^3][1]] & 6) == 2) {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i^3][0]][y+dir[i^3][1]] ^= 2; a[x][y] ^= 2;
                    long nxt = getNum();
                    if(!f.containsKey(nxt)) f.put(nxt,INF);
                    if(f.get(nxt) > f.get(now) + 1) {
                        f.put(nxt, f.get(now) + 1);
                        trans.put(nxt, i ^ 3);
                    }
                    if(!vis.contains(nxt)) dfs2(x+dir[i][0],y+dir[i][1]);
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i^3][0]][y+dir[i^3][1]] ^= 2; a[x][y] ^= 2;
                }
                else {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    long nxt = getNum();
                    if(!f.containsKey(nxt)) f.put(nxt,INF);
                    if(f.get(nxt) > f.get(now) + 1) {
                        f.put(nxt, f.get(now) + 1);
                        trans.put(nxt, i ^ 3);
                    }
                    if(!vis.contains(nxt)) dfs2(x+dir[i][0],y+dir[i][1]);
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                }
            }
        }
    }

    public static void put() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.printf("%d ", a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int getHint(int x, int y) {
        return trans.get(getNum());
    }
    public static boolean isFailed() {
        return (f.get(getNum()) == INF);
    }
    public static boolean isFinished() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if((a[i][j] & 8) != 0 && (a[i][j] & 2) == 0) return false;
            }
        }
        return true;
    }
    public static boolean isValid(int x,int y,int DIR) {
        return (
                    (
                        inInterval(x+dir[DIR][0],y+dir[DIR][1]) &&
                        (a[x+dir[DIR][0]][y+dir[DIR][1]] & 6) == 0
                    )
                ||
                    (
                        inInterval(x+dir[DIR][0],y+dir[DIR][1]) &&
                        inInterval(x+2*dir[DIR][0],y+2*dir[DIR][1]) &&
                        (a[x+dir[DIR][0]][y+dir[DIR][1]] & 4) == 0 &&
                        (a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] & 6) == 0
                    )
               );
    }
    public static boolean isValid2(int x,int y,int DIR) {
        return (
                    (
                        inInterval(x+dir[DIR][0],y+dir[DIR][1]) &&
                        (a[x+dir[DIR][0]][y+dir[DIR][1]] & 6) == 0
                    )
               );
    }
    public static boolean getBlocked2(int x,int y,int DIR) {
        return (inInterval(x+dir[DIR^3][0],y+dir[DIR^3][1]) && (a[x+dir[DIR^3][0]][y+dir[DIR^3][1]] & 6) == 2);
    }
    public static void refresh(int x, int y, int DIR, boolean isBlocked) {
//        System.out.println(x+" "+y+" "+DIR+" "+isBlocked);
        if(!isBlocked) {
            a[x][y] ^= 1; a[x+dir[DIR][0]][y+dir[DIR][1]] ^= 1;
        }
        else {
            a[x][y] ^= 1; a[x+dir[DIR][0]][y+dir[DIR][1]] ^= 1;
            a[x+dir[DIR][0]][y+dir[DIR][1]] ^= 2; a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] ^= 2;
        }
//        put();
        System.out.println("the number of rest steps is "+f.get(getNum()));
    }
    public static void refresh2(int x,int y,int DIR,boolean isBlocked) {
//        System.out.println(x+" "+y+" "+DIR+" "+isBlocked);
        if(!isBlocked) {
            a[x][y] ^= 1; a[x+dir[DIR][0]][y+dir[DIR][1]] ^= 1;
        }
        else {
            a[x][y] ^= 1; a[x+dir[DIR][0]][y+dir[DIR][1]] ^= 1;
            a[x+dir[DIR^3][0]][y+dir[DIR^3][1]] ^= 2; a[x][y] ^= 2;
        }
//        put();
        System.out.println("the number of rest steps is "+f.get(getNum()));
    }
    public static boolean getBlocked(int x, int y, int DIR) {
        return ((a[x+dir[DIR][0]][y+dir[DIR][1]] & 2) != 0);
    }

    protected static boolean inInterval(int x, int y) {
        return (0 <= x && x < col && 0 <= y && y < row);
    }
    protected static long getNum() {
        return getHash(998244353L) << 32L | getHash(1000000007L);
    }
    protected static long getHash(long mod) {
        long ans = 0, b = 137L;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                ans=(ans*b+a[i][j])%mod;
            }
        }
        return ans;
    }
    protected static Map getMap() {
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
    protected static void Init(Map map) {
        a = new int[col][row];
        for (int i = 0; i < map.item.length; i++) {
            switch (map.type[i]) {
                case 0 -> a[map.item[i].x][map.item[i].y] |= 1;
                case 1 -> a[map.item[i].x][map.item[i].y] |= 2;
                case 2 -> a[map.item[i].x][map.item[i].y] |= 4;
                case 3 -> a[map.item[i].x][map.item[i].y] |= 8;
            }
        }
    }
}
