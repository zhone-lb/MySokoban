import view.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartFrame startframe=new StartFrame();
            startframe.setVisible(true);
        });
    }
}