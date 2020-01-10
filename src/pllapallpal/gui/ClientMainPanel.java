package pllapallpal.gui;

import pllapallpal.model.ClientModel;
import pllapallpal.netio.Receiver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ClientMainPanel {

    private JPanel mainPanel;

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    private JButton sendButton;

    private ClientModel clientModel;

    private Receiver receiver;
    private Thread receiveThread;

    public ClientMainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        imageIcon = new ImageIcon("res/test.png");
        imageLabel = new JLabel(imageIcon);
        mainPanel.add(imageLabel, BorderLayout.CENTER);


        clientModel = new ClientModel("127.0.0.1", 11111);

        sendButton = new JButton("Send Image");
        sendButton.addActionListener(event -> {
            try {
                System.out.println("button clicked");
                BufferedImage image = ImageIO.read(new File("res/test.png"));
                ImageIO.write(image, "png", clientModel.getByteArrayOutputStream());

                // write the size of image firstly, actual image secondly
                byte[] sizeArray = ByteBuffer.allocate(4).putInt(clientModel.getByteArrayOutputStream().size()).array();
                byte[] byteArray = clientModel.getByteArrayOutputStream().toByteArray();
                clientModel.getOutput().write(sizeArray);
                clientModel.getOutput().write(byteArray);
                clientModel.getOutput().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        receiver = new Receiver(clientModel.getInput());
        receiver.addChangeImage(this::changeImage);
        receiveThread = new Thread(receiver);
        receiveThread.start();
    }

    public void changeImage(BufferedImage image) {
        imageIcon.setImage(image);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
