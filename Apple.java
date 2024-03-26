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
        int chance = rd.nextInt(1,20);

        if(chance == 2)
            color = Color.RED;
        else
            color = Color.GREEN;

        x = 750;
        y = rd.nextInt(0, 551);
    }

    @Override
    public boolean intersects(Ship s) {
        int shipX0 = s.getX();
        int shipY0 = s.getY();
        int shipX1 = shipX0+60;
        int shipY1 = shipY0+30;
        int appleX0 = x;
        int appleY0 = y;
        int appleX1 = x+WIDTH;
        int appleY1 = y+LENGTH;
        if((appleX0 >= shipX0 && appleX0 <= shipX1 && appleY0 >= shipY0 && appleY0 <= shipY1) || (appleX1 >= shipX0 && appleX1 <= shipX1 && appleY1 >= shipY0 && appleY1 <= shipY1))
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
        
        // g.setColor(Color.WHITE);
        // g.fillRect(x, y, WIDTH, LENGTH);
        g.setColor(color);
        g.fillOval(x, y, WIDTH, LENGTH);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
}