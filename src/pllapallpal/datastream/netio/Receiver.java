package pllapallpal.datastream.netio;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.zip.GZIPInputStream;

public class Receiver implements Runnable {

    private DataInputStream input;

    private Runnable acceptSocket;
    private Consumer<BufferedImage> changeImage;

    public Receiver(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("receiver");

                GZIPInputStream gzipInputStream = new GZIPInputStream(input);

                // receive the size of image from the inputStream
                byte[] sizeArray = new byte[4];
                gzipInputStream.read(sizeArray);
                System.out.println("read");
//                input.read(sizeArray);
                int size = ByteBuffer.wrap(sizeArray).asIntBuffer().get();
                System.out.println("size " + size);

                // receive the actual image
                byte[] imageArray = new byte[size];
                gzipInputStream.read(imageArray);
//                input.read(imageArray);
                System.out.println(Arrays.toString(imageArray));

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
