
// A class for NimPlayer
// @Author weikai Zeng

public class NimPlayer {

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
}
