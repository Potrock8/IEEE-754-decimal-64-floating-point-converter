package com.company;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.*;
import javax.swing.*;
import java.awt.*;


public class DecTo64 implements ActionListener {
    JFrame frame;
    JPanel panel, round, binary, hexadecimal;
    JTextField textField;
    JLabel out, bin, binOut, hex, hexOut;
    JButton[] funButtons = new JButton[5];
    JButton[] numButtons = new JButton[10];
    JButton[] roundButtons = new JButton[4];
    JButton decButton, expButton, delButton, clrButton, negButton, conButton;
    JButton rUpButton, rDownButton, truncButton, rToNButton;
    JButton print;

    Font font = new Font("Arial", Font.BOLD, 30);
    Font small = new Font("Arial", Font.BOLD, 20);
    Font smallest = new Font("Arial", Font.BOLD, 17);

    public DecTo64() {
        this.frame = new JFrame("IEEE-754 Decimal 64 Floating-Point Converter");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1280, 720);
        this.frame.setResizable(false);
        this.frame.setLayout(null);
        this.frame.setLocationRelativeTo(null);

        this.textField = new JTextField();
        this.textField.setBounds(50, 25, 300, 50);
        this.textField.setFont(this.small);
        this.textField.setEditable(false); //edit later if needed
        this.frame.add(this.textField);

        this.out = new JLabel("OUTPUT");
        this.out.setBounds(800, 25, 300, 50);
        this.out.setFont(this.font);
        this.frame.add(this.out);

        this.panel = new JPanel();
        this.panel.setBounds(50, 100, 300, 300);
        this.panel.setLayout(new GridLayout(4, 4, 1, 1));
        this.panel.setBackground(this.frame.getBackground());

        this.round = new JPanel();
        this.round.setBounds(50, 510, 300, 120);
        this.round.setLayout(new GridLayout(4, 1, 1, 1));
        this.round.setBackground(this.frame.getBackground());

        /*this.binary = new JPanel();
        this.binary.setBounds(600, 100, 600, 120);
        this.binary.setLayout(new GridLayout(1, 2, 1, 1));
        this.binary.setBackground(this.frame.getBackground());*/

        this.delButton = new JButton("DEL");
        this.clrButton = new JButton("CLR");
        this.negButton = new JButton("+/-");
        this.decButton = new JButton(".");
        this.expButton = new JButton("x10");

        this.funButtons[0] = this.delButton;
        this.funButtons[1] = this.clrButton;
        this.funButtons[2] = this.negButton;
        this.funButtons[3] = this.decButton;
        this.funButtons[4] = this.expButton;

        this.rUpButton = new JButton("ROUND UP");
        this.rDownButton = new JButton("ROUND DOWN");
        this.truncButton = new JButton("TRUNCATE");
        this.rToNButton = new JButton("ROUND TO NEAREST");

        this.roundButtons[0] = this.rUpButton;
        this.roundButtons[1] = this.rDownButton;
        this.roundButtons[2] = this.truncButton;
        this.roundButtons[3] = this.rToNButton;

        for(int i = 0; i < 5; i++) {
            this.funButtons[i].addActionListener(this);
            this.funButtons[i].setFont(this.small);
            this.funButtons[i].setFocusable(false);
            if(i == 0 || i == 1) {
                this.funButtons[i].setBackground(Color.red);
                this.funButtons[i].setForeground(Color.white);
            }
        }

        for(int i = 0; i < 4; i++) {
            this.roundButtons[i].addActionListener(this);
            this.roundButtons[i].setFont(this.small);
            this.roundButtons[i].setFocusable(false);
            this.round.add(this.roundButtons[i]);
        }

        for(int i = 0; i < 10; i++) {
            this.numButtons[i] = new JButton(String.valueOf(i));
            this.numButtons[i].addActionListener(this);
            this.numButtons[i].setFont(this.font);
            this.numButtons[i].setFocusable(false);
        }

