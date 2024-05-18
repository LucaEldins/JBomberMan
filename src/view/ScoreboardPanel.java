package view;

import enums.Sprite;
import model.BomberMan;
import observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScoreboardPanel extends JPanel implements Observer {

    private final HashMap<Integer,Sprite> numbers = new HashMap<>();

    public ScoreboardPanel(){
        initializeHashMap();
        this.setPreferredSize(new Dimension(624,78));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Sprite.SCOREBOARD.getImage(),0,0,624,78,null);
        drawScore(BomberMan.getInstance().getScore(), g2);
        drawHealth(BomberMan.getInstance().getHealth(),g2);
    }

    private void drawScore(int score, Graphics2D g){
        String sScore = String.valueOf(score);
        for (int i = 0 ; i < sScore.length() ; i++){
            int digit = Character.getNumericValue(sScore.charAt(i));
            g.drawImage(numbers.get(digit).getImage(),117+(16*i),24,16,29,null);
        }
    }

    private void drawHealth(int health, Graphics2D g){
        g.drawImage(numbers.get(health).getImage(),59,24,16,29,null);
    }

    private void initializeHashMap(){
        numbers.put(0,Sprite.ZERO);
        numbers.put(1,Sprite.ONE);
        numbers.put(2,Sprite.TWO);
        numbers.put(3,Sprite.THREE);
        numbers.put(4,Sprite.FOUR);
        numbers.put(5,Sprite.FIVE);
        numbers.put(6,Sprite.SIX);
        numbers.put(7,Sprite.SEVEN);
        numbers.put(8,Sprite.EIGHT);
        numbers.put(9,Sprite.NINE);
    }

    @Override
    public void update(Object o) {
        this.repaint();
    }
}
