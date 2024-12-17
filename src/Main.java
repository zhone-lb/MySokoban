import controller.Settings;
import controller.editor.EditFrame;
import controller.editor.MapEditor;
import controller.level.Game_2048;
import controller.level.MultiHero;
import controller.level.NormalFrame;
import controller.user.User;
import model.algorithm.Map;
import model.config.UserConfig;
import model.data.MyWriter;
import view.SpecialFrame;
import view.StartFrame;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println("zhone-lb main");


//        PathExplorer.dijkstra();


//        TestFrame testFrame = new TestFrame();
//        Point[] item = new Point[7];
//        for (int i = 0; i < 5; i++) {
//            item[i] = new Point(i,i);
//        }
//        item[5] = new Point(0,1);
//        item[6] = new Point(3,0);
//        int[] type = {-1,1,2,4,8,4096,4096};
//        Map map = new Map(5,6,item,type);
//        NormalFrame frame = new Game_2048(map);
//        frame.activate();
//        frame.activate();
//        MapEditor.CreateFrame();
        StartFrame.start();
//        MapEditor mapEditor = new MapEditor();
//        NormalFrame normalFrame = mapEditor.CreateFrame();
//        normalFrame.activate();
//        User.getuserlist();
//        ArrayList<NormalFrame> List=new ArrayList<NormalFrame>();
//        MapEditor mapEditor =new MapEditor();
//        NormalFrame frame=mapEditor.CreateFrame();
//        for(int i=1;i<=5;++i){
//            List.add(frame.clone());
////            NormalFrame framee = new NormalFrame(map);
////            framee.activate();
//        }
//
//        MapEditor mapEditor = new MapEditor();
//        User.userlist.get(User.getuser("admin")).framelistsave = new ArrayList<>();
//        User.userlist.get(User.getuser("admin")).specialList = new ArrayList<>();
//        User.userlist.get(User.getuser("admin")).userConfig = new UserConfig();
//        for (int i = 0; i < 9; i++) User.userlist.get(User.getuser("admin")).specialList.add(null);
//
//
//        Point[] item = new Point[8];
//        item[0] = new Point(0,0);
//        item[1] = new Point(1,0);
//        item[2] = new Point(2,0);
//        item[3] = new Point(0,1);
//        item[4] = new Point(1,1);
//        item[5] = new Point(0,2);
//        item[6] = new Point(1,2);
//        item[7] = new Point(2,2);
//        int[] type = {2,2,3,0,1,2,2,0};
//        Map map = new Map(3,3,item,type);
//        NormalFrame frame = new MultiHero(map);
//        User.userlist.get(User.getuser("admin")).specialList.set(3,frame);

//        Point[] item = new Point[8];
//        item[0] = new Point(0,0);
//        item[1] = new Point(1,0);
//        item[2] = new Point(0,1);
//        item[3] = new Point(1,1);
//        item[4] = new Point(2,1);
//        item[5] = new Point(1,2);
//        item[6] = new Point(2,2);
//        item[7] = new Point(3,2);
//        int[] type = {-256,512,4096,4096,256,4096,4096,1024};
//        Map map = new Map(3,4,item,type);
//        NormalFrame frame = new Game_2048(map);
//        User.userlist.get(User.getuser("admin")).specialList.set(6,frame);


//        User.saveuserlist();

    }
}