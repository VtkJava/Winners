package braceChecker;

import util.BraceChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BraceCheckerFrame extends JFrame {

    BraceChecker braceChecker = BraceChecker.getInstance();
    public BraceCheckerFrame() {

        JPanel controlPanel = new JPanel();
        JPanel messagePanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        JButton parseButton = new JButton("Parse");

        JTextArea textArea = new JTextArea();
        JTextField resultMessageField = new JTextField();
        resultMessageField.setForeground(Color.GREEN);
        resultMessageField.setText("No Error");

        controlPanel.add(parseButton);

        scrollPane.getViewport().add(textArea);
        messagePanel.add(resultMessageField);

        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO move this code in to separate method  "parseActionPerformed(...)"
                boolean isOk = braceChecker.parse(textArea.getText());
                if (isOk) {
                    resultMessageField.setText("No Error");
                    resultMessageField.setForeground(Color.GREEN);
                } else {
                    resultMessageField.setText(braceChecker.getResultInfo().getMessage());
                    resultMessageField.setForeground(Color.RED);
                }
            }
        });

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        messagePanel.setLayout(new GridLayout(1, 1));
        add(messagePanel, BorderLayout.SOUTH);

        setTitle("Brace checker frame");
        setBounds(350, 350, 750, 500);
        setSize(750, 500);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new BraceCheckerFrame();
    }

}
