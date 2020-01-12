package pllapallpal.gui;

import pllapallpal.model.ClientModel;

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

    public ClientMainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        imageIcon = new ImageIcon("res/test.png");
        imageLabel = new JLabel(imageIcon);
        mainPanel.add(imageLabel, BorderLayout.CENTER);


        clientModel = new ClientModel("127.0.0.1", 11111);

        sendButton = new JButton("Send Image");
        sendButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:/Users/pllap/Pictures"));
            int value = fileChooser.showOpenDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
                sendFile(fileChooser);
            }
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);
    }

    public void sendFile(JFileChooser fileChooser) {
        try {
            BufferedImage image = ImageIO.read(fileChooser.getSelectedFile());
            imageLabel.setIcon(new ImageIcon(image));
            ImageIO.write(image, "png", clientModel.getByteArrayOutputStream());

            // write the size of image firstly, actual image secondly
            byte[] sizeArray = ByteBuffer.allocate(4).putInt(clientModel.getByteArrayOutputStream().size()).array();
            byte[] byteArray = clientModel.getByteArrayOutputStream().toByteArray();

            clientModel.getOutput().write(sizeArray);
            clientModel.getOutput().write(byteArray);
            clientModel.flushByteArrayOutputStream();
            clientModel.getOutput().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeImage(BufferedImage image) {
        imageIcon.setImage(image);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
