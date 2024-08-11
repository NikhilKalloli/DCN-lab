import java.util.*;

public class Temp {

    static String xor(String a, String b){
        int maxLen = Math.max(a.length(), b.length());
        int x = Integer.parseInt(a,2);
        int y = Integer.parseInt(b,2);

        String res = Integer.toBinaryString(x^y);
        res = String.format("%" + maxLen +"s", res).replace(" ", "0");

        return res;

    }
    
    public static String divide(String dividend, String divisor){
        int dividendLen = dividend.length();
        int divisorLen = divisor.length();

        while(dividendLen >= divisorLen){
            String temp;
            if(dividend.charAt(0)=='1') temp = xor(divisor, dividend.substring(0, divisorLen))  ;
            else temp = xor("0", dividend.substring(0, divisorLen));

            dividend = temp.substring(1) + dividend.substring(divisorLen);
            dividendLen-=1;
        }

        return dividend;
    }

    public static String generate(String message, String generator){
        int msgLen = message.length();
        int genLen =  generator.length();

        String dividend = String.format("%-" + (msgLen + genLen -1) + "s", message).replace(' ', '0');

        String remainder = divide(dividend, generator);

        return message + remainder;
    }

    public static boolean check(String Codeword, String generator){
        String remainder = divide(Codeword, generator);
        return Integer.parseInt(remainder)==0;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // System.out.println(xor("1001", "0110"));
        System.out.println(divide("1101", "1001"));

    }
}    

