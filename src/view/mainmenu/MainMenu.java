package view.mainmenu;

import view.DisposeEvent;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(624,528));
        this.add(new MainMenuBackGround());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new ExitApplicationEvent());
    }
}
