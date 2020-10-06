package controller;

import java.util.Scanner;

import model.Board;
import model.Commands;
import model.EasyAI;
import model.GameState;
import model.HardAI;
import model.HumanPlayer;
import model.MediumAI;
import model.Player;
import model.XO;

public class GameController {
    Scanner scanner;
    CommandPrompter cp;
    Player player1;
    Player player2;
    GameState gameState;
    Board board;
    GamePrompter prompter;

    public GameController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startGame() {
        cp = new CommandPrompter(scanner);
        while (true) {
            cp.promptUserAndParseInput();
            if (cp.getCommand() == Commands.EXIT) {
                System.out.println("See you!");
                break;
            }
            board = new Board();
            prompter = new GamePrompter("Enter the coordinates: ", scanner);
            gameState = GameState.Game_not_finished;
            player1 = setUpPlayer(cp.getPlayer1(), XO.X);
            player2 = setUpPlayer(cp.getPlayer2(), XO.O);
            board.printField();
            playGame();
        }
    }

    private Player setUpPlayer(String str, XO play) {
        switch (str) {
            case "user":
                return new HumanPlayer(board, prompter, play);
            case "medium":
                return new MediumAI(board, play);
            case "hard":
                return new HardAI(board, play);
            default:
                return new EasyAI(board, play);
        }
    }
    private void playGame() {
        while (true) {
            player1.play();
            board.printField();
            gameState = board.getState();
            if (gameState != GameState.Game_not_finished) {
                System.out.println(gameState.toString());
                break;
            }
            player2.play();
            board.printField();
            gameState = board.getState();
            if (gameState != GameState.Game_not_finished){
                System.out.println(gameState.toString());
                break;
            }
        }
    }
}
