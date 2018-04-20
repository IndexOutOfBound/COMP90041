public class AddUserCommand implements NimCommand{
    @Override
    public void excute(PlayerList playerList, String params) {
        String[] names = params.split(",");
        //divide the params into a array with size 3. 0: username 1:lastName 2:firstName
        if( names.length != 3){
            printHelp();
        } else if(playerList.getPlayerByUsername(names[0]) != null){
            System.out.println("The player already exist.");
        } else {
            NimPlayer newplayer = new NimPlayer(names[0],names[1], names[2]);
            playerList.addPlayer(newplayer);
        }
    }

    @Override
    public void excute(PlayerList playerList) {
        printHelp();
    }

    private void printHelp(){
        System.out.println("Invaild input, please input as the format: username,family_name,given_name");
    }
}
