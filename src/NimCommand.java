
/**
 * @Author: weikai.zeng
 * this class represent the abstract form of all command
 */

public interface NimCommand {

    /**
     * execute command does not need parameters
     * @param playerList
     * @param param
     */
    void execute(PlayerList playerList, String param);


    /**
     * execute command need parameters
     * @param playerList
     */
    void execute(PlayerList playerList);

}
