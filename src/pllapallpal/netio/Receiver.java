package pllapallpal.netio;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class Receiver implements Runnable {

    private DataInputStream input;
    private Consumer<BufferedImage> changeImage;

    public Receiver(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {

        while (true) {
            try {
                // receive the size of image from the inputStream
                byte[] sizeArray = new byte[4];
                input.read(sizeArray);
                // receive the actual image
                int size = ByteBuffer.wrap(sizeArray).getInt();
                byte[] imageArray = new byte[size];
                input.read(imageArray);

                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageArray));
                // save received image as a file
                ImageIO.write(image, "png", new File("image.png"));
                changeImage.accept(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addChangeImage(Consumer<BufferedImage> changeImage) {
        this.changeImage = changeImage;
    }
}
