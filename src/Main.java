import java.util.Scanner;

import controller.GameController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gc = new GameController(scanner);
        gc.startGame();
    }
}
