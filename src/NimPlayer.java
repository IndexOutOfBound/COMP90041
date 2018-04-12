
// A class for NimPlayer
// @Author weikai Zeng

import java.util.Comparator;

public class NimPlayer implements Comparator{

    private String UserName;

    private String firstName;

    private String lastName;

    private int numberOfGames;

    private int winGames;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getWinGames() {
        return winGames;
    }

    public void setWinGames(int winGames) {
        this.winGames = winGames;
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
        this.winGames++;
    }

    public void addOneGame(){
        this.numberOfGames++;
    }

    @Override
    public int compare(Object ob1, Object ob2) {
        if(ob1 instanceof NimPlayer && ob2 instanceof NimPlayer) {
            NimPlayer player1 = (NimPlayer)ob1;
            NimPlayer player2 = (NimPlayer)ob2;
            int player1WinRate =(int)(((double)player1.winGames/(double)player1.numberOfGames)*100);
            int player2WinRate =(int)(((double)player2.winGames/(double)player2.numberOfGames)*100);
            if(player1WinRate > player2WinRate)
                return 1;

            if(player1WinRate == player2WinRate) {
                if( player1.getFirstName())
            }

            return -1;
        }

        return 0;
    }
}
