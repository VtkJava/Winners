package braceChecker;

import util.BraceChecker;

public class BraceCheckerTest {

    public static void main(String[] args) {
        String text  = "asasa{{{{";
        parseTest(text);
    }

    public static void parseTest(String text) {

        BraceChecker braceChecker = BraceChecker.getInstance();

        //boolean isOk = true;

        System.out.println("Start of testing.....................");
        // Here You have to write different test cases, for instance:
        //  opened '(' but not closed
        //  opened '{' but not closed
        //  opened '[' but not closed

        text = "text [] \n ka (";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Opened character '(' in line:'2', column:'5' but not closed.")) {
            System.out.println("opened '(' but not closed ---- OK");
        }
        else {
            System.err.println("opened '(' but not closed ---- Error");
        }

        text = "text [] \n ka {";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Opened character '{' in line:'2', column:'5' but not closed.")) {
            System.out.println("opened '{' but not closed ---- OK");
        }
        else {
            System.err.println("opened '{' but not closed ---- Error");
        }

        text = "text [] \n ka [";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Opened character '[' in line:'2', column:'5' but not closed.")) {
            System.out.println("opened '[' but not closed ---- OK");
        }
        else {
            System.err.println("opened '[' but not closed ---- Error");
        }

        //  opened '(' but closed other type's bracket
        //  opened '(' but closed other type's bracket
        //  opened '(' but closed other type's bracket
        text = "text [] \n ka ( \n ]";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Opened character '(' in line:'2', column:'5' but closed character ']' in line:'3', column:'2'")) {
            System.out.println("opened '(' but closed other type's bracket ---- OK");
        }
        else {
            System.err.println("opened '(' but closed other type's bracket ---- Error");
        }

        text = "text [] \n ka { \n ]";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Opened character '{' in line:'2', column:'5' but closed character ']' in line:'3', column:'2'")) {
            System.out.println("opened '{' but closed other type's bracket ---- OK");
        }
        else {
            System.err.println("opened '{' but closed other type's bracket ---- Error");
        }

        text = "text [] \n ka [ \n }";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Opened character '[' in line:'2', column:'5' but closed character '}' in line:'3', column:'2'")) {
            System.out.println("opened '[' but closed other type's bracket ---- OK");
        }
        else {
            System.err.println("opened '[' but closed other type's bracket ---- Error");
        }

        //  closed ')' but not opened
        //  closed '}' but not opened
        //  closed ']' but not opened

        text = "text [] \n ka  \n )";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Closed character ')' in line:'3', column:'2' but not oppened!")) {
            System.out.println("closed ')' but not opened ---- OK");
        }
        else {
            System.err.println("closed ')' but not opened ---- Error");
        }

        text = "text [] \n ka  \n }";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Closed character '}' in line:'3', column:'2' but not oppened!")) {
            System.out.println("closed ')' but not opened ---- OK");
        }
        else {
            System.err.println("closed ')' but not opened ---- Error");
        }

        text = "text [] \n ka  \n ]";
        braceChecker.parse(text);
        if(braceChecker.getResultInfo().getMessage().equals("Closed character ']' in line:'3', column:'2' but not oppened!")) {
            System.out.println("closed ']' but not opened ---- OK");
        }
        else {
            System.err.println("closed ']' but not opened ---- Error");
        }

        System.out.println("End of testing.....................");

        /*if (isOk) {
            System.out.println("'parseTest' is passed with 'No Error'");
        }else {
            System.err.println(braceChecker.getResultInfo().getMessage());
        }*/

    }
}
