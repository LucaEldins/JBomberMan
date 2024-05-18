package view.mainmenu;

import enums.Sprite;

import javax.swing.*;
import java.awt.*;

public class MainMenuImagePanel extends JPanel {

    private static final int screenWidth = 624;
    private static final int screenHeight = 300;

    public MainMenuImagePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Sprite.MAINMENU.getImage(),0,0, screenWidth,screenHeight,null);
    }
}
