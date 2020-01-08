package pllapallpal.gui;

import javax.swing.*;

public class ServerMainFrame {

    private JFrame mainFrame;

    public ServerMainFrame() {
        mainFrame = new JFrame("Server");



        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }

    public JFrame getFrame() {
        return mainFrame;
    }
}
