package exceptions;

import exceptions.BoardException;

public class OccupiedCellException extends BoardException {
    public OccupiedCellException(){
        super("This cell is occupied! Choose another one!");
    }
}
