package service;

import model.Player;

public interface PokerService {
    Player determineWinner(Player playerOne, Player playerTwo);
}
