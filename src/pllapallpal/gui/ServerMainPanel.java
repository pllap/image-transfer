package pllapallpal.gui;

import pllapallpal.model.ServerModel;
import pllapallpal.netio.Receiver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ServerMainPanel {

    private JPanel mainPanel;

    private JLabel imageLabel;

    private ServerModel serverModel;

    private Receiver receiver;
    private Thread receiveThread;

    public ServerMainPanel() {

        mainPanel = new JPanel(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.CENTER);


        serverModel = new ServerModel(11111);

        receiver = new Receiver(serverModel.getInput());
        receiver.addChangeImage(this::changeImage);
        receiveThread = new Thread(receiver);
        receiveThread.start();
    }

    public void changeImage(BufferedImage image) {
        imageLabel.setIcon(new ImageIcon(image));
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
