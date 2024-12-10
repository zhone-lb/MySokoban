import controller.editor.EditFrame;
import controller.editor.MapEditor;
import controller.level.NormalFrame;
import model.algorithm.Map;
import view.StartFrame;

import java.awt.*;

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
//        NormalFrame frame = new NormalFrame(map);
//        frame.activate();
//        MapEditor.CreateFrame();
        StartFrame.start();
        MapEditor mapEditor = new MapEditor();
        NormalFrame normalFrame = mapEditor.CreateFrame();
        normalFrame.activate();
    }
}