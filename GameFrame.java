import java.util.ArrayList;

import javax.swing.*;

public class GameFrame extends JFrame{
    private int lives = 3;
    private int score = 0;
    private int inSpeed;

    GamingPanel gamePanel;

    Thread interactableObjMovingThread;
    private boolean running = true;

    ArrayList<InteractableDrawing> bombsAndApples;
    
    private MainMenu mM;

    public int getSpeed() {
        return inSpeed;
    }

    GameFrame(Integer speed, String name, MainMenu main) {
        mM = main;
        inSpeed = speed;
        setTitle("Lives: " + lives + " - Score: " + score);
        setSize(800, 600);

        gamePanel = new GamingPanel(name, this);
        
        add(gamePanel);
        
        setVisible(true);

        interactableObjMovingThread = new Thread(() -> {
            bombsAndApples = gamePanel.getBombsAndApples();
            while(running){
                try{
                    Thread.sleep(20);
                    for(int i=0; i<bombsAndApples.size(); i++){
                        InteractableDrawing obj = bombsAndApples.get(i);
                        if(!obj.moveLeft(inSpeed)){
                            bombsAndApples.remove(obj);
                            i--;
                        }
                        else if(obj.intersects(gamePanel.getShip())){
                            obj.interact(gamePanel.getShip());
                            bombsAndApples.remove(obj);
                        }
                    }
                    gamePanel.repaint();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        interactableObjMovingThread.start();
    }

    public void getHit(){
        lives--;
        setTitle("Lives: " + lives + " - Score: " + score);
        if(lives == 0){
            inSpeed = 0;
            gamePanel.stopThreads();

            int choice = JOptionPane.showConfirmDialog(this, "Your Score: " + score + "\nPlay again?", "Game Over!", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION)
                restartGame();
            else
                System.exit(0);            
        }
    }

    private void restartGame(){
        int speed = mM.getSpeed();
        String name = mM.getName();
        dispose();
        new GameFrame(speed, name, mM);
    }

    public void increaseScore(){
        score++;
        setTitle("Lives: " + lives + " - Score: " + score);
    }

    public void increaseLife() {
        lives++;
        setTitle("Lives: " + lives + " - Score: " + score);
    }

}