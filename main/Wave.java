package main;

import java.awt.*;

public class Wave {
    int x;
    int y;
    final int width = 6, height = 6;

    int top ;
    int yScale;
    int yBase;


    public Wave(int top, int yScale){
        this.top = top;
        this.yScale = yScale;

        this.yBase = top + yScale;

        x = -6;
    }

    public void update(){
        x++;
        y = (int)(yBase - Math.sin(Math.toRadians(x)) * yScale);
    }

    public void checkCollision(Wave wave){
        if(wave.getX() >= 725) {
            wave.setX(-6);
        }
    }

    public void spawnChild(Wave wave, int children, Graphics g){
        Wave temp = new Wave(10, 160);
        temp.setY(wave.getY() - 6);
        temp.setX(wave.getX() - 6);
        temp.update();
        temp.checkCollision(temp);
        temp.draw(g);
        if(children >= 2) {
            spawnChild(temp,children - 1, g);
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
