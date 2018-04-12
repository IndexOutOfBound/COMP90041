

public class NimGame {

    private int numberOfStone = 0;

    private int upBound = 0;

    private NimPlayer[] players;

    private int indexOfCurrentPlayer = 0;//record the index of player of every turn

    public NimGame(int numberOfStone, int upBound, NimPlayer[] players){
        this.numberOfStone = numberOfStone;
        this.upBound = upBound;
        this.players = players;
    }

    public int getNumberOfStone() {
        return numberOfStone;
    }

    public NimGame setNumberOfStone(int numberOfStone) {
        this.numberOfStone = numberOfStone;
        return this;
    }

    /**
     * test whether the game is over
     * @return a boolean value
     */
    public boolean isGameOver() {
        return numberOfStone == 0;
    }

    public int getUpBound() {
        return upBound;
    }

    /**
     * let player remove the stone
     * @return
     */
    public NimGame removeStone(){
        displayRemainderStone();
        NimPlayer currentPlayer = players[indexOfCurrentPlayer];
        currentPlayer.removeStone(this);

        if(!isGameOver())//if game is not over, find the next player
            indexOfCurrentPlayer = findNextPlayer();

        return this;
    }

    /**
     *
     * @return the index of next player
     */
    public int findNextPlayer(){
        if(indexOfCurrentPlayer == players.length - 1)
            //when the index is point to the last one in player list
            return 0;

        return indexOfCurrentPlayer+1;
    }

    /**
     * over the game and print the winner
     */
    public void over(){
        NimPlayer winner = players[findNextPlayer()];
        System.out.println( "\nGame Over\n" + winner.getLastName() + " " + winner.getFirstName()+" wins!");
        winner.addOneWin();
        for(int i = 0; i< players.length; i++)
            players[i].addOneGame();
        indexOfCurrentPlayer = 0;
    }

    /**
     * ask player whether they play again
     * @return their choice
     */
    public boolean playAgain(){
        System.out.print("\nDo you want to play again (Y/N):");
        return Nimsys.chooseYN();
    }

    /**
     * display the remainder stones as "*"
     */
    private void displayRemainderStone(){
        StringBuilder stones = new StringBuilder();
        for(int i = 0; i< numberOfStone; i++)
            stones.append(" *");

        System.out.println( "\n"+numberOfStone + " stones left:" + stones.toString());
    }


    /**
     * run the game
     */
    public void startGame(){
        boolean playAgain = true;
        while(playAgain){
            while(!this.isGameOver())
                this.removeStone();

            this.over();
            playAgain = this.playAgain();
        }
    }



}
