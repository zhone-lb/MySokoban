package view.character;

import view.Activator;
import view.character.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Button extends Item implements Serializable, Activator {
    public Button(String file1, String file2) {
        this.id = 0;
        try {
            originImage = new ImageIcon(ImageIO.read(new File(file1)));
            focusedImage = new ImageIcon(ImageIO.read(new File(file2)));
            currentImage = originImage;
            setIcon(currentImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activate() {
        super.activate();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(focusedImage != null) currentImage = focusedImage;
                scale(getWidth(),getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                currentImage = originImage;
                scale(getWidth(),getHeight());
            }
        });
    }
}