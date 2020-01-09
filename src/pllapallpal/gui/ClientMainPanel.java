package pllapallpal.gui;

import pllapallpal.gui.model.ClientModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClientMainPanel {

    private JPanel mainPanel;

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    private JButton sendButton;

    private ClientModel clientModel;
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
                ImageIO.write(image, "png", clientModel.getOutput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        receiveThread = new Thread(new Receiver(clientModel.getInput(), imageIcon));
        receiveThread.start();
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
