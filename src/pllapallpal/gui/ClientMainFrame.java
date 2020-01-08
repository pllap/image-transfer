package pllapallpal.gui;

import javax.swing.*;

public class ClientMainFrame {

    private JFrame mainFrame;

    public ClientMainFrame() {
        mainFrame = new JFrame("Client");



        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }

    public JFrame getFrame() {
        return mainFrame;
    }
}
