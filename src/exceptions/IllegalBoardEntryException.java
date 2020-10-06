package tictactoe.Exceptions;

import tictactoe.Exceptions.BoardException;

public class IllegalBoardEntryException extends BoardException {
    public IllegalBoardEntryException() {
        super("Illegal board entry. Please either pass X's or O's");
    }
}
