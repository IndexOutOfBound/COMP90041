
// A class for Nimsys
// @Author weikai Zeng

import java.math.BigDecimal;
import java.util.*;



public class Nimsys {


    private List<NimPlayer> players = new ArrayList<>();

    private static final Scanner kb = new Scanner(System.in);

    private static final BigDecimal ZERO = new BigDecimal("0");

    private static final Comparator<NimPlayer> comparator = (player1, player2) -> {
        BigDecimal player1WinRate = player1.getWinGames().divide(player1.getNumberOfGames()).setScale(2);
        BigDecimal player2WinRate = player2.getWinGames().divide(player1.getNumberOfGames()).setScale(2);
        String player1FullName = player1.getFirstName() + player1.getLastName();
        String player2FullName = player2.getFirstName() + player2.getLastName();

        if (player1WinRate.compareTo(player2WinRate) > 0) {
            return 1;
        } else if (player1WinRate.compareTo(player2WinRate)==0) {
            if (player1FullName.compareTo(player2FullName) > 0)
                return 1;
            else if(player1FullName.compareTo(player2FullName) == 0)
                return 0;
            else
                return -1;
        } else {
            return -1;
        }
    };

    private void runShield(){
        System.out.println("Welcome to Nim");
        while(true){
            System.out.print("$");
            String input =inputString();
            String[] commandAndParams = input.split(",");
            if(commandAndParams.length == 1)
                runCommandWithoutParam(commandAndParams[0]);

            if(commandAndParams.length == 2)
                runCommandWithParam(commandAndParams[0], commandAndParams[1]);

            if(commandAndParams.length > 2)
                System.out.println("Invalid input");

            System.out.println();
        }
    }

    /**
     *
     * @param command
     * @param params
     */
    private void runCommandWithParam(String command, String params){

        if(command.compareTo("startgame") == 0)
            startGameCommand(params);

        if(command.compareTo("addplayer") == 0)
            addPlayer(params);

        if(command.compareTo("displayplayer") == 0)
            displayPlayer(params);

        if(command.compareTo("editplayer") == 0)
            editPlayer(params);

        if(command.compareTo("resetstats") == 0)
            resetstats(params);

        if(command.compareTo("ranking") == 0)
            rank(params);
    }

    private void runCommandWithoutParam(String command){
        if(command.compareTo("removeplayer") == 0)
            removePlayer();

        if(command.compareTo("displayplayer") == 0)
            displayPlayer();

        if(command.compareTo("resetstats") == 0)
            resetstats();

        if(command.compareTo("rankings") == 0)
            rank();

        if(command.compareTo("exit") == 0)
            System.exit(0);
    }


    /**
     * add one player
     * @param params
     * @return one boolean value indicate whether this operation is success
     */
    public boolean addPlayer(String params){
        String[] names = params.split(",");
        //divide the params into a array with size 3. 0: username 1:lastName 2:firstName
        if( names.length != 3){
            System.out.println("Invaild input, please input as the format: username,family_name,given_name");
            return false;
        }

        if(userExist(names[0])){
            System.out.println("The player already exist.");
            return false;
        }

        NimPlayer newplayer = new NimPlayer(names[0],names[1], names[2]);
        this.players.add(newplayer);

        return true;
    }


    /**
     * remove one player
     * @param username
     * @return one boolean value indicate whether this operation is success
     */
    public boolean removePlayer(String username){
        NimPlayer nimPlayer = this.findPlayerByUserName(username);
        if(nimPlayer == null) {
            System.out.println("The player not exist");
            return false;
        }

        players.remove(nimPlayer);
        return true;
    }


    /**
     * remove all players
     * @return one boolean value indicate whether this operation is success
     */
    public boolean removePlayer(){
        System.out.println("Are you sure you want to remove all players?(y/n)");
        boolean choice = this.chooseYN();
        if(choice){
            this.players = new ArrayList<>();
            return true;
        }
        return false;
    }


    /**
     * display one player
     * @return one boolean value indicate whether this operation is success
     */
    public boolean displayPlayer(String username){
        NimPlayer nimPlayer = findPlayerByUserName(username);
        if(nimPlayer == null){
            System.out.println("The player does not exist");
            return false;
        }
        System.out.println(nimPlayer.toString());
        return true;
    }


    /**
     * display all players
     */
    public void displayPlayer(){
        players.forEach(NimPlayer::toString);
    }


    /**
     * edit one player
     * @param params
     * @return one boolean value indicate whether this operation is success
     */
    public boolean editPlayer(String params){
        String[] names = params.split(",");
        if( names.length != 3){
            System.out.println("Invaild input, please input as the format: username,family_name,given_name");
            return false;
        }

        NimPlayer nimPlayer = findPlayerByUserName(names[0]);
        if( nimPlayer != null){
            System.out.println("The player does not exist.");
            return false;
        }

        nimPlayer.setFirstName(names[1]);
        nimPlayer.setLastName(names[2]);
        return true;
    }



    /**
     * reset the statics of one player
     * @param params the username
     * @return one boolean value indicate whether this operation is success
     */
    public boolean resetstats(String params){
        NimPlayer nimPlayer = findPlayerByUserName(params);
        if( nimPlayer == null){
            System.out.println("The player not exist");
            return false;
        }
        nimPlayer.setWinGames(ZERO)
                 .setNumberOfGames(ZERO);
        return true;
    }

    /**
     * reset the statics of all player
     * @return one boolean value indicate whether this operation is success
     */
    public boolean resetstats(){
        System.out.println("Are you sure you want to reset all player statistics? (y/n)");
        boolean choice = chooseYN();
        if(!choice)
            return false;
        players.forEach(nimPlayer -> {
            nimPlayer.setNumberOfGames(ZERO);
            nimPlayer.setWinGames(ZERO);
        });
        return true;
    }

    public void rank(){
        rank("desc");
    }

    public boolean rank(String params){
        players.sort(comparator);

        if(  params.compareToIgnoreCase("asc") != 0
              && params.compareToIgnoreCase("desc") != 0){
            System.out.println("Invalid input, please input asc or desc");
            return false;
        }

        if(params.compareToIgnoreCase("asc") == 0)
            outputASC();
        if(params.compareToIgnoreCase("desc") == 0)
            outputDESC();

        return true;
    }

    private void outputASC(){
        for(int i = 0; i<players.size(); i++)
            System.out.println(players.get(i).toRankString());
    }

    private void outputDESC(){
        for(int i = players.size()-1; i>=0; i--)
            System.out.println(players.get(i).toRankString());
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


    private boolean userExist(String userName){
        return findPlayerByUserName(userName) == null;
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

    public static void main(String[] args){
        Nimsys nimsys = new Nimsys();
        nimsys.runShield();
    }



}


