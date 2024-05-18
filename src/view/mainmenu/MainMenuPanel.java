package view.mainmenu;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private static final int screenWidth = 624;
    private static final int screenHeight = 200;
    private JButton playButton;
    private JButton playerInformation;
    private JButton setNickname;


    public MainMenuPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new GridBagLayout());
        this.playButton = new JButton();
        this.playerInformation = new JButton();
        this.setNickname = new JButton();
        this.playButton.setPreferredSize(new Dimension(100,100));
        this.playerInformation.setPreferredSize(new Dimension(200,100));
        this.setNickname.setPreferredSize(new Dimension(200,100));
        this.setNickname.setText("INSERISCI NICKNAME");
        this.playButton.setText("GIOCA");
        this.playButton.addActionListener(new GameButtonListener());
        this.playerInformation.setText("INFORMAZIONI GIOCATORE");
        this.playerInformation.addActionListener(new PressPlayerInformationEvent());
        this.setNickname.addActionListener(new PressNickEvent());
        this.add(playButton);
        this.add(playerInformation);
        this.add(setNickname);
        this.setVisible(true);
    }

}
