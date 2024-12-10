package model.algorithm;

import java.awt.*;
import java.util.*;

/**
 * 还有道具！！！
 */
public class PathExplorer {
    protected static final int INF = 1000000000;
    public static final int LEFT = 0, UP = 1, DOWN = 2, RIGHT = 3;
    public static final int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    protected static int row, col;
    public static int[][] a;
    protected static HashSet<Long> vis;
    protected static HashMap<Long, Integer> f;
    protected static HashMap<Long, Integer> trans;
    public static PriorityQueue<Pair<Integer, Long>> q;
    public static HashMap<Long, HashSet<Long>> e;
    public static HashMap<Long, Long> tr;
    public static void path(Map map) {
        row = map.row; col = map.col;
        a = new int[row][col];
        f = new HashMap<>();
        trans = new HashMap<>();
        vis = new HashSet<>();
        e = new HashMap<>();
        Init(map);
        int x = getHero(map).x, y = getHero(map).y;
        e.put(0L,new HashSet<>());
        dfs(x, y);
        dijkstra();
        Init(map);
        vis = new HashSet<>();
        dfs2(x,y);
    }
    protected static Point getHero(Map map) {
        for (int i = 0; i < map.item.length; i++) {
            if(map.type[i] == 0) return map.item[i];
        }
        return new Point(-1,-1);
    }
    protected static void dfs(int x, int y) {
        long now = getNum();
        e.put(now,new HashSet<>());
        if(isFinished()) {
            HashSet<Long> tp = e.get(0L);
            tp.add(now); e.put(0L,tp);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if(isValid(x,y,i)) {
                if((a[x+dir[i][0]][y+dir[i][1]] & 2) == 0) {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    long nxt = getNum();
                    if(e.get(nxt) == null) dfs(x+dir[i][0], y+dir[i][1]);
                    HashSet<Long> tp = e.get(nxt); tp.add(now); e.put(nxt,tp);
                    tp = e.get(now); tp.add(nxt); e.put(now,tp);
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                }
                else {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                    long nxt = getNum();
                    if(e.get(nxt) == null) dfs(x+dir[i][0], y+dir[i][1]);
                    HashSet<Long> tp = e.get(nxt); tp.add(now); e.put(nxt,tp);
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                }
            }
        }
    }

    public static void dijkstra() {
        vis = new HashSet<>();
        tr = new HashMap<>();
        f = new HashMap<>();
        for (Long i : e.keySet()) f.put(i,INF);
        f.put(0L,0);
        q = new PriorityQueue<>();
        q.add(new Pair<>(0,0L));
        while(!q.isEmpty()) {
            long x = q.peek().value; q.poll();
            if(vis.contains(x)) continue;
            vis.add(x);
            if(e.get(x) == null) continue;
            for (Long i : e.get(x)) {
                if(f.get(i) > f.get(x) + 1) {
                    f.put(i, f.get(x) + 1);
                    if(!vis.contains(i)) q.add(new Pair<>(f.get(i),i));
                }
                if(f.get(i) == f.get(x) + 1) tr.put(i, x);
            }
        }
    }

    protected static void dfs2(int x, int y) {
        long now = getNum();
        vis.add(now);
        if(isFinished()) return;
        for (int i = 0; i < 4; i++) {
            if(isValid(x,y,i)) {
                if((a[x+dir[i][0]][y+dir[i][1]] & 2) == 0) {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    long nxt = getNum();
                    if(!vis.contains(nxt)) dfs2(x+dir[i][0], y+dir[i][1]);
                    if(tr.containsKey(nxt) && tr.get(nxt) == now) trans.put(nxt, i^3);
                    if(tr.containsKey(now) && tr.get(now) == nxt) trans.put(now, i);
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                }
                else {
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
                    long nxt = getNum();
                    if(!vis.contains(nxt)) dfs2(x+dir[i][0], y+dir[i][1]);
                    if(tr.containsKey(now) && tr.get(now) == nxt) trans.put(now, i);
                    a[x][y] ^= 1; a[x+dir[i][0]][y+dir[i][1]] ^= 1;
                    a[x+dir[i][0]][y+dir[i][1]] ^= 2; a[x+2*dir[i][0]][y+2*dir[i][1]] ^= 2;
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
                        (a[x+dir[DIR][0]][y+dir[DIR][1]] & 7) == 0
                    )
                ||
                    (
                        inInterval(x+dir[DIR][0],y+dir[DIR][1]) &&
                        inInterval(x+2*dir[DIR][0],y+2*dir[DIR][1]) &&
                        (a[x+dir[DIR][0]][y+dir[DIR][1]] & 5) == 0 &&
                        (a[x+2*dir[DIR][0]][y+2*dir[DIR][1]] & 7) == 0
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
//        System.out.println("the number of rest steps is "+f.get(getNum()));
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
//        System.out.println("the number of rest steps is "+f.get(getNum()));
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
    public static void Init(Map map) {
        row = map.row; col = map.col;
        a = new int[map.col][map.row];
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
