import model.Card;
import model.Player;
import service.FileService;
import service.PokerService;
import service.implementation.FileServiceImpl;
import service.implementation.PokerServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();
        List<List<List<Card>>> hands = fileService.readFile();

        int playerOneWins = 0;
        int playerTwoWins = 0;
        for (int i = 0; i < hands.get(0).size(); i++) {

            Player playerOne = new Player.PlayerBuilder()
                    .setName("PlayerOne")
                    .setHand(hands.get(0).get(i))
                    .build();

            Player playerTwo = new Player.PlayerBuilder()
                    .setName("PlayerTwo")
                    .setHand(hands.get(1).get(i))
                    .build();

            PokerService pokerService = new PokerServiceImpl();
            Player winner = pokerService.determineWinner(playerOne, playerTwo);
            if (winner.getName().equals(playerOne.getName())) {
                playerOneWins++;
            } else {
                playerTwoWins++;
            }

        }

        System.out.println(playerOneWins);
        System.out.println(playerTwoWins);
    }
}
