package view.mainmenu;

import enums.Sprite;
import view.BackgroundPanel;

import java.awt.*;

public class MainMenuBackGround extends BackgroundPanel {

    private static final int screenWidth = 624;
    private static final int screenHeight = 528;

    public MainMenuBackGround() {
        super(Sprite.MAINMENUBG.getImage());
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new BorderLayout());
        this.add(new MainMenuImagePanel(),BorderLayout.NORTH);
        this.add(new MainMenuPanel(),BorderLayout.SOUTH);

    }
}
