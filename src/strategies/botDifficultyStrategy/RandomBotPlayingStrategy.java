package strategies.botDifficultyStrategy;

import models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{


    @Override
    public Move makeMove(Board board, Player player) {
        for(int i=0;i< board.getSize();i++){
            for(int j=0;j< board.getSize();j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    board.getBoard().get(i).get(j).setCellState(CellState.FILLED);
                    board.getBoard().get(i).get(j).setPlayer(player);
                    return new Move(new Cell(i,j),player);
                }
            }
        }
        return null;
    }
}
