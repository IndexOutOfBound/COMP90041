
/**
 * @Author: weikai.zeng
 * this class used for edit player
 */



public class EditPlayerCommand implements NimCommand{

    /**
     * edit the player
     * @param playerList
     * @param params
     */
    @Override
    public void execute(PlayerList playerList, String params) {
        String[] names = params.split(",");
        if( names.length != 3){
            printHelp();
        } else {

            NimPlayer nimPlayer = playerList.getPlayerByUserName(names[0]);
            if (nimPlayer == null) {
                System.out.println("The player does not exist.");
            } else{
                nimPlayer.setLastName(names[1]);
                nimPlayer.setFirstName(names[2]);
            }
        }
    }

    /**
     * when user input without parameters, print the help information
     * @param playerList
     */
    @Override
    public void execute(PlayerList playerList) {
        printHelp();
    }

    /**
     * print the help information
     */
    private void printHelp(){
        System.out.println("Invaild input, please input as the format: username,family_name,given_name");
    }
}
