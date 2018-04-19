
// A class for Nimsys
// @Author weikai Zeng

import java.util.*;



public class Nimsys {


    private List<NimPlayer> players = new ArrayList<>();

    private static HashMap<String, Command> commands;

    private PlayerList playerList;

    private static final Scanner kb = new Scanner(System.in);

    static{
        commands.put("startgame", new StartGameCommand());
    }

    public void shiel(String input){
        String[] commandAndParams = input.split(" ");
        while(true){
            if(input.length() == 1)
                commands.get(commandAndParams[0]).excute(playerList);

            if(input.length() == 2)
                commands.get(commandAndParams[0]).excute(playerList, commandAndParams[1]);
        }
    }



    /**
     * start the gameCommand
     * do some pre-process to the param
     * @param param
     */
    public void startGameCommand(String param) {
        String[] params = param.split(",");
        int[] numbers = new int[2];
        boolean startGame = true;

        if(params.length == 4) {
            //test did the first two parameter is integer
            for (int i = 0; i < 2; i++) {
                try {
                    numbers[i] = Integer.parseInt(params[0]);
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid integer");
                    startGame = true;
                }
            }

            NimPlayer[] players = new NimPlayer[2];
            //test did the player exist
            for (int j = 2; j < 4; j++) {
                players[j - 2] = findPlayerByUserName(params[j]);
                if (!Optional.ofNullable(players[j - 2]).isPresent()) {
                    System.out.println("One of the players does not exist.");
                    startGame = true;
                }
            }

            if (startGame)
                startGame(numbers[0], numbers[1], players);
        }
    }

    /**
     * find the user by their username
     * @param userName
     * @return the NimPlayer
     */
    private NimPlayer findPlayerByUserName(String userName){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getUserName().compareTo(userName) == 0)
                return players.get(i);
        }
        return null;
    }

    /**
     * run the game
     * @param numberOfStone
     * @param upBound
     * @param players
     */
    public void startGame(int numberOfStone, int upBound, NimPlayer[] players){
        NimGame nimGame = new NimGame(numberOfStone, upBound, players);
        nimGame.startGame();
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
        int n = 0;
        boolean validInput = false;
        while (!validInput) {
            n = inputInteger();
            if (n >= lower && n <= upper) {
                validInput = true;
            } else {
                System.out.println("Invalid move. You must remove between "+lower+" and "+ upper+" stones.");
            }
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

}


