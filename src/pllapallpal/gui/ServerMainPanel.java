package pllapallpal.gui;

import pllapallpal.gui.model.ClientModel;
import pllapallpal.gui.model.ServerModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ServerMainPanel {

    private JPanel mainPanel;

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    private JButton sendButton;

    private ServerModel serverModel;
    private Thread receiveThread;

    public ServerMainPanel() {

        mainPanel = new JPanel(new BorderLayout());

        imageIcon = new ImageIcon();
        imageLabel = new JLabel(imageIcon);
        mainPanel.add(imageLabel, BorderLayout.CENTER);


        serverModel = new ServerModel(11111);

        sendButton = new JButton("Send Image");
        sendButton.addActionListener(event -> {
            try {
                System.out.println("button clicked");
                BufferedImage image = ImageIO.read(new File("res/test.png"));
                ImageIO.write(image, "png", serverModel.getOutput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        receiveThread = new Thread(new Receiver(serverModel.getInput(), imageIcon));
        receiveThread.start();
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
