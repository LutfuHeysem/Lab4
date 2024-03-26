import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamingPanel extends JPanel{
    private Ship ship;

    private ArrayList<InteractableDrawing> bombsAndApples;
    private boolean running = true;

    Thread appleGenerateThread;
    Thread bombGenerateThread;

    public void stopThreads(){
        running = false;
    }

    public ArrayList<InteractableDrawing> getBombsAndApples() {
        return bombsAndApples;
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

        bombsAndApples = new ArrayList<>();

        appleGenerateThread = new Thread(() -> {
            while(running){
                try{
                    Thread.sleep(500);
                    InteractableDrawing obj = new Apple();
                    bombsAndApples.add(obj);
                    add((Apple) obj);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        bombGenerateThread = new Thread(() -> {
            while(running){
                try{
                    Thread.sleep(500);
                    InteractableDrawing obj = new Bomb();
                    bombsAndApples.add(obj);
                    add((Bomb) obj);
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
        for(InteractableDrawing obj : bombsAndApples)
            obj.draw(g);
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

