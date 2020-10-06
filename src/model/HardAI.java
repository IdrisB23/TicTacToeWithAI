package model;

import exceptions.OccupiedCellException;

import java.util.*;

public class HardAI extends Player {
    public HardAI(Board board, XO move) {
        super(board, move);
    }

    @Override
    public void announceMove() {
        System.out.println("Making move level \"hard\"");
    }

    @Override
    public void makeMove() {
        HashMap<Integer, Integer> indicesAndScores = new HashMap<>();
        for (int i = 0; i < board.getCells().length; i++) {
            if (board.getCells()[i] == null) {
                //System.out.println("Probing cell " + i);
                try {
                    Board board_tmp = (Board)board.clone();
                    board_tmp.setEmptyCell(i);
                    //System.out.println("About to enter minmax");
                    int score = minMax(board_tmp, move.getOther(), false, 0);
                    //System.out.println("Finished minmax with result " + score);
                    indicesAndScores.put(i, score);
                } catch (CloneNotSupportedException e) {
                    System.out.println("Clone Unsupported");
                } catch (OccupiedCellException ignored) {
                }
            }
        }
        Map.Entry<Integer, Integer> maxEntry = null;
        for(Map.Entry<Integer, Integer> mapEntry : indicesAndScores.entrySet()) {
            if ( maxEntry == null || mapEntry.getValue().compareTo(maxEntry.getValue()) > 0 ) {
                maxEntry = mapEntry;
            }
        }
        try {
            if(maxEntry != null) {
                //System.out.println("Now making official move");
                board.setEmptyCell(maxEntry.getKey());
                //board.printField();
            }
            else System.out.println("Oops! Something went wrong...");
        } catch (OccupiedCellException ignored) {
        }
    }

    public static int minMax(Board board, XO move, boolean isMax, int recursionDepth) {
        //System.out.println("minMax " + move + " " + isMax);
        //System.out.println("recursion depth: " + recursionDepth);
        //board.printField();
        if( !isMax && board.hasWinningSequence(move.getOther())) {
            //System.out.println("returning 10");
            return 10;
        }
        if( isMax && (board.hasWinningSequence(move.getOther())) ) {
            //System.out.println("returning -10");
            return -10;
        }
        if(board.getState() == GameState.Draw) {
            //System.out.println("returning 0");
            return 0;
        }
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < board.getCells().length; i++) {
            if (board.getCells()[i] == null) {
                //System.out.println("placing in cell " + i);
                try {
                    Board board_tmp = (Board)board.clone();
                    board_tmp.setEmptyCell(i);
                    scores.add(minMax(board_tmp, move.getOther(), !isMax, recursionDepth+1));
                } catch (CloneNotSupportedException | OccupiedCellException ignored) {
                }
            }
        }
        /*for(Integer i : scores){
           System.out.print(i + " | ");
        }*/
        //System.out.println();
        if (isMax) {
            //System.out.println("returning maximum:" + scores.stream().max(Integer::compareTo).get());
            return scores.stream().max(Integer::compareTo).get();
        }
        //System.out.println("returning minimum:" + scores.stream().min(Integer::compareTo).get());
        return scores.stream().min(Integer::compareTo).get();
    }
}
