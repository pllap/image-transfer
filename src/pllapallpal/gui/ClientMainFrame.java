package pllapallpal.gui;

import javax.swing.*;

public class ClientMainFrame {

    private JFrame mainFrame;
    private ClientMainPanel mainPanel;

    public ClientMainFrame() {
        mainFrame = new JFrame("Client");

        mainPanel = new ClientMainPanel();
        mainFrame.getContentPane().add(mainPanel.getPanel());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }

    public JFrame getFrame() {
        return mainFrame;
    }
}
