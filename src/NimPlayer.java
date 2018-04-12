
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

    /**
     * ask player to remove stone
     * @param nimsys the running nim game
     */
    public void removeStone(Nimsys nimsys){
        Integer upbound = nimsys.getUpBound();
        Integer numOfStone = nimsys.getNumberOfStone();
        System.out.println(name+"'s turn - remove how many?");
        Integer removedStone = Nimsys.inputIntegerRange(1, upbound < numOfStone ? upbound:numOfStone);
        //if the upbound is less than the numOfStone
        //      set the upbound as upper bound of input
        //else
        //      set the numOfStone as upper bound
        nimsys.setNumberOfStone(numOfStone - removedStone);
    }
}
