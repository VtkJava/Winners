package notepad;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static notepad.Constants.*;

/**
 * Created by TatevTorosyan 22.03.2016.
 */

public class NotepadeMenu extends JMenuBar{

    private Properties properties;

    private JMenu mFile;
    private JMenuItem miNew;
    private JMenuItem miOpen;
    private JMenuItem miSave;
    private JMenuItem miSaveAs;
    private JMenuItem miPageSetup;
    private JMenuItem miPrint;
    private JMenuItem miExit;

    private JMenu mEdit;
    private JMenuItem miUndo;
    private JMenuItem miCut;
    private JMenuItem miCopy;
    private JMenuItem miPaste;
    private JMenuItem miDelete;
    private JMenuItem miFind;
    private JMenuItem miFindNext;
    private JMenuItem miReplace;
    private JMenuItem miGoTo;
    private JMenuItem miSelectAll;
    private JMenuItem miTimeDate;

    private JMenu mLang;
    private JMenuItem miEn;
    private JMenuItem miRu;
    private JMenuItem miHy;

    private JMenu mFormat;

    private JMenu mView;

    private JMenu mHelp;


    public NotepadeMenu() {

        mFile = new JMenu();
        miNew = new JMenuItem();
        mFile.add(miNew);
        miOpen = new JMenuItem();
        mFile.add(miOpen);
        miSave = new JMenuItem();
        mFile.add(miSave);
        miSaveAs = new JMenuItem();
        mFile.add(miSaveAs);
        mFile.addSeparator();
        miPageSetup = new JMenuItem();
        mFile.add(miPageSetup);
        miPrint = new JMenuItem();
        mFile.add(miPrint);
        mFile.addSeparator();
        miExit = new JMenuItem();
        mFile.add(miExit);
        add(mFile);

        mEdit = new JMenu();
        miUndo = new JMenuItem();
        mEdit.add(miUndo);
        miCut = new JMenuItem();
        mEdit.add(miCut);
        miCopy = new JMenuItem();
        mEdit.add(miCopy);
        miPaste = new JMenuItem();
        mEdit.add(miPaste);
        miDelete = new JMenuItem();
        mEdit.add(miDelete);
        mEdit.addSeparator();
        miFind = new JMenuItem();
        mEdit.add(miFind);
        miFindNext = new JMenuItem();
        mEdit.add(miFindNext);
        miReplace = new JMenuItem();
        mEdit.add(miReplace);
        miGoTo = new JMenuItem();
        mEdit.add(miGoTo);
        mEdit.addSeparator();
        miSelectAll = new JMenuItem();
        mEdit.add(miSelectAll);
        miTimeDate = new JMenuItem();
        mEdit.add(miTimeDate);
        add(mEdit);

        mFormat = new JMenu();
        add(mFormat);

        mView = new JMenu();
        add(mView);

        mHelp = new JMenu();
        add(mHelp);

        mLang = new JMenu();
        miEn = new JMenuItem();
        mLang.add(miEn);
        miRu = new JMenuItem();
        mLang.add(miRu);
        miHy = new JMenuItem();
        mLang.add(miHy);
        add(mLang);

    }

    public JMenuItem getMiNew() {
        return miNew;
    }

    public void setMiNew(JMenuItem miNew) {
        this.miNew = miNew;
    }

    public JMenuItem getMiOpen() {
        return miOpen;
    }

    public void setMiOpen(JMenuItem miOpen) {
        this.miOpen = miOpen;
    }

    public JMenuItem getMiSave() {
        return miSave;
    }

    public void setMiSave(JMenuItem miSave) {
        this.miSave = miSave;
    }

    public JMenuItem getMiSaveAs() {
        return miSaveAs;
    }

    public void setMiSaveAs(JMenuItem miSaveAs) {
        this.miSaveAs = miSaveAs;
    }

    public JMenuItem getMiPageSetup() {
        return miPageSetup;
    }

    public void setMiPageSetup(JMenuItem miPageSetup) {
        this.miPageSetup = miPageSetup;
    }

    public JMenuItem getMiPrint() {
        return miPrint;
    }

    public void setMiPrint(JMenuItem miPrint) {
        this.miPrint = miPrint;
    }

    public JMenuItem getMiExit() {
        return miExit;
    }

    public void setMiExit(JMenuItem miExit) {
        this.miExit = miExit;
    }

