import java.util.ArrayList;

import javax.swing.*;

public class GameFrame extends JFrame{
    private int lives = 3;
    private int score = 0;
    private int inSpeed;
    GamingPanel gamePanel;
    Thread appleMovingThread;
    Thread bombMovingThread;
    private boolean running = true;
    ArrayList<Apple> apples;
    ArrayList<Bomb> bombs;

    public int getSpeed() {
        return inSpeed;
    }

    GameFrame(Integer speed, String name) {
        inSpeed = speed;
        setTitle("Lives: " + lives + " - Score: " + score);
        setSize(800, 600);

        gamePanel = new GamingPanel(name, this);
        
        add(gamePanel);
        
        setVisible(true);

        appleMovingThread = new Thread(() -> {
            apples = gamePanel.getApples();
            while(running){
                try{
                    Thread.sleep(20);
                    for(int i=0; i<apples.size(); i++){
                        Apple apple = apples.get(i);
                        if(!apple.moveLeft(inSpeed)){
                            apples.remove(apple);
                            i--;
                        }
                        else if(apple.intersects(gamePanel.getShip())){
                            apple.interact(gamePanel.getShip());
                            apples.remove(apple);
                        }
                    }
                    gamePanel.repaint();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        appleMovingThread.start();

        bombMovingThread = new Thread(() -> {
            bombs = gamePanel.getBombs();
            while(running){
                try{
                    Thread.sleep(20);
                    for(int i=0; i<bombs.size(); i++){
                        Bomb bomb = bombs.get(i);
                        if(!bomb.moveLeft(inSpeed)){
                            bombs.remove(bomb);
                            i--;
                        }
                        else if(bomb.intersects(gamePanel.getShip())){
                            bomb.interact(gamePanel.getShip());
                            bombs.remove(bomb);
                        }
                    }
                    gamePanel.repaint();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        bombMovingThread.start();
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
        setVisible(false);
        new MainMenu();

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