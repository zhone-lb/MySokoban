import controller.TestFrame;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartFrame startframe=new StartFrame();
            startframe.setVisible(true);


            //想控制窗口最小大小，失败了 qwq
//            startframe.addComponentListener(new ComponentAdapter() {
//                public void componentResized(ComponentEvent evt) {
//                    if (startframe.getWidth() < 540) {
//                        startframe.setSize(540,startframe.getHeight());
//                    }
//                    if (startframe.getHeight() < 360) {
//                        startframe.setSize(startframe.getWidth(),360);
//                    }
//                }
//            });
        });
    }
}