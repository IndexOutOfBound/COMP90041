
/**
 * @Author: weikai.zeng
 * this class used to start the NimGame
 */


public class StartGameCommand implements NimCommand {


    /**
     * start the NimGame
     * @param playerList
     * @param param
     */
    @Override
    public void execute(PlayerList playerList, String param) {

        String[] numberAndUsername = param.split(",");
        int[] numbers = new int[2];
        boolean startGame = true;
        int i = 0;
        if(numberAndUsername.length != 4) {
            printHelp();
            startGame = false;
        }

        //test did the first two parameter is integer
        while( i< 2 && startGame){
            try {
                numbers[i] = Integer.parseInt(numberAndUsername[i]);
            } catch (NumberFormatException e) {
                System.out.println("The first two parameters should be integer");
                startGame = false;
            }
            i++;
        }

        NimPlayer[] players = new NimPlayer[2];
        //test did the player exist
        while(i<4 && startGame){
            players[i - 2] = playerList.getPlayerByUserName(numberAndUsername[i]);
            if (players[i - 2] == null) {
                System.out.println("One of the players does not exist.");
                startGame = false;
            }
            i++;
        }

        if(startGame) {
            System.out.println("\nInitial stone count: "+numbers[0]+
            "\nMaximum stone removal: "+numbers[1]+
            "\nPlayer 1: "+players[0].getFirstName()+" "+players[0].getLastName()+
            "\nPlayer 2: "+players[1].getFirstName()+" "+players[1].getLastName());
            NimGame nimGame = new NimGame(numbers[0], numbers[1], players);
            nimGame.runGame();
        }

    }

    /**
     * when user input without parameters, print the help information
     * @param playerList
     */
    @Override
    public void execute(PlayerList playerList) {
        printHelp();
    }

    /**
     * print the help information
     */
    private void printHelp(){
        System.out.println("invalid input, please input as the format: " +
                "initial_stone_count, max_stone_removal," +
                "player1_username, player2_username");
    }


}
