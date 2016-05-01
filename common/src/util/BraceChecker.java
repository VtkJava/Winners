package util;

public class BraceChecker {
    /**
     * TO store the opened brackets in text to be parsed
     */
    private Stack<BracketItem> stack;

    private ResultInfo resultInfo = new ResultInfo("No Error", -1);

    private static volatile BraceChecker instance;

    private BraceChecker() {
        stack = new StackImpl<>();
    }

    public static BraceChecker getInstance() {
        if (instance == null) {
            synchronized (BraceChecker.class) {
                if (instance == null) {
                    instance = new BraceChecker();
                }
            }
        }
        return instance;
    }

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    /**
     * Parses the text to check correctness of disposition of brackets in text ,
     * and in case of incorrectness initializes following errorIndex,
     * errorIndexInLine and errorLineNumber fields, to indicate where
     * incorrectness occur.
     *
     * @return true if parsing passed without error, otherwise false
     */
    public boolean parse(String text) {
        reset();
        boolean isPassed = true;
        int textLength = text.length();
        BracketItem stackTopElement = null;  /* last element poped from stack */
        BracketItem currBracketItem = null;
        char ch = 0;
        int numberInLine = 0;
        int lineNumber = 1;
        int i = 0;

        lab:for (; i < textLength; i++) {
            ch = text.charAt(i);
            numberInLine++;
            switch (ch) {
                case '\n':
                    numberInLine = 0;
                    lineNumber++;
                    break;
                case '(':
                case '[':
                case '{':
                    stack.push(new BracketItem(ch, numberInLine, lineNumber, i));
                    break;
                case ')':
                    stackTopElement = stack.isEmpty() ? null : stack.pop();
                    if(stackTopElement == null || stackTopElement.getValue() != '(' ){
                        isPassed = false;
                        currBracketItem = new BracketItem(ch, numberInLine, lineNumber, i);
                        break lab;
                    }
                    break ;
                case ']':
                    stackTopElement = stack.isEmpty() ? null : stack.pop();
                    if(stackTopElement == null || stackTopElement.getValue() != '[' ){
                        isPassed = false;
                        currBracketItem = new BracketItem(ch, numberInLine, lineNumber, i);
                        break lab;
                    }
                    break ;
                case '}':
                    stackTopElement = stack.isEmpty() ? null : stack.pop();
                    if(stackTopElement == null || stackTopElement.getValue() != '{' ){
                        isPassed = false;
                        currBracketItem = new BracketItem(ch, numberInLine, lineNumber, i);
                        break lab;
                    }
                    break ;
            }
        }

        if(i < textLength){
            if(stackTopElement == null){
                resultInfo.setMessage("Closed " + currBracketItem + " but not oppened!");
                resultInfo.setClosedItem(currBracketItem);
            } else {
                resultInfo.setMessage("Opened " + stackTopElement + " but closed " + currBracketItem);
                resultInfo.setOpenedItem(stackTopElement);
                resultInfo.setClosedItem(currBracketItem);
            }
        } else if (!stack.isEmpty()) {
            stackTopElement = stack.pop();
            resultInfo.setMessage("Opened " + stackTopElement + " but not closed.");
            resultInfo.setOpenedItem(stackTopElement);
            return false;
        }

        return isPassed;
    }

    public void reset(){
        stack.reset();
        resultInfo.reset();
    }

    public static class ResultInfo {
        /**
         * TO store the parsing result
         */
        private String message = "No errors";

        BracketItem openedItem;

        BracketItem closedItem;

        public ResultInfo(String message, int errorIndex) {
            this(message, null, null);
        }

        public ResultInfo(String message, BracketItem openedItem, BracketItem closedItem) {
            this.message = message;
            this.openedItem = openedItem;
            this.closedItem = closedItem;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public BracketItem getOpenedItem() {
            return openedItem;
        }

        public void setOpenedItem(BracketItem openedItem) {
            this.openedItem = openedItem;
        }

        public BracketItem getClosedItem() {
            return closedItem;
        }

        public void setClosedItem(BracketItem closedItem) {
            this.closedItem = closedItem;
        }

        void reset() {
            this.message = "No Error";
            this.openedItem = null;
            this.closedItem = null;
        }
    }

    public static class BracketItem {
        private char value;
        private int numberInLine;
        private int lineNumber;
        private int index;

        public BracketItem(char ch, int numberInLine, int lineNumber, int index) {
            this.value = ch;
            this.numberInLine = numberInLine;
            this.lineNumber = lineNumber;
            this.index = index;
        }

        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }

        public int getNumberInLine() {
            return numberInLine;
        }

        public void setNumberInLine(int numberInLine) {
            this.numberInLine = numberInLine;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "character '" + value + "' in line:'" + lineNumber + "', column:'" + numberInLine +"'";
        }
    }

}
