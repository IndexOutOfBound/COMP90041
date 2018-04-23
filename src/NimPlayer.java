
/**
 * Author: weikai.zeng
 * this class represent the NimPlayer
 */

import java.math.BigDecimal;

public class NimPlayer{

    private String userName;

    private String firstName;

    private String lastName;

    private BigDecimal numberOfGames;

    private BigDecimal winGames;

    private static final BigDecimal ONE = new BigDecimal("1");



    public NimPlayer(String userName, String lastName, String firstName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.winGames = new BigDecimal("0");
        this.numberOfGames = new BigDecimal("0");
    }


    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(BigDecimal numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public BigDecimal getWinGames() {
        return winGames;
    }

    public void setWinGames(BigDecimal winGames) {
        this.winGames = winGames;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the string form of NimPlayer
     */
    public String toString(){
        return this.userName+","+this.firstName+","+this.lastName+
                ","+this.numberOfGames+" games,"+this.winGames+" wins";
    }

    /**
     * @return the string form of NimPlayer with the ranking information
     */
    public String toRankString(){
        BigDecimal winRate = numberOfGames.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO: winGames.divide(this.numberOfGames,2,BigDecimal.ROUND_HALF_UP);
        return String.format("%-5s| %02d games | %s %s", winRate.multiply(new BigDecimal("100")).setScale(0)+"%",
                this.numberOfGames.toBigInteger(), this.firstName, this.lastName);
    }

    /**
     * remove stone from table
     * @param nimGame the game which the player is attending
     */
    public void removeStone(NimGame nimGame){
        Integer upbound = nimGame.getUpBound();
        Integer numOfStone = nimGame.getNumberOfStone();
        System.out.println(firstName+"'s turn - remove how many?");
        Integer removedStone = Nimsys.inputIntegerRange(1, upbound < numOfStone ? upbound:numOfStone);
        //if the upbound is less than the numOfStone
        //      set the upbound as upper bound of input
        //else
        //      set the numOfStone as upper bound
        nimGame.setNumberOfStone(numOfStone - removedStone);
    }

    public void addOneWin(){
        winGames = winGames.add(ONE);
    }

    public void addOneGame(){
        numberOfGames = numberOfGames.add(ONE);
    }


    /**
     * Compare with the other player by win rate
     * @param player
     * @return
     *    1 if this player has higher mark,
     *    0 if two player has same mark,
     *    -1 if the other player has higher mark
     */
    public int compareByWinrate(NimPlayer player) {
        BigDecimal theOtherWinRate = player.getNumberOfGames().compareTo(Nimsys.ZERO) == 0 ?
                Nimsys.ZERO : player.getWinGames().divide(player.getNumberOfGames(),4,BigDecimal.ROUND_HALF_UP);
        BigDecimal mineWinRate = this.getNumberOfGames().compareTo(Nimsys.ZERO) == 0 ?
                Nimsys.ZERO : this.getWinGames().divide(this.getNumberOfGames(),4,BigDecimal.ROUND_HALF_UP);

        if (mineWinRate.compareTo(theOtherWinRate) > 0) {
            return 1;
        } else if (mineWinRate.compareTo(theOtherWinRate)==0) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Compare with the other player by their username
     * @param player
     * @return
     *    1 if this player has higher mark,
     *    0 if two player has same mark,
     *    -1 if the other player has higher mark
     */
    public int compareByUserName(NimPlayer player) {
        int compareResult = userName.compareTo(player.userName);
        if(compareResult > 0)
            return 1;
        else if(compareResult == 0)
            return 0;
        else
            return -1;
    }
}
