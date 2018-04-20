
/**
 * @Author: weikai.zeng
 * this class represent the NimSys
 */



import java.math.BigDecimal;
import java.util.*;



public class Nimsys {


    private static HashMap<String, NimCommand> commands = new HashMap<>();

    private static final Scanner kb = new Scanner(System.in);

    public static final BigDecimal ZERO = new BigDecimal("0");

    private PlayerList players = new PlayerList();

    static{
        commands.put("startgame", new StartGameCommand());
        commands.put("ranking", new RankCommand());
        commands.put("addplayer",new AddPlayerCommand());
        commands.put("displayplayer",new DisplayPlayerCommand());
        commands.put("editplayer", new EditPlayerCommand());
        commands.put("resetstats", new ResetPlayerCommand());
        commands.put("removeplayer", new RemovePlayerCommand());
        commands.put("exit", new ExistCommand());

    }

    /**
     * run the shield of NimSys
     */
    private void runShield(){
        System.out.println("Welcome to Nim");
        while(true){
            System.out.print("$");
            String input =inputString();
            String[] commandAndParams = input.split(" ");
            if(commandAndParams.length == 1 && commands.containsKey(commandAndParams[0]))
                commands.get(commandAndParams[0]).execute(players);

            if(commandAndParams.length == 2 && commands.containsKey(commandAndParams[0]))
                commands.get(commandAndParams[0]).execute(players, commandAndParams[1]);

            if(commandAndParams.length > 2)
                System.out.println("Invalid input");

            System.out.println();
        }
    }

    /**
     * let user input String
     * @return the String user input
     */
    public static String inputString() {
        return kb.nextLine();
    }

    /**
     * let user input an integer in a range
     * @param lower the lower bound of integer
     * @param upper the upper bound of integer
     * @return the integer user input
     */
    public static int inputIntegerRange(int lower, int upper) {
        int n = inputInteger();
        if (n < lower || n > upper) {
            System.out.println("\n\nInvalid move. You must remove between " + lower + " and " + upper + " stones.");
            return 0;
        }
        return n;
    }

    /**
     * let user input an integer
     * @return the integer user input
     */
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

    /**
     * let user choose one from Y / N
     * @return the boolean value of user input
     */
    public static boolean chooseYN() {
        while (true) {
            String s = inputString();
            if (s.equalsIgnoreCase("Y"))
                return true;
            if (s.equalsIgnoreCase("N"))
                return false;
            System.out.print("Not a valid input, please input one of Y/N");
        }
    }

    public static void main(String[] args){
        Nimsys nimsys = new Nimsys();
        NimPlayer player = new NimPlayer("weikai","kai","zeng");

        player.addOneGame();
        nimsys.runShield();
    }



}