        this.conButton = new JButton("CONVERT");
        this.conButton.addActionListener(this);
        this.conButton.setFont(this.small);
        this.conButton.setFocusable(false);
        this.conButton.setBounds(125, 430, 145, 50);
        this.frame.add(this.conButton);

        this.panel.add(this.numButtons[1]);
        this.panel.add(this.numButtons[2]);
        this.panel.add(this.numButtons[3]);
        this.panel.add(this.funButtons[0]);

        this.panel.add(this.numButtons[4]);
        this.panel.add(this.numButtons[5]);
        this.panel.add(this.numButtons[6]);
        this.panel.add(this.funButtons[1]);

        this.panel.add(this.numButtons[7]);
        this.panel.add(this.numButtons[8]);
        this.panel.add(this.numButtons[9]);
        this.panel.add(this.funButtons[2]);

        this.panel.add(this.funButtons[3]);
        this.panel.add(this.numButtons[0]);
        this.panel.add(this.funButtons[4]);

        this.bin = new JLabel("BINARY:");
        this.bin.setBounds(400, 100, 150, 120);
        this.bin.setFont(this.font);
        this.frame.add(this.bin);

        this.binOut = new JLabel();
        this.binOut.setBounds(550, 100, 700, 120);
        this.binOut.setFont(this.smallest);
        this.frame.add(this.binOut);

        this.hex = new JLabel("HEX:");
        this.hex.setBounds(450, 300, 150, 120);
        this.hex.setFont(this.font);
        this.frame.add(this.hex);

        this.binOut = new JLabel();
        this.binOut.setBounds(575, 100, 700, 120);
        this.binOut.setFont(this.smallest);
        this.frame.add(this.binOut);

        this.hexOut = new JLabel();
        this.hexOut.setBounds(575, 300, 700, 120);
        this.hexOut.setFont(this.smallest);
        this.frame.add(this.hexOut);

        this.print = new JButton("OUTPUT TO TEXT FILE");
        this.print.setBounds(700, 500, 300, 100);
        this.print.setFont(this.small);
        this.frame.add(this.print);

        this.frame.add(this.panel);
        this.frame.add(this.round);
        //this.frame.add(this.binary);
        this.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //number buttons
        for(int i = 0; i < 10; i++) {
            if(e.getSource() == this.numButtons[i]) {
                this.textField.setText(this.textField.getText().concat(String.valueOf(i)));
            }
        }
        //delete button
        if(e.getSource() == this.delButton && !this.textField.getText().isEmpty()) {
            String str = this.textField.getText();
            char last = str.charAt(str.length()-1);

            if(this.textField.getText().contains("x10^") && Character.compare(last, '^') == 0)
                this.textField.setText(str.replace("x10^", ""));
            else
                this.textField.setText(str.substring(0, str.length()-1));
        }
        //clear button
        if(e.getSource() == this.clrButton && !this.textField.getText().isEmpty()) {
            this.textField.setText("");
        }
        //negative button
        if(e.getSource() == this.negButton && !this.textField.getText().isEmpty()) {
            String str = this.textField.getText();
            char first = str.charAt(0);

            if(Character.compare(first, '-') == 0)
                this.textField.setText(str.substring(1));
            else
                this.textField.setText("-".concat(str));
        }
        //decimal point button
        if(e.getSource() == this.decButton && this.textField.getText().indexOf('.') == -1) {
            if(this.textField.getText().equals(""))
                this.textField.setText("0.");
            else
                this.textField.setText(this.textField.getText().concat("."));
        }
        //base-10 button
        if(e.getSource() == this.expButton && !this.textField.getText().isEmpty() && !this.textField.getText().contains("x10^")) {
            String str = this.textField.getText();
            char last = str.charAt(str.length()-1);

            /*if(this.textField.getText().indexOf('.') == -1)
                this.textField.setText(this.textField.getText().concat(".0x10^"));
            else if(Character.compare(last, '.') == 0)
                this.textField.setText(this.textField.getText().concat("0x10^"));
            else*/
                this.textField.setText(this.textField.getText().concat("x10^"));
        }

