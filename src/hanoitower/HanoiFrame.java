package hanoitower;

import figure.FigureCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HanoiFrame extends JFrame {
    private HanoiCanvas canvas;
    private Random random = new Random();

    JTextField disksCountField;

    public HanoiFrame(){
        super("HanoiTower");

        canvas = new HanoiCanvas();

        JPanel configPanel = new JPanel();
        configPanel.setBackground(Color.YELLOW);

        disksCountField = new JTextField();
        configPanel.add(disksCountField);

        JButton loadDiskButton = new JButton("Load Disks");
        loadDiskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDiskPerformed(e);
            }
        });
        configPanel.add(loadDiskButton);
        JButton startButton = new JButton("Start");
        startButton.setEnabled(false);
        configPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        configPanel.add(stopButton);

        add(configPanel,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        disksCountField.setPreferredSize( new Dimension(50, 28) );
        setSize(550, 500);
        setLocation(250, 150);
        setVisible(true);
    }

    private void loadDiskPerformed(ActionEvent e) {
        int diskCount = Integer.parseInt(disksCountField.getText());
        if(diskCount > 0 && diskCount < 30) {
            canvas.loadDisk(diskCount);
        } else {
            JOptionPane.showMessageDialog(this,"Hanoi disks count must be less then 60");
        }
    }

    public static void main(String[] args) {
        new HanoiFrame();
    }
}
