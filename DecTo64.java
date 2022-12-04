import java.math.BigInteger;
import java.util.*;


public class DecTo64{

    public class Globals{
        public static int exponent;
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

    static String RemoveDecPoint (String DecInput, int exp){
       for(int i=0;i<DecInput.length();i++){
            if(DecInput.charAt(i) == '.'){
                int index = i;
                StringBuilder sb = new StringBuilder(DecInput);
                sb.deleteCharAt(i);
                DecInput = sb.toString();   

                if(i > 17 && DecInput.charAt(0) == '-'){
                    break;
                }
                else if(i > 16 && DecInput.charAt(0) != '-'){
                    break;
                }
                else{
                    Globals.exponent = exp - (DecInput.length() - index);
                }
        }
    }
            return DecInput;
    }
    // Not a number input
    // special cases

    static String NormalizeDec (String DecInput){
        String result;
        String zerostring = new String();
        
        if(DecInput.length() < 17 && DecInput.charAt(0) == '-'){
            for(int i=0;i< 17 - DecInput.length();i++){
                zerostring = "0" + zerostring;
            }

            StringBuffer reString = new StringBuffer(DecInput);
            reString.insert(1, zerostring);
            result = reString.toString();
        }
        else if(DecInput.length() < 16){
            result = String.format("%16s", DecInput).replace(' ', '0');
        }
        else{
            result = DecInput;
        }

        return result;
    }

    static int NormalizeExp (String DecInput, int exp){
        int result;

        if(DecInput.length() > 16 && DecInput.charAt(0) != '-'){
            result = exp + (DecInput.length() - 16);
        }
        else if(DecInput.length() > 17 && DecInput.charAt(0) == '-'){
            result = exp + (DecInput.length() - 17);
        }
        else{
            result = exp;
        }

        return result;
    }

    public static void main(String[] args){
        String finalAns = new String();
        String toBCDString;
        String[] BCDList;
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter 16 digit decimal: ");
        String dec_Inp = myInput.nextLine();

        System.out.println("Enter exponent value: ");
        int exp = myInput.nextInt();
        Globals.exponent = exp;


        dec_Inp = RemoveDecPoint(dec_Inp, exp);
        exp = Globals.exponent;
        dec_Inp = NormalizeDec(dec_Inp);
        exp = NormalizeExp(dec_Inp, exp);
        
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
        }
    }
}