/**
 * @Author: weikai.zeng
 * this class used for exist the program
 */


public class ExistCommand implements NimCommand {
    @Override
    public void execute(PlayerList playerList, String param) {
        System.exit(0);
    }

    @Override
    public void execute(PlayerList playerList) {
        System.exit(0);
    }
}
