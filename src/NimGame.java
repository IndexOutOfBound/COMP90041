
// A class for NimGame
// @Author weikai Zeng

import java.util.LinkedList;
import java.util.List;

public class NimGame {

    private int numberOfStone = 0;

    private int upBound = 0;

    private boolean gameOver = false;

    private List<NimPlayer> players = new LinkedList<>();

    private int numOfPlayers = 2;

    private int indexOfCurrentPlayer = 0;//record the index of player of every turn

    public int getNumberOfStone() {
        return numberOfStone;
    }

    public NimGame setNumberOfStone(int numberOfStone) {
        this.numberOfStone = numberOfStone;
        return this;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public NimGame setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        return this;
    }

    public int getUpBound() {
        return upBound;
    }

    public NimGame setUpBound(int upBound) {
        this.upBound = upBound;
        return this;
    }

    //add the player into player list
    public NimGame addPlayers(NimPlayer nimPlayer){
        this.players.add(nimPlayer);
        return this;
    }

    //initialize the game
    //add the player into
    public NimGame initialize(){
        System.out.println("Welcome to Nim");
        for(int i = 1; i <= numOfPlayers; i++) {
            System.out.println("\nPlease enter Player " + i + "'s name:");
            String name = DataInput.inputString();
            NimPlayer nimPlayer1 = new NimPlayer().setName(name);
            this.addPlayers(nimPlayer1);
        }

        return this;
    }

    //set the number of stone and upper bound
    public NimGame start(){
        System.out.println("\nPlease enter upper bound of stone removal:");
        Integer upperBound = DataInput.inputInteger();

        System.out.println("\nPlease enter initial number of stones:");
        Integer numOfStone = DataInput.inputInteger();

        this.setNumberOfStone(numOfStone)
            .setUpBound(upperBound);

        return this;
    }

    //play the game
    public NimGame removeStone(){
        System.out.println( "\n"+numberOfStone + " stones left:" + displayStone());
        NimPlayer currentPlayer = players.get(indexOfCurrentPlayer);
        currentPlayer.removeStone(this);

        if(numberOfStone != 0)//test whether the game is over
            indexOfCurrentPlayer = findNextPlayer();
        else
            gameOver = true;

        return this;
    }

    //get the index of next player
    public int findNextPlayer(){
        if(indexOfCurrentPlayer == numOfPlayers - 1)
            //when the index is point to the last one in player list
            return 0;
        return indexOfCurrentPlayer+1;
    }

    //game over, print the winner
    public void over(){
        System.out.println( "\nGame Over\n" + players.get(findNextPlayer()).getName() + " wins!");
        gameOver = false;
        indexOfCurrentPlayer = 0;
    }

    //ask the player whether to play again
    public boolean playAgain(){
        System.out.println("\nDo you want to play again (Y/N):");
        return DataInput.chooseYN();
    }

    //display stone as *
    private String displayStone(){
        StringBuilder stones = new StringBuilder();
        for(int i = 0; i< numberOfStone; i++)
            stones.append(" *");
        return stones.toString();
    }


}
