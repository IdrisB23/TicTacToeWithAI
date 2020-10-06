package model;

import controller.GamePrompter;
import exceptions.OccupiedCellException;

public class HumanPlayer extends Player {
    private GamePrompter gamePrompter;
    public HumanPlayer(Board board, GamePrompter gamePrompter, XO move){
        super(board, move);
        this.gamePrompter = gamePrompter;
    }

    @Override
    public void announceMove() {
    }

    @Override
    public void makeMove() {
        while(true) {
            gamePrompter.promptUserAndParseInput();
            try{
                board.setEmptyCell(gamePrompter.getX(), gamePrompter.getY());
                break;
            } catch(OccupiedCellException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
