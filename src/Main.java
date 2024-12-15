import controller.Settings;
import controller.editor.EditFrame;
import controller.editor.MapEditor;
import controller.level.MultiHero;
import controller.level.NormalFrame;
import model.algorithm.Map;
import model.data.Writer;
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
        int[] type = {0,1,1,3,3,0,2};
        Map map = new Map(5,6,item,type);
        MapEditor mapEditor = new MapEditor();
        NormalFrame[] frame = new NormalFrame[5];
        for (int i = 0; i < 5; i++) frame[i] = mapEditor.CreateFrame();
//        frame.revalidate();
//        frame.activate();
//        MapEditor.CreateFrame();
//        StartFrame.start();
//        MapEditor mapEditor = new MapEditor();
//        frame = mapEditor.ModifyFrame(frame);
//        frame.activate();
//        Settings settings = new Settings();
//        NormalFrame normalFrame = mapEditor.CreateFrame();
//        normalFrame.activate();
//        Writer.write(frame,"src\\model\\algorithm\\frame.txt");
    }
}