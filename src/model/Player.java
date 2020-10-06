package model;

public abstract class Player {

    protected Board board;
    protected XO move;

    public Player(Board board, XO move){
        this.board = board;
        this.move = move;
    }

    protected abstract void announceMove();
    protected abstract void makeMove();
    public void play() {
        announceMove();
        makeMove();
    }
}
