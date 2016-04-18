package figure;

import java.awt.*;

abstract public class Figure implements Runnable {

    public static final Color DEFAULT_COLOR = Color.GREEN;

    private int x = 1;
    private int y;
    private int width;
    private int height;

    private FigureCanvas canvas;

    private Color color;

    private Thread t;
    private boolean isRunning;
    private boolean isPaused;

    private int sX;
    private int sY;

    public Figure(int x, int y, int width, int height, FigureCanvas canvas) {
        this(x, y, width, height, canvas, DEFAULT_COLOR);
    }

    public Figure(int x, int y, int width, int height, FigureCanvas canvas, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.color = color;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public abstract void draw(Graphics g);

    public abstract boolean isBelong(int x, int y);

    public void move(int dX, int dY) {
        x += dX;
        y += dY;
    }

    @Override
    public String toString() {
        return "Figure {} . ";
    }

    @Override
    public void run(){
        while (isRunning) {
            if (isPaused) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            insureDirection();

            move(sX, sY);
            canvas.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        if(isRunning) {
            stop();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = new Thread(this);
        isRunning = true;
        sX = 3;
        sY = 3;
        t.start();
    }

    public void stop() {
        resume();
        sX = 0;
        sY = 0;
        isRunning = false;
    }

    public void pause() {
        if (isRunning) {
            isPaused = true;
        }
    }

    public synchronized void resume() {
        if(isPaused){
            isPaused = false;
            notify();
        }

    }

    private void insureDirection() {
        if (x <= canvas.getBorderLeft()) {
            sX = sX < 0 ? -sX : sX;
        }
        if (x + getWidth() >= canvas.getBorderRight()) {
            sX = sX < 0 ? sX : -sX;
        }
        if (y <= canvas.getBorderTop()) {
            sY = sY > 0 ? sY : -sY;
        }
        if (y + this.getHeight() >= canvas.getBorderBottom()) {
            sY = sY > 0 ? -sY : sY;
        }
    }

}
