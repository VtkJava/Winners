package hanoitower;

import figure.Figure;
import figure.FigureCanvas;
import java.awt.*;

public class Disk extends Figure {
    public Disk(int x, int y, int width, int height, FigureCanvas canvas) {
        super(x, y, width, height, canvas);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0xFF7D7F));
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(new Color(0xFF3E12));
        g.drawLine(getX(), getY(), getX() + getWidth() - 1, getY());
    }

    @Override
    public boolean isBelong(int x, int y) {
        return x >= this.getX() && x <= (this.getX() + this.getWidth())
                && y >= this.getY() && y <= (this.getY() + this.getHeight());
    }
}
