package notepad;

import util.BraceChecker;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;

import static notepad.Constants.*;


public class NotepadeFrame extends JFrame implements ActionListener {

    final static String PROGRAM_NAME = "Notepad TT";
    final static String FILE_DEFAULT_NAME = "Untitled";
    final static String[] SAVE_DIALOG_OPTIONS = {"Save", "Don't Save", "Cancel"};
    private JFileChooser fileChooser = new JFileChooser();
    private JPanel messagePanel = new JPanel();
    JTextField resultMessageField;

    private File file;
    private static BraceChecker braceChecker = BraceChecker.getInstance();

    private DefaultHighlighter.DefaultHighlightPainter painter;
    private Highlighter highlighter;

    private NotepadeMenu menu;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public NotepadeFrame(String frameTitle) {
        super(frameTitle);

        menu = new NotepadeMenu();

        menu.getMiNew().addActionListener(this);
        menu.getMiOpen().addActionListener(this);
        menu.getMiSave().addActionListener(this);
        menu.getMiSaveAs().addActionListener(this);
        menu.getMiPrint().addActionListener(this);
        menu.getMiPageSetup().addActionListener(this);
        menu.getMiExit().addActionListener(this);
        menu.getMiUndo().addActionListener(this);
        menu.getMiCut().addActionListener(this);
        menu.getMiCopy().addActionListener(this);
        menu.getMiPaste().addActionListener(this);
        menu.getMiDelete().addActionListener(this);
        menu.getMiFind().addActionListener(this);
        menu.getMiFindNext().addActionListener(this);
        menu.getMiReplace().addActionListener(this);
        menu.getMiGoTo().addActionListener(this);
        menu.getMiSelectAll().addActionListener(this);
        menu.getMiTimeDate().addActionListener(this);

        menu.getMiEn().addActionListener(this);
        menu.getMiRu().addActionListener(this);
        menu.getMiHy().addActionListener(this);

        setJMenuBar(menu);
        menu.init(DEFAULT_LANG);

        textArea = new JTextArea();
        setLayout(new BorderLayout());
        add("Center", textArea);

        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(textArea);
        add(scrollPane, BorderLayout.CENTER);


        resultMessageField = new JTextField();
        resultMessageField.setForeground(Color.GREEN);
        resultMessageField.setText("No Error");

        messagePanel.add(resultMessageField);
        messagePanel.setLayout(new GridLayout(1, 1));
        add(messagePanel, BorderLayout.SOUTH);

        this.highlighter = textArea.getHighlighter();
        this.painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                parseText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                parseText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                parseText();
            }
        });

        setBounds(350, 350, 750, 500);
        setSize(750, 500);

        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                doFileClose();
            }
        });
    }

    public void parseText() {
        if (braceChecker.parse(textArea.getText())) {
            resultMessageField.setText("No Error");
            resultMessageField.setForeground(Color.GREEN);
            highlighter.removeAllHighlights();
        } else {
            highlighter.removeAllHighlights();
            resultMessageField.setText(braceChecker.getResultInfo().getMessage());
            resultMessageField.setForeground(Color.RED);
            try {
                if (braceChecker.getResultInfo().getOpenedItem() != null) {
                    highlighter.addHighlight(braceChecker.getResultInfo().getOpenedItem().getIndex(),
                            braceChecker.getResultInfo().getOpenedItem().getIndex() + 1,
                            painter);
                }
                if (braceChecker.getResultInfo().getClosedItem() != null) {
                    highlighter.addHighlight(braceChecker.getResultInfo().getClosedItem().getIndex(),
                            braceChecker.getResultInfo().getClosedItem().getIndex() + 1,
                            painter);
                }
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menu.getMiNew())) {
            doFileNew();
        } else if (e.getSource().equals(menu.getMiOpen())) {
            doFileOpen();
        } else if (e.getSource().equals(menu.getMiSave())) {
            doFileSave();
        } else if (e.getSource().equals(menu.getMiSaveAs())) {
            doFileSaveAs();
        } else if (e.getSource().equals(menu.getMiPrint())) {
            doFilePrint();
        } else if (e.getSource().equals(menu.getMiPageSetup())) {
            doFilePageSetup();
        } else if (e.getSource().equals(menu.getMiExit())) {
            doFileClose();
        } else if (e.getSource().equals(menu.getMiCut())) {

        } else if (e.getSource().equals(menu.getMiCopy())) {

        } else if (e.getSource().equals(menu.getMiPaste())) {

        } else if (e.getSource().equals(menu.getMiDelete())) {

        } else if (e.getSource().equals(menu.getMiFind())) {

        } else if (e.getSource().equals(menu.getMiFindNext())) {

        } else if (e.getSource().equals(menu.getMiReplace())) {

        } else if (e.getSource().equals(menu.getMiGoTo())) {

        } else if (e.getSource().equals(menu.getMiSelectAll())) {

        } else if (e.getSource().equals(menu.getMiTimeDate())) {

        } else if (e.getSource().equals(menu.getMiEn())) {
            menu.init(ENG);
        } else if (e.getSource().equals(menu.getMiRu())) {
            menu.init(RUS);
        } else if (e.getSource().equals(menu.getMiHy())) {
            menu.init(ARM);
        }
    }

    public void doFileNew() {
        if (isChanged()) {
            int result = showSaveDialog();
            if (result == 0) {
                doFileSave();
                openNewFile();
            } else if (result == 1) {
                openNewFile();
            }
        } else {
            openNewFile();
        }
    }

    public void doFileOpen() {
        if (isChanged()) {
            int result = showSaveDialog();
            if (result == 0) {
                doFileSave();
                openExistsFile();
            } else if (result == 1) {
                openExistsFile();
            }
        } else {
            openExistsFile();
        }
    }

    public void doFileSave() {
        if (isNewMode()) {
            doFileSaveAs();
        } else {
            write(file);
        }
    }

    public void doFileSaveAs() {
        fileChooser.setSelectedFile(new File("*.txt"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            while (doOvewrite()) ;
            file = fileChooser.getSelectedFile();
            write(file);
            setTitle(PROGRAM_NAME + " - " + file.getAbsolutePath());
        } else if (userSelection == JFileChooser.ERROR_OPTION) {
            System.out.println("error");
            doFileSaveAs();
        }
    }

    public void doFilePrint() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (PrinterException exc) {
                System.out.println(2 + exc.toString());
            }
        }
    }

    public void doFilePageSetup() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
    }

    public void doFileClose() {
        if (isChanged()) {
            int result = showSaveDialog();
            if (result == 0) {
                doFileSave();
                this.dispose();
            } else if (result == 1) {
                this.dispose();
                System.exit(0);
            }
        } else {
            this.dispose();
            System.exit(0);
        }
    }

    public void openNewFile() {
        file = null;
        setTitle(FILE_DEFAULT_NAME + " - " + PROGRAM_NAME);
        textArea.setText("");
    }

    public void openExistsFile() {
        fileChooser.setSelectedFile(new File("*.txt"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            setTitle(PROGRAM_NAME + " - " + file.getAbsolutePath());
            textArea.setText(read(file));
        }
    }

    private void write(File file) {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(textArea.getText().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isNewMode() {
        return file == null;
    }

    private boolean isChanged() {
        if (file == null && textArea.getText().length() > 0) {
            return true;
        } else if (file != null &&
                !textArea.getText().equals(read(file))) {
            return true;
        }
        return false;
    }

    private boolean doOvewrite() {
        File selectedFile;
        int response;
        selectedFile = fileChooser.getSelectedFile();
        if ((selectedFile != null) && selectedFile.exists() && !selectedFile.equals(file)) {
            response = JOptionPane.showConfirmDialog(this,
                    "The file " + selectedFile.getName() +
                            " already exists. Do you want to replace the existing file?",
                    "Ovewrite file", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (response != JOptionPane.YES_OPTION) {
                doFileSaveAs();
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private String read(File file) {
        if (file == null) {
            throw new NullPointerException("The file can't be null");
        }
        int size = (int) file.length();
        byte[] result = new byte[size];
        try (FileInputStream stream = new FileInputStream(file)) {
            stream.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public int showSaveDialog() {
        return JOptionPane.showOptionDialog(this.getContentPane(), "Do you want to save changes to" +
                file.getAbsolutePath() + "?", PROGRAM_NAME, 0, JOptionPane.INFORMATION_MESSAGE, null, SAVE_DIALOG_OPTIONS, null);
    }

}
