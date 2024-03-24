import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ship extends JPanel{
    public final int WIDTH = 60;
    public final int LENGTH = 30;
    private GameFrame gameFrame;

    Ship(String name, GameFrame gameFrame){
        this.gameFrame = gameFrame;
        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(WIDTH, LENGTH));

        JLabel label = new JLabel(name);
        label.setForeground(Color.WHITE);
        add(label);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
    }

    public void increaseScore(){
        gameFrame.increaseScore();
    }

    public void getHit(){
        gameFrame.getHit();
    }

    public void increaseLife() {
        gameFrame.increaseLife();
    }

}