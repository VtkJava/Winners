package figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Company: VTC
 * Date: 24/02/16
 * <p>
 * Modified by Tatev Torosyan.
 */
public class FigureCanvas extends JPanel {
    public static final int DEFAULT_MARGIN = 50;
    public static final Color DEFAULT_BORDER_COLOR = new Color(250, 244, 161);

    private List<Figure> figures = new ArrayList<>();
    private boolean isSelected;

    private int mX;
    private int mY;

    private int currentWidth;
    private int currentHeight;

    BorderResizeMode borderResizeMode = BorderResizeMode.NO_RESIZE;

    private Rectangle border;

    public int getBorderLeft() {
        return border.getX();
    }

    public int getBorderRight() {
        return border.getX() + border.getWidth();
    }

    public int getBorderTop() {
        return border.getY();
    }

    public int getBorderBottom() {
        return border.getY() + border.getHeight();
    }

    public FigureCanvas() {
        this(DEFAULT_MARGIN, DEFAULT_MARGIN, 0, 0, DEFAULT_BORDER_COLOR);
    }

    public FigureCanvas(int borderX, int borderY, int borderWidth, int borderHeight, Color borderColor) {
        border = new Rectangle(borderX, borderY, borderWidth, borderHeight, this, borderColor);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedPerformed(e);
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
                mouseDraggedPerformed(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMovedPerformed(e);
            }
        });

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                canvasResizedPerformed(e);
            }
        });
    }

    private void canvasResizedPerformed(ComponentEvent e) {

        if (border.getWidth() == 0) {
            border.setWidth(getWidth() - border.getX() * 2);
        } else {
            border.setWidth(border.getWidth() + getWidth() - currentWidth);
        }

        if (border.getHeight() == 0) {
            border.setHeight(getHeight() - border.getY() * 2);
        } else {
            border.setHeight(border.getHeight() + getHeight() - currentHeight);

        }

        currentWidth = getWidth();
        currentHeight = getHeight();
    }

    public void add(Figure figure) {
        if (figure != null) {
            figures.add(figure);
            repaint();
        }
    }

    public void remove() {
        if (isSelected) {
            Figure figure = getSelected();
            figures.remove(figure);
            figure.stop();
            repaint();
        }
    }

    public Figure getSelected() {
        int size = figures.size();
        return isSelected && size > 0 ? figures.get(size - 1) : null;
    }

    public void start() {
        if (isSelected) {
            getSelected().start();
        }
    }

    public void stop() {
        if (isSelected) {
            getSelected().stop();
        }
    }

    public void pause() {
        if (isSelected) {
            getSelected().pause();
        }
    }

    public void resume() {
        if (isSelected) {
            getSelected().resume();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 3000, 3000);
        border.draw(g);
        for (Figure figure : figures) {
            figure.draw(g);

            //            if(figure instanceof Rectangle ){
            //                ((Rectangle)figure).draw(g);
            //            } else if (figure instanceof Circle ){
            //                ((Circle)figure).draw(g);
            //            } else {
            //                throw new InvalidParameterException();
            //            }
            //

        }
    }

    private void select(int x, int y) {
        isSelected = false;
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure figure = figures.get(i);
            if (figure.isBelong(x, y)) {
                figures.add(figures.remove(i));
                isSelected = true;
                return;
            }
        }
    }

    private void mousePressedPerformed(MouseEvent e) {
        select(e.getX(), e.getY());
        if (isSelected) {
            repaint();
        }
        mX = e.getX();
        mY = e.getY();
    }

    private void mouseDraggedPerformed(MouseEvent e) {
        if (borderResizeMode != BorderResizeMode.NO_RESIZE) {
            switch (borderResizeMode) {
                case RESIZE_LEFT_TOP:
                    border.setWidth(getBorderRight() - e.getX());
                    border.setHeight(getBorderBottom() - e.getY());
                    border.setX(e.getX());
                    border.setY(e.getY());
                    break;
                case RESIZE_TOP:
                    border.setHeight(getBorderBottom() - e.getY());
                    border.setY(e.getY());
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    break;
                case RESIZE_RIGHT_TOP:
                    border.setWidth(e.getX() - border.getX());
                    border.setHeight(getBorderBottom() - e.getY());
                    border.setY(e.getY());
                    break;
                case RESIZE_RIGHT:
                    border.setWidth(e.getX() - border.getX());
                    break;
                case RESIZE_RIGHT_BOTTOM:
                    border.setWidth(e.getX() - border.getX());
                    border.setHeight(e.getY() - border.getY());
                    break;
                case RESIZE_BOTTOM:
                    border.setHeight(e.getY() - border.getY());
                    break;
                case RESIZE_LEFT_BOTTOM:
                    border.setHeight(e.getY() - border.getY());
                    border.setWidth(getBorderRight() - e.getX());
                    border.setX(e.getX());
                    break;
                case RESIZE_LEFT:
                    border.setWidth(getBorderRight() - e.getX());
                    border.setX(e.getX());
                    break;
            }
            repaint();
        }

        if (isSelected) {
            getSelected().move(e.getX() - mX, e.getY() - mY);
            repaint();
        }
        mX = e.getX();
        mY = e.getY();
    }

    private void mouseMovedPerformed(MouseEvent e) {
        checkBorders(e.getX(), e.getY());

        mX = e.getX();
        mY = e.getY();
    }

    private void checkBorders(MouseEvent e) {
        checkBorders(e.getX(), e.getY());
    }

    private void checkBorders(int x, int y) {
        if (onBorderLeftTop(x, y)) {
            borderResizeMode = BorderResizeMode.RESIZE_LEFT_TOP;
            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else if (onBorderRightTop(x, y)) {
            borderResizeMode = BorderResizeMode.RESIZE_RIGHT_TOP;
            setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        } else if (onBorderRightBottom(x, y)) {
            borderResizeMode = BorderResizeMode.RESIZE_RIGHT_BOTTOM;
            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        } else if (onBorderLeftBottom(x, y)) {
            borderResizeMode = BorderResizeMode.RESIZE_LEFT_BOTTOM;
            setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        } else if (onBorderRight(x)) {
            borderResizeMode = BorderResizeMode.RESIZE_RIGHT;
            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else if (onBorderTop(y)) {
            borderResizeMode = BorderResizeMode.RESIZE_TOP;
            setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        } else if (onBorderBottom(y)) {
            borderResizeMode = BorderResizeMode.RESIZE_BOTTOM;
            setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else if (onBorderLeft(x)) {
            borderResizeMode = BorderResizeMode.RESIZE_LEFT;
            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else {
            borderResizeMode = BorderResizeMode.NO_RESIZE;
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private boolean onBorderTop(int y) {
        return y >= getBorderTop() - 5 && y < getBorderTop() + 5;
    }

    private boolean onBorderRight(int x) {
        return x >= getBorderRight() - 5 && x < getBorderRight() + 5;
    }

    private boolean onBorderBottom(int y) {
        return y >= getBorderBottom() - 5 && y < getBorderBottom() + 5;
    }

    private boolean onBorderLeft(int x) {
        return x >= getBorderLeft() - 5 && x < getBorderLeft() + 5;
    }

    private boolean onBorderLeftTop(int x, int y) {
        return onBorderLeft(x) && onBorderTop(y);
    }

    private boolean onBorderRightTop(int x, int y) {
        return onBorderRight(x) && onBorderTop(y);
    }

    private boolean onBorderRightBottom(int x, int y) {
        return onBorderRight(x) && onBorderBottom(y);
    }

    private boolean onBorderLeftBottom(int x, int y) {
        return onBorderLeft(x) && onBorderBottom(y);
    }

    public enum BorderResizeMode {
        NO_RESIZE(0, "noResize"),
        RESIZE_TOP(1, "resizeTop"),
        RESIZE_LEFT(2, "resizeLeft"),
        RESIZE_RIGHT(3, "resizeRight"),
        RESIZE_BOTTOM(4, "resizeBottom"),
        RESIZE_LEFT_TOP(5, "resizeLeftTop"),
        RESIZE_RIGHT_TOP(6, "resizeRightTop"),
        RESIZE_RIGHT_BOTTOM(7, "resizeRightBottom"),
        RESIZE_LEFT_BOTTOM(8, "resizeLeftBottom");

        private BorderResizeMode(int value, String name) {
            this.value = value;
            this.name = name;
        }

        private final String name;
        private final int value;
    }

}

