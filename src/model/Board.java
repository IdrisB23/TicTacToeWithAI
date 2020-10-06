package model;

import exceptions.BoardInitialisationException;
import exceptions.BoardSizeException;
import exceptions.IllegalBoardEntryException;
import exceptions.OccupiedCellException;

import static model.XO.*;

public class Board {
     private XO[] cells;
     int numberOfX;
     int numberOfO;
     boolean isNextEntryX;
     private GameState state;

    public GameState getState() {
        return state;
    }

    public Board() {
        this.cells = new XO[9];
        isNextEntryX = true;
        state = GameState.Game_not_finished;
    }

    public Board(XO[] cells) throws BoardInitialisationException, IllegalBoardEntryException {
        if (cells.length != 9)
            throw new BoardSizeException();
        if (!boardOnlyContainsXAndO(cells))
            throw new IllegalBoardEntryException();
        this.cells = cells;
        numberOfX = getNumberOf(X, cells);
        numberOfO = getNumberOf(O, cells);
        isNextEntryX = numberOfX <= numberOfO;
        state = hasWinningSequence(X) ? GameState.X_wins : hasWinningSequence(O) ? GameState.O_wins
                : isBoardComplete(cells) ? GameState.Draw : GameState.Game_not_finished;
    }

    public boolean hasWinningSequence(XO c) {
        return cells[0] == c && cells[1] == c && cells[2] == c
                || cells[3] == c && cells[4] == c && cells[5] == c
                || cells[6] == c && cells[7] == c && cells[8] == c
                || cells[0] == c && cells[3] == c && cells[6] == c
                || cells[1] == c && cells[4] == c && cells[7] == c
                || cells[2] == c && cells[5] == c && cells[8] == c
                || cells[0] == c && cells[4] == c && cells[8] == c
                || cells[2] == c && cells[4] == c && cells[6] == c;
    }

    private static boolean isBoardComplete(XO[] cells) {
        for (XO c : cells)
            if (c == null)
                return false;
        return true;
    }

    private static boolean boardOnlyContainsXAndO(XO[] cells) {
        for (XO c : cells)
            if (c != X && c != O && c != null)
                return false;
        return true;
    }

    public static int getNumberOf(XO c, XO[] cells) {
        int number = 0;
        for (XO ct : cells)
            if (ct == c)
                number++;
        return number;
    }

    public XO[] getCells() {
        return cells;
    }

    public int getNumberOfX() {
        return numberOfX;
    }

    public int getNumberOfO() {
        return numberOfO;
    }

    public void setEmptyCell(int x, int y) throws OccupiedCellException {
        XO nextEntry = isNextEntryX ? X : O;
        switch (y) {
            case 1:
                if (cells[5 + x] == null) cells[5 + x] = nextEntry;
                else throw new OccupiedCellException();
                break;
            case 2:
                if (cells[2 + x] == null) cells[2 + x] = nextEntry;
                else throw new OccupiedCellException();
                break;
            case 3:
                if (cells[x - 1] == null) cells[x - 1] = nextEntry;
                else throw new OccupiedCellException();
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
        if (isNextEntryX)
            numberOfX++;
        else numberOfO++;
        isNextEntryX = !isNextEntryX;
        state = hasWinningSequence(X) ? GameState.X_wins : hasWinningSequence(O) ? GameState.O_wins
                : isBoardComplete(cells) ? GameState.Draw : GameState.Game_not_finished;
    }

    public void setEmptyCell (int index) throws OccupiedCellException {
        XO nextEntry = isNextEntryX ? X : O;
        if (cells[index] != null)
            throw new OccupiedCellException();
        cells[index] = nextEntry;
        numberOfX += isNextEntryX? 1 : 0;
        numberOfO += isNextEntryX? 0 : 1;
        isNextEntryX = !isNextEntryX;
        state = hasWinningSequence(X) ? GameState.X_wins : hasWinningSequence(O) ? GameState.O_wins
                : isBoardComplete(cells) ? GameState.Draw : GameState.Game_not_finished;
    }

    public void printField() {
        System.out.println("---------");
        for (int i = 0; i < cells.length; i++) {
            if (i % 3 == 0)
                System.out.print("| ");
            if(cells[i] == null)
                System.out.print(" " + " ");
            if(cells[i] == XO.X)
                System.out.print("X" + " ");
            if(cells[i] == XO.O)
                System.out.print("O" + " ");
            if (i % 3 == 2)
                System.out.println("|");

        }
        System.out.println("---------");
    }


    /**
     * Checks whether a player with a given move(parameter) almost has a winning sequence and returns the index in
     * implementation array. Otherwise returns -1
     */
    public int IsAlmostWinning(XO move) {
        if(cells[0] == null && (cells[2] == move && cells[1] == move ||
                cells[3] == move && cells[6] == move ||
                cells[4] == move && cells[8] == move)
        ) return 0;
        if(cells[1] == null && (cells[0] == move && cells[2] == move ||
                cells[4] == move && cells[7] == move)
        ) return 1;
        if(cells[2] == null && (cells[0] == move && cells[1] == move ||
                cells[5] == move && cells[8] == move ||
                cells[4] == move && cells[6] == move)
        ) return 2;
        if(cells[3] == null && (cells[5] == move && cells[4] == move ||
                cells[0] == move && cells[6] == move)
        ) return 3;
        if(cells[4] == null && (cells[0] == move && cells[8] == move ||
                cells[2] == move && cells[6] == move ||
                cells[1] == move && cells[7] == move ||
                cells[3] == move && cells[5] == move)
        ) return 4;
        if(cells[5] == null && (cells[3] == move && cells[4] == move ||
                cells[2] == move && cells[8] == move)
        ) return 5;
        if(cells[6] == null && (cells[7] == move && cells[8] == move ||
                cells[3] == move && cells[0] == move ||
                cells[4] == move && cells[2] == move)
        ) return 6;
        if(cells[7] == null && (cells[8] == move && cells[6] == move ||
                cells[4] == move && cells[1] == move)
        ) return 7;
        if(cells[8] == null && (cells[7] == move && cells[6] == move ||
                cells[5] == move && cells[2] == move ||
                cells[4] == move && cells[0] == move)
        ) return 8;
        return -1;
    }

    public void setCells(XO[] cells) {
        this.cells = cells;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    @Override
	public Object clone() throws CloneNotSupportedException {
        Board cloned = new Board();
        cloned.setState(this.getState());
        cloned.isNextEntryX = this.isNextEntryX;
        cloned.numberOfO = this.numberOfO;
        cloned.numberOfX = this.numberOfX;
        cloned.setCells(cells.clone());
        return cloned;
    }
}
