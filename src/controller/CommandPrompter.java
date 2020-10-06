package controller;

import java.util.Scanner;

import model.Commands;

public class CommandPrompter extends Prompter {
    private Commands command;
    private String player1;
    private String player2;

    public CommandPrompter(Scanner scanner) {
        super("Input command(Start {your username/easy/medium/hard} {your username/easy/medium/hard} to start or Exit to exit the game): ", scanner);
    }

    public void promptUserAndParseInput() {
        while (true) {
            promptUser();
            String[] inputParts = getInput().split(" ");
            if (inputParts.length == 3
                    && inputParts[0].equals("Start")
                    && (inputParts[1].equals("easy") || inputParts[1].equals("medium") || inputParts[1].equals("hard") || inputParts[1].equals("user"))
                    && (inputParts[2].equals("easy") || inputParts[2].equals("medium") || inputParts[2].equals("hard") || inputParts[2].equals("user"))
            ) {
                command = Commands.START;
                player1 = inputParts[1];
                player2 = inputParts[2];
                break;
            } else if (inputParts.length == 1 && inputParts[0].equals("Exit")) {
                command = Commands.EXIT;
                break;
            } else System.out.println(Commands.BAD_PARAMETERS);
        }

    }

    public Commands getCommand() {
        return command;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
