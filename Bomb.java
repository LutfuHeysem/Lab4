import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

public class Bomb extends JComponent implements InteractableDrawing{
    
    private int x = 750, y = 20;
    private final int WIDTH = 20, LENGTH = 20; 

    Random rd = new Random();

    public Bomb(){
        x = 750;
        y = rd.nextInt(0, 551);
    }
    
    @Override
    public boolean intersects(Ship s) {
        int shipX0 = s.getX();
        int shipY0 = s.getY();
        int shipX1 = shipX0+60;
        int shipY1 = shipY0+30;
        int bombX0 = x;
        int bombY0 = y;
        int bombX1 = x+WIDTH;
        int bombY1 = y+LENGTH;
        if((bombX0 >= shipX0 && bombX0 <= shipX1 && bombY0 >= shipY0 && bombY0 <= shipY1) || (bombX1 >= shipX0 && bombX1 <= shipX1 && bombY1 >= shipY0 && bombY1 <= shipY1))
            return true;
        else
            return false;
    }

    @Override
    public void interact(Ship s) {
        s.getHit();
    }

    @Override
    public boolean moveLeft(int speed) {
        x -= speed;
        return x<0?false:true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, WIDTH, LENGTH);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, WIDTH, LENGTH);
        g.setColor(Color.RED);
        g.fillOval(x+WIDTH/2-1, y+LENGTH/2-1, 3, 3);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
}