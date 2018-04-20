public interface NimCommand {

    void excute( PlayerList playerList, String param);

    void excute(PlayerList playerList);

    void printHelp();
}
