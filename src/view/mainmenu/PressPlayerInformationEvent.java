package view.mainmenu;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PressPlayerInformationEvent implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        if (Player.getNickname() == null) {
            JOptionPane.showMessageDialog((Component) e.getSource(),"Nome utente non inserito!");
            return;
        }
        PlayerInformation playerInformation = new PlayerInformation();
    }
}
