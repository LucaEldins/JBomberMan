package view.mainmenu;

import model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PressNickEvent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname = JOptionPane.showInputDialog("INSERISCI NICKNAME","Guest");
        Player.setNickname(nickname);
        Player.getInstance();
        JButton button = (JButton) e.getSource();
        button.setVisible(false);


    }
}
