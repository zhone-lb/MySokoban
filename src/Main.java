import controller.editor.EditFrame;
import controller.editor.MapEditor;
import controller.level.NormalFrame;
import controller.user.User;
import model.algorithm.Map;
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
        int[] type = {0,1,1,3,3,2,2};
        Map map = new Map(5,6,item,type);
        NormalFrame frame = new NormalFrame(map);
//        frame.activate();
//        frame.activate();
//        MapEditor.CreateFrame();
        StartFrame.start();
//        MapEditor mapEditor = new MapEditor();
//        NormalFrame normalFrame = mapEditor.CreateFrame();
//        normalFrame.activate();
//        User.getuserlist();
//        ArrayList<NormalFrame> List=new ArrayList<NormalFrame>();
//        for(int i=1;i<=5;++i){
//            List.add(frame.clone());
////            NormalFrame framee = new NormalFrame(map);
////            framee.activate();
//        }
//
//        User.userlist.get(User.getuser("admin")).framelistsave=List;
//        User.saveuserlist();
    }
}