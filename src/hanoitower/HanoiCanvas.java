package hanoitower;

import figure.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HanoiCanvas extends FigureCanvas {
    private int diskCount;
    private List<Disk> disks = new ArrayList<>();
    private Peg peg1;
    private Peg peg2;
    private Peg peg3;


    public HanoiCanvas() {
        super();

        peg1 = new Peg(DEFAULT_MARGIN, DEFAULT_MARGIN, Constants.pegWidth, Constants.pegHeight, this);
        peg2 = new Peg(DEFAULT_MARGIN, DEFAULT_MARGIN, Constants.pegWidth, Constants.pegHeight, this);
        peg3 = new Peg(DEFAULT_MARGIN, DEFAULT_MARGIN, Constants.pegWidth, Constants.pegHeight, this);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseDraggedPerformedHanoi(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMovedPerformedHanoi(e);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                hanoiCanvasResizedPerformed(e);
            }
        });
    }

    private void hanoiCanvasResizedPerformed(ComponentEvent e) {
        int space = (getBorderRight() - getBorderLeft() - Constants.pegWidth * 3) / 2;
        peg1.setX(getBorderLeft());
        peg1.setY(getBorderBottom() - Constants.pegHeight);
        peg2.setX(getBorderLeft() + Constants.pegWidth + space);
        peg2.setY(getBorderBottom() - Constants.pegHeight);
        peg3.setX(getBorderLeft() + (Constants.pegWidth + space) * 2);
        peg3.setY(getBorderBottom() - Constants.pegHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        peg1.draw(g);
        peg2.draw(g);
        peg3.draw(g);
    }

    public void loadDisk(int diskCount) {
        if(this.disks.size() > 0) {
            return;
        }
        this.diskCount = diskCount;

        int diskHeight = ((peg1.getHeight() - 5) / diskCount);
        diskHeight = diskHeight > 15 ? 15 : diskHeight;
        int xDiff = (peg1.getWidth() - Constants.rodWidth - 10) / (diskCount *2);
        xDiff = xDiff > 10 ? 10 : xDiff;
        int x = peg1.getX() +  xDiff;
        int y = peg1.getY() + peg1.getHeight() - Constants.rodWidth - diskHeight;
        int currentWidth = peg1.getWidth() - 2 * xDiff;
        Disk disk;

        for (int i = 0; i < diskCount; i++) {
            disk = new Disk(x, y, currentWidth, diskHeight, this);
            disks.add(disk);
            peg1.addDiscksList(disk);
            repaint();
            x += xDiff;
            y -= diskHeight;
            currentWidth -= xDiff * 2;

        }
    }

    private void mouseDraggedPerformedHanoi(MouseEvent e) {
        repaint();

    }

    private void mouseMovedPerformedHanoi(MouseEvent e) {
        repaint();

    }
}
