// A class for data input methods
// Chris Trathen October 2014
import java.util.Scanner;

public class DataInput {
    private static final Scanner kb = new Scanner(System.in);


    //method used to input string
    public static String inputString() {
        return kb.nextLine();
    }

    //method used to input a integer in a specific range
    public static int inputIntegerRange(int lower, int upper) {
        int n = 0;
        boolean validInput = false;
        while (!validInput) {
            n = inputInteger();
            if (n >= lower && n <= upper) {
                validInput = true;
            } else {
                System.out.println("Not from " + lower + " to " + upper);
            }
        }
        return n;
    }

    //method used to input a integer
    public static int inputInteger() {
        int i = 0;
        boolean validInput = false;
        while (!validInput) {
            String s = inputString();
            try {
                i = Integer.parseInt(s);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid integer");
            }
        }
        return i;
    }

    //method used to choose Y/N
    public static boolean chooseYN(){
        while (true) {
            String s = inputString();
            if(s.equalsIgnoreCase("Y"))
                return true;
            if(s.equalsIgnoreCase("N"))
                return false;
            System.out.println("Not a valid input, please input one of Y/N");
        }
    }
}
