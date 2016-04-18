package hanoitower;

import figure.*;
import figure.Rectangle;

import java.awt.*;
import java.util.*;

public class Peg extends Figure {

    private java.util.List<Disk> disksList = new ArrayList<>();
    private figure.Rectangle stand;
    private figure.Rectangle rod;

    public Peg(int x, int y, int width, int height, FigureCanvas canvas) {
        super(x, y, width, height, canvas, Color.DARK_GRAY);

        int rodX = getWidth() / 2 + x - Constants.rodWidth / 2;
        int standY = getHeight() + y - Constants.rodWidth;

        rod = new Rectangle(rodX, y, Constants.rodWidth, height - Constants.rodWidth, canvas);
        stand = new Rectangle(x, standY, width, Constants.rodWidth, canvas);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        int rodX = getWidth() / 2 + getX() - Constants.rodWidth / 2;
        int standY = getHeight() + getY() - Constants.rodWidth;
        // draw rod
        g.fillRect(rodX, getY(), Constants.rodWidth, getHeight() - Constants.rodWidth);
        // draw stand
        g.fillRect(getX(), standY, getWidth(), Constants.rodWidth);

        for(Disk disk : disksList) {
            disk.draw(g);
        }
    }

    public boolean isBelong(int x, int y) {
        // return as stand isBelong
        return stand.isBelong(x, y);
    }

    public void addDiscksList(Disk disk) {
        disksList.add(disk);
    }
}
