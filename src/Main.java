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
        Point[] item = new Point[7];
        for (int i = 0; i < 5; i++) {
            item[i] = new Point(i,i);
        }
        item[5] = new Point(0,1);
        item[6] = new Point(3,0);
        int[] type = {-1,1,2,4,8,4096,4096};
        Map map = new Map(5,6,item,type);
        NormalFrame frame = new Game_2048(map);
//        frame.activate();
//        frame.activate();
//        MapEditor.CreateFrame();
//        StartFrame.start();
//        MapEditor mapEditor = new MapEditor();
//        NormalFrame normalFrame = mapEditor.CreateFrame();
//        normalFrame.activate();
        User.getuserlist();
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
//        for (int i = 0; i < 5; i++) User.userlist.get(User.getuser("admin")).framelistsave.add(mapEditor.CreateFrame());
//        for (int i = 0; i < User.userlist.get(User.getuser("admin")).framelistsave.size(); i++) {
//            User.userlist.get(User.getuser("admin")).framelistsave.set(i,mapEditor.CreateFrame());
//        }
//        User.userlist.get(User.getuser("admin")).specialList = new ArrayList<>();
//        User.userlist.get(User.getuser("admin")).specialList.add(frame);
//        User.userlist.get(User.getuser("admin")).userConfig = new UserConfig();
        for (int i = 0; i < User.userlist.get(User.getuser("admin")).framelistsave.size(); i++) {
            User.userlist.get(User.getuser("admin")).framelistsave.get(i).activate();
        }
        User.userlist.get(User.getuser("admin")).specialList.get(0).activate();
//        User.saveuserlist();

    }
}