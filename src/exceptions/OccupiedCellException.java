package tictactoe.Exceptions;

import tictactoe.Exceptions.BoardException;

public class OccupiedCellException extends BoardException {
    public OccupiedCellException(){
        super("This cell is occupied! Choose another one!");
    }
}
