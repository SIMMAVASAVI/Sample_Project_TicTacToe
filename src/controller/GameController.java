package controller;

import models.Game;
import models.GameStatus;
import models.Move;
import models.Player;
import strategies.winningStrategy.WinningStrategy;
import strategies.winningStrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> playersList){
        try{
            return Game.builder()
                    .setBoard(dimension)
                    .setPlayers(playersList)
                    .setWinningStrategies(List.of(WinningStrategyFactory.getWinningStrategy(dimension)))
                    .build();
        }
        catch (Exception e){
            System.out.println("Could not start game something went wrong");
        }
        return null;
    }

    public void printBoard(Game game){
        game.getBoard().printBoard();
    }

    public GameStatus printGameState(Game game){
        return game.getGameState();
    }

    public Move makeMove(Game game,Player player){
        Move move =player.makeMove(game.getBoard());
        updateGameMoves(game,move);
        return move;
    }

    public void updateGameMoves(Game game, Move move ){
        game.getMoves().add(move);

    }

    public Player checkWinner(Game game, Move recentMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player=winningStrategy.checkWinner(game.getBoard(),recentMove);
            if(player!=null){
                return player;
            }
        }
        return null;
    }

    public String getWinner(Game game){
        return game.getWinner().getName();
    }
}
