

public class StartGameCommand implements Command{


    @Override
    public void excute(PlayerList playerList, String param) {

        String[] numberAndUsername = param.split(",");
        int[] numbers = new int[2];
        boolean startGame = true;

        if(numberAndUsername.length != 4) {
            printHelp();
            startGame = false;
        }

        //test did the first two parameter is integer
        for (int i = 0; i < 2; i++) {
            try {
                numbers[i] = Integer.parseInt(numberAndUsername[0]);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid integer");
                startGame = false;
            }
        }

        NimPlayer[] players = new NimPlayer[2];
        //test did the player exist
        for (int j = 2; j < 4; j++) {
            players[j - 2] = playerList.getPlayerByUsername(numberAndUsername[j]);
            if (players[j - 2] == null) {
                System.out.println("One of the players does not exist.");
                startGame = false;
            }
        }

        if(startGame) {
            NimGame nimGame = new NimGame(numbers[0], numbers[1], players);
            nimGame.startGame();
        }

    }

    @Override
    public void excute(PlayerList playerList) {
        printHelp();
    }

    public void printHelp(){
        System.out.println("invalid input, please input as the format: " +
                "initial_stone_count, max_stone_removal," +
                "player1_username, player2_username");
    }


}
