package figure;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Company: VTC
 * Date: 24/02/16
 * <p>
 * Modified by Tatev Torosyan.
 */
public class FigureFrame extends JFrame {

    private FigureCanvas canvas;
    private Random random = new Random();

    public FigureFrame() {
        super("figure");

        canvas = new FigureCanvas();

        JPanel configPanel = new JPanel();
        configPanel.setBackground(Color.YELLOW);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActionPerformed(e);
            }
        });

        JButton remove = new JButton("Remove");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeActionPerformed(e);
            }
        });

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startActionPerformed(e);
            }
        });

        JButton stopBtn = new JButton("Stop");
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopActionPerformed(e);
            }
        });

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseActionPerformed(e);
            }
        });

        JButton resumeButton = new JButton("Continue");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeActionPerformed(e);
            }
        });

        configPanel.add(addButton);
        configPanel.add(remove);
        configPanel.add(startBtn);
        configPanel.add(stopBtn);
        configPanel.add(pauseButton);
        configPanel.add(resumeButton);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 500);
        setLocation(250, 150);
        setVisible(true);

    }

    private void addActionPerformed(ActionEvent e) {
        int rand = random.nextInt(2);
        canvas.add(rand == 0 ? new Circle(canvas.getBorderLeft() + 10, canvas.getBorderTop() + 10, 70, canvas) :
                new Rectangle(canvas.getBorderLeft() + 50, canvas.getBorderTop() + 50, 30, 25, canvas));
    }

    private void startActionPerformed(ActionEvent e) {
        canvas.start();
    }

    private void removeActionPerformed(ActionEvent e) {
        canvas.remove();
    }


    private void stopActionPerformed(ActionEvent e) {
        canvas.stop();
    }

    private void pauseActionPerformed(ActionEvent e) {
        canvas.pause();
    }

    private void resumeActionPerformed(ActionEvent e) {
        canvas.resume();
    }


    public static void main(String[] args) {
        new FigureFrame();
    }

    private static class AddButtonActionListener implements ActionListener {

        private FigureFrame figureFrame;

        public AddButtonActionListener(FigureFrame figureFrame) {
            this.figureFrame = figureFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            figureFrame.addActionPerformed(e);
        }
    }

}

