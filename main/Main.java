package main;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel implements Runnable{
    static Main main;
    JFrame jf;
    Thread thread;
    Wave wav;

    final int WIDTH = 725, HEIGHT = 370;

    public Main() {
        wav = new Wave(10, 160);

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
            wav.update();
            wav.checkCollision();
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
        wav.draw(g);
        wav.spawnChild(wav,60, g);
    }

    public static void main(String[] args){
        main = new Main();
    }

    public int getWidth(){
        return WIDTH;
    }

}
