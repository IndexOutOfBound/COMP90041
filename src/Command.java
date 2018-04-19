public interface Command {

    void excute(String param, PlayerList playerList);

    void excute(PlayerList playerList);
}
