import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamingPanel extends JPanel{
    private Ship ship;
    private ArrayList<Apple> apples;
    private ArrayList<Bomb> bombs;
    private boolean running = true;

    public void stopThreads(){
        running = false;
    }

    public ArrayList<Apple> getApples() {
        return apples;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public Ship getShip() {
        return ship;
    }

    GamingPanel(String name, GameFrame gameFrame){
        setSize(800, 600);
        setBackground(Color.BLUE);
        addMouseMotionListener(new MotionListener());

        ship = new Ship(name, gameFrame);
        add(ship);

        apples = new ArrayList<>();
        Thread appleGenerateThread = new Thread(() -> {
            while(running){
                try{
                    Thread.sleep(500);
                    apples.add(new Apple());
                    add(apples.get(apples.size()-1));
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        bombs = new ArrayList<>();    
        Thread bombGenerateThread = new Thread(() -> {
            while(running){
                try{
                    Thread.sleep(500);
                    bombs.add(new Bomb());
                    add(bombs.get(bombs.size()-1));
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        appleGenerateThread.start();
        bombGenerateThread.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Apple apple : apples)
            apple.draw(g);
        for(Bomb bomb : bombs)
            bomb.draw(g);
    }

    class MotionListener implements MouseMotionListener{

        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            ship.setLocation(mouseX - ship.WIDTH/2, mouseY - ship.LENGTH/2);
        }

        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            ship.setLocation(mouseX - ship.WIDTH/2, mouseY - ship.LENGTH/2);
        }

    }

}

