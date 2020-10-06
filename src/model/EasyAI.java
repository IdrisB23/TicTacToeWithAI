package model;

import exceptions.OccupiedCellException;

import java.util.Random;

public class EasyAI extends Player {

    public EasyAI(Board board, XO move) {
        super(board, move);
    }

    @Override
    public void announceMove() {
        System.out.println("Making move level \"easy\"");
    }

    @Override
    public void makeMove() {
        Random r = new Random();
        while (true) {
            try {
                int x = Math.abs(r.nextInt() % 3) + 1;
                int y = Math.abs(r.nextInt() % 3) + 1;
                board.setEmptyCell(x, y);
                break;
            } catch (OccupiedCellException ignored) {
            }
        }
    }
}
