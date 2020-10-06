package controller;

import java.util.Scanner;

public class GamePrompter extends Prompter {
    private int x = -1;
    private int y = -1;

    public GamePrompter(String msg, Scanner scanner){
        super(msg, scanner);
    }

    @Override
	public void promptUserAndParseInput() {
        while(true) {
            promptUser();
            String[] coordinates = getInput().split(" ");
            if (coordinates.length == 2) {
                try {
                    int x_tmp = Integer.parseInt(coordinates[0]);
                    int y_tmp = Integer.parseInt(coordinates[1]);
                    if (x_tmp < 4 && x_tmp > 0 && y_tmp < 4 && y_tmp > 0) {
                        x = x_tmp;
                        y = y_tmp;
                        break;
                    }
                    System.out.println("Coordinates should be from 1 to 3!");
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers");
                }
            } else System.out.println("Please enter exactly two numbers for the coordinates separated by space(s)");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
