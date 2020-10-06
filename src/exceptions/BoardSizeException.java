package tictactoe.Exceptions;

import tictactoe.Exceptions.BoardInitialisationException;

public class BoardSizeException extends BoardInitialisationException {
    public BoardSizeException() {
        super("Invalid board Size! Please pass a board with exactly 9 cells either empty or containing X's or O's");
    }
}
