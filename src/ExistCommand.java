/**
 * @Author: weikai.zeng
 * this class used for exist the program
 */


public class ExistCommand implements NimCommand {
    @Override
    public void execute(PlayerList playerList, String param) {
        execute(playerList);
    }

    @Override
    public void execute(PlayerList playerList) {
        System.out.println();
        System.exit(0);
    }
}
