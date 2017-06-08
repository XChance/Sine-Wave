package main;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel implements Runnable{
    static Main main;
    JFrame jf;
    Thread thread;
    Wave[] wav = new Wave[200];

    final int WIDTH = 725, HEIGHT = 370;

    public Main() {
        instantiate();
        setPos();

        jf = new JFrame("Sine Wave");
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.add(this);
        jf.setVisible(true);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true){
            update();
            checkCollision();
            repaint();

            try {
                thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);
        draw(g);
    }

    public void instantiate(){
        for(int i = 0; i < wav.length; i++){
            wav[i] = new Wave(10, 160);
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < wav.length; i++){
            wav[i].draw(g);
        }
    }

    public void checkCollision(){
        for(int i = 0; i < wav.length; i++){
            wav[i].checkCollision(wav[i]);
        }
    }

    public void update(){
        for(int i = 0; i < wav.length; i++){
            wav[i].update();
        }
    }

    public void setPos(){
        for(int i = 0; i < wav.length; i++){
            if(i != 0) {
                wav[i].setX(wav[i-1].getX() - 6);
                wav[i].setY((wav[i-1].getY() - 6));
            }
        }
    }

    public static void main(String[] args){
        main = new Main();
    }

    public int getWidth(){
        return WIDTH;
    }

}

