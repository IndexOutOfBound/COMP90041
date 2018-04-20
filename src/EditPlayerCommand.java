public class EditPlayerCommand implements NimCommand{

    @Override
    public void excute(PlayerList playerList, String params) {
        String[] names = params.split(",");
        if( names.length != 3){
            printHelp();
        } else {

            NimPlayer nimPlayer = playerList.getPlayerByUsername(names[0]);
            if (nimPlayer != null) {
                System.out.println("The player does not exist.");
            } else{
                nimPlayer.setFirstName(names[1]);
                nimPlayer.setLastName(names[2]);
            }
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