        if(e.getSource() == this.conButton && !this.textField.getText().isEmpty() && this.textField.getText().contains("x10^")) {
            String finalAns = new String();
            String toBCDString;
            String[] BCDList;
            String dec_Inp;
            int exp;

            String str = this.textField.getText();
            char last = str.charAt(str.length()-1);
            int x = str.indexOf('x');
            System.out.println(x);
            int caret = str.indexOf('^');
            System.out.println(caret);

            dec_Inp = str.substring(0, x);
            System.out.println(dec_Inp);
            if (dec_Inp.charAt(0) == '-'){
                toBCDString = dec_Inp.substring(2);
            }
            else{
                toBCDString = dec_Inp.substring(1);
            }

            if(Character.compare(last, '^') == 0)
                exp = 0;
            else
                exp = Integer.parseInt(str.substring(caret+1));
            System.out.println(exp);
            
            if(exp > 369){
                finalAns = finalAns + "+INFINITY";
                this.binOut.setText(finalAns);
            }
            else if(exp < -398){
                finalAns = finalAns + "-INFINITY";
                this.binOut.setText(finalAns);
            }
            else {
                String sign = getSignBit(dec_Inp);
                String combi = getCombiField(dec_Inp, exp);

                if (combi.equals("INFINITY")){
                    if (sign == "0"){
                        finalAns = finalAns + "+INFINITY";
                        this.binOut.setText(finalAns);
                        this.hexOut.setText(finalAns);
                    }
                    else{
                        finalAns = finalAns + "-INFINITY";
                        this.binOut.setText(finalAns);
                        this.hexOut.setText(finalAns);
                    }
                }
                else if(combi.equals("NaN")){
                    finalAns = finalAns + "NaN";
                    this.binOut.setText(finalAns);
                        this.hexOut.setText(finalAns);
                }
                else{
                    finalAns = finalAns + sign;
                    finalAns = finalAns + combi;
                    finalAns = finalAns + getExpCont(exp);

                    BCDList = toBCDString.split("(?<=\\G.{" + 3 + "})");
                    System.out.println(BCDList[0]);
                    for (int i = 0; i < 5; i++) {
                        String bcd = DecToBCD(BCDList[i]);
                        String dpbcd = BCDToDPBCD(bcd);

                        finalAns = finalAns + dpbcd;
                    }

                    String hexString = new BigInteger(finalAns, 2).toString(16);
                    System.out.println(finalAns);
                    System.out.println(hexString.toUpperCase());
                    this.binOut.setText(finalAns);
                    this.hexOut.setText(hexString.toUpperCase());
                }
            }
            /*String finalAns = new String();
            String toBCDString;
            String[] BCDList;
            Scanner myInput = new Scanner(System.in);

            System.out.println("Enter 16 digit decimal: ");
            String dec_Inp = myInput.nextLine();

            System.out.println("Enter exponent value: ");
            int exp = myInput.nextInt();

            if (dec_Inp.charAt(0) == '-'){
                toBCDString = dec_Inp.substring(2);
            }
            else{
                toBCDString = dec_Inp.substring(1);
            }

            if(exp > 369 || exp < -398){
                finalAns = "Invalid Exponent";
                System.out.println(finalAns);
            }
            else{
                finalAns = finalAns + getSignBit(dec_Inp);
                finalAns = finalAns + getCombiField(dec_Inp, exp);
                finalAns = finalAns + getExpCont(exp);

                BCDList = toBCDString.split("(?<=\\G.{" + 3 + "})");

                for (int i = 0; i < 5; i++){
                    String bcd = DecToBCD(BCDList[i]);
                    String dpbcd = BCDToDPBCD(bcd);

                    finalAns = finalAns + dpbcd;
                }

                String hexString = new BigInteger(finalAns, 2).toString(16);
                System.out.println(finalAns);
                System.out.println(hexString.toUpperCase());
            }*/
        }
    }

    static String BCDToDPBCD (String BCDVals){
        char[] output = new char[10];
        char a = BCDVals.charAt(0);
        char b = BCDVals.charAt(1);
        char c = BCDVals.charAt(2);
        char d = BCDVals.charAt(3);
        char e = BCDVals.charAt(4);
        char f = BCDVals.charAt(5);
        char g = BCDVals.charAt(6);
        char h = BCDVals.charAt(7);
        char i = BCDVals.charAt(8);
        char j = BCDVals.charAt(9);
        char k = BCDVals.charAt(10);
        char m = BCDVals.charAt(11);

        if(a == '0' && e == '0' && i == '0'){
            output[0] = b; output[1] = c; output[2] = d; output[3] = f; output[4] = g;
            output[5] = h; output[6] = '0'; output[7] = j; output[8] = k; output[9] = m;
        }
        else if(a == '0' && e == '0' && i == '1'){
            output[0] = b; output[1] = c; output[2] = d; output[3] = f; output[4] = g;
            output[5] = h; output[6] = '1'; output[7] = '0'; output[8] = '0'; output[9] = m;
        }
        else if(a == '0' && e == '1' && i == '0'){
            output[0] = b; output[1] = c; output[2] = d; output[3] = j; output[4] = k;
            output[5] = h; output[6] = '1'; output[7] = '0'; output[8] = '1'; output[9] = m;
        }
        else if(a == '0' && e == '1' && i == '1'){
            output[0] = b; output[1] = c; output[2] = d; output[3] = '1'; output[4] = '0';
            output[5] = h; output[6] = '1'; output[7] = '1'; output[8] = '1'; output[9] = m;
        }
        else if(a == '1' && e == '0' && i == '0'){
            output[0] = j; output[1] = k; output[2] = d; output[3] = f; output[4] = g;
            output[5] = h; output[6] = '1'; output[7] = '1'; output[8] = '0'; output[9] = m;
        }
        else if(a == '1' && e == '0' && i == '1'){
            output[0] = f; output[1] = g; output[2] = d; output[3] = '0'; output[4] = '1';
            output[5] = h; output[6] = '1'; output[7] = '1'; output[8] = '1'; output[9] = m;
        }
        else if(a == '1' && e == '1' && i == '0'){
            output[0] = j; output[1] = k; output[2] = d; output[3] = '0'; output[4] = '0';
            output[5] = h; output[6] = '1'; output[7] = '1'; output[8] = '1'; output[9] = m;
        }
        else if(a == '1' && e == '1' && i == '1'){
            output[0] = '0'; output[1] = '0'; output[2] = d; output[3] = '1'; output[4] = '1';
            output[5] = h; output[6] = '1'; output[7] = '1'; output[8] = '1'; output[9] = m;
        }

        String DPBCD = String.copyValueOf(output);
        return DPBCD;
    }

    static String DecToBCD (String DecVals){
        char[] output = new char[12];

        int j = 0;
        for (int i=0; i<3; i++){

            if(DecVals.charAt(i) == '0'){
                output[j] = '0'; output[j+1] = '0'; output[j+2] = '0'; output[j+3] = '0';
                j+=4;
            }
            if(DecVals.charAt(i) == '1'){
                output[j] = '0'; output[j+1] = '0'; output[j+2] = '0'; output[j+3] = '1';
                j+=4;
            }
            if(DecVals.charAt(i) == '2'){
                output[j] = '0'; output[j+1] = '0'; output[j+2] = '1'; output[j+3] = '0';
                j+=4;
            }
            if(DecVals.charAt(i) == '3'){
                output[j] = '0'; output[j+1] = '0'; output[j+2] = '1'; output[j+3] = '1';
                j+=4;
            }
            if(DecVals.charAt(i) == '4'){
                output[j] = '0'; output[j+1] = '1'; output[j+2] = '0'; output[j+3] = '0';
                j+=4;
            }
            if(DecVals.charAt(i) == '5'){
                output[j] = '0'; output[j+1] = '1'; output[j+2] = '0'; output[j+3] = '1';
                j+=4;
            }
            if(DecVals.charAt(i) == '6'){
                output[j] = '0'; output[j+1] = '1'; output[j+2] = '1'; output[j+3] = '0';
                j+=4;
            }
            if(DecVals.charAt(i) == '7'){
                output[j] = '0'; output[j+1] = '1'; output[j+2] = '1'; output[j+3] = '1';
                j+=4;
            }
            if(DecVals.charAt(i) == '8'){
                output[j] = '1'; output[j+1] = '0'; output[j+2] = '0'; output[j+3] = '0';
                j+=4;
            }
            if(DecVals.charAt(i) == '9'){
                output[j] = '1'; output[j+1] = '0'; output[j+2] = '0'; output[j+3] = '1';
                j+=4;
            }
        }
        String BCD = String.copyValueOf(output);
        return BCD;
    }

    static String getSignBit (String DecInput){
        String signBit;
        if (DecInput.charAt(0) == '-'){
            signBit = "1";
        }
        else{
            signBit = "0";
        }

        return signBit;
    }

    static String getCombiField (String DecInput, int exp){
        char msd;
        exp += 398;
        String exp_bin = Integer.toBinaryString(exp);
        char[] combiField = new char[5];

        if (exp_bin.length() > 10){
            exp_bin = "111111111111";
        }

        if (exp_bin.length() != 10){
            while(exp_bin.length() != 10){
                exp_bin = '0' + exp_bin;
            }
        }

        if (DecInput.charAt(0) == '-'){
            msd = DecInput.charAt(1);
        }
        else{
            msd = DecInput.charAt(0);
        }

        String msd_bin = Integer.toBinaryString(Character.getNumericValue(msd));
        
        if (msd_bin.length() < 4){
            while(msd_bin.length() < 4){
                msd_bin = '0' + msd_bin;
            }
        }

        if (Character.getNumericValue(msd) < 8){
            combiField[0] = exp_bin.charAt(0);
            combiField[1] = exp_bin.charAt(1);
            combiField[2] = msd_bin.charAt(1);
            combiField[3] = msd_bin.charAt(2);
            combiField[4] = msd_bin.charAt(3);
        }
        else if (Character.getNumericValue(msd) > 7){
            combiField[0] = '1';
            combiField[1] = '1';
            combiField[2] = exp_bin.charAt(0);
            combiField[3] = exp_bin.charAt(1);
            combiField[4] = msd_bin.charAt(3);
        }

        String result = String.copyValueOf(combiField);

        if (result.equals("11110")){
                result = "INFINITY";
        } 
        else if (result.equals("11111")){
            result = "NaN";
        }
        return result;
    }

    static String getExpCont (int exp){
        exp += 398;
        String exp_bin = Integer.toBinaryString(exp);
        String result;

        if (exp_bin.length() != 10){
            while(exp_bin.length() != 10){
                exp_bin = '0' + exp_bin;
            }
        }

        result = exp_bin.substring(2);
        return result;
    }

    public static void main(String[] args){
        DecTo64 converter = new DecTo64();
        /*String finalAns = new String();
        String toBCDString;
        String[] BCDList;
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter 16 digit decimal: ");
        String dec_Inp = myInput.nextLine();

        System.out.println("Enter exponent value: ");
        int exp = myInput.nextInt();
    
        if (dec_Inp.charAt(0) == '-'){
            toBCDString = dec_Inp.substring(2);
        }
        else{
            toBCDString = dec_Inp.substring(1);
        }

        if(exp > 369 || exp < -398){
            finalAns = "Invalid Exponent";
            System.out.println(finalAns);
        }
        else{
            finalAns = finalAns + getSignBit(dec_Inp);
            finalAns = finalAns + getCombiField(dec_Inp, exp);
            finalAns = finalAns + getExpCont(exp);

            BCDList = toBCDString.split("(?<=\\G.{" + 3 + "})");

            for (int i = 0; i < 5; i++){
                String bcd = DecToBCD(BCDList[i]);
                String dpbcd = BCDToDPBCD(bcd);

                finalAns = finalAns + dpbcd;
            }

            String hexString = new BigInteger(finalAns, 2).toString(16);
            System.out.println(finalAns);
            System.out.println(hexString.toUpperCase());
        }*/
    }
}