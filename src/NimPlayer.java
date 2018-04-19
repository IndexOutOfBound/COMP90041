
// A class for NimPlayer
// @Author weikai Zeng

import java.math.BigDecimal;
import java.util.Comparator;

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public NimPlayer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
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

    public NimPlayer setWinGames(BigDecimal winGames) {
        this.winGames = winGames;
        return this;
    }

    public NimPlayer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String toString(){
        return this.userName+","+this.firstName+","+this.lastName+
                ","+this.numberOfGames+" games "+this.winGames+" wins";
    }

    public String toRankString(){
        BigDecimal winRate = this.winGames.divide(this.numberOfGames).setScale(2);
        return String.format("%-5s| %02d games | %s %s", winRate,
                this.numberOfGames, this.firstName, this.lastName);
    }

    public void removeStone(NimGame nimGame){
        Integer upbound = nimGame.getUpBound();
        Integer numOfStone = nimGame.getNumberOfStone();
        System.out.println(lastName+"'s turn - remove how many?");
        Integer removedStone = Nimsys.inputIntegerRange(1, upbound < numOfStone ? upbound:numOfStone);
        //if the upbound is less than the numOfStone
        //      set the upbound as upper bound of input
        //else
        //      set the numOfStone as upper bound
        nimGame.setNumberOfStone(numOfStone - removedStone);
    }

    public void addOneWin(){
        this.winGames.add(ONE);
    }

    public void addOneGame(){
        this.numberOfGames.add(ONE);
    }


}
