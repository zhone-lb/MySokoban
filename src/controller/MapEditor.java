package controller;

import controller.level.NormalFrame;
import model.algorithm.Map;

import javax.swing.*;
import java.awt.*;

public class MapEditor {
    public static NormalFrame CreateFrame() {
        JDialog dialog = new JDialog();
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400,300);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        dialog.setLayout(layout);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        JLabel title = new JLabel("请设置地图长与宽");
        title.setFont(new Font("微软雅黑",Font.BOLD,32));
        layout.setConstraints(dialog, constraints);
        dialog.add(title);

        constraints.gridwidth = 1;

    }
}
