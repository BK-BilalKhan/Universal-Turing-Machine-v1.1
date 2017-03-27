
package universalturingmachine;

/*
    Author Bilal (K132314)
    Created on 22/March/2015
                                */

public class TuringMachine_Programs {
    
    final String D = "\u25CA";     //Diamond
    private final String[][] AnBn = {
                    {"0", "a", "x", "R", "1"},
                    {"0", "y", "y", "R", "3"},
                    {"1", "a", "a", "R", "1"},
                    {"1", "y", "y", "R", "1"},
                    {"1", "b", "y", "L", "2"},
                    {"2", "a", "a", "L", "2"},
                    {"2", "y", "y", "L", "2"},
                    {"2", "x", "x", "R", "0"},
                    {"3", "y", "y", "R", "3"},
                    {"3",  D ,  D , "L", "4"},
                };
    private final String[][] Palindrome =  {
                    {"0", "0", "X", "R", "1"},
                    {"0", "1", "Y", "R", "2"},
                    {"0", "X", "X", "R", "6"},
                    {"0", "Y", "Y", "R", "6"},
                    {"0",  D ,  D , "R", "6"},
                    {"1", "0", "0", "R", "1"},
                    {"1", "1", "1", "R", "1"},
                    {"1", "X", "X", "L", "3"},
                    {"1", "Y", "Y", "L", "3"},
                    {"1",  D ,  D , "L", "3"},
                    {"2", "0", "0", "R", "2"},
                    {"2", "1", "1", "R", "2"},
                    {"2", "X", "X", "L", "4"},
                    {"2", "Y", "Y", "L", "4"},
                    {"2",  D ,  D , "L", "4"},
                    {"3", "0", "X", "L", "5"},
                    {"3", "X", "X", "R", "6"},
                    {"3", "Y", "Y", "R", "6"},
                    {"4", "1", "Y", "L", "5"},
                    {"4", "X", "X", "R", "6"},
                    {"4", "Y", "Y", "R", "6"},
                    {"5", "0", "0", "L", "5"},
                    {"5", "1", "1", "L", "5"},
                    {"5", "X", "X", "R", "0"},
                    {"5", "Y", "Y", "R", "0"},
                    {"6", "Y", "Y", "R", "6"},
                    {"6", "X", "X", "R", "6"},
                    {"6",  D ,  D , "L", "7"},
                };
    private final String[][] unaryAddition = {
                    {"0", "1", "1", "R", "0"},
                    {"0", "0", "1", "R", "1"},
                    {"1", "1", "1", "R", "1"},
                    {"1",  D ,  D , "L", "2"},
                    {"2", "1", "0", "L", "3"},
                    {"3", "1", "1", "L", "3"},
                    {"3",  D ,  D , "R", "4"},
                };
    private String[][] UTM;
    
    public String[][] getAnBnMachine() {
        return AnBn.clone();
    }
    public String[][] getPalindromeMachine() {
        return Palindrome.clone();
    }
    public String[][] getUnaryAdditionMachine() {
        return unaryAddition.clone();
    }
    public String[][] getUTM() {
        return UTM.clone();
    }
    public void setUTM(String[][] UTM, int rows, int columns) {
        this.UTM = new String[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                this.UTM[i][j] = UTM[i][j];
    }
    public void setUTM(String[][] UTM) {
        this.UTM = UTM;
    }
    public int getSize(String[][] UTM) {
        return UTM.length;
    }
    public String getInitialState(String[][] TM, int index) {
        return TM[index][0];
    }
    public String getNextState(String[][] TM, int index) {
        return TM[index][4];
    }
    public char getReadAlphabet(String[][] TM, int index) {
        return TM[index][1].charAt(0);
    }
    public char getWriteAlphabet(String[][] TM, int index) {
        return TM[index][2].charAt(0);
    }
    public void setWriteAlphabet(String[][] TM, int index, char write) {
        TM[index][2] = String.valueOf(write);
    }
    public char getHeadMovement(String[][] TM, int index) {
        return TM[index][3].charAt(0);
    }
    
}