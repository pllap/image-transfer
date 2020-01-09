package pllapallpal.gui;

import javax.swing.*;
import java.awt.*;

public class ServerMainPanel {

    private JPanel mainPanel;

    public ServerMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
