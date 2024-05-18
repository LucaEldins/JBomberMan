package view.mainmenu;

import model.Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvatarChooseListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser imageBrowse = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Immagini", ".png", ".jpg", ".jpeg");
        imageBrowse.addChoosableFileFilter(filter);
        int show = imageBrowse.showOpenDialog(null);
        if (show == JFileChooser.APPROVE_OPTION){
            String path = imageBrowse.getSelectedFile().getPath();
            Player.setImagePath(path);
            ImageIcon imageIcon = new ImageIcon(path);
            Image image = imageIcon.getImage();
            imageIcon = new ImageIcon(image.getScaledInstance(150,150, Image.SCALE_SMOOTH));
            PlayerInformationPanel.getAvatarLabel().setIcon(imageIcon);
            PlayerInformationPanel.getAvatarLabel().setVisible(true);

        }
    }
}
