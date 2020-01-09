package pllapallpal.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;

public class Receiver implements Runnable {

    private DataInputStream input;
    private ImageIcon imageIcon;

    public Receiver(DataInputStream input, ImageIcon imageIcon) {
        this.input = input;
        this.imageIcon = imageIcon;
    }

    @Override
    public void run() {
        BufferedImage image;

        while (true) {
            try {
                System.out.println(1);
                image = ImageIO.read(input);
                System.out.println(2);
                imageIcon.setImage(image);
                System.out.println(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
