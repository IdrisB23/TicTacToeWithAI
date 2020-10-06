package model;

import exceptions.OccupiedCellException;

public class MediumAI extends Player {
    EasyAI primitiveMe;

    public MediumAI(Board board, XO move) {
        super(board, move);
        primitiveMe = new EasyAI(board, move);
    }

    @Override
    public void announceMove() {
        System.out.println("Making move level \"medium\"");
    }

    @Override
    public void makeMove() {
        int winSlot = board.IsAlmostWinning(this.move);
        int dangerSlot = board.IsAlmostWinning(this.move.getOther());
        if (winSlot != -1) {
            try {
                board.setEmptyCell(winSlot);
            } catch (OccupiedCellException ignored) {
            }
        } else if (dangerSlot != -1) {
            try {
                board.setEmptyCell(dangerSlot);
            } catch (OccupiedCellException ignored) {
            }
        } else primitiveMe.makeMove();
    }
}
