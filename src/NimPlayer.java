
// A class for NimPlayer
// @Author weikai Zeng

public class NimPlayer {

    private String name;

    public String getName() {
        return name;
    }

    public NimPlayer setName(String name) {
        this.name = name;
        return this;
    }

    //remove the stone
    public void removeStone(NimGame nimGame){
        Integer upbound = nimGame.getUpBound();
        Integer numOfStone = nimGame.getNumberOfStone();
        System.out.println(name+"'s turn - remove how many?");
        Integer removedStone = DataInput.inputIntegerRange(1, upbound < numOfStone ? upbound:numOfStone);

        nimGame.setNumberOfStone(numOfStone - removedStone);
    }
}
