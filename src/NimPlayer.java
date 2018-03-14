
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
    public void removeStone(Nimsys nimsys){
        Integer upbound = nimsys.getUpBound();
        Integer numOfStone = nimsys.getNumberOfStone();
        System.out.println(name+"'s turn - remove how many?");
        Integer removedStone = Nimsys.inputIntegerRange(1, upbound < numOfStone ? upbound:numOfStone);

        nimsys.setNumberOfStone(numOfStone - removedStone);
    }
}
