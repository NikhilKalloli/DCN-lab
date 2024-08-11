import java.util.Scanner;

class CRC {

   
    public static String xor(String a, String b) {
        int maxLength = Math.max(a.length(), b.length());
        int x = Integer.parseInt(a, 2);
        int y = Integer.parseInt(b, 2);
        
        String result = Integer.toBinaryString(x ^ y);
        
        result = String.format("%" + maxLength + "s", result).replace(" ", "0");
        return result;
    }

    
    public static String divide(String dividend, String divisor) {
        int divisorLength = divisor.length();
        int dividendLength = dividend.length();

        while (dividendLength >= divisorLength) {
            String temp;
            if(dividend.charAt(0) == '1') temp = xor(divisor, dividend.substring(0, divisorLength));
            else temp = xor("0", dividend.substring(0, divisorLength));
            
            dividend = temp.substring(1) + dividend.substring(divisorLength);
            dividendLength -= 1;
        }
        return dividend;
    }
    
    public static String generate(String message, String generator) {
        int messageLength = message.length();
        int generatorLength = generator.length();

        String dividend = String.format("%-" + (messageLength + generatorLength - 1) + "s", message).replace(' ', '0');
        String remainder = divide(dividend, generator);

        return message + remainder;
    }

    public static boolean checkCodeWord(String codeword, String generator) {
        String remainder = divide(codeword, generator);
        return Integer.parseInt(remainder) == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Using CRC-CCITT");
        String generator = "10001000000100001";

        while (true) {
            System.out.println("1. Generate codeword");
            System.out.println("2. Check data");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter dataword: ");
                    String dataword = scanner.next();
                    System.out.println("Codeword: " + generate(dataword, generator));
                    break;

                case 2:
                    System.out.print("Enter codeword: ");
                    String codeword = scanner.next();
                
                    if(checkCodeWord(codeword, generator)) System.out.println("No Error");
                    else System.out.println("Error");
                    
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                
                default:
                    System.out.println("Invalid choice, please select 1 or 2.");
                    break;   
            }
        }
    }
}
