
package universalturingmachine;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/*
    Author Bilal (K132314)
    Created on 22/March/2015
                                */

public class UTM_Implement {
    
    private String appendTape = "";
    private int noOfSteps = 0;
    private int curState = 0;
    private int tapeSPEED = 450;
    
    public void UTM_Simulate(String[][] UTM, char[] inputOfUTM, int initialState, JTextArea tape,
            JTextField currentState, JTextField steps, JTextField userFinalState, boolean tapeSpeed) {
        
        noOfSteps = 0;
        tape.setText("");
        appendTape = "";
        TuringMachine_Programs TM_P = new TuringMachine_Programs();
        inputOfUTM = this.increaseInputSizeRight(inputOfUTM);
        inputOfUTM = this.increaseInputSizeLeft(inputOfUTM);
        display(inputOfUTM, 1, tape, steps, currentState, tapeSpeed);
        
        boolean isFinal = false;
        int finalState = initialState;
        for(int i = 0; i < TM_P.getSize(UTM); i++) {
            if ("f".equals(TM_P.getNextState(UTM, i)) || "final".equals(TM_P.getNextState(UTM, i)) 
                    || "f".equals(userFinalState.getText()) || "final".equals(userFinalState.getText())) {
                isFinal = true;
            }
            else if (finalState < Integer.parseInt(TM_P.getNextState(UTM, i))) {
                //finalState = Integer.parseInt(TM_P.getNextState(UTM, i));
                finalState = Integer.parseInt(userFinalState.getText());
            }
        }
        if (TM_P.getSize(UTM) == 0) {
            finalState = Integer.parseInt(userFinalState.getText());
        }
        int z = 1, state = initialState;
        while (true) {
            for(int i = 0; i < TM_P.getSize(UTM); i++) {
               if (state == Integer.parseInt(TM_P.getInitialState(UTM, i))) {
                   curState = state;
                   //System.out.println(state);
                   if (inputOfUTM[z] == TM_P.getReadAlphabet(UTM, i)) {
                       //System.out.println(inputOfUTM[z]);
                        if (!tapeSpeed) {
                            currentState.update(currentState.getGraphics());
                            currentState.setText(String.valueOf(curState));
                        }
                       inputOfUTM[z] = TM_P.getWriteAlphabet(UTM, i);
                       if (TM_P.getHeadMovement(UTM, i) == 'R' || TM_P.getHeadMovement(UTM, i) == 'r') {
                           if (inputOfUTM[0] != '\u25CA') {
                                inputOfUTM = this.increaseInputSizeLeft(inputOfUTM);
                           }
                           z++;
                       } else if (TM_P.getHeadMovement(UTM, i) == 'L' || TM_P.getHeadMovement(UTM, i) == 'l') {
                           if (inputOfUTM[inputOfUTM.length - 1] != '\u25CA') {
                                inputOfUTM = this.increaseInputSizeRight(inputOfUTM);
                           }
                           z--;
                       } else if (TM_P.getHeadMovement(UTM, i) == 'H' || TM_P.getHeadMovement(UTM, i) == 'h') {
                           currentState.setText("Halt\n ");
                           return;
                       } else {
                           //z = z;
                       }
                       if ("f".equals(TM_P.getNextState(UTM, i)) || "final".equals(TM_P.getNextState(UTM, i)))
                           state = -1;
                       else
                        state = Integer.parseInt(TM_P.getNextState(UTM, i));
                       //System.out.println(state);
                       this.display(inputOfUTM, z, tape, steps, currentState, tapeSpeed);
                       noOfSteps++;
                   }
               }
            }
            if (isFinal && (state == -1)) {
//                while (inputOfUTM[++z] != '\u25CA') {
//                    this.display(inputOfUTM, z, tape);
//                    steps.setText(String.valueOf(noOfSteps));
//                    noOfSteps++;
//                }
                currentState.setText("Halt and Accept\n ");
                break;
            }
            else if(!isFinal && state == finalState) {
//                while (true) {
//                    if (inputOfUTM[++z] != '\u25CA') {
//                        this.display(inputOfUTM, z, tape);
//                        steps.setText(String.valueOf(noOfSteps));
//                        noOfSteps++;
//                    }
//                    else {
//                        break;
//                    }
//                }
                currentState.setText("Halt and Accept\n ");
                break;
            }
            boolean flag = false;
            for (int i = 0; i < TM_P.getSize(UTM); i++) {
                if (state == Integer.parseInt(TM_P.getInitialState(UTM, i))) {
                    if (inputOfUTM[z] == TM_P.getReadAlphabet(UTM, i))
                        flag = true;
                }
            }
            if(!flag) {
                currentState.setText("Halt and Reject\n ");
                break;
            }
        }
        tape.setText(appendTape);
        steps.setText(String.valueOf(noOfSteps));
    }
    public char[] increaseInputSizeRight(char[] inputOfUTM) {
        char[] tmp = new char[inputOfUTM.length + 1];
        for(int i = 0; i < tmp.length; i++) {
            if (i < inputOfUTM.length)
                tmp[i] = inputOfUTM[i];
            else
                tmp[i] = '\u25CA';
        }
        return tmp;
    }
    public char[] increaseInputSizeLeft(char[] inputOfUTM) {
        char[] tmp = new char[inputOfUTM.length + 1];
        for(int i = 0; i <= tmp.length; i++) {
            if (i == 0)
                tmp[i] = '\u25CA';
            else if (i <= inputOfUTM.length)
                tmp[i] = inputOfUTM[i - 1];
        }
        return tmp;
    }
    public String[] Encoding(String[][] UTM) {
        String[] eProgram = new String[UTM.length];
        for (int i = 0; i < UTM.length; i++) {
            eProgram[i] = "";
            int[] loopCount = {0, 1, 4, 2, 3, 7};
            int j = 0;
            int x = 0;
            while (j != 7) {
                j = loopCount[x];
                if (j == 7)
                    break;
                if (j == 0) {
                    for (int k = 0; k <= Integer.parseInt(UTM[i][j]); k++)
                        eProgram[i] += 1;
                    eProgram[i] += 0;
                }
                if (j == 1 || j == 2) {
                    int negLength = 0;
                    if (UTM[i][j].charAt(0) >= 'A' && UTM[i][j].charAt(0) <= 'Z') {
                        negLength = (int) ('A');
                    } else if (UTM[i][j].charAt(0) >= 'a' && UTM[i][j].charAt(0) <= 'z') {
                        negLength = (int) ('a');
                    } else if (UTM[i][j].charAt(0) >= '0' && UTM[i][j].charAt(0) <= '9') {
                        negLength = (int) ('0');
                        negLength -= 27;
                    }
                    for (int k = 0; k <= (int) UTM[i][j].charAt(0) - negLength; k++) {
                        if (UTM[i][j].charAt(0) != '\u25CA')
                            eProgram[i] += 1;
                        else {
                            for (int z = 0; z < 27; z++)
                                eProgram[i] += 1;
                            break;
                        }
                    }
                    eProgram[i] += 0;
                }
                if (j == 3) {
                    if (UTM[i][j].charAt(0) == 'R' || UTM[i][j].charAt(0) == 'r')
                        eProgram[i] += 1;
                    else if (UTM[i][j].charAt(0) == 'L' || UTM[i][j].charAt(0) == 'l')
                        eProgram[i] += 11;
                    else if (UTM[i][j].charAt(0) == 'S' || UTM[i][j].charAt(0) == 's')
                        eProgram[i] += 111;
                    eProgram[i] += 0;
                }
                if (j == 4) {
                    boolean isFinal = false;
                    int max = 0;
                    if (!"final".equals(UTM[i][j]) && !"f".equals(UTM[i][j]))
                        max = Integer.valueOf(UTM[i][j]);
                    for (int z = 0; z < UTM.length; z++) {
                        if (!"final".equals(UTM[z][j]) && !"f".equals(UTM[z][j])) {
                            if (max < Integer.valueOf(UTM[z][j])) {
                                max = Integer.valueOf(UTM[z][j]);
                            }
                        } else {
                            isFinal = true;
                        }
                    }
                    if (isFinal)
                        max += 1;   // Final State
                    if (!"final".equals(UTM[i][j]) && !"f".equals(UTM[i][j])) {
                        for (int k = 0; k <= Integer.parseInt(UTM[i][j]); k++)
                            eProgram[i] += 1;
                        eProgram[i] += 0;
                    } else {
                        for (int k = 0; k <= max; k++)
                            eProgram[i] += 1;
                        eProgram[i] += 0;
                    }
                }
                j = loopCount[x++];
            }
            eProgram[i] += 0;
            //System.out.println(eProgram[i] + " ");
        }
        return eProgram;
    }
    public String[][] Decoding(String UTM) {
        int countRows = 0;
        for (int i = 0; i < UTM.length() - 1; i++) {
            if (UTM.charAt(i) == '0' && UTM.charAt(i + 1) == '0') {
                countRows++;
            }
        }
        final int maxColumn = 5;
        String[][] dProgram = new String[countRows][maxColumn];
        int z = 0;
        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < maxColumn; j++) {
                if (j == 0 || j == 2) {
                    int countState = -1;
                    while(UTM.charAt(z++) != '0') {
                        countState++;
                    }
                    dProgram[i][j] = String.valueOf(countState);
                }
                if (j == 1 || j== 3) {
                    char readTape = 96;
                    while(UTM.charAt(z++) != '0') {
                        readTape = (char) (readTape + 1);
                    }
                    if (readTape != (char) 123) {
                        dProgram[i][j] = String.valueOf(readTape);
                    } else {
                        dProgram[i][j] = String.valueOf("D");
                    }
                    if(readTape > (char) 123) {
                        dProgram[i][j] = String.valueOf((char) (readTape - 76));
                    }
                }
                if (j == 4) {
                    int countState = 0;
                    while(UTM.charAt(z++) != '0') {
                        countState++;
                    }
                    if (countState == 1)
                        dProgram[i][j] = "R";
                    else if (countState == 2)
                        dProgram[i][j] = "L";
                    else if (countState == 3)
                        dProgram[i][j] = "S";
                }
            }
            while(UTM.charAt(z) == '0' || UTM.charAt(z) == '\r'
                    || UTM.charAt(z) == ' ') {
                if (UTM.charAt(z++) == '0')
                    break;
                z++;
            }
            z++;
        }
        return dProgram;
    }
    public void display(char[] inputOfUTM, int headMovement, JTextArea tape,
            JTextField steps, JTextField currentState, boolean tapeSpeed) {
        tape.append("\n");
        appendTape += "\n";
        for (int i = 0; i < inputOfUTM.length; i++) {
            //System.out.print("  " + inputOfUTM[i]);
            tape.append("  " + inputOfUTM[i]);
            appendTape += "  " + inputOfUTM[i];
            
            if (i+1 < inputOfUTM.length && inputOfUTM[i + 1] != '\u25CA') {
                tape.append(" ");
                appendTape += " ";
            } else {
                for(int j = 0; j < 5; j++) {
                    tape.append("\u200A");
                    appendTape += "\u200A";
                }
            }
        }
        tape.append("\n  ");
        appendTape += "\n  ";
        for(int i = 0; i < 2*headMovement; i++) {
            tape.append("  ");
            appendTape += "  ";
        }
        tape.append("\u21C8\n");
        appendTape += "\u21C8\n";
        tape.update(tape.getGraphics());
        if (!tapeSpeed)
            tape.setText("");
        if (!tapeSpeed) {
            steps.update(steps.getGraphics());
            steps.setText(String.valueOf(noOfSteps));
        }
        if (!tapeSpeed) {
            try {
                Thread.sleep(tapeSPEED);                 // 1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void setTapeSpeed(int value) {
        tapeSPEED = (900 - value * 9);
    }
}