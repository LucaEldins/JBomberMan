package view.mainmenu;
import javax.swing.*;
import java.awt.*;

public class PlayerInformation extends JFrame {

    public PlayerInformation(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(400,400));
        this.add(new PlayerInformationPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
