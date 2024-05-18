package view.mainmenu;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerInformationPanel extends JPanel {

    private JLabel playerInfo;
    private JButton avatarChoose;
    private static final JLabel avatarLabel = new JLabel();



    public PlayerInformationPanel() {
        playerInfo = new JLabel();
        playerInfo.setText("<html>PARTITE GIOCATE: " + Player.getInstance().getGamesPlayed() + "<br/>PARTITE VINTE : " + Player.getInstance().getGamesWon() +
                "<br/>PARTITE PERSE : " + Player.getInstance().getGamesLost() + "<br/> PUNTEGGIO TOTALE : " + Player.getInstance().getTotalScore() + "</html>");
        this.setPreferredSize(new Dimension(500,400));
        this.setLayout(null);
        this.add(playerInfo);
        this.avatarChoose = new JButton("SCEGLI AVATAR");
        this.add(avatarChoose);
        this.add(avatarLabel);
        avatarChoose.setLocation(100,240);
        avatarChoose.setSize(200,50);
        avatarChoose.setVisible(true);
        avatarChoose.addActionListener(new AvatarChooseListener());
        avatarChoose.setVisible(true);
        avatarChoose.setLocation(100,300);
        avatarLabel.setLocation(100,0);
        avatarLabel.setSize(150,150);
        avatarLabel.setVisible(false);
        playerInfo.setSize(200,150);
        playerInfo.setLocation(100,150);
        playerInfo.setFont(new Font("Calibri", Font.PLAIN,18));
        loadPlayerImage();
    }

    public static JLabel getAvatarLabel(){
        return avatarLabel;
    }

    private void loadPlayerImage(){
        if (Player.getImagePath() == "null") return;
        ImageIcon imageIcon = new ImageIcon(Player.getImagePath());
        Image image = imageIcon.getImage();
        imageIcon = new ImageIcon(image.getScaledInstance(150,150, Image.SCALE_SMOOTH));
        PlayerInformationPanel.getAvatarLabel().setIcon(imageIcon);
        PlayerInformationPanel.getAvatarLabel().setVisible(true);
    }
}
