package view.mainmenu;

import model.Player;
import view.DisposeEvent;

public class ExitApplicationEvent extends Thread{

    @Override
    public void run(){
        if (Player.getNickname() == null) return;
        DisposeEvent.saveUserData();
    }
}
