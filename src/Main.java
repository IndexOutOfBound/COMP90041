
// A class for running the program
// @Author weikai Zeng

public class Main {

    public static void main(String[] args) {
        NimGame nimGame = new NimGame();
        nimGame.initialize();
        boolean playAgain = true;
        while(playAgain){
            nimGame.start();
            while(!nimGame.isGameOver())
                nimGame.removeStone();
            nimGame.over();
            playAgain = nimGame.playAgain();
        }
    }
}