    public JMenu getmEdit() {
        return mEdit;
    }

    public void setmEdit(JMenu mEdit) {
        this.mEdit = mEdit;
    }

    public JMenuItem getMiUndo() {
        return miUndo;
    }

    public void setMiUndo(JMenuItem miUndo) {
        this.miUndo = miUndo;
    }

    public JMenuItem getMiCut() {
        return miCut;
    }

    public void setMiCut(JMenuItem miCut) {
        this.miCut = miCut;
    }

    public JMenuItem getMiCopy() {
        return miCopy;
    }

    public void setMiCopy(JMenuItem miCopy) {
        this.miCopy = miCopy;
    }

    public JMenuItem getMiPaste() {
        return miPaste;
    }

    public void setMiPaste(JMenuItem miPaste) {
        this.miPaste = miPaste;
    }

    public JMenuItem getMiDelete() {
        return miDelete;
    }

    public void setMiDelete(JMenuItem miDelete) {
        this.miDelete = miDelete;
    }

    public JMenuItem getMiFind() {
        return miFind;
    }

    public void setMiFind(JMenuItem miFind) {
        this.miFind = miFind;
    }

    public JMenuItem getMiFindNext() {
        return miFindNext;
    }

    public void setMiFindNext(JMenuItem miFindNext) {
        this.miFindNext = miFindNext;
    }

    public JMenuItem getMiReplace() {
        return miReplace;
    }

    public void setMiReplace(JMenuItem miReplace) {
        this.miReplace = miReplace;
    }

    public JMenuItem getMiGoTo() {
        return miGoTo;
    }

    public void setMiGoTo(JMenuItem miGoTo) {
        this.miGoTo = miGoTo;
    }

    public JMenuItem getMiSelectAll() {
        return miSelectAll;
    }

    public void setMiSelectAll(JMenuItem miSelectAll) {
        this.miSelectAll = miSelectAll;
    }

    public JMenuItem getMiTimeDate() {
        return miTimeDate;
    }

    public void setMiTimeDate(JMenuItem miTimeDate) {
        this.miTimeDate = miTimeDate;
    }

    public JMenuItem getMiHy() {
        return miHy;
    }

    public void setMiHy(JMenuItem miHy) {
        this.miHy = miHy;
    }

    public JMenuItem getMiRu() {
        return miRu;
    }

    public void setMiRu(JMenuItem miRu) {
        this.miRu = miRu;
    }

    public JMenuItem getMiEn() {
        return miEn;
    }

    public void setMiEn(JMenuItem miEn) {
        this.miEn = miEn;
    }

    public void init(String key) {

        properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("i18n/notepad" + key + ".properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            System.err.println("Property file does not exist. " + e.getMessage());
            e.printStackTrace();
        }

        mFile.setText(properties.getProperty(FILE));
        miNew.setText(properties.getProperty(_NEW));
        miOpen.setText(properties.getProperty(OPEN));
        miSave.setText(properties.getProperty(SAVE));
        miSaveAs.setText(properties.getProperty(SAVE_AS));
        miPageSetup.setText(properties.getProperty(PAGE_SETUP));
        miPrint.setText(properties.getProperty(PRINT));
        miExit.setText(properties.getProperty(EXIT));

        mEdit.setText(properties.getProperty(EDIT));
        miUndo.setText(properties.getProperty(UNDO));
        miCut.setText(properties.getProperty(CUT));
        miCopy.setText(properties.getProperty(COPY));
        miPaste.setText(properties.getProperty(PASTE));
        miDelete.setText(properties.getProperty(DELETE));
        miFind.setText(properties.getProperty(FIND));
        miFindNext.setText(properties.getProperty(FIND_NEXT));
        miReplace.setText(properties.getProperty(REPLACE));
        miGoTo.setText(properties.getProperty(GO_TO));
        miSelectAll.setText(properties.getProperty(SELECT_ALL));
        miTimeDate.setText(properties.getProperty(TIME_DATE));

        mFormat.setText(properties.getProperty(FORMAT));
        mView.setText(properties.getProperty(VIEW));
        mHelp.setText(properties.getProperty(HELP));

        mLang.setText(properties.getProperty(LANG));
        miEn.setText(properties.getProperty(EN));
        miRu.setText(properties.getProperty(RU));
        miHy.setText(properties.getProperty(HY));
    }
}
