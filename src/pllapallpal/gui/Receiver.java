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
        while (true) {
            try {
                BufferedImage bufferedImage = ImageIO.read(input);
                imageIcon.setImage(bufferedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
