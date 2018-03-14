
// A class for Nimsys
// @Author weikai Zeng

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Nimsys {

    private int numberOfStone = 0;

    private int upBound = 0;

    private boolean gameOver = false;

    private List<NimPlayer> players = new LinkedList<>();

    private static final int NUM_OF_PLAYER = 2;

    private static final Scanner kb = new Scanner(System.in);

    private int indexOfCurrentPlayer = 0;//record the index of player of every turn

    public int getNumberOfStone() {
        return numberOfStone;
    }

    public Nimsys setNumberOfStone(int numberOfStone) {
        this.numberOfStone = numberOfStone;
        return this;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Nimsys setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        return this;
    }

    public int getUpBound() {
        return upBound;
    }

    public Nimsys setUpBound(int upBound) {
        this.upBound = upBound;
        return this;
    }

    //add the player into player list
    public Nimsys addPlayers(NimPlayer nimPlayer){
        this.players.add(nimPlayer);
        return this;
    }

    //initialize the game
    //add the player into
    public Nimsys initialize(){
        System.out.println("Welcome to Nim");
        for(int i = 1; i <= NUM_OF_PLAYER; i++) {
            System.out.println("\nPlease enter Player " + i + "'s name:");
            String name = inputString();
            NimPlayer nimPlayer1 = new NimPlayer().setName(name);
            this.addPlayers(nimPlayer1);
        }

        return this;
    }

    //set the number of stone and upper bound
    public Nimsys start(){
        System.out.println("\nPlease enter upper bound of stone removal:");
        Integer upperBound = inputInteger();

        System.out.println("\nPlease enter initial number of stones:");
        Integer numOfStone = inputInteger();

        this.setNumberOfStone(numOfStone)
            .setUpBound(upperBound);

        return this;
    }

    //play the game
    public Nimsys removeStone(){
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
        if(indexOfCurrentPlayer == NUM_OF_PLAYER - 1)
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
        System.out.print("\nDo you want to play again (Y/N):");
        return chooseYN();
    }

    //display stone as *
    private String displayStone(){
        StringBuilder stones = new StringBuilder();
        for(int i = 0; i< numberOfStone; i++)
            stones.append(" *");
        return stones.toString();
    }

    //main function used to run program
    public static void main(String[] args) {
        Nimsys nimsys = new Nimsys();
        nimsys.initialize();
        boolean playAgain = true;
        while(playAgain){
            nimsys.start();
            while(!nimsys.isGameOver())
                nimsys.removeStone();
            nimsys.over();
            playAgain = nimsys.playAgain();
        }
    }


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
