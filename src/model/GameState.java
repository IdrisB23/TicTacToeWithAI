package model;

public enum GameState {
    Game_not_finished, Draw, X_wins, O_wins;

    @Override
    public String toString() {
        return super.name().replaceAll("_", " ");
    }

}
