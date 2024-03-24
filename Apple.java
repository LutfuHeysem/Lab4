import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;

public class Apple extends JComponent implements InteractableDrawing{
    private int x = 750, y = 20;
    private final int WIDTH = 20, LENGTH = 20;
    private Color color;

    Random rd = new Random();

    public Apple(){
        x = 750;
        y = rd.nextInt(0, 551);
    }

    @Override
    public boolean intersects(Ship s) {
        int shipX0 = s.getX();
        int shipY0 = s.getY();
        int shipX1 = shipX0+60;
        int shipY1 = shipY0+30;
        if(x >= shipX0 && x <= shipX1 && y >= shipY0 && y <= shipY1)
            return true;
        else
            return false;
    }

    @Override
    public void interact(Ship s) {
        s.increaseScore();
        if(color == Color.RED)
            s.increaseLife();
    }

    @Override
    public boolean moveLeft(int speed) {
        x -= speed;
        return x<0?false:true;
    }

    @Override
    public void draw(Graphics g) {
        int chance = rd.nextInt(1,6);
        
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, LENGTH);
        if(chance == 2){
            g.setColor(Color.RED);
            color = Color.RED;
        }
        else{
            g.setColor(Color.GREEN);
            color = Color.GREEN;
        }
        g.fillOval(x, y, WIDTH, LENGTH);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
}